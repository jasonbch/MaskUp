package GameEngine;

import GameEngine.Collision.CommandController;
import GameEngine.Collision.EnemyCollisionCommand;
import GameEngine.Collision.PlayerCollisionCommand;
import GameEngine.Movement.BulletMovementController;
import GameEngine.Movement.EnemyMovementController;
import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Spawning.BulletSpawnerSpawningController;
import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.EnemySpawningController;
import GameEngine.Stage.StageController;
import Interface.GameOverScreen;
import Interface.GameVictoryScreen;
import Interface.MaskGame;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class GameController implements GameObserver, GameSubject {
    private static boolean isLost;
    private static boolean isWon;

    // player instance
    private static Player player = Player.instance();
    private static final GameController uniqueInstance = new GameController();

    // game controllers
    private final StageController stageController = StageController.instance();
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemyMovementController enemyMovementController = EnemyMovementController.instance();
    private final BulletMovementController bulletMovementController = BulletMovementController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final BulletSpawnerSpawningController bulletSpawnerSpawningController = BulletSpawnerSpawningController.instance();

    private final CommandController commandController = new CommandController();
    private boolean isSlowMode;
    private float gameSpeed;
    private float playerInvulnerableTime = 3;
    private boolean godMode = false;

    private ArrayList<GameObserver> myObservers = new ArrayList<>();

    private GameController() {
        this.isSlowMode = false;
        this.gameSpeed = 1;

        StageController.instance().attachGameObserver(this);
        Player.instance().attachGameObserver(this);
    }

    public static GameController instance() {
        return uniqueInstance;
    }


    public void updateGame(float deltaTime, MaskGame game) {
        // God Mode
        startGodMode();

        // Spawn bullets from player and enemies
        if (!player.isInvulnerable()) {
            bulletSpawningController.playerFire(player);

            // Fire from the bullet spawner
            bulletSpawningController.enemyFire(deltaTime, (List<Enemy>) (List<?>) bulletSpawnerSpawningController.getBulletSpawnerList());
        }

        player.updateTimeSinceLastShot(deltaTime);  // Restrict shooting interval

        // Check players invulnerability time
        if (!this.godMode) {
            resetPlayerInvulnerableTime();
        }

        // Update the collision commands
        if (!player.isInvulnerable()) {
            commandController.addCommand(new PlayerCollisionCommand(player));
        }
        commandController.addCommand(new EnemyCollisionCommand());
        commandController.executeCommand();

        player.movePlayer(deltaTime);

        // Update movement controllers
        bulletMovementController.update(deltaTime, bulletSpawningController);
        enemyMovementController.update(deltaTime, enemySpawningController.getEnemyList());
        enemyMovementController.update(deltaTime, (List<Enemy>) (List<?>) bulletSpawnerSpawningController.getBulletSpawnerList());

        // Make Stage
        stageController.makeStages();
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

    public void startGodMode() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            player.setInvulnerable(!player.isInvulnerable());
            if (player.isInvulnerable()) {
                System.out.println("God Mode: Activated");
                godMode = true;
            } else {
                System.out.println("God Mode: Deactivated");
                godMode = false;
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.L) && !isSlowMode) {
            setIsSlowMode(true);  // Change the slow mode to true
            gameSpeed = 0.4f;   // Change the game speed to slow speed
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L) && isSlowMode) {
            // If the L key is just press and it is slow down mode
            setIsSlowMode(false); // Change slow mode to false
            gameSpeed = 1;      // Change the game speed to normal speed
        }

        return gameSpeed;
    }

    @Override
    public void update(Object object, String args) {
        if (object instanceof StageController) {
            if (args.equals("winingState")) {
                notifyGameObserver("winningState");
            }
        } else if (object instanceof Player) {
            if (args.equals("die")) {
                notifyGameObserver("losingState");
            }
        }
    }

    @Override
    public void attachGameObserver(GameObserver object) {
        myObservers.add(object);
    }

    @Override
    public void detachGameObserver(GameObserver object) {
        myObservers.remove(object);
    }

    @Override
    public void notifyGameObserver(String args) {
        for (int i = 0; i < this.myObservers.size(); i++) {
            this.myObservers.get(i).update(this, args);
        }
    }
}