package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class Mask extends Ammo{

    public Mask(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return "Mask";
    }

    @Override
    public String[] getAcceptableTargets() {
        return new String[]{"Karen"};
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public Texture getImage() {
        return new Texture("Mask.png");
    }
}
