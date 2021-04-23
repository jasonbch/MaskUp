package Objects.GameObject;

import GameEngine.Observer.BulletSpawnerObserver;
import GameEngine.Observer.EnemySubject;
import GameEngine.Observer.GameObserver;
import Objects.GameObject.Enemy.Enemy;
import com.badlogic.gdx.graphics.Texture;

// Spawner must observe the enemy
public class BulletSpawner extends Enemy implements BulletSpawnerObserver {
    protected float scaling;
    private float angle;

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

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScaling() {
        return this.scaling;
    }

    public void setScaling(float duration) {
        this.scaling = duration;
    }

    @Override
    public void update(EnemySubject mySubject) {
        if (mySubject instanceof Enemy) {
            Enemy enemy = (Enemy) mySubject;

            // Update the spawner's behaviors to enemy's behavior
            this.setXAxis(enemy.getXAxis());
            this.setYAxis(enemy.getYAxis());
            this.setMovingPattern(enemy.getMovingPattern());
            this.setBulletFormation(enemy.getBulletFormation());
            this.setTimeBetweenShot(enemy.getTimeBetweenShots());

            if (enemy.isDone()) {
                // Delete the spawner if the enemy is done
                this.setIsDone();
            } else if (!enemy.getMovingPattern().equals("PatternFour") && this.name.equals("2")) {
                // Delete second spawner if the next pattern is not pattern four
                // and if the spawner name is "2"
                this.setIsDone();
            } else if (!enemy.getMovingPattern().equals("PatternFive")
                    && !enemy.getMovingPattern().equals("PatternFour")
                    && this.name.equals("1")) {
                // Reset the angle for next pattern after pattern four
                this.setAngle(0);

                // Reset the scaling for next pattern
                this.setScaling(0);

                // Reset the spawner position to enemy position
                this.setXPosition(enemy.getXPosition());
                this.setYPosition(enemy.getYPosition());
            }
        }
    }
}
