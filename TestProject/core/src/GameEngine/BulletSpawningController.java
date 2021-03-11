package GameEngine;

import Ammo.Ammo;
import Factories.AmmoFactory;
import Enemy.Enemy;

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

    /**
     * Return the enemy ammo list.
     *
     * @return  the enemy ammo list.
     */
    public LinkedList<Ammo> getEnemyAmmoList() {
        return this.enemyAmmoList;
    }

    /**
     * Return the player ammo list.
     *
     * @return  the player ammo list.
     */
    public LinkedList<Ammo> getPlayerAmmoList() {
        return this.playerAmmoList;
    }

    private List<Ammo> fire(Enemy enemy) {
        List<Ammo> ammos = shootController.create(enemy, "FanPattern");
        enemy.resetTimeSinceLastShot();

        return ammos;
    }

    /**
     * Update the enemy position and fire their bullets if they can fire.
     *
     * @param deltaTime the delta time
     */
    public void enemyFire(float deltaTime, EnemySpawningController enemySpawningController) {
        ListIterator<Enemy> iterator = enemySpawningController.getEnemyList().listIterator();
        while (iterator.hasNext()) {
            Enemy currEnemy = iterator.next();
            currEnemy.updateTimeSinceLastShot(deltaTime);

            if (currEnemy.canFire()) {
                List<Ammo> ammos = fire(currEnemy);

                for (Ammo ammo : ammos) {
                    enemyAmmoList.add(ammo);
                }
            }
        }
    }

//    /**
//     * Fire the bullet from player if the space bar is pressed.
//     */
//    public void playerFire(Entity player) {
//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//            if (player.canFire()) {
//                Ammo ammo = fire(player, "Syringe");
//                playerAmmoList.add(ammo);
//            }
//        }
//    }
}
