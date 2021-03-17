package MaskGame;

import Ammo.Ammo;
import Entity.Enemy;
import Entity.Entity;
import Entity.Player;

import GameEngine.*;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ListIterator;

/**
 * GameScreen class that implements from Screen that let the user play
 * the game.
 */
public class GameScreen extends ApplicationAdapter implements Screen {
    // Screen
    private final Camera camera;

    private final Viewport viewport;


    // Graphic
    private final SpriteBatch batch;
    private final SpriteBatch batch2;
    private Texture[] backgrounds;

    // Background variables
    private final float[] backgroundOffsets = {0, 0, 0, 0};
    private float maxScrollingSpeed;

    // World dimension
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();

    // Game objects
    private final Entity player = Player.instance();

    private CommandController collisionController;
    private Command playerIsHitCommand;
    private Command enemyIsHitCommand;


    // Game controllers
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemyMovementController enemyMoveController = EnemyMovementController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final StageController stageController = StageController.instance();
    private final BulletMovementController bulletMovementController = BulletMovementController.instance();
    private final GameController gameController = GameController.instance();

    DrawController drawController;


    // Slow mode variables
    private boolean isSlowMode;
    private float gameSpeed;    // Current game speed

    private final FPSLogger logger = new FPSLogger();

    /**
     * Create a GameScreen that let the user play a game of bullet hell.
     */
    public GameScreen() {
        // Initialize camera and view
        camera = new OrthographicCamera();
        viewport = new StretchViewport(576, 1024, camera);

        initializeScrollingBackground();

        collisionController = new CommandController();

        playerIsHitCommand = new PlayerCommand(player);

        // Initialize slow mode
        this.isSlowMode = false;
        this.gameSpeed = 1; // Set current game speed to normal speed

        batch = new SpriteBatch();
        batch2 = new SpriteBatch();

        drawController = new DrawController(batch, player);

        // Music
        Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BackgroundMusic.mp3"));

        // Play background music
        backgroundMusic.setVolume((float) 0.05);
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
        //logger.log();

        // Get the game speed
        gameSpeed = getGameSpeed();
        deltaTime *= gameSpeed;

        //update gameController time
        gameController.updateElapsedTime();

        // Begin the Batch
        batch.begin();
        batch2.begin();

        // Update scrolling background
        renderBackground(deltaTime);

        // Process player
        drawController.draw("player");
        drawController.draw("enemy");
        drawController.draw("ammoPlayer");
        drawController.draw("ammoEnemy");

        player.updateTimeSinceLastShot(deltaTime);  // Update player
        ((Player) player).movePlayer(deltaTime);    // Move player

        enemyIsHitCommand = new EnemyCommand();

        collisionController.addCommand(playerIsHitCommand);
        collisionController.addCommand(enemyIsHitCommand);
        collisionController.executeCommand();

        gameController.checkInvulnerabilityTime();

        bulletSpawningController.playerFire(player);
        bulletSpawningController.enemyFire(deltaTime);      // Fire enemy bullets if they can fire

        // Process enemies
        stageController.makeStages();               // Spawn game enemies
        updateMovementAndDrawBullets(deltaTime);    // Draw and update all
        updateMovementAndDrawEnemies(deltaTime);
        enemySpawningController.deleteEnemies();    // Delete enemies if they need deleted
        bulletSpawningController.deleteBullet("Player");
        bulletSpawningController.deleteBullet("Enemy");

        // Draw white dor in slow mode
        drawWhiteDotInSlowMode();

        //hud rendering
        //createHudScreen();
        drawController.updateAndRenderHUD();

        // End the batch
        batch.end();
        batch2.end();
    }

    private void creatHudScreen() {
        batch2.draw(new Texture("Cloud4.png"), (float) (WORLD_HEIGHT / 2), (float) (WORLD_WIDTH / 2));
    }

    /**
     * Initialize the scrolling background.
     */
    private void initializeScrollingBackground() {
        // Initialize background objects
        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("BlueBackground.png");
        backgrounds[1] = new Texture("Clouds1.png");
        backgrounds[2] = new Texture("Clouds2.png");
        backgrounds[3] = new Texture("Cloud4.png");
        maxScrollingSpeed = (float) (WORLD_HEIGHT) / 4;
    }

    /**
     * Draw the white dot at the center of the player is the game
     * is in slow mode.
     */
    private void drawWhiteDotInSlowMode() {
        if (isSlowMode) {
            batch.draw(new Texture("CircleHitBox.png"),
                    player.getXPosition() + (float) (player.getImageWidth() / 2) - (float) (player.getImageWidth() / 4),
                    player.getYPosition() + (float) (player.getImageHeight() / 2) - (float) (player.getImageWidth() / 4),
                    (float) player.getImageWidth() / 2,
                    (float) player.getImageWidth() / 2);
        }
    }

    /**
     * Return the speed of the game. If the game is in slow speed, the
     * speed of the game is reduced by 60%.
     *
     * @return the speed of the game
     */
    private float getGameSpeed() {
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
     * Render the background.
     *
     * @param deltaTime the delta time
     */
    private void renderBackground(float deltaTime) {
        backgroundOffsets[0] += deltaTime * maxScrollingSpeed / 8;
        backgroundOffsets[1] += deltaTime * maxScrollingSpeed / 4;
        backgroundOffsets[2] += deltaTime * maxScrollingSpeed / 2;
        backgroundOffsets[3] += deltaTime * maxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; layer++) {
            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }

            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        }
    }

    /**
     * TODO: Update movement
     * Draw and update bullets on the screen.
     *
     * @param deltaTime the delta time
     */
    private void updateMovementAndDrawBullets(float deltaTime) {
        ListIterator<Ammo> iterator = bulletSpawningController.getPlayerAmmoList().listIterator();
        while (iterator.hasNext()) {
            Ammo ammo = iterator.next();
            bulletMovementController.move(ammo, deltaTime);

            if (ammo.getYPosition() > WORLD_HEIGHT) {
                iterator.remove();
            }
        }

        // Enemy bullets
        ListIterator<Ammo> iter = bulletSpawningController.getEnemyAmmoList().listIterator();
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            bulletMovementController.move(ammo, deltaTime);

            if (ammo.getYPosition() < 0) {
                iter.remove();
            }
        }
    }

    /**
     * TODO: Get rid of spawnMove
     * Draw and update enemies on the screen.
     *
     * @param deltaTime the delta time
     */
    private void updateMovementAndDrawEnemies(float deltaTime) {
        ListIterator<Enemy> iter2 = enemySpawningController.getEnemyList().listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();
            enemyMoveController.move(currEnemy, deltaTime);
        }
    }

    @Override
    public void resize(int width, int height) {

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
