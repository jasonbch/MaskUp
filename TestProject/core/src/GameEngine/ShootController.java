package GameEngine;

import Ammo.Ammo;
import Factories.AmmoFactory;
import Enemy.Enemy;
import Entity.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * ShootController class that controls the spawning of ammo. The entity can
 * fire their default bullet or a given bullet can be given to them.
 */
public class ShootController {
    protected AmmoFactory factory = new AmmoFactory();
    private final LinkedList<Ammo> enemyAmmoList = new LinkedList<>();
    private final LinkedList<Ammo> playerAmmoList = new LinkedList<>();

    /**
     * Create a new instance of the ShootController.
     */
    public ShootController() {
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

    /**
     * Return the bullet ammo at the given entity and the default bullet.
     *
     * @param   entity  the entity.
     * @return  The ammo that the entity fires.
     */
    public Ammo fire(Entity entity) {
        // Get the entity default bullet
        String currentBullet = entity.getBullet();

        return this.fire(entity, currentBullet);
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
                Ammo ammo = fire(currEnemy);
                enemyAmmoList.add(ammo);
            }
        }
    }

    /**
     * Fire the bullet from player if the space bar is pressed.
     */
    public void playerFire(Entity player) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (player.canFire()) {
                Ammo ammo = fire(player, "Mask");
                playerAmmoList.add(ammo);
            }
        }
    }
}
