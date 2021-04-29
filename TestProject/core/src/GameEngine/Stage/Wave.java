package GameEngine.Stage;

import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;
import java.util.HashMap;
import java.util.List;

public class Wave extends StageComponent {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private int amount;
    private List<HashMap<String, Integer>> spawnLocations;

    public Wave(String section, String enemyName, int startTimeFromStage, String enemyMovementPattern, String bulletFormation, int amount, List<HashMap<String, Integer>> locations) {
        super(section, enemyName, startTimeFromStage, enemyMovementPattern, bulletFormation);
        this.amount = amount;
        // adding xDrop and yDrop array for enemies
        this.spawnLocations = locations;
    }

    public void run() {
        if (!isRan) {
            for (int i = 0; i < this.amount; i++) {
                HashMap<String, Integer> enemyPos = spawnLocations.get(i);
                int xPos = enemyPos.get("x");
                int yPos = enemyPos.get("y");
                Enemy enemy = enemySpawningController.spawnEnemies(enemyName, enemyMovementPattern, xPos, yPos);
                enemy.setBulletFormation(bulletFormation);
            }
            this.isRan = true;
        }
    }
}
