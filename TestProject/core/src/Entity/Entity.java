package Entity;

import Ammo.Ammo;
import Enemy.Enemy;
import GameEngine.EnemySpawningController;
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
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();



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

    public void playerCollidedWith(Entity player)
    {
        ListIterator<Ammo> iter = sc.getEnemyAmmoList().listIterator();
        while(iter.hasNext())
        {
            Ammo ammo = iter.next();
            if(player.intersects(ammo.getBoundingBox()))
            {
                iter.remove();
            }
        }
    }
    public boolean enemyCollidedWith(Enemy enemy)
    {
        ListIterator<Ammo> iter = sc.getPlayerAmmoList().listIterator();
        while(iter.hasNext())
        {
            Ammo ammo = iter.next();
            if(enemy.intersects(ammo.getBoundingBox()))
            {
                iter.remove();
                return true;
            }
        }
        return false;
    }
}
