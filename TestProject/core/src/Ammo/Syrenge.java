package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class Syrenge extends Ammo{

    public Syrenge(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return "Syrenge";
    }

    @Override
    public String[] getAcceptableTargets() {
        return new String[]{"Covid"};
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public Texture getImage() {
        return new Texture("Syrenge.png");
    }
}
