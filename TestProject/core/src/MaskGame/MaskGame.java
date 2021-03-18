package MaskGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;

public class MaskGame extends Game {
    MainMenuScreen mainMenuScreen;

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
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);

    }
}
