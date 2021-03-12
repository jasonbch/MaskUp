package GameEngine;

import Entity.Enemy;
import Factories.EnemyMovementFactory;
import EnemyMovementPattern.EnemyMovementPattern;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {
    private final EnemyMovementFactory enemyMovementFactory = new EnemyMovementFactory();

    // Implement Singleton
    private static EnemyMovementController uniqueInstance = null;

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

    private EnemyMovementController() {
    }

    public void move(Enemy enemy, float deltaTime) {
        EnemyMovementPattern enemyMovementPattern;
        enemy.updateTimeAlive();

        if (enemy.getTimeAlive() >= enemy.getMaxLifespan()) {
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
