package Objects.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;

/**
 * The Objects.EnemyMovementPattern that move the enemy left and right of the screen.
 * If the enemy touches the side of the screen, the enemy switches horizontal
 * direction and continue moving.
 */
public class EnemyMovementPatternTwo extends EnemyMovementPattern {

    @Override
    public String getName() {
        return "PatternTwo";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        if (enemy.isLeftOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() + 3);
        } else if (enemy.isRightOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() - 3);
        }

        enemy.setXPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.getXMultiplier() * deltaTime);
    }
}
