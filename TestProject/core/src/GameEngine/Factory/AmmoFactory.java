package GameEngine.Factory;

import GameEngine.Resource.GameResources;
import Objects.GameObject.Ammo.*;
import Objects.GameObject.Ammo.Ammo.PatternAttribute;
import Objects.GameObject.PowerUp;
import com.badlogic.gdx.graphics.Texture;

/**
 * The factory class to create different kind of ammo.
 */
public class AmmoFactory {
    private final Texture texture1 = GameResources.getAssetsManager().get("Stinger.png", Texture.class);
    private final Texture texture2 = GameResources.getAssetsManager().get("GreenCloud.png", Texture.class);
    private final Texture texture3 = GameResources.getAssetsManager().get("BabyCovid.png", Texture.class);
    private final Texture texture4 = GameResources.getAssetsManager().get("Bullet.png", Texture.class);
    private final Texture texture5 = GameResources.getAssetsManager().get("CovidGerm.png", Texture.class);
    private final Texture texture6 = GameResources.getAssetsManager().get("Mask.png", Texture.class);
    private final Texture texture7 = GameResources.getAssetsManager().get("Syringe.png", Texture.class);
    private final Texture texture8 = GameResources.getAssetsManager().get("StimulusPack.png", Texture.class);

    /**
     * The factory class to create different kind of ammo.
     *
     * @param Ammo the name of the ammo.
     * @param xPos initial x position.
     * @param yPos initial y position.
     */
    public Ammo create(String Ammo, float xPos, float yPos, PatternAttribute patternAttribute) {
        switch (Ammo) {
            case "Stinger":
                return new Stinger(xPos - (texture1.getWidth() / 2), yPos - texture1.getHeight(), patternAttribute);
            case "GreenCloud":
                return new GreenCloud(xPos - (texture2.getWidth() / 2), yPos - texture2.getHeight(), patternAttribute);
            case "BabyCovid":
                return new BabyCovid(xPos - (texture3.getWidth() / 2), yPos - texture3.getHeight(), patternAttribute);
            case "Bullet":
                return new Bullet(xPos - (texture4.getWidth() / 2), yPos, patternAttribute);
            case "CovidGerm":
                return new CovidGerm(xPos - (texture5.getWidth() / 2), yPos - texture5.getHeight(), patternAttribute);
            case "Mask":
                return new Mask(xPos - (texture6.getWidth() / 2), yPos, patternAttribute);
            case "Syringe":
                return new Syringe(xPos - (texture7.getWidth() / 2), yPos, patternAttribute);
            case "PowerUp":
                return new PowerUp(xPos - (texture8.getWidth() / 2), yPos - texture8.getHeight(), patternAttribute);
        }

        return null;
    }
}
