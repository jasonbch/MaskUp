package Objects.MovementPattern.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.GameObject;

/**
 * The Objects.MovementPattern.EnemyMovementPattern that move the enemy to the top of the screen.
 */

public class EnemyMovementPatternEnter extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternEnter";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {
        Enemy enemy = (Enemy) obj;
        enemy.notifyObservers();
        enemy.moveDown(deltaTime);

        if (enemy.getYPosition() <= enemy.getYAxis()) {
            enemy.setIsSpawned(true);
        }
    }
}
