package MaskGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MaskGame extends Game {
    GameScreen gameScreen;
    MainMenuScreen mainMenuScreen;
    //MaskGame game;
    //Batch batch;

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        //gameScreen.resize(width, height);
    }

    @Override
    public void dispose() {
        dispose();
    }

    @Override
    public void create() {
        //game = new MaskGame();
        //this.batch = new SpriteBatch();
        mainMenuScreen = new MainMenuScreen(this);
        //gameScreen = new GameScreen(game);
        this.setScreen(mainMenuScreen);
    }
}