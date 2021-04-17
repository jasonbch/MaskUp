package Objects.GameObject;

import GameEngine.Observer.BulletSpawnerObserver;
import GameEngine.Observer.EnemySubject;
import Objects.GameObject.Enemy.Enemy;

import com.badlogic.gdx.graphics.Texture;

// Spawner must observe the enemy
public class BulletSpawner extends Enemy implements BulletSpawnerObserver {
    private float angle;

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public BulletSpawner(float xPos,
                         float yPos,
                         String movingPattern,
                         String name,
                         float speed,
                         String bullet,
                         Texture texture,
                         float timeBetweenShot,
                         String bulletFormation,
                         int maxTimeAlive,
                         int maxHealth) {
        super(xPos, yPos, movingPattern);
        this.name = name;
        this.speed = speed;
        this.bullet = bullet;
        this.texture = texture;
        this.timeBetweenShot = timeBetweenShot;
        this.bulletFormation = bulletFormation;
        this.maxTimeAlive = maxTimeAlive;
        this.maxHealth = maxHealth;
        this.angle = 0;
    }

    @Override
    public void update(EnemySubject mySubject) {
        if (mySubject instanceof Enemy) {
            Enemy enemy = (Enemy) mySubject;

            this.setXAxis(enemy.getXAxis());
            this.setYAxis(enemy.getYAxis());
            this.setMovingPattern(enemy.getMovingPattern());
            this.setBulletFormation(enemy.getBulletFormation());
            this.setTimeBetweenShot(enemy.getTimeBetweenShots());

            if (enemy.isDone()) {
                this.setIsDone();
            } else if (!enemy.getMovingPattern().equals("PatternFour") && this.name.equals("2")) {
                this.setIsDone();
            } else if (!enemy.getMovingPattern().equals("PatternFour")) {
                this.setAngle(0);
            }
        }
    }
}
