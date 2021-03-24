package GameEngine;

import MaskGame.MaskGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import MaskGame.GameOverScreen;

public class EndState {

    GameOverScreen gameOverScreen;

    public EndState(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }


}
