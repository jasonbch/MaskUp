package GameEngine.Spawning;

import GameEngine.Factory.BulletSpawnerFactory;
import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.Enemy;
import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;

public class BulletSpawnerSpawningController {
    private static BulletSpawnerSpawningController uniqueInstance = null;
    private final BulletSpawnerFactory bulletSpawnerFactory = new BulletSpawnerFactory();
    private final LinkedList<BulletSpawner> bulletSpawnerList = new LinkedList<>();

    private BulletSpawnerSpawningController() {
    }

    public static BulletSpawnerSpawningController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new BulletSpawnerSpawningController();
        }

        return uniqueInstance;
    }

    public LinkedList<BulletSpawner> getBulletSpawnerList() {
        return this.bulletSpawnerList;
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
}
