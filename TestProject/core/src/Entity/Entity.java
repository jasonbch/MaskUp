package Entity;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * The Entity abstract class that can move and fire.
 */
public abstract class Entity {
    public AmmoFactory factory = new AmmoFactory();

    public float xPos;  // Initial x position
    public float yPos;  // Initial y position
    public float timeSinceLastShot = 0;

    /**
     * Create a new instance of an Entity at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Entity(float xPos, float yPos ) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Return the name.
     */
    public abstract String getName();

    /**
     * Return the speed of the entity.
     */
    public abstract float getSpeed();

    /**
     * Return the time between shot.
     */
    public abstract float getTimeBetweenShots();

    /**
     * Return the Texture image.
     */
    public abstract Texture getImage();

    /**
     * Return the ammo that the entity fires.
     */
    public abstract Ammo fire();

    /**
     * Update the position of the entity.
     *
     * @param  deltaTime The current delta time.
     */
    public void update(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    /**
     * Return True if the entity can fire, otherwise false.
     */
    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    /**
     * Return the bullet ammo that the entity fires.
     *
     * @param  bullet  The name of the type of bullet.
     * @return The ammo that the entity fires.
     */
    public Ammo fire(String bullet) {
        Ammo ammo = factory.create(bullet, xPos, yPos);
        timeSinceLastShot = 0;
        return  ammo;
    }

    /**
     * Return the ammo that the entity fires.
     *
     * @param  batch  The current batch.
     */
    public void draw(Batch batch) {
        batch.draw(getImage(), xPos, yPos, 10, 10);
    }
}
