package Objects.GameObject;

import GameEngine.Observer.BulletSpawnerObserver;
import GameEngine.Observer.EnemySubject;
import Objects.GameObject.Enemy.Enemy;
import com.badlogic.gdx.graphics.Texture;

// Spawner must observe the enemy
public class BulletSpawner extends Enemy implements BulletSpawnerObserver {
    public BulletSpawner(float xPos, float yPos, String movingPattern, float speed, String bullet, Texture texture, float timeBetweenShot, String bulletFormation, int maxTimeAlive, int maxHealth) {
        super(xPos, yPos, movingPattern);
        this.name = "Bullet Spawner";
        this.speed = speed;
        this.bullet = bullet;
        this.texture = texture;
        this.timeBetweenShot = timeBetweenShot;
        this.bulletFormation = bulletFormation;
        this.maxTimeAlive = maxTimeAlive;
        this.maxHealth = maxHealth;
    }

    @Override
    public void update(EnemySubject mySubject) {
        Enemy enemy = (Enemy) mySubject;

        this.setYAxis(enemy.getYAxis());
        this.setMovingPattern(enemy.getMovingPattern());
        this.setBulletFormation(enemy.getBulletFormation());

        if (enemy.isDone()) {
            this.setIsDone();
        }
    }
}