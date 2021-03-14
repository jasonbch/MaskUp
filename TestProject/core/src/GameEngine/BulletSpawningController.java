package GameEngine;

import Ammo.Ammo;
import Entity.*;
import Factories.AmmoFactory;
import Enemy.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * ShootController class that controls the spawning of ammo. The entity can
 * fire their default bullet or a given bullet can be given to them.
 */
public class BulletSpawningController {
    private AmmoFactory factory = new AmmoFactory();
    private final LinkedList<Ammo> enemyAmmoList = new LinkedList<>();
    private final LinkedList<Ammo> playerAmmoList = new LinkedList<>();
    private final ShootController shootController = new ShootController();


    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();

    // Implement Singleton
    private static BulletSpawningController uniqueInstance = null;

    /**
     * Return the instance of EnemySpawningController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of EnemySpawningController.
     */
    public static BulletSpawningController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new BulletSpawningController();
        }

        return uniqueInstance;
    }

    /**
     * Create a new instance of the ShootController.
     */
    private BulletSpawningController() {
    }


    public LinkedList<Ammo> getEnemyAmmoList() {
        return this.enemyAmmoList;
    }


    public LinkedList<Ammo> getPlayerAmmoList() {
        return this.playerAmmoList;
    }

    /**
     * Return a list of ammo that the entity fires.
     *
     * @param entity the entity that fires the bullet
     */
    private List<Ammo> fire(Entity entity) {
        List<Ammo> ammoList;

        if (entity instanceof Bat) {
            ammoList = shootController.create(entity, "FanPattern");
        } else if (entity instanceof MurderHornet) {
            ammoList = shootController.create(entity, "FanPattern");
        } else if (entity instanceof Karen) {
            ammoList = shootController.create(entity, "FanPattern");
        } else if (entity instanceof Covid) {
            ammoList = shootController.create(entity, "FanPattern");
        } else if (entity instanceof Player) {
            ammoList = shootController.create(entity, "LinearPattern");
        } else {
            ammoList = shootController.create(entity, "LinearPattern");
        }
        entity.resetTimeSinceLastShot();

        return ammoList;
    }

    /**
     * Update the enemy position and fire their bullets if they can fire.
     *
     * @param deltaTime the delta time
     */
    public void enemyFire(float deltaTime) {
        ListIterator<Enemy> iterator = enemySpawningController.getEnemyList().listIterator();
        while (iterator.hasNext()) {
            Enemy currEnemy = iterator.next();
            currEnemy.updateTimeSinceLastShot(deltaTime);

            if (currEnemy.canFire()) {
                List<Ammo> ammoList = fire(currEnemy);

                for (Ammo ammo : ammoList) {
                    enemyAmmoList.add(ammo);
                }
            }
        }
    }

    /**
     * Deleting the enemies bullet if state isDone
     */
    public void deleteBullet(String type) {
        ListIterator<Ammo> iter;
        if (type == "Player") {

          iter = getPlayerAmmoList().listIterator();
        }
        else {
            iter = getEnemyAmmoList().listIterator();
        }
        while (iter.hasNext())
        {
            Ammo ammo = iter.next();
            if (ammo.isDone())
            {
                iter.remove();
            }
        }
    }



    /**
     * Fire the bullet from player if the space bar is pressed.
     */
    public void playerFire(Entity player) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (player.canFire()) {
                List<Ammo> ammoList;
                ammoList = fire(player);

                for (Ammo ammo : ammoList) {
                    playerAmmoList.add(ammo);
                }
            }
        }
    }
}
