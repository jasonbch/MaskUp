package GameEngine;

import Enemy.Enemy;
import Factories.EnemyFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;


/**
 * EnemySpawningController class that in charge of spawning enemies.
 * The class can create different type of enemies. The class can also
 * create waves of enemies.
 */
public class EnemySpawningController {
    // TODO: Refactor words dimension
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final LinkedList<Enemy> enemyList = new LinkedList<>();

    private long startTime = TimeUtils.millis();
    private long elapsedTime = 0;

    private long lastBatSpawnTime = 0;
    private long lastMurderHornetSpawnTime = 0;
    private long lastMidBossTime = 0;
    private long lastFinalBossTime = 0;

    // Variable for stages
    private final int spawnGruntInterval = 5;
    private final int stageBuffer = 5;

    // Stages duration
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


    private boolean isJustSpawnMidBoss = false;
    private boolean isJustSpawnFinalBoss = false;

    private Random rand = new Random();

    /**
     * Create an instance of the EnemySpawningController.
     */
    public EnemySpawningController() {
    }

    /**
     * Return the enemy list.
     *
     * @return the enemy list.
     */
    public LinkedList<Enemy> getEnemyList() {
        return this.enemyList;
    }

    /**
     * Set start time for spawning.
     */
    public void setStartTime() {
        this.startTime = TimeUtils.millis();
    }

    /**
     * TODO: Introduce interval variables.
     * This is where the movement class will be used
     * Spawn enemies, mid boss, and final boss on the screen in different intervals.
     */
    public void spawnEnemies() {
        // Spawn enemies
        elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;

        // Stage 1
        spawnGruntStage(stageOneStart, stageOneEnd);

        // Stage 2
        spawnMidBossStage(stageTwoStart, stageTwoEnd);

        // Stage 3
        spawnGruntStage(stageThreeStart, stageThreeEnd);

        // Stage 4
        spawnFinalBossStage(stageFourStart, stageFourEnd);
    }

    /**
     * TODO: Refactor into one function. May take a while.
     * Spawn enemies for grunt stage one of the game. The enemies
     * are spawned constantly in an interval and during the
     * given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnGruntStage(long startTime, long endTime) {
        if (elapsedTime >= startTime && elapsedTime < endTime) {
            spawnBat();
            spawnMurderHornet();
        }
    }

    /**
     * TODO: Refactor into one function. May take a while.
     * Spawn mid boss for mid boss stage of the game. The boss
     * is spawned once during the given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnMidBossStage(long startTime, long endTime) {
        if (elapsedTime >= startTime && elapsedTime < endTime) {
            if (!isJustSpawnMidBoss) {
                spawnMidBoss();
                isJustSpawnMidBoss = true;
            }
        }
    }

    /**
     * TODO: Refactor into one function. May take a while.
     * Spawn mid boss for final boss stage of the game. The boss
     * is spawned once during the given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnFinalBossStage(long startTime, long endTime) {
        if (elapsedTime >= startTime && elapsedTime < endTime) {
            if (!isJustSpawnFinalBoss) {
                spawnFinalBoss();
                isJustSpawnFinalBoss = true;
            }
        }
    }

    private void spawnBat() {
        int xPosition = rand.nextInt(WORLD_WIDTH - 300) + 100;
        if (elapsedTime % spawnGruntInterval == 0 && elapsedTime != 0 && elapsedTime - lastBatSpawnTime > 1) {
            spawnEnemies("Bat", xPosition, WORLD_HEIGHT);
            lastBatSpawnTime = elapsedTime;
        }
    }

    private void spawnMurderHornet() {
        int xPosition = rand.nextInt(WORLD_WIDTH - 300) + 100;
        if (elapsedTime % spawnGruntInterval == 0 && elapsedTime != 0 && elapsedTime - lastMurderHornetSpawnTime > 1) {
            spawnEnemies("MurderHornet", xPosition, WORLD_HEIGHT);
            lastMurderHornetSpawnTime = elapsedTime;
        }
    }

    /**
     * Spawn the mid boss at the given position.
     */
    private void spawnMidBoss() {
        int xPosition = rand.nextInt(WORLD_WIDTH - 200) + 100;
        if (elapsedTime != 0 && elapsedTime - lastMidBossTime > 1) {
            spawnEnemies("Karen", xPosition, WORLD_HEIGHT);
            lastMidBossTime = elapsedTime;
        }
    }

    /**
     * Spawn the final boss at the given position.
     */
    private void spawnFinalBoss() {
        int xPosition = rand.nextInt(WORLD_WIDTH - 200) + 100;
        if (elapsedTime != 0 && elapsedTime - lastFinalBossTime > 1) {
            spawnEnemies("Covid", xPosition, WORLD_HEIGHT);
            lastFinalBossTime = elapsedTime;
        }
    }

    /**
     * Delete the enemies if they got out of the screen.
     */
    public void deleteEnemies() {
        ListIterator<Enemy> iter2 = enemyList.listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();

            if (currEnemy.getYPosition() > WORLD_HEIGHT) {
                iter2.remove();
            }
        }
    }

    /**
     * Spawn a given enemy at the given position.
     *
     * @param enemy     the enemy's name
     * @param xPosition the x position
     * @param yPosition the y position
     */
    private void spawnEnemies(String enemy, int xPosition, int yPosition) {
        enemyList.add(enemyFactory.create(enemy, xPosition, yPosition));
    }
}
