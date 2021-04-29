package Objects.EnemyMovementPattern;

import Objects.GameObject.GameObject;
import Objects.MovementPattern;

/**
 * The Objects.EnemyMovementPattern class that move the enemy in a specific order.
 */
public abstract class EnemyMovementPattern extends MovementPattern {
    /**
     * Return the name of the pattern.
     */
    public abstract String getName();

    /**
     * Move the game object.
     */
    public abstract void move(GameObject obj, float deltaTime);
}
