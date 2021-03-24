package GameEngine;

import MaskGame.MaskGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import MaskGame.GameOverScreen;

public class EndState  {

    GameOverScreen gameOverScreen;

    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont endfont = new BitmapFont();
    private final String end = "Game Over";


    public EndState(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }
}
