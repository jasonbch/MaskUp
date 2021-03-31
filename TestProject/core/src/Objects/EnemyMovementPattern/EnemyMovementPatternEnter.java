package Objects.EnemyMovementPattern;

import GameEngine.Movement.EnemyMovementController;
import Objects.GameObject.Enemy.Enemy;

/**
 * The Objects.EnemyMovementPattern that move the enemy to the top of the screen.
 */

public class EnemyMovementPatternEnter extends EnemyMovementPattern {

    private final EnemyMovementController enemyMovementController = EnemyMovementController.instance();

    @Override
    public String getName() {
        return "PatternEnter";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        enemy.moveDown(deltaTime);

        if (enemy.getYPosition() <= enemyMovementController.getEnemyRandomYMap().get(enemy)) {
            enemy.setIsSpawned(true);
        }
    }
}
