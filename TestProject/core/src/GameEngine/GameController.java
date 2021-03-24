package GameEngine;

import MaskGame.GameOverScreen;
import MaskGame.GameVictoryScreen;
import MaskGame.MaskGame;
import GameObject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

public class GameController {


    private static int End = 1;
    private static int Victory = 2;

    private static Player player = Player.instance();
    private static GameController uniqueInstance = null;

    private boolean isSlowMode;
    private float gameSpeed;

    private GameController() {
        this.isSlowMode = false;
        this.gameSpeed = 1;
    }

    public static GameController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameController();
        }
        return uniqueInstance;
    }


    public void setState(int state, MaskGame game) {
        if (state == End) {
            game.setScreen((new GameOverScreen(game)));
        }
        else if (state == Victory) {
            game.setScreen((new GameVictoryScreen(game)));
        }
    }


    public boolean getIsSlowMode() {
        return this.isSlowMode;
    }

    public void setIsSlowMode(Boolean val) {
        this.isSlowMode = val;
    }

    public void checkInvulnerabilityTime() {
        if (player.getInvulnerable()) {
            long elapsedTime = TimeUtils.timeSinceMillis(player.getStartInvulnerabilityTime()) / 1000;
            if (elapsedTime >= 5) {
                player.setInvulnerable(false);
            }
        }
    }

    /**
     * Return the speed of the game. If the game is in slow speed, the
     * speed of the game is reduced by 60%.
     *
     * @return the speed of the game
     */
    public float getGameSpeed() {
        // If the L key is just press and it is not slow down mode
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && !isSlowMode) {
            isSlowMode = true;  // Change the slow mode to true
            gameSpeed = 0.4f;   // Change the game speed to slow speed
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && isSlowMode) {
            // If the L key is just press and it is slow down mode
            isSlowMode = false; // Change slow mode to false
            gameSpeed = 1;      // Change the game speed to normal speed
        }

        return gameSpeed;
    }
}