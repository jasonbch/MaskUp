package GameEngine;

import Ammo.Ammo;
import Enemy.Bat;
import Enemy.Covid;
import Enemy.Karen;
import Enemy.MurderHornet;
import Entity.Enemy;
import Entity.Entity;
import Entity.Player;
import Factories.AmmoFactory;
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
    // Implement Singleton
    private static BulletSpawningController uniqueInstance = null;
    private final LinkedList<Ammo> enemyAmmoList = new LinkedList<>();
    private final LinkedList<Ammo> playerAmmoList = new LinkedList<>();
    private final ShootController shootController = new ShootController();


    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private AmmoFactory factory = new AmmoFactory();

    /**
     * Create a new instance of the ShootController.
     */
    private BulletSpawningController() {
    }

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

        ammoList = shootController.create(entity, entity.getFormationPattern());

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
