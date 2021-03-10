package GameEngine;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * StageController class that implements Singleton.
 * The class can create stages for a game.
 */
public class StageController {
    // Implement Singleton
    private static StageController uniqueInstance = null;

    // Stages duration
    private final int stageBuffer = 5;

    private final int stageOneDuration = 30;
    private final int stageTwoDuration = 45;
    private final int stageThreeDuration = 30;
    private final int stageFourDuration = 60;

    private final int stageOneStart = 0;
    private final int stageOneEnd = stageOneStart + stageOneDuration;
    private final int stageTwoStart = stageOneEnd + stageBuffer;
    private final int stageTwoEnd = stageTwoStart + stageTwoDuration;
    private final int stageThreeStart = stageTwoEnd + stageBuffer;
    private final int stageThreeEnd = stageThreeStart + stageThreeDuration;
    private final int stageFourStart = stageThreeEnd + stageBuffer;
    private final int stageFourEnd = stageFourStart + stageFourDuration;

    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();

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

    private StageController() {
    }

    public void makeStages() {
        // Set new elapsed time
        long newStartTime = enemySpawningController.getStartTime();
        enemySpawningController.setElapsedTime(TimeUtils.timeSinceMillis(newStartTime) / 1000);

        // Stage 1
        enemySpawningController.spawnBatWave(stageOneStart, stageOneEnd, "PatternOne");
        enemySpawningController.spawnMurderHornetWave(stageOneStart, stageOneEnd, "PatternOne");

        // Stage 2
        enemySpawningController.spawnMidBossWave(stageTwoStart, stageTwoEnd, "PatternTwo");

        // Stage 3
        enemySpawningController.spawnBatWave(stageThreeStart, stageThreeEnd, "PatternOne");
        enemySpawningController.spawnMurderHornetWave(stageThreeStart, stageThreeEnd, "PatternOne");

        // Stage 4
        enemySpawningController.spawnFinalBossWave(stageFourStart, stageFourEnd, "PatternThree");
    }
}