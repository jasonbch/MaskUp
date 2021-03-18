package EnemyMovementPattern;

import Enemy.Enemy;

/**
 * The EnemyMovementPattern that move the enemy up and down. If the enemy touch
 * the top of the screen, the enemy changes direction and move down to the bottom
 * of the screen and vice versa.
 */
public class EnemyMovementPatternFour extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternFour";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        if (enemy.isLeftOfScreen() || enemy.isRightOfScreen()) {
            enemy.revertYMultiplier();
        }

        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * enemy.getYMultiplier() * deltaTime);
    }
}
