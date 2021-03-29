package Objects.BulletMovementPattern;

import Objects.GameObject.Ammo.Ammo;

/**
 * The Objects.BulletMovementPattern class that move the bullet in a specific order.
 */
public abstract class BulletMovementPattern {
    /**
     * Return the name of the pattern.
     */
    public abstract String getName();

    /**
     * Move the game object.
     */
    public abstract void move(Ammo ammo, float deltaTime, float xMultiplier, float yMultiplier);
}