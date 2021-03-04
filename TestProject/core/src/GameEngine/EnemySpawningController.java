package GameEngine;

import Enemy.Enemy;
import Enemy.EnemyFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;
import java.util.ListIterator;

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
    private int stageOneStart = 0;
    private int stageTwoStart = 30;
    private int stageThreeStart = 60;
    private int stageFourStart = 90;

    /**
     * Create an instance of the EnemySpawningController.
     */
    public EnemySpawningController() {
    }

    /**
     * Return the enemy list.
     *
     * @return  the enemy list.
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
     *      This is where the movement class will be used
     * Spawn enemies, mid boss, and final boss on the screen in different intervals.
     */
    public void spawnEnemies() {
        // Spawn enemies
        elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;

        if (elapsedTime >= stageOneStart && elapsedTime < stageTwoStart) {
            spawnBat();
            spawnMurderHornet();
        } else if (elapsedTime >= stageTwoStart && elapsedTime <= stageThreeStart) {
            spawnKaren();
        } else if (elapsedTime >= stageThreeStart && elapsedTime <= stageFourStart) {
            spawnBat();
            spawnMurderHornet();
        } else if (elapsedTime >= stageFourStart) {
            spawnCovid();
        }
    }

    private void spawnBat() {
        if (elapsedTime % 5 == 0 && elapsedTime != 0 && elapsedTime - lastBatSpawnTime > 1) {
            System.out.println("spawning enemies");
            spawnEnemies("Bat", WORLD_WIDTH/2 - 5, WORLD_HEIGHT);
            lastBatSpawnTime = elapsedTime;
        }
    }

    private void spawnMurderHornet(){
        if (elapsedTime % 5 == 0 && elapsedTime != 0 && elapsedTime - lastMurderHornetSpawnTime > 1) {
            System.out.println("spawning enemies");
            spawnEnemies("MurderHornet", WORLD_WIDTH/2, WORLD_HEIGHT);
            lastMurderHornetSpawnTime = elapsedTime;
        }
    }

    private void spawnKaren(){
        if (elapsedTime % 5 == 0 && elapsedTime != 0 && elapsedTime - lastMidBossTime > 1) {
            System.out.println("spawning mid boss");
            spawnMidBoss(WORLD_WIDTH/3, WORLD_HEIGHT);
            lastMidBossTime = elapsedTime;
        }
    }

    private void spawnCovid(){
        if (elapsedTime % 5 == 0 && elapsedTime != 0 && elapsedTime - lastFinalBossTime > 1) {
            System.out.println("spawning final boss");
            spawnFinalBoss(WORLD_WIDTH/3, WORLD_HEIGHT);
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
     * @param enemy the enemy's name
     * @param xPosition  the x position
     * @param yPosition  the y position
     */
    public void spawnEnemies(String enemy, int xPosition, int yPosition) {
        enemyList.add(enemyFactory.create(enemy, xPosition, yPosition));
    }

    /**
     * Spawn the mid boss at the given position.
     *
     * @param xPosition  the x position
     * @param yPosition  the y position
     */
    public void spawnMidBoss(int xPosition, int yPosition) {
        spawnEnemies("Karen", xPosition, yPosition);
    }

    /**
     * Spawn the final boss at the given position.
     *
     * @param xPosition  the x position
     * @param yPosition  the y position
     */
    public void spawnFinalBoss(int xPosition, int yPosition) {
        spawnEnemies("Covid", xPosition, yPosition);
    }
}
