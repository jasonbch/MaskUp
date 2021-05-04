package GameEngine.Movement;

import GameEngine.Factory.EnemyMovementFactory;
import GameEngine.Factory.MovementPatternFactory;
import Objects.GameObject.Enemy.Enemy;
import Objects.MovementPattern.MovementPattern;

import java.util.List;
import java.util.ListIterator;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {
    // Implement Singleton
    private static final EnemyMovementController uniqueInstance = new EnemyMovementController();
    private final MovementPatternFactory enemyMovementFactory = new EnemyMovementFactory();

    private EnemyMovementController() {
    }

    /**
     * Return the instance of EnemyMovementController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of EnemyMovementController.
     */
    public static EnemyMovementController instance() {
        return uniqueInstance;
    }

    public void update(float deltaTime, List<Enemy> enemyList) {
        synchronized(enemyList) {
            ListIterator<Enemy> iter = enemyList.listIterator();
            while (iter.hasNext()) {
                Enemy currEnemy = iter.next();

                this.move(currEnemy, deltaTime);
            }
        }
    }

    public void move(Enemy enemy, float deltaTime) {
        MovementPattern enemyMovementPattern;
        enemy.updateCurrentTimeAlive();

        if (enemy.getCurrentTimeAlive() >= enemy.getMaxTimeAlive()) {
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
