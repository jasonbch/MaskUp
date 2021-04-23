package Objects.GameObject.Enemy;

/**
 * The Karen class that extends from Entity.Enemy that can move and fire.
 */
public class Karen extends Enemy {
    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Karen(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "Karen";
    }
}
