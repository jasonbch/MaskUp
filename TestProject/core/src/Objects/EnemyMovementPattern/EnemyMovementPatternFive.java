package Objects.EnemyMovementPattern;

import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.GameObject;

/**
 * Circular pattern from a point for spawner.
 * The enemy is static.
 */
public class EnemyMovementPatternFive extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternFive";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {
        Enemy enemy = (Enemy) obj;
        if (enemy instanceof BulletSpawner) {

            BulletSpawner bulletSpawner = (BulletSpawner) enemy;
            // Move spawner in a circle
            float centerX = enemy.getXAxis();
            float centerY = enemy.getYAxis();

            // Initial radius
            float radius = 50f;
            float speed = 15000f;
            float rate = deltaTime;

            // Scaling to expand circle
            float scaling = bulletSpawner.getScaling() + deltaTime * 2f;

            float circleX = (float) (Math.cos(bulletSpawner.getAngle()) * (radius * scaling) + centerX);
            float circleY = (float) (Math.sin(bulletSpawner.getAngle()) * (radius * scaling) + centerY);
            float angle = bulletSpawner.getAngle() + (speed * (rate / 1000)) % 360;

            if (angle >= 360) {
                angle = 0;
            }

            //System.out.println(angle);

            bulletSpawner.setAngle(angle);
            bulletSpawner.setScaling(scaling);
            enemy.setXPosition(circleX);
            enemy.setYPosition(circleY + (enemy.getImageHeight() / 2));
        } else {
            // Continuously setting x axis for enemy for movement purpose
            enemy.setXAxis(enemy.getXPosition());
        }
    }
}

