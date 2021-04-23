package GameEngine.Spawning;

import GameEngine.Factory.EnemyFactory;
import GameEngine.Observer.GameObserver;
import GameEngine.Resource.GameResources;
import GameEngine.Score.ScoreController;
import GameEngine.Stage.StageController;
import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.*;

import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * EnemySpawningController class that implements Singleton.
 * The class is in charged of spawning enemies. The class can
 * also create different type of enemies.
 */
public class EnemySpawningController implements GameObserver {
    private static final GameResources gameResources = GameResources.instance();
    private static final ScoreController scoreController = ScoreController.instance();
    private static final BulletSpawnerSpawningController bulletSpawnerSpawningController = BulletSpawnerSpawningController.instance();

    // Implement Singleton
    private static final EnemySpawningController uniqueInstance = new EnemySpawningController();

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final Random rand = new Random();
    private CopyOnWriteArrayList enemyList = new CopyOnWriteArrayList<Enemy>();

    private EnemySpawningController() {
    }

    /**
     * Return the instance of EnemySpawningController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of EnemySpawningController.
     */
    public static EnemySpawningController instance() {
        return uniqueInstance;
    }

    public CopyOnWriteArrayList<Enemy> getEnemyList() {
        return this.enemyList;
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

        // Attach Observers
        concreteEnemy.attachGameObserver(this);
        concreteEnemy.attachGameObserver(scoreController);
        if (enemy.equals("Karen") || enemy.equals("Covid")) {
            concreteEnemy.attachGameObserver(StageController.instance());
        }

        // Add enemy to the list
        enemyList.add(concreteEnemy);

        // Add bullet spawner to the list
        bulletSpawnerSpawningController.getBulletSpawnerList().add(bulletSpawner);

        return concreteEnemy;
    }

    /**
     * Return the enemy with the given name from the enemy list.
     *
     * @param enemyName the enemy's name
     */
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

    @Override
    public void update(Object object, String args) {
        if (object instanceof Enemy) {
            if (args.equals("deleteEnemy")) {
                deleteEnemies((Enemy) object);
            }
        }
    }

    private void deleteEnemies(Enemy enemy) {
        enemyList.remove(enemy);
    }
}
