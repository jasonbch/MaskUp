package MaskGame;

import Ammo.Ammo;
import Entity.Enemy;
import Entity.Entity;
import Entity.Player;

import GameEngine.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.*;

import java.util.ListIterator;

/**
 * GameScreen class that implements from Screen that let the user play
 * the game.
 */
public class GameScreen extends ApplicationAdapter implements Screen  {
    // Screen
    private final Camera camera;
    private final Viewport viewport;

    // Graphic
    private final SpriteBatch batch;


    // World dimension
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
    private final UIController UIController;

    private final FPSLogger logger = new FPSLogger();

    /**
     * Create a GameScreen that let the user play a game of bullet hell.
     */
    public GameScreen() {
        // Initialize camera and view
        camera = new OrthographicCamera();
        viewport = new StretchViewport(576, 1024, camera);

        collisionController = new CommandController();

        playerIsHitCommand = new PlayerCommand(player);

        batch = new SpriteBatch();
        UIController = new UIController(batch);

        UIController.playMusic();
    }

    /**
     * Render the screen
     *
     * @param deltaTime the delta time
     */
    @Override
    public void render(float deltaTime) {
        logger.log();

        // Get the game speed
        deltaTime *= gameController.getGameSpeed();

        //update gameController time
        gameController.updateElapsedTime();

        // Begin the Batch
        batch.begin();

        // Update scrolling background
        UIController.drawBackground(deltaTime);

        // Draw game objects
        UIController.drawGameObjects();

        player.updateTimeSinceLastShot(deltaTime);  // Update player
        ((Player) player).movePlayer(deltaTime);    // Move player


        enemyIsHitCommand = new EnemyCommand();


        collisionController.addCommand(playerIsHitCommand);
        collisionController.addCommand(enemyIsHitCommand);
        collisionController.executeCommand();


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
        UIController.drawWhiteDotInSlowMode();

        // End the batch
        batch.end();
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
            enemyMoveController.move(currEnemy,deltaTime);
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
