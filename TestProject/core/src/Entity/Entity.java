package Entity;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * The Entity abstract class that can move and fire.
 */
public abstract class Entity {
    protected AmmoFactory factory = new AmmoFactory();
    protected float xPosition;  // Initial x position
    protected float yPosition;  // Initial y position
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
     * Return the y position.
     *
     * @return y position
     */
    public float getYPos() {
        return this.yPosition;
    }

    /**
     * Return the x position.
     *
     * @return x position
     */
    public float getXPos() {
        return this.xPosition;
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
        Ammo ammo = factory.create(bullet,
                xPosition + (getImageWidth() / 2),
                yPosition + getImageHeight());
        timeSinceLastShot = 0;
        return  ammo;
    }

    /**
     * Return the ammo that the entity fires.
     *
     * @param  batch  The current batch.
     */
    public void draw(Batch batch) {
        Texture image = getImage();
        batch.draw(image, xPosition, yPosition, image.getWidth(), image.getHeight());
    }

    /**
     * Return the world width.
     */
    public int getWorldWidth() {
        return Gdx.graphics.getWidth();
    }

    /**
     * Return the world height.
     */
    public int getWorldHeight() {
        return Gdx.graphics.getHeight();
    }

    /**
     * Return the image width.
     */
    public int getImageWidth() {
        return getImage().getWidth();
    }

    /**
     * Return the image height.
     */
    public int getImageHeight() {
        return getImage().getHeight();
    }
}
