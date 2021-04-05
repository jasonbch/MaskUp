package GameEngine.Stage;

import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;

public class Wave {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private String name;
    private int amount;
    private int startTime;
    private String enemyMovementPattern;
    private String bulletFormation;
    private boolean isRan;

    public Wave(String name, int amount, int startTime, String enemyMovementPattern, String bulletFormation) {
        this.name = name;
        this.amount = amount;
        this.startTime = startTime;
        this.enemyMovementPattern = enemyMovementPattern;
        this.bulletFormation = bulletFormation;
        this.isRan = false;
    }

    public int getStartTime() {
        return startTime;
    }

    public void run() {
        if (! isRan) {
            for (int i = 0; i < this.amount; i++) {
                Enemy enemy = enemySpawningController.spawnEnemies(name, enemyMovementPattern);
                enemy.setFormationPattern(bulletFormation);
            }

            this.isRan = true;
        }
    }
}
