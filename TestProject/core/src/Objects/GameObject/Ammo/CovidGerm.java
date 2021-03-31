package Objects.GameObject.Ammo;

import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * CovidGerm class that extends Entity.Ammo.
 */
public class CovidGerm extends Ammo {
    /**
     * Create a new instance of a Entity.Ammo at the xPos and yPos.
     *
     * @param xPosition        initial x position.
     * @param yPosition        initial y position.
     * @param patternAttribute
     */
    public CovidGerm(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        super(xPosition, yPosition, patternAttribute);
        this.name = "CovidGerm";
        this.speed = 450;
        this.acceptableTargets = new String[]{"Player"};
        this.texture = GameResources.getAssetsManager().get("CovidGerm.png", Texture.class);
        this.damage = 1;
    }
}
