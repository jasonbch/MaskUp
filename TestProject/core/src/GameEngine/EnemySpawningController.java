package GameEngine;

import Enemy.Enemy;
import Factories.EnemyFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * EnemySpawningController class that implements Singleton.
 * The class is in charged of spawning enemies. The class can
 * also create different type of enemies.
 */
public class EnemySpawningController {
    // TODO: Refactor words dimension
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();

    // Implement Singleton
    private static EnemySpawningController uniqueInstance = null;

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final LinkedList<Enemy> enemyList = new LinkedList<>();

    // Timing for spawning enemies
    private final long startTime = TimeUtils.millis();
    private long elapsedTime = 0;
    private long lastBatSpawnTime = 0;
    private long lastMurderHornetSpawnTime = 0;
    private long lastMidBossTime = 0;
    private long lastFinalBossTime = 0;
    private final int spawnGruntInterval = 5;

    // Checks to spawn only one boss
    private boolean isJustSpawnMidBoss = false;
    private boolean isJustSpawnFinalBoss = false;

    private final Random rand = new Random();

    /**
     * Return the instance of EnemySpawningController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of EnemySpawningController.
     */
    public static EnemySpawningController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EnemySpawningController();
        }

        return uniqueInstance;
    }

    private EnemySpawningController() {
    }

    public LinkedList<Enemy> getEnemyList() {
        return this.enemyList;
    }

    /**
     * Get start time for spawning.
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Set elapse time for spawning.
     */
    public void setElapsedTime(long time) {
        this.elapsedTime = time;
    }

    /**
     * TODO: Refactor into one function. May take a while.
     * Spawn bat in a wave. The enemies are spawned constantly
     * in an interval and during the given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnBatWave(long startTime, long endTime) {
        if (elapsedTime >= startTime && elapsedTime < endTime) {
            spawnBat();
        }
    }

    /**
     * TODO: Refactor into one function. May take a while.
     * Spawn murder hornet in a wave. The enemies are spawned constantly
     * in an interval and during the given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnMurderHornetWave(long startTime, long endTime) {
        if (elapsedTime >= startTime && elapsedTime < endTime) {
            spawnMurderHornet();
        }
    }

    /**
     * TODO: Refactor into one function. May take a while.
     * Spawn mid boss for a wave. The mid boss is spawned once
     * during the given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnMidBossWave(long startTime, long endTime) {
        if (elapsedTime >= startTime && elapsedTime < endTime) {
            if (!isJustSpawnMidBoss) {
                spawnMidBoss();
                isJustSpawnMidBoss = true;
            }
        }
    }

    /**
     * TODO: Refactor into one function. May take a while.
     * Spawn final boss for a wave. The boss is spawned once
     * during the given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnFinalBossWave(long startTime, long endTime) {
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
