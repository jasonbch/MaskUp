package GameEngine;

import GameEngine.Spawning.BulletSpawningController;
import GameObject.Enemy.Enemy;
import MaskGame.GameOverScreen;
import MaskGame.GameVictoryScreen;
import MaskGame.MaskGame;
import GameObject.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

public class GameController {
    private static boolean isLost;
    private static boolean isWon;

    private static Player player = Player.instance();
    private final TimeController timeController = TimeController.instance();
    private final StageController stageController = StageController.instance();
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();

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

    public void updateGame(float deltaTime) {
        // Spawn bullets from player and enemies
        if (!player.getInvulnerable()) {
            bulletSpawningController.playerFire(player);
            bulletSpawningController.enemyFire(deltaTime);
        }

        // Clear used enemies and bullets
        bulletSpawningController.deleteBullet("Player");
        bulletSpawningController.deleteBullet("Enemy");
    }

    public void checkGameOver(MaskGame game) {
        if (player.getHealth() == 0) {
            setLosingState(game);
        }
    }

    public void checkVictoryGame(MaskGame game) {
        if (timeController.getElapsedTime() == stageController.getStageFourEnd()
                || this.isFinalBossDead()) {
            setWiningState(game);
        }
    }

    private void setWiningState(MaskGame game) {
        game.setScreen((new GameVictoryScreen(game)));
    }

    private boolean isFinalBossDead() {
        Enemy covid = stageController.getCovid();

        if (covid != null) {
            return covid.IsDone();
        }

        return false;
    }

    private void setLosingState(MaskGame game) {
        game.setScreen((new GameOverScreen(game)));
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

    public void preventBulletFromBeingShot() {
        if (player.getInvulnerable()) {

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