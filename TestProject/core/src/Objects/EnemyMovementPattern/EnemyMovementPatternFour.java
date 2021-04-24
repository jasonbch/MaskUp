package Objects.EnemyMovementPattern;

import GameEngine.Movement.EnemyMovementController;
import Objects.GameObject.GameObject;

/**
 * The Objects.EnemyMovementPattern that move the enemy up and down. If the enemy touch
 * the top of the screen, the enemy changes direction and move down to the bottom
 * of the screen and vice versa.
 */
public class EnemyMovementPatternFour extends EnemyMovementPattern {
    private final EnemyMovementController enemyMovementController = EnemyMovementController.instance();

    @Override
    public String getName() {
        return "PatternFour";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {

    }
}

