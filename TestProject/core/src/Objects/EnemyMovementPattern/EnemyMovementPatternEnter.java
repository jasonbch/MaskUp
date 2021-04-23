package Objects.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;

/**
 * The Objects.EnemyMovementPattern that move the enemy to the top of the screen.
 */

public class EnemyMovementPatternEnter extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternEnter";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        enemy.moveDown(deltaTime);

        if (enemy.getYPosition() <= enemy.getYAxis()) {
            enemy.setIsSpawned(true);
        }
    }
}
