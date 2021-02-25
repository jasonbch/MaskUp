package Enemy;

import Ammo.Ammo;
import Entity.Entity;

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
     * Return the bullet string that the enemy fires.
     */
    public abstract String bullet();

    /**
     * Return the ammo that the enemy fires.
     */
    public Ammo fire() {
        Ammo ammo = factory.create(bullet(), xPosition + (getImageWidth() / 2), yPosition);
        timeSinceLastShot = 0;
        return  ammo;
    }
}
