package Enemy;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import Entity.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Enemy extends Entity {
    public Enemy(float xPos, float yPos) {
        super(xPos, yPos);
    }

    public abstract String bullet();

    public Ammo fire() {
        Ammo ammo = factory.create(bullet(), xPos, yPos);
        timeSinceLastShot = 0;
        return  ammo;
    }
}
