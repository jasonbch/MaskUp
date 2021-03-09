package GameEngine;

import Enemy.Enemy;
import Factories.EnemyMovementFactory;
import EnemyMovementPattern.EnemyMovementPattern;

import java.util.Random;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {
    private final EnemyMovementFactory enemyMovementFactory = new EnemyMovementFactory();
    private Random rand = new Random();

    public EnemyMovementController() {
    }

    /**
     * TODO: Randomize 650
     */
    public void spawnMove(Enemy enemy, float deltaTime) {
        // a function that brings the enemies down from top of screen.
        // once spawned, will change enemy.isSpawned = true
        enemy.moveDown(deltaTime);

        if (enemy.getYPosition() <= 650) {
            enemy.setIsSpawned(true);
        }
    }

    public void move(Enemy enemy, float deltaTime, Integer stage){
        EnemyMovementPattern enemyMovementPattern = null;
        enemy.updateTimeAlive();

        if (enemy.getTimeAlive() >= enemy.getMaxLifespan()) {
            enemyMovementPattern = enemyMovementFactory.create("PatternExit");
        } else {
            switch (enemy.getName()) {
                case "Bat":
                    enemyMovementPattern = enemyMovementFactory.create("PatternOne");
                    break;
                case "MurderHornet":
                    enemyMovementPattern = enemyMovementFactory.create("PatternTwo");
                    break;
                case "Karen":
                    enemyMovementPattern = enemyMovementFactory.create("PatternThree");
                    break;
                case "Covid":
                    enemyMovementPattern = enemyMovementFactory.create("PatternOne");
                    break;
                default:
                    enemyMovementPattern = enemyMovementFactory.create("PatternFour");
            }
        }

        enemyMovementPattern.Move(enemy, deltaTime);
    }
}
