package Interface;

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

public class DifficultyScreen extends InputAdapter implements Screen {
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private MaskGame game;
    private Batch batch;
    private Texture easyButtonActive;
    private Texture easyButtonInActive;
    private Texture MediumButtonActive;
    private Texture MediumButtonInActive;
    private Texture background;
    private Texture HardButtonActive;
    private Texture HardButtonInActive;
    private int buttonWidth = 241;
    private int buttonHeight = 41;
    private String Easy = "Easy";
    private String Medium = "Medium";
    private String Hard = "Hard";


    public DifficultyScreen(MaskGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        initializeTextures();
    }

    private void initializeTextures() {
        background = new Texture("GameDifficulty.png");
        easyButtonInActive = new Texture("Easy.png");
        easyButtonActive = new Texture( "EasyButtonPressed.png");
        MediumButtonInActive = new Texture("Medium.png");
        MediumButtonActive = new Texture("MediumButtonPressed.png");
        HardButtonInActive = new Texture("Hard.png");
        HardButtonActive = new Texture("HardButtonPressed.png");
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
        // Easy Button Clicked

        Rectangle easyBound = new Rectangle((WORLD_WIDTH / 2) - (buttonWidth/ 2), (WORLD_HEIGHT / 3), easyButtonInActive.getWidth(), easyButtonInActive.getHeight());
        Vector3 easy = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(easy);


        Rectangle mediumBound = new Rectangle((WORLD_WIDTH / 2) - (buttonWidth/ 2), (WORLD_HEIGHT / 4), MediumButtonInActive.getWidth(), MediumButtonInActive.getHeight());
        Vector3 medium = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(medium);


        Rectangle hardBound = new Rectangle((WORLD_WIDTH / 2) - (buttonWidth/ 2), (WORLD_HEIGHT / 6), HardButtonInActive.getWidth(), HardButtonInActive.getHeight());
        Vector3 hard = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(hard);

        if (easyBound.contains(easy.x, easy.y)) {
            batch.draw(easyButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new GameScreen(game, Easy));
            }
        } else {
            batch.draw(easyButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 3, buttonWidth, buttonHeight);
        }

        // Medium Button Clicked
        if (mediumBound.contains(medium.x, medium.y)) {
            batch.draw(MediumButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new GameScreen(game, Medium));
            }
        } else {
            batch.draw(MediumButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 4, buttonWidth, buttonHeight);
        }

        // Hard Button Clicked
        if (hardBound.contains(hard.x, hard.y)) {
            batch.draw(HardButtonActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 6, buttonWidth, buttonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new GameScreen(game, Hard));
            }
        } else {
            batch.draw(HardButtonInActive, (WORLD_WIDTH / 2) - (buttonWidth / 2), WORLD_HEIGHT / 6, buttonWidth, buttonHeight);
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
