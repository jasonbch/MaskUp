package GameEngine.Stage;

import GameEngine.Movement.EnemyMovementController;
import GameEngine.Resource.GameResources;
import GameEngine.Spawning.EnemySpawningController;
import GameEngine.Time.TimeController;
import Objects.GameObject.Enemy.Covid;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.Enemy.Karen;
import Objects.GameObject.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * StageController class that implements Singleton.
 * The class can create stages for a game.
 */
public class StageController {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private static final TimeController timeController = TimeController.instance();
    private static final GameResources gameResources = GameResources.instance();
    private static final EnemyMovementController enemyMovementController = EnemyMovementController.instance();

    // Implement Singleton
    private static StageController uniqueInstance = null;
    private final Player player = Player.instance();
    // Stages duration
    public int stageBuffer = 5;
    public int stageOneStart = 10;
    private int stageOneDuration = 30;
    private int stageTwoDuration = 45;
    private int stageThreeDuration = 30;
    private int stageFourDuration = 60;
    private int stageOneEnd = stageOneStart + stageOneDuration;
    public int stageTwoStart = stageOneEnd + stageBuffer;
    private int stageTwoEnd = stageTwoStart + stageTwoDuration;
    public int stageThreeStart = stageTwoEnd + stageBuffer;
    private int stageThreeEnd = stageThreeStart + stageThreeDuration;
    public int stageFourStart = stageThreeEnd + stageBuffer;
    private int stageFourEnd = stageFourStart + stageFourDuration;
    // Final Boss
    private Enemy covid;
    // Mid Boss
    private Enemy karen;

    private StageController() {
        this.initialize();
    }

    /**
     * Return the instance of StageController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of StageController.
     */
    public static StageController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new StageController();
        }
        return uniqueInstance;
    }

    public Enemy getKaren() {
        return this.karen;
    }

    public Enemy getCovid() {
        return this.covid;
    }

    public int getStageOneEnd() {
        return stageOneEnd;
    }

    public int getStageFourEnd() {
        return stageFourEnd;
    }

    public int getStageThreeEnd() {
        return stageThreeEnd;
    }

    public int getStageTwoEnd() {
        return stageTwoEnd;
    }

    private List<Wave> waves = new ArrayList<>();

    private void initialize() {
        Wave wave1 = new Wave("Bat", 3, 5, "PatternOne", "TriangleTargetBulletFormation");
        Wave wave2 = new Wave("Bat", 3, 15, "PatternOne", "TriangleTargetBulletFormation");
        Wave wave3 = new Wave("Bat", 3, 25, "PatternOne", "TriangleTargetBulletFormation");

        Wave wave4 = new Wave("MurderHornet", 3, 10, "PatternThree", "DownwardLinearBulletFormation");
        Wave wave5 = new Wave("MurderHornet", 3, 20, "PatternThree", "DownwardLinearBulletFormation");
        Wave wave6 = new Wave("MurderHornet", 3, 30, "PatternThree", "DownwardLinearBulletFormation");

        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);
        waves.add(wave4);
        waves.add(wave5);
        waves.add(wave6);

        Wave waveMidBoss = new Wave("Karen", 1, 35, "PatternTwo", "TriangleTargetBulletFormation");
        Wave waveBoss = new Wave("Covid", 1, 120, "PatternOne", "TriangleTargetBulletFormation");

        waves.add(waveMidBoss);
        waves.add(waveBoss);
    }

    public void makeStages() {
        this.karen = findEnemy("Karen");
        this.covid = findEnemy("Covid");
        changePlayerBulletType();

        for (Wave wave : waves) {
            if (timeController.getElapsedTime() == wave.getStartTime()) {
                wave.run();
            }
        }
    }

    private void changePlayerBulletType() {
        if (timeController.getElapsedTime() >= stageOneStart && timeController.getElapsedTime() <= getStageOneEnd()) {
            player.setBullet("Bullet");
        }
        if (timeController.getElapsedTime() >= stageTwoStart && timeController.getElapsedTime() <= getStageTwoEnd()) {
            player.setBullet("Mask");
        }
        if (timeController.getElapsedTime() >= stageThreeStart && timeController.getElapsedTime() <= getStageThreeEnd()) {
            player.setBullet("Bullet");
        }
        if (timeController.getElapsedTime() >= stageFourStart && timeController.getElapsedTime() <= getStageFourEnd()) {
            player.setBullet("Syringe");
        }
    }

    private Enemy findEnemy(String enemyType) {
        ListIterator<Enemy> iterator = enemySpawningController.getEnemyList().listIterator();
        while (iterator.hasNext()) {
            Enemy currentEnemy = iterator.next();
            if (enemyType.equals("Karen")) {
                if (currentEnemy instanceof Karen) {
                    return currentEnemy;
                }
            } else if (enemyType.equals("Covid")) {
                if (currentEnemy instanceof Covid) {
                    return currentEnemy;
                }
            }
        }
        return null;
    }


    /**
     * Fast forward the game to the start of stage three if the player
     * kills the mid boss before the stage two end.
     */
    private void fastForwardToStageThree() {
        if (this.karen != null) {
            if (this.stageThreeStart > (int) timeController.getElapsedTime() && this.karen.IsDone()) {
                this.stageThreeStart = (int) timeController.getElapsedTime();
            }
        }
    }
}