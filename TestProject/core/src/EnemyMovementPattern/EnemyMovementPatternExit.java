package EnemyMovementPattern;

import Enemy.Enemy;

/**
 * The EnemyMovementPattern that move the enemy to the top of the screen.
 */
public class EnemyMovementPatternExit extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternExit";
    }

    @Override
    public void Move(Enemy enemy, float deltaTime) {
        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * deltaTime);
    }
}
