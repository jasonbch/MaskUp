package Objects.EnemyMovementPattern;

import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.Enemy;

/**
 * Circular pattern from a point
 */
public class EnemyMovementPatternFour extends EnemyMovementPattern {
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
        } else {
            enemy.setXAxis(enemy.getXPosition());
        }
    }
}

