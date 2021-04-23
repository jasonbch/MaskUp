package GameEngine.Stage;

import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;

public class Wave extends StageComponent {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private int amount;

    public Wave(String section,
                String enemyName,
                int startTimeFromStage,
                String enemyMovementPattern,
                String bulletFormation,
                int amount) {
        super(section, enemyName, startTimeFromStage, enemyMovementPattern, bulletFormation);
        this.amount = amount;
    }

    public void run() {
        if (!isRan) {
            for (int i = 0; i < this.amount; i++) {
                Enemy enemy = enemySpawningController.spawnEnemies(enemyName, enemyMovementPattern);
                enemy.setBulletFormation(bulletFormation);
            }

            this.isRan = true;
        }
    }
}
