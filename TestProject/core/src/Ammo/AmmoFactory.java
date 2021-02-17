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
            default:
                return new Bullet(xPos, yPos);
        }
    }


}
