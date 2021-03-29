package GameEngine.Movement;

import GameEngine.Factory.BulletMovementPatternFactory;
import GameEngine.Resource.GameResources;
import GameEngine.Spawning.BulletSpawningController;
import Objects.BulletMovementPattern.BulletMovementPattern;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Ammo.Ammo.PatternAttribute;

import java.util.ListIterator;

/**
 * BulletMovementController class that controls the moving of bullets.
 */
public class BulletMovementController {
    // Implement Singleton
    private static BulletMovementController uniqueInstance = null;
    private final BulletMovementPatternFactory bulletMovementPatternFactory = new BulletMovementPatternFactory();
    private final GameResources gameResources = GameResources.instance();

    private BulletMovementController() {
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

    public void update(float deltaTime, BulletSpawningController bulletSpawningController) {
        ListIterator<Ammo> iterator = bulletSpawningController.getPlayerAmmoList().listIterator();
        while (iterator.hasNext()) {
            Ammo ammo = iterator.next();
            this.move(ammo, deltaTime);

            if (ammo.getYPosition() > gameResources.getWorldHeight()) {
                iterator.remove();
            }
        }

        // Entity.Enemy bullets
        ListIterator<Ammo> iter = bulletSpawningController.getEnemyAmmoList().listIterator();
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            this.move(ammo, deltaTime);

            if (ammo.getYPosition() < 0) {
                iter.remove();
            }
        }
    }

    public void move(Ammo ammo, float deltaTime) {
        BulletMovementPattern bulletMovementPattern;
        PatternAttribute bulletPattern = ammo.getPatternAttribute();
        bulletMovementPattern = bulletMovementPatternFactory.create(bulletPattern.getName());
        bulletMovementPattern.move(ammo, deltaTime, bulletPattern.getXMultiplier(), bulletPattern.getYMultiplier());
    }
}
