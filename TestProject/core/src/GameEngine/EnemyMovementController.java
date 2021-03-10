package GameEngine;

import Enemy.Enemy;
import Factories.EnemyMovementFactory;
import EnemyMovementPattern.EnemyMovementPattern;

import java.util.Random;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {
    private final EnemyMovementFactory enemyMovementFactory = new EnemyMovementFactory();
    private Random rand = new Random();

    // Implement Singleton
    private static EnemyMovementController uniqueInstance = null;

    /**
     * Return the instance of EnemySpawningController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of EnemySpawningController.
     */
    public static EnemyMovementController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EnemyMovementController();
        }

        return uniqueInstance;
    }

    private EnemyMovementController() {
    }


    public void move(Enemy enemy, float deltaTime, Integer stage){
        EnemyMovementPattern enemyMovementPattern = null;
        enemy.updateTimeAlive();

        if (enemy.getTimeAlive() >= enemy.getMaxLifespan()) {
            enemyMovementPattern = enemyMovementFactory.create("PatternExit");
        } else if (!enemy.isSpawned()) {
            enemyMovementPattern = enemyMovementFactory.create("PatternEnter");
        } else {
            String enemyPattern = enemy.getPattern();
            enemyMovementPattern = enemyMovementFactory.create(enemyPattern);
        }

        enemyMovementPattern.Move(enemy, deltaTime);
    }
}
