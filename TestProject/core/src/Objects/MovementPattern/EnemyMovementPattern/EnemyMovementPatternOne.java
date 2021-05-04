package Objects.MovementPattern.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.GameObject;

/**
 * The Objects.MovementPattern.EnemyMovementPattern that move the enemy left and right on the screen
 * in a sine wave pattern. If the enemy touches the side of the screen, the
 * enemy changes direction and continue moving.
 */
public class EnemyMovementPatternOne extends EnemyMovementPattern {

    @Override
    public String getName() {
        return "PatternOne";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {
        Enemy enemy = (Enemy) obj;
        enemy.notifyObservers();

        float val = enemy.getYAxis() + (float) (50 * Math.sin(enemy.getXPosition() * .5 * Math.PI / 80));
        enemy.setYPosition(val);

        if (enemy.isLeftOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() + 3);
        } else if (enemy.isRightOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() - 3);
        }

        enemy.setXPosition(enemy.getXPosition() + (enemy.getXMultiplier() * enemy.getSpeed() * deltaTime));
    }
}
