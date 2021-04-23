package GameEngine.Stage;

import GameEngine.Observer.GameObserver;
import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;

public class Wave implements GameObserver {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private String section;
    private String name;
    private int amount;
    private int startTime;
    private int startTimeFromStage;
    private String enemyMovementPattern;
    private String bulletFormation;
    private boolean isRan;
    private int stageNumber = 0;

    public Wave(String section, String name, int amount, int startTimeFromStage, String enemyMovementPattern, String bulletFormation) {
        this.section = section;
        this.name = name;
        this.amount = amount;
        this.startTimeFromStage = startTimeFromStage;
        this.enemyMovementPattern = enemyMovementPattern;
        this.bulletFormation = bulletFormation;
        this.isRan = false;
        this.setStageNumber();
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    private void setStageNumber() {
        this.stageNumber = Integer.parseInt(String.valueOf(section.toCharArray()[0]));
    }

    public String getSection() {
        return this.section;
    }

    public int getStageNumber() {
        return this.stageNumber;
    }

    public int getStartTimeFromStage() {
        return this.startTimeFromStage;
    }

    public void run() {
        if (!isRan) {
            for (int i = 0; i < this.amount; i++) {
                Enemy enemy = enemySpawningController.spawnEnemies(name, enemyMovementPattern);
                enemy.setBulletFormation(bulletFormation);
            }

            this.isRan = true;
        }
    }

    @Override
    public void update(Object object, String args) {
        if (object instanceof StageController) {
            String[] array = args.split(",");

            if (this.stageNumber == 3) {
                this.setStartTime(Integer.parseInt(array[0]) + this.startTimeFromStage);
            } else if (this.stageNumber == 4) {
                this.setStartTime(Integer.parseInt(array[1]) + this.startTimeFromStage);
            }
        }
    }
}
