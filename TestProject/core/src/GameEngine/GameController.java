package GameEngine;

import GameEngine.Collision.CommandController;
import GameEngine.Collision.EnemyCollisionCommand;
import GameEngine.Collision.PlayerCollisionCommand;
import GameEngine.Movement.BulletMovementController;
import GameEngine.Movement.EnemyMovementController;
import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.EnemySpawningController;
import GameObject.Enemy.Enemy;
import GameObject.Player;
import MaskGame.GameOverScreen;
import MaskGame.GameVictoryScreen;
import MaskGame.MaskGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

public class GameController {

    private static int End = 1;
    private static int Victory = 2;

    private static boolean isLost;
    private static boolean isWon;

    // player instance
    private static Player player = Player.instance();
    private static GameController uniqueInstance = null;
    // game controllers
    private final TimeController timeController = TimeController.instance();
    private final StageController stageController = StageController.instance();
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemyMovementController enemyMoveController = EnemyMovementController.instance();
    private final BulletMovementController bulletMovementController = BulletMovementController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final CommandController collisionController = new CommandController();
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

    public void updateGame(float deltaTime, MaskGame game) {
        // Spawn bullets from player and enemies
        if (!player.getInvulnerable()) {
            bulletSpawningController.playerFire(player);
            bulletSpawningController.enemyFire(deltaTime);
        }

        // Clear used enemies and bullets
        bulletSpawningController.deleteBullet("Player");
        bulletSpawningController.deleteBullet("Enemy");

        player.updateTimeSinceLastShot(deltaTime);  // Restrict shooting interval

        // check players invulnerability time
        checkInvulnerabilityTime();

        // update the collision commands
        collisionController.addCommand(new PlayerCollisionCommand(player));
        collisionController.addCommand(new EnemyCollisionCommand());
        collisionController.executeCommand();

        ((Player) player).movePlayer(deltaTime);

        // update movement controllers
        bulletMovementController.update(deltaTime);
        enemyMoveController.update(deltaTime);

        // delete enemies if they need deleted
        enemySpawningController.deleteEnemies();

        // make stages
        stageController.makeStages();

        // Check if the game is over
        checkGameOver(game);

        // Check if the player won
        checkVictoryGame(game);
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