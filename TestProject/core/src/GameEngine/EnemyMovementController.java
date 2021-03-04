package GameEngine;

import Enemy.Enemy;
import Factories.MovementFactory;
import MovementPatterns.Pattern;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {
    private final MovementFactory movementFactory = new MovementFactory();

    public EnemyMovementController(){
    }

    public void spawnMove(Enemy enemy, float deltaTime) {
        // a function that brings the enemies down from top of screen.
        // once spawned, will change enemy.isSpawned = true
        enemy.moveDown(deltaTime);

        if (enemy.getYPosition() <= 600) {
            enemy.setIsSpawned(true);
        }
    }

    public void move(Enemy enemy, float deltaTime, Integer stage){
        Pattern pattern = null;

        switch (enemy.getName()) {
            case "Bat":
                pattern = movementFactory.create("PatternOne");
                break;
            case "MurderHornet":
                pattern = movementFactory.create("PatternTwo");
                break;
            case "Karen":
                pattern = movementFactory.create("PatternThree");
                break;
            case "Covid":
                pattern = movementFactory.create("PatternFour");
                break;
            default:
                pattern = movementFactory.create("PatternOne");
        }

        pattern.Move(enemy, deltaTime);
    }

    /**
     * Exit the screen.
     */
    public void exitScreen(Enemy enemy, float deltaTime) {
        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * deltaTime);
    }
}
