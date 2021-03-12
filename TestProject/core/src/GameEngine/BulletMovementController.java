package GameEngine;

import Ammo.Ammo;
import Ammo.Ammo.*;
import BulletMovementPattern.BulletMovementPattern;
import Factories.BulletMovementFactory;


/**
 * BulletMovementController class that controls the moving of bullets.
 */
public class BulletMovementController {

    // Implement Singleton
    private static BulletMovementController uniqueInstance = null;

    private final BulletMovementFactory bulletMovementFactory = new BulletMovementFactory();

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
