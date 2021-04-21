package Interface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import MaskGame.SettingsScreen;

public class MainMenuScreen extends InputAdapter implements Screen {
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private MaskGame game;
    private Batch batch;
    private Texture exitButtonActive;
    private Texture exitButtonInActive;
    private Texture playButtonActive;
    private Texture playButtonInActive;
    private Texture background;
    private Texture settingsButton;
    private int buttonWidth = 241;
    private int buttonHeight = 41;

    public MainMenuScreen(MaskGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        initializeTextures();
    }

    private void initializeTextures() {
        background = new Texture("Background.png");
        playButtonActive = new Texture("PlayButtonPressed.png");
        playButtonInActive = new Texture("PlayButton.png");
        exitButtonActive = new Texture("ExitButtonPressed.png");
        exitButtonInActive = new Texture("ExitButton.png");
        settingsButton = new Texture("settingsButton.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.setToOrtho(false);
        batch.begin();

        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

        // Play Button
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            batch.draw(playButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
            dispose();
            game.setScreen(new GameScreen(game));
        } else {
            batch.draw(playButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
        }

        // Quit Button
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            batch.draw(exitButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
            dispose();
            Gdx.app.exit();
        } else {
            batch.draw(exitButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
        }

        // Settings Button
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
            game.setScreen(new SettingsScreen(game));
        } else{
            batch.draw(settingsButton, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 6, buttonWidth, buttonHeight);
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.update();
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
    }
}