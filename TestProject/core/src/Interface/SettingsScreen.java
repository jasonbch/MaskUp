package MaskGame;

import Interface.MainMenuScreen;
import Interface.MaskGame;
import Objects.GameObject.Player;
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

public class SettingsScreen extends InputAdapter implements Screen {
    private final Camera camera;
    private final Viewport viewport;
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    Player player = Player.instance();
    MaskGame game;
    private Batch batch;
    private Texture ChangeToArrowKeysInactive;
    private Texture ChangeToArrowKeysActive;
    private Texture ChangeToWASDKeysInactive;
    private Texture ChangeToWASDKeysActive;
    private Texture arrowKeyInstructions;
    private Texture wasdKeyInstructions;
    private Texture backButtonInActive;
    private Texture backButtonActive;
    private Texture background;
    private int changeKeyButtonWidth = 241;
    private int changeKeyButtonHeight = 41;
    private int backButtonWidth = 241;
    private int backButtonHeight = 41;
    private int instructionWidth = 291;
    private int instructionHeight = 201;


    public SettingsScreen(MaskGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        initializeTextures();
    }

    private void initializeTextures() {
        ChangeToArrowKeysInactive = new Texture("ArrowKeysButton.png");
        ChangeToArrowKeysActive = new Texture("ArrowKeysButtonPressed.png");
        ChangeToWASDKeysInactive = new Texture("WASDKeysButton.png");
        ChangeToWASDKeysActive = new Texture("WASDKeysButtonPressed.png");
        arrowKeyInstructions = new Texture("arrowKeyInstructions.png");
        wasdKeyInstructions = new Texture("wasdInstructions.png");
        backButtonInActive = new Texture("ExitButton.png");
        backButtonActive = new Texture("ExitButtonPressed.png");
        background = new Texture("SettingsScreen.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backButtonInActive, (WORLD_WIDTH / 2) - (backButtonWidth / 2), WORLD_HEIGHT / 4, backButtonWidth, backButtonHeight);

        Rectangle arrowKeyBound = new Rectangle((WORLD_WIDTH / 2) - (changeKeyButtonWidth/ 2), (WORLD_HEIGHT / 3), ChangeToArrowKeysInactive.getWidth(), ChangeToArrowKeysInactive.getHeight());
        Vector3 arrowKey = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(arrowKey);

        Rectangle wasdKeyBound = new Rectangle((WORLD_WIDTH / 2) - (changeKeyButtonWidth/ 2), (WORLD_HEIGHT / 3), ChangeToArrowKeysInactive.getWidth(), ChangeToArrowKeysInactive.getHeight());
        Vector3 wasdKey = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(wasdKey);

        Rectangle exitBound = new Rectangle((WORLD_WIDTH / 2) - (changeKeyButtonWidth/ 2), (WORLD_HEIGHT / 4), backButtonInActive.getWidth(), backButtonInActive.getHeight());
        Vector3 exit = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(exit);


        if (player.isDefaultKeySet()) {
            batch.draw(arrowKeyInstructions, (WORLD_WIDTH / 2) - (instructionWidth / 2), (WORLD_HEIGHT / 2), instructionWidth, instructionHeight);
            if (wasdKeyBound.contains(wasdKey.x, wasdKey.y)) {
                batch.draw(ChangeToWASDKeysActive, (WORLD_WIDTH / 2) - (changeKeyButtonWidth / 2), WORLD_HEIGHT / 3, changeKeyButtonWidth, changeKeyButtonHeight);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    player.changeDefaultKeySet();
                }
            }   else {
                batch.draw(ChangeToWASDKeysInactive, (WORLD_WIDTH / 2) - (changeKeyButtonWidth / 2), WORLD_HEIGHT / 3, changeKeyButtonWidth, changeKeyButtonHeight);
            }

        } else {
            batch.draw(wasdKeyInstructions, (WORLD_WIDTH / 2) - (instructionWidth / 2), (WORLD_HEIGHT / 2), instructionWidth, instructionHeight);
            if (arrowKeyBound.contains(arrowKey.x, arrowKey.y)) {
                batch.draw(ChangeToArrowKeysActive, (WORLD_WIDTH / 2) - (changeKeyButtonWidth / 2), WORLD_HEIGHT / 3, changeKeyButtonWidth, changeKeyButtonHeight);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    player.changeDefaultKeySet();
                }
            }   else {
                batch.draw(ChangeToArrowKeysInactive, (WORLD_WIDTH / 2) - (changeKeyButtonWidth / 2), WORLD_HEIGHT / 3, changeKeyButtonWidth, changeKeyButtonHeight);
            }

        }


        if (exitBound.contains(exit.x, exit.y)) {
            batch.draw(backButtonActive, (WORLD_WIDTH / 2) - (backButtonWidth / 2), WORLD_HEIGHT / 4, backButtonWidth, backButtonHeight);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new MainMenuScreen(game));
            }
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
