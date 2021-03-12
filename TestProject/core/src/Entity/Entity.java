package Entity;

import Ammo.Ammo;
import GameEngine.ShootController;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import java.util.ListIterator;

/**
 * The Entity abstract class that can move and fire.
 */
public abstract class Entity extends GameObject {
    protected float timeSinceLastShot = 0;
    private static final ShootController sc = ShootController.instance();



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

    public boolean intersects(Rectangle otherRectangle)
    {
        Rectangle rectangle = new Rectangle(xPosition,yPosition,getImage().getWidth(), getImage().getHeight());
        return rectangle.overlaps(otherRectangle);
    }

    //TODO make abstract collision function

    // move to player
    // collision
    public void playerCollidedWith()
    {
        ListIterator<Ammo> iter = sc.getEnemyAmmoList().listIterator();
        while(iter.hasNext())
        {
            Ammo ammo = iter.next();
            if(intersects(ammo.getBoundingBox()))
            {
                // set bullet and player state
                // remove iter.remove
                iter.remove();
            }
        }
    }

    // move to enemy
    // collision
    public boolean enemyCollidedWith()
    {
        ListIterator<Ammo> iter = sc.getPlayerAmmoList().listIterator();
        while(iter.hasNext())
        {
            Ammo ammo = iter.next();
            if(intersects(ammo.getBoundingBox()))
            {
                // set bullet state
                // remove iter.remove
                iter.remove();
                return true;
            }
        }
        return false;
    }
}
