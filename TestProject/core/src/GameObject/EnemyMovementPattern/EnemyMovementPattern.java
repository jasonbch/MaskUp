package GameObject.EnemyMovementPattern;

import GameObject.Enemy.Enemy;

/**
 * The GameObject.EnemyMovementPattern class that move the enemy in a specific order.
 */
public abstract class EnemyMovementPattern {
    /**
     * Return the name of the pattern.
     */
    public abstract String getName();

    /**
     * Move the game object.
     */
    public abstract void move(Enemy enemy, float deltaTime);

}
