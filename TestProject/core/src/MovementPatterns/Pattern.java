package MovementPatterns;

import Enemy.Enemy;

public abstract class Pattern {
    /**
     * Return the name of the pattern.
     */
    public abstract String getName();


    /**
     * Move the game object.
     */
    public abstract void Move(Enemy enemy, float deltaTime);

}
