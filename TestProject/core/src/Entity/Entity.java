package Entity;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Entity {
    public AmmoFactory factory = new AmmoFactory();

    // position and dimension
    public float xPos;
    public float yPos;
    public float timeSinceLastShot = 0;

    public Entity(float xPos, float yPos ) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // enemy characteristics
    public abstract float getSpeed();
    public abstract float getTimeBetweenShots();

    public abstract String getName();

    // Return the texture graphic for the object
    public abstract Texture getImage();

    public abstract Ammo fire();

    public void update(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    public Ammo fire(String Bullet) {
        Ammo ammo = factory.create(Bullet, xPos, yPos);
        timeSinceLastShot = 0;
        return  ammo;
    }

    public void draw(Batch batch) {
        batch.draw(getImage(), xPos, yPos, 10, 10);
    }
}
