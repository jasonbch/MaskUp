package Objects.GameObject;

import GameEngine.Resource.GameResources;
import Objects.GameObject.Ammo.Ammo;
import com.badlogic.gdx.graphics.Texture;

public class PowerUp extends Ammo {

    /**
     * Create a new instance of a Entity.Ammo at the xPos and yPos.
     *
     * @param xPosition        initial x position.
     * @param yPosition        initial y position.
     * @param patternAttribute
     */
    public PowerUp(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        super(xPosition, yPosition, patternAttribute);
        this.name = "PowerUp";
        this.speed = 12;
        this.acceptableTargets = new String[]{"Player"};
        this.texture = GameResources.getAssetsManager().get("StimulusPack.png", Texture.class);
        this.damage = -1;
    }
}
