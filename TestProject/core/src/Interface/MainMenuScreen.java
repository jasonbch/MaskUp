package Interface;

import MaskGame.SettingsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen extends InputAdapter implements Screen {
    //changed to orthographicCamera
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
    private Texture settingsButtonInActive;
    private Texture settingsButtonActive;
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
        settingsButtonInActive = new Texture("settingsButton.png");
        settingsButtonActive = new Texture("settingsButtonPressed.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);


        batch.begin();

        Rectangle playBound = new Rectangle((WORLD_WIDTH / 2) - (buttonWidth/ 2), (WORLD_HEIGHT / 3), playButtonInActive.getWidth(), playButtonInActive.getHeight());
        Vector3 play = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(play);


        Rectangle exitBound = new Rectangle((WORLD_WIDTH / 2) - (buttonWidth/ 2), (WORLD_HEIGHT / 4), exitButtonInActive.getWidth(), exitButtonInActive.getHeight());
        Vector3 exit = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(exit);


        Rectangle settingsBound = new Rectangle((WORLD_WIDTH / 2) - (buttonWidth/ 2), (WORLD_HEIGHT / 6), settingsButtonInActive.getWidth(), settingsButtonInActive.getHeight());
        Vector3 settings = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(settings);

        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

        // Play Button
        if (playBound.contains(play.x, play.y)) {
            batch.draw(playButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                dispose();
                game.setScreen(new DifficultyScreen(game));
            }
        } else {
            batch.draw(playButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
        }

        // Quit Button
        if (exitBound.contains(exit.x, exit.y)) {
            batch.draw(exitButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                dispose();
                Gdx.app.exit();
            }
        } else {
            batch.draw(exitButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
        }

        // Settings Button
        if (settingsBound.contains(settings.x, settings.y)) {
            batch.draw(settingsButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 6, buttonWidth, buttonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new SettingsScreen(game));
            }
        } else {
            batch.draw(settingsButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 6, buttonWidth, buttonHeight);
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
    }
}