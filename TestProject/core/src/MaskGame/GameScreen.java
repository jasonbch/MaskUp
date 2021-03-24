package MaskGame;

import GameEngine.Collision.CommandController;
import GameEngine.Collision.EnemyCollisionCommand;
import GameEngine.Collision.PlayerCollisionCommand;
import GameEngine.GameController;
import GameEngine.Movement.BulletMovementController;
import GameEngine.Movement.EnemyMovementController;
import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.EnemySpawningController;
import GameEngine.StageController;
import GameEngine.TimeController;
import GameEngine.UI.UIController;
import GameObject.Entity;
import GameObject.Player;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * GameScreen class that implements from Screen that let the user play
 * the game.
 */
public class GameScreen extends ApplicationAdapter implements Screen {
    // Screen
    private final Camera camera;

    private final Viewport viewport;
    private final int VIEWPORT_WIDTH = 576;
    private final int VIEWPORT_HEIGHT = 1024;

    // Graphic
    private final SpriteBatch batch;

    // Game objects
    private final Entity player = Player.instance();

    // Game controllers
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemyMovementController enemyMoveController = EnemyMovementController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final StageController stageController = StageController.instance();
    private final BulletMovementController bulletMovementController = BulletMovementController.instance();
    private final GameController gameController = GameController.instance();

    private final TimeController timeController = TimeController.instance();
    private final GameEngine.UI.UIController UIController;

    private final CommandController collisionController = new CommandController();

    private final FPSLogger logger = new FPSLogger();
    private final MaskGame game;

    /**
     * Create a GameScreen that let the user play a game of bullet hell.
     */
    public GameScreen(MaskGame mainGame) {
        this.game = mainGame;

        // Initialize camera and view
        camera = new OrthographicCamera();

        viewport = new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
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

        // Update game time
        timeController.updateElapsedTime();

        batch.begin();

        // Update scrolling background
        UIController.drawBackground(deltaTime);


        // Draw all game objects
        UIController.drawGameObjects();

        // TODO Move this out
        player.updateTimeSinceLastShot(deltaTime);  // Restrict shooting interval

        // Collision commands
        collisionController.addCommand(new PlayerCollisionCommand(player));
        collisionController.addCommand(new EnemyCollisionCommand());
        collisionController.executeCommand();

        gameController.checkInvulnerabilityTime();

        // Spawn enemies
        stageController.makeStages();

        // Spawn bullets from player and enemies
        bulletSpawningController.playerFire(player);
        bulletSpawningController.enemyFire(deltaTime);

        // Move player
        ((Player) player).movePlayer(deltaTime);
        /*if (player.getHealth() == 0)
        {
            gameController.setState(End, game);
        }*/

        // Move enemies and bullets
        bulletMovementController.update(deltaTime);
        enemyMoveController.update(deltaTime);

        // Clear used enemies and bullets
        enemySpawningController.deleteEnemies();
        bulletSpawningController.deleteBullet("Player");
        bulletSpawningController.deleteBullet("Enemy");

        // Draw white dot in slow mode
        UIController.drawWhiteDotInSlowMode();

        // Draw and update Health Bar
        UIController.updateAndRenderHealthBar();
        UIController.updateScore();

        // Draw stage message
        UIController.drawStageMessage();

        // Pause Option
        this.pauseGame();

        // Check if the game is over
        gameController.checkGameOver(game);

        // Check if the player won
        gameController.checkVictoryGame(game);

        batch.end();
    }

    private void pauseGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            pause();
            game.setScreen(new MainMenuScreen(game));
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
