package Ammo;

public class AmmoFactory {

    public Ammo create(String Ammo, float xPos, float yPos)
    {
        switch(Ammo)
        {
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
            case "Syrenge":
                return new Syrenge(xPos, yPos);
        }
        return null;
    }


}
