package Ammo;

import com.badlogic.gdx.graphics.Texture;

/**
 * The factory class to create different kind of ammo.
 */
public class AmmoFactory {
    private final Texture texture1 = new Texture("Stinger.png");
    private final Texture texture2 = new Texture("GreenCloud.png");
    private final Texture texture3 = new Texture("BabyCovid.png");
    private final Texture texture4 = new Texture("Bullet.png");
    private final Texture texture5 = new Texture("CovidGerm.png");
    private final Texture texture6 = new Texture("Mask.png");
    private final Texture texture7 = new Texture("Syringe.png");

    /**
     * The factory class to create different kind of ammo.
     *
     * @param  Ammo the name of the ammo.
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Ammo create(String Ammo, float xPos, float yPos) {
        switch (Ammo) {
            case "Stinger":
                return new Stinger(xPos - (texture1.getWidth() / 2), yPos);
            case "GreenCloud":
                return new GreenCloud(xPos - (texture2.getWidth() / 2), yPos);
            case "BabyCovid":
                return new BabyCovid(xPos - (texture3.getWidth() / 2), yPos);
            case "Bullet":
                return new Bullet(xPos - (texture4.getWidth() / 2), yPos);
            case "CovidGerm":
                return new CovidGerm(xPos - (texture5.getWidth() / 2), yPos);
            case "Mask":
                return new Mask(xPos - (texture6.getWidth() / 2), yPos);
            case "Syringe":
                return new Syringe(xPos - (texture7.getWidth() / 2), yPos);
        }
        return null;
    }
}
