package Objects.EnemyMovementPattern;

import GameEngine.Spawning.BulletSpawnerSpawningController;
import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.Covid;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.Enemy.Karen;

/**
 * Circular pattern from a point for spawner.
 * The enemy is static.
 */
public class EnemyMovementPatternFour extends EnemyMovementPattern {
    private final BulletSpawnerSpawningController bulletSpawnerSpawningController = BulletSpawnerSpawningController.instance();

    @Override
    public String getName() {
        return "PatternFour";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        if (enemy instanceof BulletSpawner) {

            BulletSpawner bulletSpawner = (BulletSpawner) enemy;
            // Move spawner in a circle
            float centerX = enemy.getXAxis();
            float centerY = enemy.getYAxis();

            // Initial radius
            float radius = 50f;
            float speed = 12000f;
            float rate = deltaTime;

            // Scaling to expand circle
            float scaling = bulletSpawner.getScaling() + deltaTime * 3f;

            float circleX = (float) (Math.cos(bulletSpawner.getAngle()) * (radius * scaling) + centerX);
            float circleY = (float) (Math.sin(bulletSpawner.getAngle()) * (radius * scaling) + centerY);
            float angle = bulletSpawner.getAngle() + (speed * (rate / 1000)) % 360;

            if (angle >= 360) {
                angle = 0;
            }

            bulletSpawner.setAngle(angle);
            bulletSpawner.setScaling(scaling);
            enemy.setXPosition(circleX);
            enemy.setYPosition(circleY + (enemy.getImageHeight() / 2));

        } else if (enemy instanceof Karen
                || enemy instanceof Covid) {
            // Generate second spawner only if the spawner count is 1
            if (enemy.getBulletSpawnerCount() == 1) {
                generateSecondSpawner(enemy);
                enemy.setBulletSpawnerCount(enemy.getBulletSpawnerCount() + 1);
            }

            // Continuously setting x axis for enemy for movement purpose
            enemy.setXAxis(enemy.getXPosition());
        } else {
            // Continuously setting x axis for enemy for movement purpose
            enemy.setXAxis(enemy.getXPosition());
        }
    }

    private void generateSecondSpawner(Enemy concreteEnemy) {
        // Add an observer for the enemy
        BulletSpawner secondBulletSpawner = bulletSpawnerSpawningController.addSpawner(concreteEnemy, "2");
        secondBulletSpawner.setAngle(180);

        // Make the bulletSpawner observe the enemy
        concreteEnemy.addObserver(secondBulletSpawner);

        // Add bullet spawner to the list
        bulletSpawnerSpawningController.getBulletSpawnerList().add(secondBulletSpawner);
    }
}

