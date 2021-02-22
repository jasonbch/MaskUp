package MaskGame;

import Ammo.Ammo;
import Enemy.Enemy;
import Enemy.EnemyFactory;
import Enemy.MurderHornet;
import Entity.Entity;
import Entity.Player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * GameScreen class that implements from Screen that let the user play
 * the game.
 */
public class GameScreen extends ApplicationAdapter implements Screen  {
    // Screen
    private Camera camera;
    private Viewport viewport;

    // Graphic
    private SpriteBatch batch;
    private Texture[] backgrounds;

    private long recentSpawnTime = 0;

    // Timing Variables
    private float[] backgroundOffsets = {0,0,0,0};
    private float maxScrollingSpeed;

    private int backgroundOffset;
    private long elapsedTime;
    private long startTime;
    private long lastSpawnTime = 0;
    private long lastMidBossTime = 0;
    private long lastFinalBossTime = 0;


    // World dimension
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private final int QUARTER_WORLD_HEIGHT = WORLD_HEIGHT/4;
    private final int HALF_WORLD_HEIGHT = WORLD_HEIGHT/2;
    private final int THREE_QUARTER__WORLD_HEIGHT = WORLD_HEIGHT*3/4;

    // Game Objects
    private Entity player;

    private EnemyFactory enemyFactory = new EnemyFactory();
    private LinkedList<Ammo> enemyAmmoList;
    private LinkedList<Ammo> playerAmmoList;
    private LinkedList<Enemy> enemyList;

    // Slow mode variables
    private boolean isSlowMode;
    private float gameSpeed;    // Current game speed

    // Music
    private Music backgroundMusic;

    /**
     * Create a GameScreen that let the user play a game of bullet hell.
     */
    public GameScreen() {
        // Initialize camera and view
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        // Initialize background objects
        // backgroundOffset = 0;
        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("BlueBackground.png");
        backgrounds[1] = new Texture("Clouds1.png");
        backgrounds[2] = new Texture("Clouds2.png");
        backgrounds[3] = new Texture("Cloud4.png");
        maxScrollingSpeed = (float) (WORLD_HEIGHT) / 4;

        // Initialize player object
        player = new Player(WORLD_WIDTH/2, WORLD_HEIGHT/4);

        // Initialize slow mode
        this.isSlowMode = false;
        this.gameSpeed = 1; // Set current game speed to normal speed

        // Initialize ammo and enemy lists
        playerAmmoList = new LinkedList<>();
        enemyAmmoList = new LinkedList<>();
        enemyList = new LinkedList<>();

        // Initialize start time
        startTime = TimeUtils.millis();

        batch = new SpriteBatch();

        // Music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BackgroundMusic.mp3"));
        // start the playback of the background music immediately
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    /**
     * Render the screen.
     *
     * @param deltaTime the delta time
     */
    @Override
    public void render(float deltaTime) {
        // Get the game speed
        gameSpeed = getGameSpeed();
        deltaTime *= gameSpeed;

        // Begin the Batch
        batch.begin();

        // Update scrolling background
        renderBackground(deltaTime);

        // Process player
        player.draw(batch);         // Draw player
        player.update(deltaTime);   // Update player
        movePlayer(deltaTime);      // Move player
        playerFire();               // Check player shooting input

        // Process enemies
        spawnEnemies();         // Spawn game enemies
        enemyFire(deltaTime);   // Fire enemy bullets if they can fire
        drawAndUpdateBulletsAndEnemies(deltaTime);  // Draw and update all bullets(enemy and player) and enemies
        deleteEnemies();        // Delete enemies if they need deleted

        // TODO: extract bullet processing

        // Draw white dor in slow mode
        drawWhiteDotInSlowMode();

        // End the batch
        batch.end();
    }

    /**
     * Draw the white dot at the center of the player is the game
     * is in slow mode.
     */
    public void drawWhiteDotInSlowMode() {
        if (isSlowMode) {
            batch.draw(new Texture("CircleHitBox.png"),
                    player.xPos + (player.getImageWidth() / 2) - (player.getImageWidth() / 4),
                    player.yPos + (player.getImageHeight() / 2) - (player.getImageWidth() / 4),
                    player.getImageWidth() / 2,
                    player.getImageWidth() / 2);
        }
    }

    /**
     * Return the speed of the game. If the game is in slow speed, the
     * speed of the game is reduced by 60%.
     *
     * @return  the speed of the game
     */
    public float getGameSpeed() {
        // If the L key is just press and it is not slow down mode
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && !isSlowMode) {
            isSlowMode = true;  // Change the slow mode to true
            gameSpeed = 0.4f;   // Change the game speed to slow speed
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && isSlowMode) {
            // If the L key is just press and it is slow down mode
            isSlowMode = false; // Change slow mode to false
            gameSpeed = 1;      // Change the game speed to normal speed
        }

        return gameSpeed;
    }

    /**
     * Fire the bullet from player if the space bar is pressed.
     */
    public void playerFire() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (player.canFire()) {
                Ammo ammo = player.fire("Syringe");
                playerAmmoList.add(ammo);
            }
        }
    }

    /**
     * TODO: Introduce interval variables.
     * Spawn enemies, mid boss, and final boss on the screen in different intervals.
     */
    public void spawnEnemies() {
        // Spawn enemies
        elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;

        if (elapsedTime % 5 == 0
                && elapsedTime != 1
                && elapsedTime != 0
                && elapsedTime - lastSpawnTime > 3) {
            System.out.println("spawning enemies");
            spawnEnemies("MurderHornet", WORLD_WIDTH/2, WORLD_HEIGHT*3/4);
            spawnEnemies("Bat", WORLD_WIDTH/2 - 5, WORLD_HEIGHT*3/5);
            lastSpawnTime = elapsedTime;
        }

        // Spawn mid boss
        if (elapsedTime % 20 == 0
                && elapsedTime != 1
                && elapsedTime != 0
                && elapsedTime - lastMidBossTime > 3) {
            System.out.println("spawning mid boss");
            spawnMidBoss(WORLD_WIDTH/2, WORLD_HEIGHT*3/4);
            lastMidBossTime = elapsedTime;
        }

        // Spawn final boss
        if (elapsedTime % 40 == 0
                && elapsedTime != 1
                && elapsedTime != 0
                && elapsedTime - lastFinalBossTime > 3) {
            System.out.println("spawning final boss");
            spawnFinalBoss(WORLD_WIDTH/2, WORLD_HEIGHT*3/4);
            lastFinalBossTime = elapsedTime;
        }
    }

    /**
     * Render the background.
     *
     * @param deltaTime the delta time
     */
    private void renderBackground(float deltaTime) {
        backgroundOffsets[0] += deltaTime * maxScrollingSpeed / 8;
        backgroundOffsets[1] += deltaTime * maxScrollingSpeed / 4;
        backgroundOffsets[2] += deltaTime * maxScrollingSpeed / 2;
        backgroundOffsets[3] += deltaTime * maxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; layer ++) {
            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }

            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        }
    }

    /**
     * Delete the enemies if they got out of the screen.
     */
    private void deleteEnemies() {
        ListIterator<Enemy> iter2 = enemyList.listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();

            if (currEnemy.yPos > WORLD_HEIGHT) {
                iter2.remove();
            }
        }
    }

    /**
     * Spawn a given enemy at the given position.
     *
     * @param enemy the enemy's name
     * @param xPos  the x position
     * @param yPos  the y position
     */
    private void spawnEnemies(String enemy, int xPos, int yPos) {
        enemyList.add(enemyFactory.create(enemy, xPos, yPos));
    }

    /**
     * Spawn the mid boss at the given position.
     *
     * @param xPos  the x position
     * @param yPos  the y position
     */
    private void spawnMidBoss(int xPos, int yPos) {
        spawnEnemies("Karen", xPos, yPos);
    }

    /**
     * Spawn the final boss at the given position.
     *
     * @param xPos  the x position
     * @param yPos  the y position
     */
    private void spawnFinalBoss(int xPos, int yPos) {
        spawnEnemies("Covid", xPos, yPos);
    }

    /** TODO: Move update method for enemies out of firing bullet.
     * Update the enemy position and fire their bullets if they can fire.
     *
     * @param deltaTime the delta time
     */
    private void enemyFire(float deltaTime) {
        ListIterator<Enemy> iterator = enemyList.listIterator();
        while (iterator.hasNext()) {
            Enemy currEnemy = iterator.next();
            currEnemy.update(deltaTime);

            if (currEnemy.canFire()) {
                Ammo ammo = currEnemy.fire();
                enemyAmmoList.add(ammo);
            }
        }
    }

    /**
     * TODO: Move player bullet out of the method or
     *  move the draw enemy out of the method.
     *
     * Draw and update enemies and all bullets on the screen.
     *
     * @param deltaTime the delta time
     */
    private void drawAndUpdateBulletsAndEnemies(float deltaTime) {
        // Player bullets
        ListIterator<Ammo> iterator = playerAmmoList.listIterator();
        while (iterator.hasNext()) {
            Ammo ammo = iterator.next();
            ammo.draw(batch);
            ammo.yPos += ammo.getSpeed()*deltaTime;

            if (ammo.yPos > WORLD_HEIGHT) {
                iterator.remove();
            }
        }

        // Enemy bullets
        ListIterator<Ammo> iter = enemyAmmoList.listIterator();
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            ammo.draw(batch);
            ammo.yPos -= ammo.getSpeed()*deltaTime;

            if (ammo.yPos < 0) {
                iter.remove();
            }
        }

        // Draw Enemies
        ListIterator<Enemy> iter2 = enemyList.listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();
            currEnemy.updateMovement(deltaTime);
            currEnemy.draw(batch);
        }
    }

    /**
     * Restrict the player position inside the screen.
     * Move the player accordingly to the key presses.
     *
     * @param deltaTime the delta time
     */
    public void movePlayer(float deltaTime) {
        // Check player movement
        // Restrict player movement in the screen
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.xPos > 0) {
            player.xPos -= player.getSpeed() * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.xPos < WORLD_WIDTH - player.getImageWidth()) {
            player.xPos += player.getSpeed() * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.yPos < WORLD_HEIGHT - player.getImageHeight()) {
            player.yPos += player.getSpeed() * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.yPos > 0) {
            player.yPos -= player.getSpeed() * deltaTime;
        }
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width,height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }

    @Override
    public void dispose() {
    }
}
