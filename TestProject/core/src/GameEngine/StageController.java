package GameEngine;

import GameEngine.Spawning.EnemySpawningController;
import GameObject.Enemy.Covid;
import GameObject.Enemy.Enemy;
import GameObject.Enemy.Karen;

import java.util.ListIterator;

/**
 * StageController class that implements Singleton.
 * The class can create stages for a game.
 */
public class StageController {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private static final TimeController timeController = TimeController.instance();

    // Implement Singleton
    private static StageController uniqueInstance = null;

    // Stages duration
    public final int stageBuffer = 5;
    public final int stageOneStart = 10;
    private final int stageOneDuration = 30;
    private final int stageTwoDuration = 45;
    private final int stageThreeDuration = 30;
    private final int stageFourDuration = 60;
    private final int stageOneEnd = stageOneStart + stageOneDuration;
    public final int stageTwoStart = stageOneEnd + stageBuffer;
    private final int stageTwoEnd = stageTwoStart + stageTwoDuration;
    public final int stageThreeStart = stageTwoEnd + stageBuffer;
    private final int stageThreeEnd = stageThreeStart + stageThreeDuration;
    public final int stageFourStart = stageThreeEnd + stageBuffer;
    private final int stageFourEnd = stageFourStart + stageFourDuration;

    private StageController() {
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

    // figure out time manipulation
    public void makeStages() {
        // Set new elapsed time
        //long newStartTime = enemySpawningController.getStartTime();
        //enemySpawningController.setElapsedTime(TimeUtils.timeSinceMillis(newStartTime) / 1000);

        // Stage 1
        enemySpawningController.spawnBatWave(stageOneStart, stageOneEnd, "PatternFour");
        enemySpawningController.spawnMurderHornetWave(stageOneStart, stageOneEnd, "PatternOne");

        // Stage 2
        enemySpawningController.spawnMidBossWave(stageTwoStart, stageTwoEnd, "PatternTwo");

        // Stage 3
        enemySpawningController.spawnBatWave(stageThreeStart, stageThreeEnd, "PatternOne");
        enemySpawningController.spawnMurderHornetWave(stageThreeStart, stageThreeEnd, "PatternOne");

        // Stage 4
        enemySpawningController.spawnFinalBossWave(stageFourStart, stageFourEnd, "PatternThree");

        // Change pattern of Karen into PatternOne in the middle of stage 2
        int midBossChangeMovementPatternTime = ((stageTwoEnd - stageTwoStart) / 2 + stageTwoStart);
        changeMovementPatternOfMidBoss(midBossChangeMovementPatternTime, "PatternOne");

        int finalBossChangeMovementPatternTime = ((stageFourEnd - stageFourStart) / 2 + stageFourStart);
        changeMovementPatternOfFinalBoss(finalBossChangeMovementPatternTime, "PatternOne");
    }

    private void changeMovementPatternOfMidBoss(int time, String pattern) {
        if (timeController.getElapsedTime() == time) {
            ListIterator<Enemy> iterator = enemySpawningController.getEnemyList().listIterator();

            while (iterator.hasNext()) {
                Enemy currentEnemy = iterator.next();

                if (currentEnemy instanceof Karen) {
                    currentEnemy.setMovingPattern(pattern);
                }
            }
        }
    }

    private void changeMovementPatternOfFinalBoss(int time, String pattern) {
        if (timeController.getElapsedTime() == time) {
            ListIterator<Enemy> iterator = enemySpawningController.getEnemyList().listIterator();

            while (iterator.hasNext()) {
                Enemy currentEnemy = iterator.next();

                if (currentEnemy instanceof Covid) {
                    currentEnemy.setMovingPattern(pattern);
                }
            }
        }
    }

    private void changeBulletFormationOfEnemy(int time, String pattern, String type) {
        if (timeController.getElapsedTime() == time) {
            ListIterator<Enemy> iterator = enemySpawningController.getEnemyList().listIterator();
            while (iterator.hasNext()) {
                Enemy currentEnemy = iterator.next();
                if (currentEnemy.getName() == type) {
                    currentEnemy.setFormationPattern(pattern);
                }
            }
        }
    }
}
