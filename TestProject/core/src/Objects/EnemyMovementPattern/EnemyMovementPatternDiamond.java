package Objects.EnemyMovementPattern;

import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.GameObject;

public class EnemyMovementPatternDiamond extends EnemyMovementPattern {

    @Override
    public String getName() {
        return "PatternDiamond";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {
        // left coordinate of diamond(start) - (50, 700)
        // top coordinate of diamond - (225, 900)
        // right coordinate of diamond - (400, 700)
        // bottom coordinate of diamond - (225, 500)
        Enemy enemy = (Enemy) obj;

        if (!enemy.getDiamondPatternStartLocation()) {
            moveToStartPos(enemy, deltaTime);
        } else {
            // were at the start location
            // now check each quadrant to find where the enemy is along the path, and move according to
            // the slope for that path
            correctOvermove(enemy);

            if (firstQuadrant(enemy)) {
                // first path logic
                // slope for path 1 is 8/7 == 1.6/1.4


                enemy.setXPosition((float) (enemy.getXPosition() + (1.4 * enemy.getXMultiplier() * enemy.getSpeed() * deltaTime)));
                enemy.setYPosition((float) (enemy.getYPosition() + (1.6 * enemy.getSpeed() * enemy.getYMultiplier() * deltaTime)));
            } else if (secondQuadrant(enemy)) {
                // second path logic
                //slope for path 2 is -8/7 == -1.6/1.4

                enemy.setXPosition((float) (enemy.getXPosition() + (1.4 * enemy.getXMultiplier() * enemy.getSpeed() * deltaTime)));
                enemy.setYPosition((float) (enemy.getYPosition() + (-1.6 * enemy.getSpeed() * enemy.getYMultiplier() * deltaTime)));
            } else if (thirdQuadrant(enemy)) {
                // third path logic
                // slope for path 3 is -8/-7 = -1.6/-1.4

                enemy.setXPosition((float) (enemy.getXPosition() + (-1.4 * enemy.getXMultiplier() * enemy.getSpeed() * deltaTime)));
                enemy.setYPosition((float) (enemy.getYPosition() + (-1.6 * enemy.getSpeed() * enemy.getYMultiplier() * deltaTime)));
            } else if (fourthQuadrant(enemy)) {
                // fourth path logic
                // slope for path 4 is 8/-7 == 1.6/-1.4

                enemy.setXPosition((float) (enemy.getXPosition() + (-1.4 * enemy.getXMultiplier() * enemy.getSpeed() * deltaTime)));
                enemy.setYPosition((float) (enemy.getYPosition() + (1.6 * enemy.getSpeed() * enemy.getYMultiplier() * deltaTime)));
            } else {
                //System.out.println("idk where im at");
                System.out.println(enemy.getXPosition() + ", " + enemy.getYPosition());
            }
        }
    }

    private boolean firstQuadrant(Enemy enemy) {
        int xPos = (int) enemy.getXPosition();
        int yPos = (int) enemy.getYPosition();
        boolean isInFirstQuad = false;
        if (xPos >= 50 && xPos < 225 && yPos >= 700 && yPos < 900) {
            //System.out.println("in first quadrant");
            isInFirstQuad = true;
        }

        return isInFirstQuad;
    }

    private boolean secondQuadrant(Enemy enemy) {
        int xPos = (int) enemy.getXPosition();
        int yPos = (int) enemy.getYPosition();
        boolean isInSecondQuad = false;
        if (xPos >= 220 && xPos < 400 && yPos >= 700 && yPos <= 905) {
           // System.out.println("in second quadrant");

            isInSecondQuad = true;
        }

        return isInSecondQuad;
    }

    private boolean thirdQuadrant(Enemy enemy) {
        int xPos = (int) enemy.getXPosition();
        int yPos = (int) enemy.getYPosition();
        boolean isInThirdQuad = false;
        if (xPos >= 220 && xPos <= 400 && yPos >= 500 && yPos <= 705) {
            //System.out.println("in third quadrant");

            isInThirdQuad = true;
        }

        return isInThirdQuad;
    }

    private boolean fourthQuadrant(Enemy enemy) {
        int xPos = (int) enemy.getXPosition();
        int yPos = (int) enemy.getYPosition();
        boolean isInFourthQuad = false;
        if (xPos >= 50 && xPos < 225 && yPos >= 500 && yPos <= 700) {
            //System.out.println("in fourth quadrant");

            isInFourthQuad = true;
        }

        return isInFourthQuad;
    }

    private void moveToStartPos(Enemy enemy, float deltaTime) {

        if ((int) enemy.getXPosition() > 50) {
            enemy.moveLeft(deltaTime);
        } else if ((int) enemy.getXPosition() < 50) {
            enemy.moveRight(deltaTime);
        } else {
            enemy.setDiamondPatternX();
        }

        if ((int) enemy.getYPosition() > 700) {
            enemy.moveDown(deltaTime);
        } else if ((int) enemy.getYPosition() < 700) {
            enemy.moveUp(deltaTime);
        } else {
            enemy.setDiamondPatternY();
        }

        if (enemy.getDiamondPatternX() && enemy.getDiamondPatternY()) {
            //System.out.println("at the start position");
            enemy.setDiamondPatternStartLocation();
        }
    }

    public void correctOvermove(Enemy enemy) {
        float xPos = enemy.getXPosition();
        float yPos = enemy.getYPosition();

        if (xPos < 50) {
            enemy.setXPosition(50);
        }
        if (xPos > 400) {
            enemy.setXPosition(400);
        }
        if (yPos < 500) {
            enemy.setYPosition(500);
        }
        if (yPos > 900) {
            enemy.setYPosition(900);
        }
    }
}