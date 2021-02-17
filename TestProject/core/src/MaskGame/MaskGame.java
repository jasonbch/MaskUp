package MaskGame;

import com.badlogic.gdx.Game;

public class MaskGame extends Game {
    GameScreen gameScreen;

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        gameScreen.resize(width, height);
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
    }

    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);

    }
}
