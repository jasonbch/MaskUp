package Objects.GameObject.Ammo;

import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * Syringe class that extends Entity.Ammo.
 */
public class Syringe extends Ammo {
    /**
     * Create a new instance of a Entity.Ammo at the xPos and yPos.
     *
     * @param xPosition        initial x position.
     * @param yPosition        initial y position.
     * @param patternAttribute
     */
    public Syringe(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        super(xPosition, yPosition, patternAttribute);
        this.name = "Syringe";
        this.speed = 700;
        this.acceptableTargets = new String[]{"Covid"};
        this.texture = GameResources.getAssetsManager().get("Syringe.png", Texture.class);
        this.damage = 1;
    }
}