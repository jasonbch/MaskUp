package GameEngine;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import Entity.Entity;
import com.badlogic.gdx.math.GridPoint2;

/**
 * ShootController class that controls the spawning of ammo. The entity can
 * fire their default bullet or a given bullet can be given to them.
 */
public class ShootController {
    protected AmmoFactory factory = new AmmoFactory();

    /**
     * Create a new instance of the ShootController.
     */
    public ShootController() {
    }

    /**
     * Return the bullet ammo at the given entity and the default bullet.
     *
     * @param   entity  the entity.
     * @return  The ammo that the entity fires.
     */
    public Ammo fire(Entity entity) {
        // Get the entity default bullet
        String currentBullet = entity.getBullet();

        Ammo ammo = this.fire(entity, currentBullet);
        return  ammo;
    }

    /**
     * Return the bullet ammo at the given entity and the given bullet.
     *
     * @param   entity  the entity.
     * @param   bullet  the type of bullet.
     * @return  The ammo that the entity fires.
     */
    public Ammo fire(Entity entity, String bullet) {
        // Get shoot position
        GridPoint2 shootPosition = entity.getShootingPosition();

        // Create the ammo
        Ammo ammo = factory.create(bullet,
                shootPosition.x,
                shootPosition.y);

        // Reset the time since last shot
        entity.resetTimeSinceLastShot();
        return  ammo;
    }
}
