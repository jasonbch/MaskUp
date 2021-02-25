package Entity;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.math.GridPoint2;

/**
 * The Entity abstract class that can move and fire.
 */
public abstract class Entity extends GameObject {
    protected AmmoFactory factory = new AmmoFactory();
    protected float timeSinceLastShot = 0;

    /**
     * Create a new instance of an Entity at the xPos and yPos.
     *
     * @param  xPosition initial x position.
     * @param  yPosition initial y position.
     */
    public Entity(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Return the speed of the entity.
     */
    public abstract float getSpeed();

    /**
     * Return the time between shot.
     */
    public abstract float getTimeBetweenShots();

    /**
     * Return the bullet string that the enemy fires.
     */
    public abstract String getBullet();

    /**
     * Update time since last shot.
     *
     * @param  deltaTime The current delta time.
     */
    public void updateTimeSinceLastShot(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    /**
     * Reset time since last shot to 0.
     *
     */
    public void resetTimeSinceLastShot() {
        timeSinceLastShot = 0;
    }

    /**
     * Return True if the entity can fire, otherwise false.
     *
     * @return  if the entity can fie.
     */
    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    /**
     * Return the coordinate for shooting position.
     *
     * @return  shooting position.
     */
    public abstract GridPoint2 getShootingPosition();
}
