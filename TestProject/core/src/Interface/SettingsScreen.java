package MaskGame;

import GameEngine.UI.UIController;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import GameEngine.UI.UIController;

public class SettingsScreen extends InputAdapter implements Screen {
    private final Camera camera;
    private final Viewport viewport;
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    Player player = Player.instance();
    MaskGame game;
    private Batch batch;
    private Texture changeToArrowKeySet;
    private Texture arrowKeyInstructions;
    private Texture changeToWASDKeySet;
    private Texture wasdKeyInstructions;
    private Texture backButton;
    private Texture background;
    private int changeKeyButtonWidth = 281;
    private int changeKeyButtonHeight = 61;
    private int backButtonWidth = 281;
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
        changeToArrowKeySet = new Texture("tabArrow.png");
        changeToWASDKeySet = new Texture("TabWASD.png");
        arrowKeyInstructions = new Texture("arrowKeyInstructions.png");
        wasdKeyInstructions = new Texture("wasdInstructions.png");
        backButton = new Texture("BackspaceExitButton.png");
        background = new Texture("SettingsScreen.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backButton, (WORLD_WIDTH/2) - (backButtonWidth/2), WORLD_HEIGHT/4, backButtonWidth, backButtonHeight);

        if (player.isDefaultKeySet()) {
            batch.draw(changeToWASDKeySet, (WORLD_WIDTH / 2) - (changeKeyButtonWidth / 2), WORLD_HEIGHT / 3, changeKeyButtonWidth, changeKeyButtonHeight);
            batch.draw(arrowKeyInstructions, (WORLD_WIDTH / 2) - (instructionWidth / 2), (WORLD_HEIGHT / 2), instructionWidth, instructionHeight);
        } else {
            batch.draw(changeToArrowKeySet, (WORLD_WIDTH / 2) - (changeKeyButtonWidth / 2), WORLD_HEIGHT / 3, changeKeyButtonWidth, changeKeyButtonHeight);
            batch.draw(wasdKeyInstructions, (WORLD_WIDTH / 2) - (instructionWidth / 2), (WORLD_HEIGHT / 2), instructionWidth, instructionHeight);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
            player.changeDefaultKeySet();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            game.setScreen(new MainMenuScreen(game));
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
