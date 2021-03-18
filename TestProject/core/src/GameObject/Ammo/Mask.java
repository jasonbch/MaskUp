package GameObject.Ammo;

import GameEngine.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * Mask class that extends Entity.Ammo.
 */
public class Mask extends Ammo {
    /**
     * Create a new instance of a Entity.Ammo at the xPos and yPos.
     *
     * @param xPosition        initial x position.
     * @param yPosition        initial y position.
     * @param patternAttribute
     */
    public Mask(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        super(xPosition, yPosition, patternAttribute);
        this.name = "Mask";
        this.speed = 300;
        this.acceptableTargets = new String[]{"Karen"};
        this.texture = GameResources.getAssetsManager().get("Mask.png", Texture.class);
        this.damage = 1;
    }
}