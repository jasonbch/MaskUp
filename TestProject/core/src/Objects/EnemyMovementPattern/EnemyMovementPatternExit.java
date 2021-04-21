package Objects.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;

/**
 * The Objects.EnemyMovementPattern that move the enemy to the top of the screen.
 */
public class EnemyMovementPatternExit extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternExit";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        enemy.notifyObservers();

        if (enemy.isLeftOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() + 3);
        }
        if (enemy.isRightOfScreen()) {
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() - 3);
        }
        enemy.setYPosition(enemy.getYPosition() + enemy.getSpeed() * deltaTime);
    }
}
