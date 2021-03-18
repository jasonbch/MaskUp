package GameEngine;

import Entity.Enemy;
import Factories.EnemyFactory;

import com.badlogic.gdx.Gdx;

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
    //private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();


    // Implement Singleton
    private static EnemySpawningController uniqueInstance = null;

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final LinkedList<Enemy> enemyList = new LinkedList<>();

    // Timing for spawning enemies

    private static final GameController gameController = GameController.instance();
    private static final GameResources gameResources = GameResources.instance();
    private static final ScoreController scoreController = ScoreController.instance();

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
     * TODO: Refactor into one function. May take a while.
     * Spawn bat in a wave. The enemies are spawned constantly
     * in an interval and during the given start time and end time.
     *
     * @param startTime the start time.
     * @param endTime   the end time
     */
    public void spawnBatWave(long startTime, long endTime, String pattern) {
        if (gameController.getElapsedTime() >= startTime && gameController.getElapsedTime() < endTime) {
            spawnBat(pattern);
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
    public void spawnMurderHornetWave(long startTime, long endTime, String pattern) {
        if (gameController.getElapsedTime() >= startTime && gameController.getElapsedTime() < endTime) {
            spawnMurderHornet(pattern);
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
    public void spawnMidBossWave(long startTime, long endTime, String pattern) {
        if (gameController.getElapsedTime() >= startTime && gameController.getElapsedTime() < endTime) {
            if (!isJustSpawnMidBoss) {
                spawnMidBoss(pattern);
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
    public void spawnFinalBossWave(long startTime, long endTime, String pattern) {
        if (gameController.getElapsedTime() >= startTime && gameController.getElapsedTime() < endTime) {
            if (!isJustSpawnFinalBoss) {
                spawnFinalBoss(pattern);
                isJustSpawnFinalBoss = true;
            }
        }
    }

    private void spawnBat(String pattern) {
        int xPosition = rand.nextInt(gameResources.getScreenOneEnd() - 300) + 100;
        if (gameController.getElapsedTime() % spawnGruntInterval == 0 && gameController.getElapsedTime() != 0 && gameController.getElapsedTime() - lastBatSpawnTime > 1) {
            spawnEnemies("Bat", xPosition, WORLD_HEIGHT, pattern);
            lastBatSpawnTime = gameController.getElapsedTime();
        }
    }

    private void spawnMurderHornet(String pattern) {
        int xPosition = rand.nextInt(gameResources.getScreenOneEnd() - 300) + 100;
        if (gameController.getElapsedTime() % spawnGruntInterval == 0 && gameController.getElapsedTime() != 0 && gameController.getElapsedTime() - lastMurderHornetSpawnTime > 1) {
            spawnEnemies("MurderHornet", xPosition, WORLD_HEIGHT, pattern);
            lastMurderHornetSpawnTime = gameController.getElapsedTime();
        }
    }

    /**
     * Spawn the mid boss at the given position.
     */
    private void spawnMidBoss(String pattern) {
        int xPosition = rand.nextInt(gameResources.getScreenOneEnd() - 200) + 50;
        System.out.println("Karen x position: " + xPosition);
        if (gameController.getElapsedTime() != 0 && gameController.getElapsedTime() - lastMidBossTime > 1) {
            spawnEnemies("Karen", xPosition, WORLD_HEIGHT, pattern);
            lastMidBossTime = gameController.getElapsedTime();
        }
    }

    /**
     * Spawn the final boss at the given position.
     */
    private void spawnFinalBoss(String pattern) {
        int xPosition = rand.nextInt(gameResources.getScreenOneEnd() - 200) + 100;
        if (gameController.getElapsedTime() != 0 && gameController.getElapsedTime() - lastFinalBossTime > 1) {
            spawnEnemies("Covid", xPosition, WORLD_HEIGHT, pattern);
            lastFinalBossTime = gameController.getElapsedTime();
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
            else if (currEnemy.IsDone())
            {
                scoreController.addScore(currEnemy);
                System.out.println("The current score is: " + scoreController.getScore());
                iter2.remove();
            }
            else if (currEnemy.getHealth() <= 0)
            {
                currEnemy.setIsDone();
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
    private void spawnEnemies(String enemy, int xPosition, int yPosition, String pattern) {
        enemyList.add(enemyFactory.create(enemy, xPosition, yPosition, pattern));
    }
}
