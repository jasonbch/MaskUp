package Entity;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public abstract class Entity {
    //enemy characteristics
    public abstract float getSpeed();
    public abstract float getTimeBetweenShots();

    Rectangle entityBoundingBox;

    public AmmoFactory factory = new AmmoFactory();
    public abstract String getName();

    //position and dimension
    public float xPos;
    public float yPos;
    public float timeSinceLastShot = 0;
    public float Width = 10;
    public float Height = 10;

    public Entity(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.entityBoundingBox = new Rectangle(xPos,yPos,Width,Height);
    }

    public void update(float deltaTime)
    {
        entityBoundingBox.set(xPos,yPos,Width,Height);
        timeSinceLastShot += deltaTime;
    }

    public boolean canFire()
    {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);

    }

    public boolean intersects(Rectangle otherRectangle)
    {
        return entityBoundingBox.overlaps(otherRectangle);
    }

    public Ammo fire(String Bullet)
    {
        Ammo ammo = factory.create(Bullet, xPos, yPos);
        timeSinceLastShot = 0;
        return  ammo;
    }
    public abstract Ammo fire();

    //graphics
    public abstract Texture getImage();

    public void draw(Batch batch)
    {
        batch.draw(getImage(), xPos, yPos, Width, Height);
    }
}
