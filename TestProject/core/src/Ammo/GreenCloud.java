package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class GreenCloud extends Ammo{
    public GreenCloud(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return "GreenCloud";
    }


    @Override
    public String[] getAcceptableTargets() {
        return new String[]{"Player"};
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public Texture getImage() {
        return null;
    }
}
