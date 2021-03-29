package Interface;

import com.badlogic.gdx.Game;

public class MaskGame extends Game {
    private MainMenuScreen mainMenuScreen;
    private MaskGame game;

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void create() {
        game = new MaskGame();
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }
}
