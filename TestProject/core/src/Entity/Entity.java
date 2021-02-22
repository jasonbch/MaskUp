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
public abstract class Entity {


    Rectangle entityBoundingBox;

    public AmmoFactory factory = new AmmoFactory();
    public float xPos;  // Initial x position
    public float yPos;  // Initial y position
    public float timeSinceLastShot = 0;
    public float Width = 10;
    public float Height = 10;

    /**
     * Create a new instance of an Entity at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Entity(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.entityBoundingBox = new Rectangle(xPos,yPos,Width,Height);
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
        entityBoundingBox.set(xPos,yPos,Width,Height);
    }

    /**
     * Return True if the entity can fire, otherwise false.
     */
    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    public boolean intersects(Rectangle otherRectangle)
    {
        return entityBoundingBox.overlaps(otherRectangle);
    }

    /**
     * Return the bullet ammo that the entity fires.
     *
     * @param  bullet  The name of the type of bullet.
     * @return The ammo that the entity fires.
     */
    public Ammo fire(String bullet) {
        Ammo ammo = factory.create(bullet,
                xPos + (getImageWidth() / 2),
                yPos + getImageHeight());
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
        batch.draw(image, xPos, yPos, image.getWidth(), image.getHeight());
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
