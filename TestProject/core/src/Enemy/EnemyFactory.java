package Enemy;




/**
 * The factory class to create different kind of enemies.
 *
 */

public class EnemyFactory {

    /**
     * Return the enemy from the given enemy's name at the given position.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Enemy create(String enemy, float xPos, float yPos) {
        switch (enemy) {
            case "MurderHornet":
                return new MurderHornet(xPos, yPos);
            case "Bat":
                return new Bat(xPos, yPos);
            case "Karen":
                return new Karen(xPos, yPos);
            case "Covid":
                return new Covid(xPos, yPos);
        }

        return null;
    }
}
