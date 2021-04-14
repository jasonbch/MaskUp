package Objects.GameObject.Enemy;

import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * The MurderHornet class that extends from Entity.Enemy that can move and fire.
 */
public class MurderHornet extends Enemy {
    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern movement pattern for enemy.
     */
    public MurderHornet(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "MurderHornet";
    }
}
