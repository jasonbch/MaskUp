package MaskGame;

import Ammo.Ammo;
import Enemy.Enemy;
import Enemy.EnemyFactory;
import Entity.Entity;
import Entity.Player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
public class GameScreen implements Screen {
    // Screen
    private Camera camera;
    private Viewport viewport;

    // Graphic
    private SpriteBatch batch;
    private Texture[] backgrounds;

    long recentSpawnTime = 0;

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
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;
    private final int QUARTER_WORLD_HEIGHT = WORLD_HEIGHT/4;
    private final int HALF_WORLD_HEIGHT = WORLD_HEIGHT/2;
    private final int THREE_QUARTER__WORLD_HEIGHT = WORLD_HEIGHT*3/4;

    // Game Objects
    private Entity player;
    private Enemy bee;
    private Enemy bat;
    private Enemy covid;

    private EnemyFactory enemyFactory = new EnemyFactory();
    private LinkedList<Ammo> enemyAmmoList;
    private LinkedList<Ammo> playerAmmoList;
    private LinkedList<Enemy> enemyList;

    // Slow mode
    private boolean isSlowMode;
    private float gameSpeed;    // Current game speed

    /**
     * Create a GameScreen that let the user play a game of bullet hell.
     */
    public GameScreen() {
        // Set up camera
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);


//        background = new Texture("bluebackground.png");
//        backgroundOffset = 0;
        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("BlueBackground.png");
        backgrounds[1] = new Texture("Clouds1.png");
        backgrounds[2] = new Texture("Clouds2.png");
        backgrounds[3] = new Texture("Cloud4.png");
        maxScrollingSpeed = (float) (WORLD_HEIGHT)/4;

        // Set up game objects
        player = new Player(WORLD_WIDTH/2, WORLD_HEIGHT/4);
        bee = enemyFactory.create("MurderHornet", WORLD_WIDTH/2, WORLD_HEIGHT*3/4);

        // Set up mode
        this.isSlowMode = false;
        // Set current game speed to normal speed
        this.gameSpeed = 1;

        bat = enemyFactory.create("Bat", WORLD_WIDTH/2 - 5, WORLD_HEIGHT*3/5);
        covid = enemyFactory.create("Covid", WORLD_WIDTH/2-10, WORLD_HEIGHT*3/6);

        playerAmmoList = new LinkedList<>();
        enemyAmmoList = new LinkedList<>();
        enemyList = new LinkedList<>();

        startTime = TimeUtils.millis();

        // Add initial enemies to list
        enemyList.add(bee);
        enemyList.add(bat);
        enemyList.add(covid);

        batch = new SpriteBatch();
    }

    /**
     * Render the screen.
     *
     * @param deltaTime the delta time
     */
    @Override
    public void render(float deltaTime) {
        // If the L key is just press and it is not slow down mode
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && !isSlowMode) {
            isSlowMode = true;  // Change the slow mode to true
            gameSpeed = 0.1f;   // Change the game speed to slow speed
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && isSlowMode) {
            // If the L key is just press and it is slow down mode
            isSlowMode = false; // Change slow mode to false
            gameSpeed = 1;      // Change the game speed to normal speed
        }
        deltaTime *= gameSpeed;

        batch.begin();

        // Spawn game enemies
        spawnEnemies();

        // Update objects (movements and bullet times)
        // TODO ???
        player.update(deltaTime);
        bee.update(deltaTime);
        bat.update(deltaTime);
        covid.update(deltaTime);

        // Scrolling background
        renderBackground(deltaTime);

        // Draw the player
        player.draw(batch);

        // Fire enemy bullets if they can fire.
        enemyFire(deltaTime);

        // Draw and update all bullets(enemy and player) and enemies.
        drawAndUpdateBulletsAndEnemies(deltaTime);

        // Delete enemies if they need deleted
        deleteEnemies();

        // Move player
        movePlayer(deltaTime);

        // Check player shooting input
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(player.canFire()) {
                Ammo ammo = player.fire("Bullet");
                playerAmmoList.add(ammo);
            }
        }

        batch.end();
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

    /** TODO: Move update method for enemies
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
                Ammo ammo = currEnemy.fire(currEnemy.bullet());
                enemyAmmoList.add(ammo);
            }
        }
    }

    /**
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
        // Make sure the bucket stays within the screen bounds
        if (player.xPos < 0) {
            player.xPos = 0;
        }
        if (player.xPos > WORLD_WIDTH - 10) {
            player.xPos = WORLD_WIDTH - 10;
        }
        if (player.yPos < 0) {
            player.yPos = 0;
        }
        if (player.yPos > WORLD_HEIGHT - 10) {
            player.yPos = WORLD_HEIGHT - 10;
        }

        // Check player movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.xPos -= player.getSpeed() * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.xPos += player.getSpeed() * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.yPos += player.getSpeed() * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
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
