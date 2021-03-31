package GameEngine.Spawning;

import GameEngine.Movement.EnemyMovementController;
import GameEngine.Resource.GameResources;
import GameEngine.Time.TimeController;
import Objects.GameObject.Enemy.Covid;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.Enemy.Karen;
import Objects.GameObject.Player;

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

    // figure out time manipulation
    public void makeStages() {

        // Set new elapsed time
        //long newStartTime = enemySpawningController.getStartTime();
        //enemySpawningController.setElapsedTime(TimeUtils.timeSinceMillis(newStartTime) / 1000);

        // Stage 1
        enemySpawningController.spawnBatWave(stageOneStart, stageOneEnd, "PatternOne");
        enemySpawningController.spawnMurderHornetWave(stageOneStart, stageOneEnd, "PatternThree");


        // Stage 2
        enemySpawningController.spawnMidBossWave(stageTwoStart, stageTwoEnd, "PatternTwo");
        this.karen = findEnemy("Karen");

        this.fastForwardToStageThree();

        // Stage 3
        enemySpawningController.spawnBatWave(stageThreeStart, stageThreeEnd, "PatternOne");
        enemySpawningController.spawnMurderHornetWave(stageThreeStart, stageThreeEnd, "PatternOne");

        // Stage 4
        enemySpawningController.spawnFinalBossWave(stageFourStart, stageFourEnd, "PatternOne");
        this.covid = findEnemy("Covid");

        changeMovementPatternOfMidBoss();

        changeMovementPatternOfFinalBoss();

        changePlayerBulletType();
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

    private void changeMovementPatternOfMidBoss() {

        createRound(karen, new Float[]{(float) 0.5, (float) .1, (float) 0.5},
                new String[]{"CircularBulletFormation", "CircularBulletFormation", "CircularBulletFormation"},
                new String[]{"PatternOne", "PatternTwo", "PatternFour"},
                "Karen", 1);


        createRound(karen, new Float[]{(float) 0.5, (float) .1, (float) 0.5},
                new String[]{"TargetDownwardLinearBulletFormation", "TargetDownwardLinearBulletFormation", "TargetDownwardLinearBulletFormation"},
                new String[]{"PatternFour", "PatternTwo", "PatternTwo"},
                "Karen", 2);


        createRound(karen, new Float[]{(float) 0.5, (float) .1, (float) 0.5},
                new String[]{"FanBulletFormation", "CircularBulletFormation", "FanBulletFormation"},
                new String[]{"PatternFour", "PatternOne", "PatternOne"},
                "Karen", 3);


        createRound(karen, new Float[]{(float) 0.1, (float) 0.1, (float) 0.1},
                new String[]{"FanBulletFormation", "CircularBulletFormation", "FanBulletFormation"},
                new String[]{"PatternFour", "PatternTwo", "PatternTwo"},
                "Karen", 4);


        createRound(karen, new Float[]{(float) .2, (float) .2, (float) .2},
                new String[]{"TargetDownwardLinearBulletFormation", "FanBulletFormation", "CircularBulletFormation"},
                new String[]{"PatternFour", "PatternOne", "PatternOne"},
                "Karen", 5);


    }

    private void changeMovementPatternOfFinalBoss() {

        createRound(covid, new Float[]{(float) 0.5, (float) .9, (float) 0.5},
                new String[]{"TargetDownwardLinearBulletFormation", "CircularBulletFormation", "TargetDownwardLinearBulletFormation"},
                new String[]{"PatternTwo", "PatternFour", "PatternTwo"},
                "Covid", 1);


        createRound(covid, new Float[]{(float) 0.8, (float) .8, (float) 0.8},
                new String[]{"FanBulletFormation", "CircularBulletFormation", "FanBulletFormation"},
                new String[]{"PatternFour", "PatternTwo", "PatternTwo"},
                "Covid", 2);


        createRound(covid, new Float[]{(float) 0.8, (float) .9, (float) 0.8},
                new String[]{"FanBulletFormation", "FanBulletFormation", "TargetDownwardLinearBulletFormation"},
                new String[]{"PatternFour", "PatternOne", "PatternOne"},
                "Covid", 3);


        createRound(covid, new Float[]{(float) 0.8, (float) 0.8, (float) 0.8},
                new String[]{"FanBulletFormation", "CircularBulletFormation", "FanBulletFormation"},
                new String[]{"PatternFour", "PatternTwo", "PatternTwo"},
                "Covid", 4);


        createRound(covid, new Float[]{(float) .8, (float) .9, (float) .9},
                new String[]{"CircularBulletFormation", "FanBulletFormation", "CircularBulletFormation"},
                new String[]{"PatternFour", "PatternOne", "PatternOne"},
                "Covid", 5);
    }

    private int[] getFinalBossRounds(int round) {
        int roundInterval = 4;
        int roundTime = stageFourDuration / 5;
        int round1Start = stageFourStart;
        int round1End = round1Start + roundTime;
        int round2Start = round1End;
        int round2End = round2Start + roundTime;
        int round3Start = round2End;
        int round3End = round3Start + roundTime;
        int round4Start = round3End;
        int round4End = round4Start + roundTime;
        int round5Start = round4End;
        int round5End = round5Start + roundTime;
        int round6Start = round5End;
        int round6End = round6Start + roundTime;
        switch (round) {
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
            case 6:
                return new int[]{roundInterval, round6Start, round6End};
            default:
                return null;
        }
    }


    private void createRound(Enemy enemy, Float[] speeds, String[] bulletPatterns, String[] movePatterns, String type, int round) {
        if (enemy != null) {
            int[] roundTimes;
            if (type.equals("Karen")) {
                roundTimes = getMidBossRounds(round);
            } else {
                roundTimes = getFinalBossRounds(round);
            }
            if (timeController.getElapsedTime() >= roundTimes[1] && timeController.getElapsedTime() <= roundTimes[2]) {

                if (timeController.getElapsedTime() == roundTimes[1]) {
                    setBossLocation(round, enemy);
                    enemy.setMovingPattern(movePatterns[0]);
                    enemy.setTimeBetweenShot((speeds[0]));
                    enemy.setFormationPattern(bulletPatterns[0]);
                }
                if (timeController.getElapsedTime() == roundTimes[1] + roundTimes[0]) {
                    setBossLocation(round, enemy);
                    enemy.setMovingPattern(movePatterns[1]);
                    enemy.setTimeBetweenShot((speeds[1]));
                    enemy.setFormationPattern(bulletPatterns[1]);
                }
                if (timeController.getElapsedTime() == roundTimes[2] - roundTimes[0]) {
                    setBossLocation(round, enemy);
                    enemy.setMovingPattern(movePatterns[2]);
                    enemy.setTimeBetweenShot(speeds[2]);
                    enemy.setFormationPattern(bulletPatterns[2]);
                }
            }
        }
    }


    private void setBossLocation(int round, Enemy enemy) {
        switch (round) {
            case 1:
                enemyMovementController.setNewEnemyPosition(enemy, gameResources.getScreenOneWidth() / 2, 850);
                break;
            case 2:
                enemyMovementController.setNewEnemyPosition(enemy, 90, gameResources.getWorldHeight() - gameResources.getWorldHeight() / 4);
                break;
            case 3:
                enemyMovementController.setNewEnemyPosition(enemy, 490, gameResources.getWorldHeight() - gameResources.getWorldHeight() / 4);
                break;
            case 4:
                enemyMovementController.setNewEnemyPosition(enemy, gameResources.getScreenOneWidth() / 2, 850);
                break;
            case 5:
                enemyMovementController.setNewEnemyPosition(enemy, 90, gameResources.getWorldHeight() - gameResources.getWorldHeight() / 4);
                break;
            case 6:
                enemyMovementController.setNewEnemyPosition(enemy, 490, gameResources.getWorldHeight() - gameResources.getWorldHeight() / 4);
                break;
        }
    }


    private int[] getMidBossRounds(int round) {
        int roundInterval = 3;
        int roundTime = stageTwoDuration / 5;
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
        switch (round) {
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