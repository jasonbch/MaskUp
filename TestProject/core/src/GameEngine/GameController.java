package GameEngine;

import GameEngine.Collision.CommandController;
import GameEngine.Collision.EnemyCollisionCommand;
import GameEngine.Collision.PlayerCollisionCommand;
import GameEngine.Movement.BulletMovementController;
import GameEngine.Movement.EnemyMovementController;
import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.EnemySpawningController;
import GameEngine.Stage.StageController;
import GameEngine.Time.TimeController;
import Interface.GameOverScreen;
import Interface.GameVictoryScreen;
import Interface.MaskGame;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

public class GameController {
    private static boolean isLost;
    private static boolean isWon;

    // player instance
    private static Player player = Player.instance();
    private static GameController uniqueInstance = null;

    // game controllers
    private final TimeController timeController = TimeController.instance();
    private final StageController stageController = StageController.instance();
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemyMovementController enemyMovementController = EnemyMovementController.instance();
    private final BulletMovementController bulletMovementController = BulletMovementController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final CommandController commandController = new CommandController();
    private boolean isSlowMode;
    private float gameSpeed;
    private float playerInvulnerableTime = 3;

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
        if (!player.isInvulnerable()) {
            bulletSpawningController.playerFire(player);
            bulletSpawningController.enemyFire(deltaTime, enemySpawningController.getEnemyList());
        }

        // Clear used enemies and bullets
        bulletSpawningController.deleteBullet("Player");
        bulletSpawningController.deleteBullet("Enemy");

        player.updateTimeSinceLastShot(deltaTime);  // Restrict shooting interval

        // check players invulnerability time
        resetPlayerInvulnerableTime();

        // update the collision commands
        commandController.addCommand(new PlayerCollisionCommand(player));
        commandController.addCommand(new EnemyCollisionCommand());
        commandController.executeCommand();

        player.movePlayer(deltaTime);

        // update movement controllers
        bulletMovementController.update(deltaTime, bulletSpawningController);
        enemyMovementController.update(deltaTime, enemySpawningController.getEnemyList());

        // delete enemies if they need deleted
        enemySpawningController.deleteEnemies();

        // TODO: make stages
        stageController.makeStages();

        // Check if the game is over
        checkGameOver(game);

        // Check if the player won
        checkVictoryGame(game);
    }

    public void checkGameOver(MaskGame game) {
        if (player.getHealth() <= 0) {
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
            return covid.isDone();
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

    public void resetPlayerInvulnerableTime() {
        if (player.isInvulnerable()) {
            long elapsedTime = getPlayerInvulnerableTime();
            if (elapsedTime >= this.playerInvulnerableTime) {
                // Reset the player invulnerable if it is more than 5 seconds
                player.setInvulnerable(false);
            }
        }
    }

    public long getPlayerInvulnerableTime() {
        return TimeUtils.timeSinceMillis(player.getStartInvulnerabilityTime()) / 1000;
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
            setIsSlowMode(true);  // Change the slow mode to true
            gameSpeed = 0.4f;   // Change the game speed to slow speed
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && isSlowMode) {
            // If the L key is just press and it is slow down mode
            setIsSlowMode(false); // Change slow mode to false
            gameSpeed = 1;      // Change the game speed to normal speed
        }

        return gameSpeed;
    }
}