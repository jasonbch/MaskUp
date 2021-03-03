package GameEngine;

import Enemy.Enemy;
import Entity.Entity;

import java.lang.Math;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {


    public EnemyMovementController(){
    }


    public void spawnMove(Enemy enemy, float deltaTime) {
        // a function that brings the enemies down from top of screen.
        // once spawned, will change enemy.isSpawned = true
        enemy.moveDown(deltaTime);

        if (enemy.getYPosition() <= 600) {
            enemy.isSpawned = true;
        }
    }

    /*
     * bat pattern function.
     */
    public void patternBat(Enemy enemy, float deltaTime) {
        float val = 600 + (float)(50 * Math.sin(enemy.getXPosition() * .5 * Math.PI / 80));
        enemy.setyPosition(val);

        enemy.setxPosition(enemy.getXPosition() + (enemy.xMultiplier * enemy.getSpeed() * deltaTime));

        if (enemy.isLeftOfScreen() || enemy.isRightOfScreen()){
            enemy.xMultiplier *= -1;
        }
    }

    // Murder Hornet pattern function
    public void patternMurderHornet(Entity entity, float deltaTime) {
        // move in a circle
        // center of the circle - (200, 500)


    }

    // Karen mid boss pattern function
    public void patternKaren(Entity entity, float deltaTime) {

    }

    // Covid final boss pattern function
    public void patternCovid(Entity entity, float deltaTime) {

    }
}
