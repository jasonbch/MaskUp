package EnemyMovementPattern;

import Enemy.Enemy;

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
        if (enemy.isLeftOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setxPosition(enemy.getXPosition() + 3);
        }
        else if(enemy.isRightOfScreen()){
            enemy.revertXMultiplier();
            enemy.setxPosition(enemy.getXPosition() - 3);
        }

        enemy.setxPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.getXMultiplier() * deltaTime);
    }
}
