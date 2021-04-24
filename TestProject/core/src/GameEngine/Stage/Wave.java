package GameEngine.Stage;

import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;

import java.util.HashMap;
import java.util.List;

public class Wave {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private String name;
    private int amount;
    private int startTime;
    private String enemyMovementPattern;
    private String bulletFormation;
    private boolean isRan;

    // adding xDrop and yDrop array for enemies
    private List<HashMap<String, Integer>> spawnLocations;


    public Wave(String name, int amount, int startTime, List<HashMap<String, Integer>> locations, String enemyMovementPattern, String bulletFormation) {
        this.name = name;
        this.amount = amount;
        this.spawnLocations = locations;
        this.startTime = startTime;
        this.enemyMovementPattern = enemyMovementPattern;
        this.bulletFormation = bulletFormation;
        this.isRan = false;
    }

    public int getStartTime() {
        return startTime;
    }

    public void run() {
        if (!isRan) {
            for (int i = 0; i < this.amount; i++) {
                HashMap<String, Integer> enemyPos = spawnLocations.get(i);
                int xPos = enemyPos.get("x");
                int yPos = enemyPos.get("y");
                Enemy enemy = enemySpawningController.spawnEnemies(name, enemyMovementPattern, xPos, yPos);
                enemy.setBulletFormation(bulletFormation);
            }

            this.isRan = true;
        }
    }
}
