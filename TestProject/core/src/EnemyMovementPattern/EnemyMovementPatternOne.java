package EnemyMovementPattern;

import GameEngine.Movement.EnemyMovementController;
import GameObject.Enemy.Enemy;

/**
 * The EnemyMovementPattern that move the enemy left and right on the screen
 * in a sine wave pattern. If the enemy touches the side of the screen, the
 * enemy changes direction and continue moving.
 */
public class EnemyMovementPatternOne extends EnemyMovementPattern {
    private final EnemyMovementController enemyMovementController = EnemyMovementController.instance();


    @Override
    public String getName() {
        return "PatternOne";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        float val = enemyMovementController.getEnemyRandomYMap().get(enemy) + (float) (50 * Math.sin(enemy.getXPosition() * .5 * Math.PI / 80));
        enemy.setYPosition(val);

        if (enemy.isLeftOfScreen()) {
            System.out.print("changing xmultiplier");
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() + 3);
        } else if (enemy.isRightOfScreen()) {
            System.out.println("changing xmultiplier");
            enemy.revertXMultiplier();
            enemy.setXPosition(enemy.getXPosition() - 3);
        }

        enemy.setXPosition(enemy.getXPosition() + (enemy.getXMultiplier() * enemy.getSpeed() * deltaTime));

    }
}
