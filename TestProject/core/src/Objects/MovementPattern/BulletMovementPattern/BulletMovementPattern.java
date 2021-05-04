package Objects.MovementPattern.BulletMovementPattern;

import Objects.GameObject.GameObject;
import Objects.MovementPattern.MovementPattern;

/**
 * The Objects.MovementPattern.BulletMovementPattern class that move the bullet in a specific order.
 */
public abstract class BulletMovementPattern extends MovementPattern {
    /**
     * Return the name of the pattern.
     */
    public abstract String getName();

    /**
     * Move the game object.
     */
    public abstract void move(GameObject obj, float deltaTime);
}