package EnemyMovementPattern;

import Entity.Enemy;

/**
 * The EnemyMovementPattern that move the enemy left and right on the screen
 * in a sine wave pattern. If the enemy touches the side of the screen, the
 * enemy changes direction and continue moving.
 */
public class EnemyMovementPatternOne extends EnemyMovementPattern {

    @Override
    public String getName() {
        return "PatternOne";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        float val = 600 + (float) (50 * Math.sin(enemy.getXPosition() * .5 * Math.PI / 80));
        enemy.setyPosition(val);

        if (enemy.isLeftOfScreen() || enemy.isRightOfScreen()){
            System.out.println("changing x multiplier");
            enemy.revertXMultiplier();
        }

        enemy.setxPosition(enemy.getXPosition() + (enemy.getXMultiplier() * enemy.getSpeed() * deltaTime));
    }
}
