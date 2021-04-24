package Objects;

import Objects.GameObject.GameObject;

public abstract class MovementPattern {

    /**
     * Return the name of the pattern.
     */
    public abstract String getName();

    /**
     * Move the game object.
     */
    public abstract void move(GameObject object, float deltaTime);


}
