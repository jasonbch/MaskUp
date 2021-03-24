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

    // Mid Boss
    private Enemy karen;

    // Final Boss
    private Enemy covid;

    private StageController() {

    }

    public int getStageOneEnd() {
        return stageOneEnd;
    }
    public int getStageFourEnd() {
        return stageFourEnd;
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
        this.karen = findEnemy("Karen");

        // Stage 3
        enemySpawningController.spawnBatWave(stageThreeStart, stageThreeEnd, "PatternOne");
        enemySpawningController.spawnMurderHornetWave(stageThreeStart, stageThreeEnd, "PatternOne");

        // Stage 4
        enemySpawningController.spawnFinalBossWave(stageFourStart, stageFourEnd, "PatternThree");
        this.covid = findEnemy("Covid");

        // Change pattern of Karen into PatternOne in the middle of stage 2


        changeMovementPatternOfMidBoss();

        int finalBossChangeMovementPatternTime = ((stageFourEnd - stageFourStart) / 2 + stageFourStart);
        changeMovementPatternOfFinalBoss(finalBossChangeMovementPatternTime, "PatternOne");
    }


    private void changeMovementPatternOfMidBoss() {

        createRound(karen, new Float[]{(float)0.5, (float).1, (float)0.5},
                new String[]{"CircularBulletFormation", "CircularBulletFormation", "CircularBulletFormation"},
                new String[]{"PatternOne", "PatternOne", "PatternOne"},
                "Karen", 1);


        createRound(karen, new Float[]{(float)0.5, (float).1, (float)0.5},
                new String[]{"TargetDownwardLinearBulletFormation", "TargetDownwardLinearBulletFormation", "TargetDownwardLinearBulletFormation"},
                new String[]{"PatternTwo", "PatternTwo", "PatternTwo"},
                "Karen", 2);


        createRound(karen, new Float[]{(float)0.5, (float).1, (float)0.5},
                new String[]{"FanBulletFormation", "CircularBulletFormation", "FanBulletFormation"},
                new String[]{"PatternOne", "PatternOne", "PatternOne"},
                "Karen", 3);


        createRound(karen, new Float[]{(float)0.1, (float)0.1, (float)0.1},
                new String[]{"FanBulletFormation", "CircularBulletFormation", "FanBulletFormation"},
                new String[]{"PatternTwo", "PatternTwo", "PatternTwo"},
                "Karen", 4);


        createRound(karen, new Float[]{(float).2, (float).2, (float).2},
                new String[]{"TargetDownwardLinearBulletFormation", "FanBulletFormation", "CircularBulletFormation"},
                        new String[]{"PatternTwo", "PatternTwo", "PatternTwo"},
                        "Karen", 5);


    }

    private void createRound(Enemy enemy, Float[] speeds, String[] bulletPatterns, String[] movePatterns, String type, int round){
        int[] roundTimes;
        if(type.equals("Karen")) {roundTimes = getMidBossRounds(round);}
        else {roundTimes = getMidBossRounds(round);}

            if(timeController.getElapsedTime() >= roundTimes[1] && timeController.getElapsedTime() <= roundTimes[2])
            {
                enemy.setMovingPattern(movePatterns[0]);
                if(timeController.getElapsedTime() == roundTimes[1])
                {
                    enemy.setTimeBetweenShot((speeds[0]));
                    enemy.setFormationPattern(bulletPatterns[0]);
                }
                karen.setMovingPattern(movePatterns[1]);
                if(timeController.getElapsedTime() == roundTimes[1] + roundTimes[0])
                {
                    enemy.setTimeBetweenShot((speeds[1]));
                    enemy.setFormationPattern(bulletPatterns[1]);
                }
                karen.setMovingPattern(movePatterns[2]);
                if(timeController.getElapsedTime() == roundTimes[2] - roundTimes[0])
                {
                    enemy.setTimeBetweenShot(speeds[2]);
                    enemy.setFormationPattern(bulletPatterns[2]);
                }
            }
        }


    private int[] getMidBossRounds(int round){
        int roundInterval = 3;
        int roundTime = stageTwoDuration/5;
        int round1Start = stageTwoStart;
        int round1End = round1Start + roundTime;
        int round2Start = round1End;
        int round2End = round2Start + roundTime;
        int round3Start = round2End;
        int round3End = round3Start + roundTime;
        int round4Start = round3End;
        int round4End = round4Start + roundTime;
        int round5Start = round4End;
        int round5End = round5Start + roundTime;
        switch(round)
        {
            case 1:
                return new int[]{roundInterval, round1Start, round1End};
            case 2:
                return new int[]{roundInterval, round2Start, round2End};
            case 3:
                return new int[]{roundInterval, round3Start, round3End};
            case 4:
                return new int[]{roundInterval, round4Start, round4End};
            case 5:
                return new int[]{roundInterval, round5Start, round5End};
            default:
                return null;
        }

    }



    private Enemy findEnemy(String enemyType)
    {
        ListIterator<Enemy> iterator = enemySpawningController.getEnemyList().listIterator();
        while (iterator.hasNext()) {
            Enemy currentEnemy = iterator.next();
            if(enemyType.equals("Karen"))
            {
                if (currentEnemy instanceof Karen) {
                    return currentEnemy;
                }
            }
            else if(enemyType.equals("Covid"))
            {
                if (currentEnemy instanceof Covid) {
                    return currentEnemy;
                }
            }

        }
        return null;
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
