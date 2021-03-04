package GameEngine;

import Enemy.Enemy;
import Entity.Entity;
import com.badlogic.gdx.Gdx;

import java.lang.Math;

/**
 * EnemyMovementController class that controls the moving of enemies.
 */
public class EnemyMovementController {

    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();

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
    public void patternMurderHornet(Enemy enemy, float deltaTime) {
        // Move left and right across screen
        if (enemy.getXPosition() >= enemy.getWorldWidth() - enemy.getImageWidth() || enemy.getXPosition() <= 0) {
            enemy.xMultiplier *= -1;
        }

        enemy.setxPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.xMultiplier * deltaTime);

    }

    // Karen md boss pattern function
    public void patternKaren(Enemy enemy, float deltaTime) {

        if (enemy.getXPosition() >= enemy.getWorldWidth() - enemy.getImageWidth() || enemy.getXPosition() <= 0) {
            enemy.xMultiplier *= -1;
        }

        if (enemy.getYPosition() >= enemy.getWorldHeight() - enemy.getImageHeight() || enemy.getYPosition() <= 0) {
            enemy.yMultiplier *= -1;
        }


        enemy.setxPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.xMultiplier * deltaTime);
        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * enemy.yMultiplier * deltaTime);
    }

    // Covid final boss pattern function
    public void patternCovid(Enemy enemy, float deltaTime) {

        if (enemy.getYPosition() >= enemy.getWorldHeight() - enemy.getImageHeight() || enemy.getYPosition() <= 0) {
            enemy.yMultiplier *= -1;
        }

        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * enemy.yMultiplier * deltaTime);
    }

    /**
     * Exit the screen.
     */
    public void exitScreen(Enemy enemy, float deltaTime) {
        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * deltaTime);
    }
}
