package EnemyMovementPattern;

import GameEngine.Movement.EnemyMovementController;
import GameObject.Enemy.Enemy;
import com.badlogic.gdx.math.GridPoint2;

import javax.swing.*;

/**
 * The EnemyMovementPattern that move the enemy up and down. If the enemy touch
 * the top of the screen, the enemy changes direction and move down to the bottom
 * of the screen and vice versa.
 */
public class EnemyMovementPatternFour extends EnemyMovementPattern {

    private final EnemyMovementController enemyMovementController = EnemyMovementController.instance();

    @Override
    public String getName() {
        return "PatternFour";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {

        //// UPDATED PATTERN
        if(!enemyMovementController.getEnemyPositionMap().containsKey(enemy)){
            GridPoint2 position = new GridPoint2(20, 900);
            enemyMovementController.getEnemyPositionMap().put(enemy, position);
        }
        else{
            moveToPosition(enemy, deltaTime);
        }
    }

    public void moveToPosition(Enemy enemy, float deltaTime) {
        int goalX = enemyMovementController.getEnemyPositionMap().get(enemy).x;
        int goalY = enemyMovementController.getEnemyPositionMap().get(enemy).y;

        if(enemy.getYPosition() < goalY || enemy.getXPosition() > goalX){
            if(enemy.getYPosition() < goalY) {
                enemy.moveUp(deltaTime);
            }
            if(enemy.getXPosition() > goalX){
                enemy.moveLeft(deltaTime);
            }
        }
    }
}

