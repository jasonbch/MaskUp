package MaskGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameVictoryScreen extends InputAdapter implements Screen {
    private final Camera camera;
    private final Viewport viewport;
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private MaskGame game;
    private Batch batch;
    private Texture background;
    private Texture replayButton;
    private Texture replayButtonPressed;
    private Texture quitButton;
    private Texture quitButtonPressed;
    private int buttonWidth = 241;
    private int buttonHeight = 41;

    public GameVictoryScreen(MaskGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        initializeTextures();
    }

    private void initializeTextures() {
        background = new Texture("gameVictoryScreen.png");
        replayButton = new Texture("PlayButtonPressed.png");
        replayButtonPressed = new Texture("PlayButton.png");
        quitButton = new Texture("ExitButtonPressed.png");
        quitButtonPressed = new Texture("ExitButton.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            batch.draw(replayButtonPressed, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
            dispose();
            game.setScreen(new GameScreen(new MaskGame()));
        } else {
            batch.draw(replayButton, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            batch.draw(quitButtonPressed, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
            dispose();
            Gdx.app.exit();
        } else {
            batch.draw(quitButton, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
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
