package GameEngine.Spawning;

import GameEngine.Factory.BulletSpawnerFactory;
import GameEngine.Resource.GameResources;
import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.Enemy;
import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;
import java.util.ListIterator;

public class BulletSpawnerSpawningController {
    private static final GameResources gameResources = GameResources.instance();
    private static BulletSpawnerSpawningController uniqueInstance = null;
    private final BulletSpawnerFactory bulletSpawnerFactory = new BulletSpawnerFactory();
    private final LinkedList<BulletSpawner> bulletSpawnerList = new LinkedList<>();

    public LinkedList<BulletSpawner> getBulletSpawnerList() {
        return this.bulletSpawnerList;
    }

    private BulletSpawnerSpawningController() {
    }

    public static BulletSpawnerSpawningController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new BulletSpawnerSpawningController();
        }

        return uniqueInstance;
    }

    public BulletSpawner addSpawner(Enemy enemy, String spawnerName) {
        float xPos = enemy.getXPosition();
        float yPos = enemy.getYPosition();
        String movingPattern = enemy.getMovingPattern();
        String name = spawnerName;
        float speed = enemy.getSpeed();
        String bullet = enemy.getBullet();
        Texture texture = enemy.getTexture();
        float timeBetweenShot = enemy.getTimeBetweenShots();
        String bulletFormation = enemy.getBulletFormation();
        int maxTimeAlive = enemy.getMaxTimeAlive();
        int maxHealth = enemy.getMaxHealth();

        BulletSpawner bulletSpawner = bulletSpawnerFactory.create(xPos,
                yPos,
                movingPattern,
                name,
                speed,
                bullet,
                texture,
                timeBetweenShot,
                bulletFormation,
                maxTimeAlive,
                maxHealth);

        return bulletSpawner;
    }

    /**
     * Delete the enemies if they got out of the screen.
     */
    public void deleteBulletSpawners() {
        ListIterator<BulletSpawner> iter2 = bulletSpawnerList.listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();

            if (currEnemy.getYPosition() > gameResources.getWorldHeight()) {
                iter2.remove();
            } else if (currEnemy.isDone()) {
                iter2.remove();
            } else if (currEnemy.getMaxHealth() <= 0) {
                currEnemy.setIsDone();
            }
        }
    }
}
