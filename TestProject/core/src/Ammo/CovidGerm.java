package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class CovidGerm extends Ammo{

    public CovidGerm(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return "CovidGerm";
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
        return new Texture("CovidGerm.png");
    }
}
