package Interface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOverScreen extends InputAdapter implements Screen {
    private final Camera camera;
    private final Viewport viewport;
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private MaskGame game;
    private Batch batch;
    private Texture background;
    private Texture replayButton;
    private Texture replayButtonPressed;
    private Texture quitButtonInActive;
    private Texture quitButtonActive;
    private int buttonWidth = 241;
    private int buttonHeight = 41;

    public GameOverScreen(MaskGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        initializeTextures();
    }

    private void initializeTextures() {
        background = new Texture("gameOverScreen.png");
        replayButton = new Texture("PlayButtonPressed.png");
        replayButtonPressed = new Texture("PlayButton.png");
        quitButtonInActive = new Texture("ExitButton.png");
        quitButtonActive = new Texture("ExitButtonPressed.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, (WORLD_WIDTH), (WORLD_HEIGHT));

        Rectangle quitBound = new Rectangle((WORLD_WIDTH / 2) - (buttonWidth/ 2), (WORLD_HEIGHT / 4), quitButtonInActive.getWidth(), quitButtonInActive.getHeight());
        Vector3 quit = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(quit);

        // Quit Button
        if (quitBound.contains(quit.x, quit.y)) {
            batch.draw(quitButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                dispose();
                Gdx.app.exit();
            }
        } else {
            batch.draw(quitButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
    public void dispose() {
        batch.dispose();
    }
}
