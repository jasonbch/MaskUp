package Ammo;

/**
 * The factory class to create different kind of ammo.
 *
 */
public class AmmoFactory {

    /**
     * The factory class to create different kind of ammo.
     *
     * @param  Ammo the name of the ammo.
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Ammo create(String Ammo, float xPos, float yPos) {
        switch(Ammo) {
            case "Stinger":
                return new Stinger(xPos, yPos);
            case "GreenCloud":
                return new GreenCloud(xPos, yPos);
            case "BabyCovid":
                return new BabyCovid(xPos, yPos);
            case "Bullet":
                return new Bullet(xPos, yPos);
            case "CovidGerm":
                return new CovidGerm(xPos, yPos);
            case "Mask":
                return new Mask(xPos, yPos);
            case "Syringe":
                return new Syringe(xPos, yPos);
        }
        return null;
    }
}
