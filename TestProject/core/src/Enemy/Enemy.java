package Enemy;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Enemy {
    //enemy characteristics
    public abstract float getSpeed();
    public abstract String bullet();
    public abstract float getTimeBetweenShots();

    AmmoFactory factory = new AmmoFactory();
    public abstract String getName();

    //position and dimension
    public float xPos;
    public float yPos;
    public float timeSinceLastShot = 0;

    public Enemy(float xPos, float yPos ) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void update(float deltaTime)
    {
        timeSinceLastShot += deltaTime;
    }

    public boolean canFire()
    {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);

    }

    public Ammo fire() {
        Ammo ammo = factory.create(bullet(), xPos, yPos);
        timeSinceLastShot = 0;
        return  ammo;
    }

    //graphics
    public abstract Texture getImage();

    public void draw(Batch batch)
    {
        batch.draw(getImage(), xPos, yPos, 10, 10);
    }

}
