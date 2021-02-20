package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class BabyCovid extends Ammo{

    public BabyCovid(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return "BabyCovid";
    }

    @Override
    public String[] getAcceptableTargets() {
        return new String[]{"Player"};
    }

    @Override
    public float getSpeed() {
        return 45;
    }

    @Override
    public Texture getImage() {
        return new Texture("BabyCovid.png");
    }
}
