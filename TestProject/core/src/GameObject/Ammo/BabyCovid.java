package GameObject.Ammo;

import GameEngine.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * BabyCovid class that extends Entity.Ammo.
 */
public class BabyCovid extends Ammo {
    /**
     * Create a new instance of a Entity.Ammo at the xPos and yPos.
     *
     * @param xPosition        initial x position.
     * @param yPosition        initial y position.
     * @param patternAttribute
     */
    public BabyCovid(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        super(xPosition, yPosition, patternAttribute);
        this.name = "BabyCovid";
        this.speed = 300;
        this.acceptableTargets = new String[]{"Player"};
        this.texture = GameResources.getAssetsManager().get("BabyCovid.png", Texture.class);
        this.damage = 1;
    }
}