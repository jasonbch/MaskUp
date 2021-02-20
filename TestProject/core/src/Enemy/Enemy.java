package Enemy;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import Entity.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * The Enemy abstract class that extends from Entity that can move and fire.
 */
public abstract class Enemy extends Entity {

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Enemy(float xPos, float yPos) {
        super(xPos, yPos);
    }

    /**
     * Return the bullet string that the enemy fires.
     */
    public abstract String bullet();

    /**
     * Return the ammo that the enemy fires.
     */
    public Ammo fire() {
        Ammo ammo = factory.create(bullet(), xPos, yPos);
        timeSinceLastShot = 0;
        return  ammo;
    }
}