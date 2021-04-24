package Objects.GameObject.Enemy;

/**
 * The Covid class that extends from Entity.Enemy that can move and fire.
 * Covid is the final boss of the game.
 */
public class Covid extends Enemy {
    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Covid(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "Covid";
    }
}
