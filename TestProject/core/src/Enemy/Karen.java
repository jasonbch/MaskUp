package Enemy;

import Ammo.Ammo;
import com.badlogic.gdx.graphics.Texture;

public class Karen extends Enemy{

    public Karen(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public String bullet() {
        return "GreenCloud";
    }

    @Override
    public float getTimeBetweenShots() {
        return  0.5f;
    }

    @Override
    public String getName() {
        return "Karen";
    }

    @Override
    public Texture getImage() {
        return new Texture("Covid.png");
    }
}
