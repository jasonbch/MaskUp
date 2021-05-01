package Interface;

import GameEngine.GameController;
import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Score.ScoreController;
import GameEngine.Spawning.EnemySpawningController;
import GameEngine.Resource.GameResources;
import GameEngine.Time.TimeController;
import GameEngine.UI.UIController;
import Objects.GameObject.Entity;
import Objects.GameObject.GameObject;
import Objects.GameObject.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * GameScreen class that implements from Screen that let the user play
 * the game.
 */
public class GameScreen extends ApplicationAdapter implements Screen, GameObserver {
    // Screen
    private final OrthographicCamera camera;
    //private final OrthographicCamera camera2;

    private final Viewport viewport;
    // private final Viewport viewport2;

    private final int VIEWPORT_WIDTH = 576;
    private final int VIEWPORT_HEIGHT = 1024;

    // Graphic
    private final SpriteBatch batch;
    //private final SpriteBatch batch2;


    // Game controllers
    private final GameController gameController = GameController.instance();
    private final TimeController timeController = TimeController.instance();
    private final GameResources gameResources = GameResources.instance();
    private final UIController uiController = UIController.instance();

    private final FPSLogger logger = new FPSLogger();
    private final MaskGame game;
    private final ScoreController scoreController = ScoreController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final Player player = Player.instance();

    //testing with a matrix
    private final Matrix4 tempMatrix4 = new Matrix4();


    /**
     * Create a GameScreen that let the user play a game of bullet hell.
     */
    public GameScreen(MaskGame mainGame, String difficulty) {
        this.game = mainGame;

        // Initialize camera and view
        camera = new OrthographicCamera();
        //camera2 = new OrthographicCamera();

        gameResources.setDifficulty(difficulty);

        viewport = new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
        //viewport2 = new StretchViewport(VIEWPORT_WIDTH/ 2, VIEWPORT_HEIGHT, camera2);
        batch = new SpriteBatch();


        //        // Attach Observers
        //        player.Attach(enemySpawningController);
        //        player.Attach(scoreController);

        gameController.attachGameObserver(this);
        gameResources.setBatch(batch);
        uiController.setBatch(batch);
        uiController.setCamera(camera);

        uiController.playMusic();
    }

    /**
     * Render the screen
     *
     * @param deltaTime the delta time
     */
    @Override
    public void render(float deltaTime) {
        //logger.log();

        // Get the game speed
        deltaTime *= gameController.getGameSpeed();

        // Update game time
        timeController.updateElapsedTime();

        batch.begin();

        //Update scrolling background
        uiController.drawBackground(deltaTime);

        // Draw all game objects
        uiController.drawGameObjects();

        // GameController
        gameController.updateGame(deltaTime, game);

        // Draw stage message
        uiController.drawStageMessage();


        // Draw white dot in slow mode
        uiController.drawWhiteDotInSlowMode();

        // Draw and update Health Bar
        uiController.updateAndRenderHealthBar();
        uiController.updateTimeElapsed();

        uiController.updateScore();

        // Pause Option
        this.pauseGame();

        batch.end();
    }

    private void pauseGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            pause();
            game.setScreen(new MainMenuScreen(game));
        }
    }

    private void setWiningState(MaskGame game) {
        game.setScreen((new GameVictoryScreen(game)));
    }

    private void setLosingState(MaskGame game) {
        game.setScreen((new GameOverScreen(game)));
    }

    @Override
    public void update(Object object, String args) {
        if (object instanceof GameController) {
            if (args.equals("winningState")) {
                setWiningState(game);
            } else if (args.equals("losingState")) {
                setLosingState(game);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
//        viewport.update(width/ 2, height);
//        viewport2.update(width / 2, height);
//        viewport2.setScreenX(width/2);
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
