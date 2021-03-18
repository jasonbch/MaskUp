package GameEngine.Movement;

import EnemyMovementPattern.EnemyMovementFactory;
import EnemyMovementPattern.EnemyMovementPattern;
import GameEngine.Spawning.EnemySpawningController;
import GameObject.Enemy.Enemy;

import java.util.ListIterator;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {
    // Implement Singleton
    private static EnemyMovementController uniqueInstance = null;
    private final EnemyMovementFactory enemyMovementFactory = new EnemyMovementFactory();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();

    private EnemyMovementController() {
    }

    /**
     * Return the instance of EnemyMovementController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of EnemyMovementController.
     */
    public static EnemyMovementController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EnemyMovementController();
        }

        return uniqueInstance;
    }

    public void update(float deltaTime) {
        ListIterator<Enemy> iter2 = enemySpawningController.getEnemyList().listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();
            this.move(currEnemy, deltaTime);
        }
    }


    public void move(Enemy enemy, float deltaTime) {
        EnemyMovementPattern enemyMovementPattern;
        enemy.updateTimeAlive();

        if (enemy.getTimeAlive() >= enemy.getMaxLifeSpan()) {
            enemyMovementPattern = enemyMovementFactory.create("PatternExit");
        } else if (!enemy.isSpawned()) {
            enemyMovementPattern = enemyMovementFactory.create("PatternEnter");
        } else {
            String enemyPattern = enemy.getMovingPattern();
            enemyMovementPattern = enemyMovementFactory.create(enemyPattern);
        }

        enemyMovementPattern.move(enemy, deltaTime);
    }
}
