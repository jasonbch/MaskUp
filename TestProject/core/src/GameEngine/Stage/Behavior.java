package GameEngine.Stage;

import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;

public class Behavior extends StageComponent {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private int speed;
    private float timeBetweenShot;

    public Behavior(String section, String enemyName, int startTimeFromStage, String enemyMovementPattern, String bulletFormation, int speed, float timeBetweenShot) {
        super(section, enemyName, startTimeFromStage, enemyMovementPattern, bulletFormation);
        this.speed = speed;
        this.timeBetweenShot = timeBetweenShot;
    }

    public void change() {
        if (!isRan) {
            Enemy enemy = enemySpawningController.findEnemy(this.enemyName);

            if (enemy != null) {
                enemy.setSpeed(this.speed);
                enemy.setTimeBetweenShot(this.timeBetweenShot);
                enemy.setMovingPattern(this.enemyMovementPattern);
                enemy.setBulletFormation(this.bulletFormation);
                this.isRan = true;
            }
        }
    }
}
