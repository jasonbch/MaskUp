package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class Stinger extends Ammo {

    public Stinger(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return "Stinger";
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
