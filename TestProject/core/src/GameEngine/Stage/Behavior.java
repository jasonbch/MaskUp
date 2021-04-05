package GameEngine.Stage;

import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;

public class Behavior {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private String enemy;
    private int startTime;
    private int speed;
    private double timeBetweenShot;
    private String enemyMovementPattern;
    private String bulletFormation;
    private boolean isRan;

    public Behavior(String enemy, int startTime, int speed, double timeBetweenShot, String enemyMovementPattern, String bulletFormation) {
        this.enemy = enemy;
        this.startTime = startTime;
        this.speed = speed;
        this.timeBetweenShot = timeBetweenShot;
        this.enemyMovementPattern = enemyMovementPattern;
        this.bulletFormation = bulletFormation;
        this.isRan = false;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void change() {
        if (! isRan) {
            Enemy enemy = enemySpawningController.findEnemy(this.enemy);

            if (enemy != null) {
                enemy.setSpeed(this.speed);
                enemy.setTimeBetweenShot((float) this.timeBetweenShot);
                enemy.setMovingPattern(this.enemyMovementPattern);
                enemy.setFormationPattern(this.bulletFormation);
                this.isRan = true;
            }
        }
    }
}
