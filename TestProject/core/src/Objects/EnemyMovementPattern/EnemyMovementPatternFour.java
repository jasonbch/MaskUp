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
    private boolean isSpawned = false;

    @Override
    public String getName() {
        return "PatternFour";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        if (enemy instanceof BulletSpawner) {
            float centerX = enemy.getXAxis();
            float centerY = enemy.getYAxis();

            float speed = 2000f;
            float rate = deltaTime;
            float circleX = (float) (Math.cos(((BulletSpawner)enemy).getAngle()) * (enemy.getImageWidth() / 1.25) + centerX);
            float circleY = (float) (Math.sin(((BulletSpawner)enemy).getAngle()) * (enemy.getImageHeight() / 1.25) + centerY);
            float angle = ((BulletSpawner)enemy).getAngle() + (speed * (rate / 1000)) % 360;

            if (angle >= 360) {
                angle = 0;
            }

            ((BulletSpawner)enemy).setAngle(angle);
            enemy.setXPosition(circleX);
            enemy.setYPosition(circleY + (enemy.getImageHeight() / 2));
        } else if (enemy instanceof Karen
                || enemy instanceof Covid) {
            if  (enemy.getBulletSpawnerCount() == 1) {
                generateSecondSpawner(enemy);
                enemy.setBulletSpawnerCount(enemy.getBulletSpawnerCount() + 1);
            }

            enemy.setXAxis(enemy.getXPosition());
        } else {
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

