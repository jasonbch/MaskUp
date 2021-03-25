package MaskGame;

import GameEngine.GameController;
import GameEngine.TimeController;
import GameEngine.UI.UIController;
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

    // Game controllers
    private final GameController gameController = GameController.instance();
    private final TimeController timeController = TimeController.instance();
    private final GameEngine.UI.UIController UIController;

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

        // GameController
        gameController.updateGame(deltaTime, game);

        // Draw white dot in slow mode
        UIController.drawWhiteDotInSlowMode();

        // Draw and update Health Bar
        UIController.updateAndRenderHealthBar();
        UIController.updateScore();

        UIController.updateScore();
        // Draw stage message

        UIController.drawStageMessage();

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
