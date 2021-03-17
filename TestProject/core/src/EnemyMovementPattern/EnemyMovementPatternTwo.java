package EnemyMovementPattern;

import Entity.Enemy;

/**
 * The EnemyMovementPattern that move the enemy left and right of the screen.
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
        if (enemy.isLeftOfScreen() || enemy.isRightOfScreen()) {
            System.out.println("changing xMultiplier");
            enemy.revertXMultiplier();
        }

        enemy.setxPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.getXMultiplier() * deltaTime);
    }
}
