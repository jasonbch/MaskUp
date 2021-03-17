package GameEngine;

import Ammo.Ammo;
import Ammo.Ammo.PatternAttribute;
import BulletMovementPattern.BulletMovementPattern;
import Factories.BulletMovementFactory;

import java.util.ListIterator;


/**
 * BulletMovementController class that controls the moving of bullets.
 */
public class BulletMovementController {

    // Implement Singleton
    private static BulletMovementController uniqueInstance = null;
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final BulletMovementFactory bulletMovementFactory = new BulletMovementFactory();
    private final GameResources gameResources = GameResources.instance();

    public void update(float deltaTime) {
        ListIterator<Ammo> iterator = bulletSpawningController.getPlayerAmmoList().listIterator();
        while (iterator.hasNext()) {
            Ammo ammo = iterator.next();
            this.move(ammo, deltaTime);

            if (ammo.getYPosition() > gameResources.getWorldHeight()) {
                iterator.remove();
            }
        }

        // Enemy bullets
        ListIterator<Ammo> iter = bulletSpawningController.getEnemyAmmoList().listIterator();
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            this.move(ammo, deltaTime);

            if (ammo.getYPosition() < 0) {
                iter.remove();
            }
        }
    }


    /**
     * Return the instance of BulletMovementController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of BulletMovementController.
     */
    public static BulletMovementController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new BulletMovementController();
        }

        return uniqueInstance;
    }

    private BulletMovementController() {
    }

    public void move(Ammo ammo, float deltaTime) {
        BulletMovementPattern bulletMovementPattern;

        PatternAttribute bulletPattern = ammo.getPatternAttribute();

        bulletMovementPattern = bulletMovementFactory.create(bulletPattern.getName());

        bulletMovementPattern.move(ammo, deltaTime, bulletPattern.getXMultiplier(), bulletPattern.getYMultiplier());
    }
}
