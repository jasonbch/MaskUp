package GameEngine.Movement;

import GameEngine.Factory.EnemyMovementFactory;
import Objects.EnemyMovementPattern.EnemyMovementPattern;
import Objects.GameObject.Enemy.Enemy;
import com.badlogic.gdx.math.GridPoint2;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {
    // Implement Singleton
    private static EnemyMovementController uniqueInstance = null;
    private final EnemyMovementFactory enemyMovementFactory = new EnemyMovementFactory();
    private final Random rand = new Random();

    // hash map for random spawning y values & position tracking for pattern 4
    private final HashMap<Enemy, Integer> enemyRandomYMap = new HashMap<>();

    private final HashMap<Enemy, GridPoint2> enemyPositionMap = new HashMap<>();

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

    public void update(float deltaTime, List<Enemy> enemyList) {
        ListIterator<Enemy> iter2 = enemyList.listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();

            // check if enemy is in the list
            if (! enemyRandomYMap.containsKey(currEnemy)) {
                addRandomY(currEnemy);
            }
            this.move(currEnemy, deltaTime);
        }
    }

    public void move(Enemy enemy, float deltaTime) {
        EnemyMovementPattern enemyMovementPattern;
        enemy.updateTimeAlive();

        if (enemy.getTimeAlive() >= enemy.getMaxLifeSpan()) {
            enemyMovementPattern = enemyMovementFactory.create("PatternExit");
        } else if (! enemy.isSpawned()) {
            enemyMovementPattern = enemyMovementFactory.create("PatternEnter");
        } else {
            String enemyPattern = enemy.getMovingPattern();
            enemyMovementPattern = enemyMovementFactory.create(enemyPattern);
        }

        enemyMovementPattern.move(enemy, deltaTime);
    }

    public HashMap<Enemy, Integer> getEnemyRandomYMap() {
        return this.enemyRandomYMap;
    }

    public HashMap<Enemy, GridPoint2> getEnemyPositionMap() {
        return this.enemyPositionMap;
    }

    public void setNewEnemyPosition(Enemy enemy, int newX, int newY) {
        GridPoint2 newPoint = new GridPoint2(newX, newY);
        enemyPositionMap.put(enemy, newPoint);
        enemyRandomYMap.put(enemy, newY);
        //System.out.println(enemyRandomYMap.get(enemy));
    }

    public void addRandomY(Enemy enemy) {
        // random y value between 300 & 800
        int randomY = rand.nextInt(300) + 500;
        enemyRandomYMap.put(enemy, randomY);
    }
}
