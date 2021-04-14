package GameEngine.Spawning;

import GameEngine.Factory.EnemyFactory;
import GameEngine.Resource.GameResources;
import GameEngine.Score.ScoreController;
import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.*;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * EnemySpawningController class that implements Singleton.
 * The class is in charged of spawning enemies. The class can
 * also create different type of enemies.
 */
public class EnemySpawningController {
    private static final GameResources gameResources = GameResources.instance();
    private static final ScoreController scoreController = ScoreController.instance();
    private static final BulletSpawnerSpawningController bulletSpawnerSpawningController = BulletSpawnerSpawningController.instance();

    // Implement Singleton
    private static EnemySpawningController uniqueInstance = null;

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final LinkedList<Enemy> enemyList = new LinkedList<>();
    private final Random rand = new Random();

    private EnemySpawningController() {
    }

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

    public LinkedList<Enemy> getEnemyList() {
        return this.enemyList;
    }

    /**
     * Delete the enemies if they got out of the screen.
     */
    public void deleteEnemies() {
        ListIterator<Enemy> iter2 = enemyList.listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();

            if (currEnemy.getYPosition() > gameResources.getWorldHeight()) {
                iter2.remove();
            } else if (currEnemy.isDone()) {
                scoreController.addScore(currEnemy);
                iter2.remove();
            } else if (currEnemy.getMaxHealth() <= 0) {
                currEnemy.setIsDone();
            }
        }
    }

    /**
     * Spawn a given enemy with the given pattern
     *
     * @param enemy   the enemy's name
     * @param pattern the movement pattern of the enemy
     */
    public Enemy spawnEnemies(String enemy, String pattern) {
        int xPosition = rand.nextInt(gameResources.getScreenOneEnd() - 200) + 100;
        int yPosition = gameResources.getWorldHeight();
        Enemy concreteEnemy = enemyFactory.create(enemy, xPosition, yPosition, pattern);
        BulletSpawner bulletSpawner = bulletSpawnerSpawningController.addSpawner(concreteEnemy);

        // Make the bulletSpawner observe the enemy
        concreteEnemy.addObserver(bulletSpawner);
        float randomY = rand.nextInt(300) + 500;
        concreteEnemy.setYAxis(randomY);

        // Add enemy to the list
        enemyList.add(concreteEnemy);

        // Add bullet spawner to the list
        bulletSpawnerSpawningController.getBulletSpawnerList().add(bulletSpawner);

        return concreteEnemy;
    }

    public Enemy findEnemy(String enemyName) {
        ListIterator<Enemy> iterator = this.getEnemyList().listIterator();
        while (iterator.hasNext()) {
            Enemy currentEnemy = iterator.next();
            if (enemyName.equals("Karen")) {
                if (currentEnemy instanceof Karen) {
                    return currentEnemy;
                }
            } else if (enemyName.equals("Covid")) {
                if (currentEnemy instanceof Covid) {
                    return currentEnemy;
                }
            } else if (enemyName.equals("Bat")) {
                if (currentEnemy instanceof Bat) {
                    return currentEnemy;
                }
            } else if (enemyName.equals("MurderHornet")) {
                if (currentEnemy instanceof MurderHornet) {
                    return currentEnemy;
                }
            }
        }
        return null;
    }
}
