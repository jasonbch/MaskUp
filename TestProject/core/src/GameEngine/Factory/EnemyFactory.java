package GameEngine.Factory;

import Objects.GameObject.Enemy.*;

/**
 * The factory class to create different kind of enemies.
 */
public class EnemyFactory {

    /**
     * Return the enemy from the given enemy's name at the given position.
     *
     * @param xPos initial x position.
     * @param yPos initial y position.
     */
    public Enemy create(String enemy, float xPos, float yPos, String pattern) {
        switch (enemy) {
            case "MurderHornet":
                return new MurderHornet(xPos, yPos, pattern);
            case "Bat":
                return new Bat(xPos, yPos, pattern);
            case "Karen":
                return new Karen(xPos, yPos, pattern);
            case "Covid":
                return new Covid(xPos, yPos, pattern);
        }
        return null;
    }
}
