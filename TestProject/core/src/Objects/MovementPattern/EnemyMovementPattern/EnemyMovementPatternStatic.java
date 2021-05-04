package Objects.MovementPattern.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.GameObject;

/**
 * The Objects.MovementPattern.EnemyMovementPattern that move the enemy left and right on the screen
 * in a sine wave pattern. If the enemy touches the side of the screen, the
 * enemy changes direction and continue moving.
 */
public class EnemyMovementPatternStatic extends EnemyMovementPattern {

    @Override
    public String getName() {
        return "PatternStatic";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {
        Enemy enemy = (Enemy) obj;

        float xPos = enemy.getXPosition();
        float yPos = enemy.getYPosition();

        enemy.setXPosition(xPos);
        enemy.setYPosition(yPos);
        enemy.notifyObservers();
    }
}
