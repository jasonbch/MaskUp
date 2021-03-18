package Enemy;

import GameEngine.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Karen class that extends from Enemy that can move and fire.
 */
public class Karen extends Enemy {

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Karen(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "Karen";
        this.speed = 150;
        this.bullet = "GreenCloud";
        this.texture = GameResources.getAssetsManager().get("Karen.png", Texture.class);
        this.timeBetweenShot = 0.8f;
        setFormationPattern("CircularFormation");
        this.maxLifespan = 45;
        this.maxHealth = 10;
    }
}
