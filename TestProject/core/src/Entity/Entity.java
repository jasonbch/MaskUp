package Entity;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;


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
     * Return the ammo that the entity fires.
     */
    public abstract Ammo fire();

    /**
     * Update time since last shot.
     *
     * @param  deltaTime The current delta time.
     */
    public void updateTimeSinceLastShot(float deltaTime) {
        timeSinceLastShot += deltaTime;
        //entityBoundingBox.set(xPos,yPos,Width,Height);
    }

    /**
     * Return True if the entity can fire, otherwise false.
     */
    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    /*public boolean intersects(Rectangle otherRectangle)
    {
        return entityBoundingBox.overlaps(otherRectangle);
    }*/

    /**
     * Return the bullet ammo that the entity fires.
     *
     * @param  bullet  The name of the type of bullet.
     * @return The ammo that the entity fires.
     */
    public Ammo fire(String bullet) {
        Ammo ammo = factory.create(bullet,
                xPosition + (getImageWidth() / 2),
                yPosition + getImageHeight());
        timeSinceLastShot = 0;
        return  ammo;
    }
}
