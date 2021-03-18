package MaskGame;

import com.badlogic.gdx.Game;

public class MaskGame extends Game {
    MainMenuScreen mainMenuScreen;
    MaskGame game;
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
