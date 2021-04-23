package GameEngine.Spawning;

import GameEngine.Observer.GameObserver;
import GameEngine.Resource.GameResources;
import GameEngine.Stage.FormationController;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.Entity;
import Objects.GameObject.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ShootController class that controls the spawning of ammo. The entity can
 * fire their default bullet or a given bullet can be given to them.
 */
public class BulletSpawningController implements GameObserver {
    // Implement Singleton
    private static BulletSpawningController uniqueInstance = null;
    private final FormationController formationController = new FormationController();
    CopyOnWriteArrayList enemyAmmoList = new CopyOnWriteArrayList<Ammo>();
    CopyOnWriteArrayList playerAmmoList = new CopyOnWriteArrayList<Ammo>();

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

    public CopyOnWriteArrayList<Ammo> getEnemyAmmoList() {
        return this.enemyAmmoList;
    }

    public CopyOnWriteArrayList<Ammo> getPlayerAmmoList() {
        return this.playerAmmoList;
    }

    public void clearEnemyAmmoList() {
        this.enemyAmmoList.clear();
    }

    /**
     * Return a list of ammo that the entity fires.
     *
     * @param entity the entity that fires the bullet
     */
    private List<Ammo> fire(Entity entity) {
        List<Ammo> ammoList;
        ammoList = formationController.create(entity, entity.getBulletFormation());

        entity.resetTimeSinceLastShot();

        return ammoList;
    }

    /**
     * Update the enemy position and fire their bullets if they can fire.
     *
     * @param deltaTime the delta time
     * @param enemyList
     */
    public void enemyFire(float deltaTime, List<Enemy> enemyList) {
        ListIterator<Enemy> iterator = enemyList.listIterator();
        while (iterator.hasNext()) {
            Enemy currEnemy = iterator.next();
            currEnemy.updateTimeSinceLastShot(deltaTime);

            if (currEnemy.canFire()) {
                List<Ammo> ammoList = fire(currEnemy);

                for (Ammo ammo : ammoList) {
                    enemyAmmoList.add(ammo);
                    ammo.attachGameObserver(this);
                }
            }
        }
    }

    /**
     * TODO: Refactor into two methods.
     * Deleting the enemies bullet if state isDone
     */
    public void deleteBullet(Ammo ammo) {
        if (playerAmmoList.contains(ammo)) {
            playerAmmoList.remove(ammo);
        }
        if (enemyAmmoList.contains(ammo)) {
            enemyAmmoList.remove(ammo);
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
                    ammo.attachGameObserver(this);
                }
            }
        }
    }

    @Override
    public void update(Object object, String args) {
        if (object instanceof Ammo
                || object instanceof GameObject) {
            if (args.equals("deleteAmmo")) {
                this.deleteBullet((Ammo) object);
            }
        }
    }
}
