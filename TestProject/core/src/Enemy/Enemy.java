package Enemy;

import Entity.Entity;
import com.badlogic.gdx.math.GridPoint2;

/**
 * The Enemy abstract class that extends from Entity that can move and fire.
 */
public abstract class Enemy extends Entity {
    protected float xMultiplier = 1;
    protected float yMultiplier = 1;

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Enemy(float xPos, float yPos) {
        super(xPos, yPos);
    }

    /**
     * Update the enemies position.
     */
    public abstract void updateMovement(float deltaTime);

    /**
     * Exit the screen.
     */
    public void exitScreen(float deltaTime) {
        this.yPosition += this.getSpeed() * deltaTime;
    }

    /**
     * Return the coordinate for shooting position.
     *
     * @return shooting position.
     */
    public GridPoint2 getShootingPosition() {
        float xShootPosition = getXPosition() + getImageWidth() / 2;
        float yShootPosition = getYPosition();
        GridPoint2 shootPosition = new GridPoint2((int) xShootPosition, (int) yShootPosition);
        return shootPosition;
    }
}
