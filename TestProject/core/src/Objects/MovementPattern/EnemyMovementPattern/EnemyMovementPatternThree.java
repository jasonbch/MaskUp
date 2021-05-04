package Objects.MovementPattern.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.GameObject;

/**
 * The Objects.MovementPattern.EnemyMovementPattern that move the enemy diagonally in the screen.
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
    public void move(GameObject obj, float deltaTime) {
        Enemy enemy = (Enemy) obj;
        enemy.notifyObservers();

        if (enemy.isLeftOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() + 3);
        } else if (enemy.isRightOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() - 3);
        }

        if (enemy.isAboveScreen()) {
            enemy.revertYMultiplier();
            enemy.setYPosition(enemy.getYPosition() - 3);
        } else if (enemy.getYPosition() <= 400) {
            enemy.revertYMultiplier();
            enemy.setYPosition(enemy.getYPosition() + 3);
        }

        enemy.setXPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.getXMultiplier() * deltaTime);
        enemy.setYPosition(enemy.getYPosition() + enemy.getSpeed() * enemy.getYMultiplier() * deltaTime);
    }
}
