package Objects.GameObject.Enemy;

import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Bat class that extends from Entity.Enemy that can move and fire.
 */
public class Bat extends Enemy {
    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Bat(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "Bat";
    }
}
