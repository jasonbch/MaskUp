package EnemyMovementPattern;

import Enemy.Enemy;

/**
 * The EnemyMovementPattern that move the enemy diagonally in the screen.
 * If the enemy touches the side of the screen, the enemy switch direction
 * to the opposite side but still maintain vertical direction. If the
 * enemy touches the top or bottom of the screen, the enemy switches
 * vertical direction but still maintain horizontal direction.
 */
public class EnemyMovementPatternThree extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternThree";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        if (enemy.isLeftOfScreen() || enemy.isRightOfScreen()) {
            enemy.revertXMultiplier();
        }

        if (enemy.isLeftOfScreen() || enemy.isRightOfScreen()) {
            enemy.revertYMultiplier();
        }

        enemy.setxPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.getXMultiplier() * deltaTime);
        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * enemy.getYMultiplier() * deltaTime);
    }
}
