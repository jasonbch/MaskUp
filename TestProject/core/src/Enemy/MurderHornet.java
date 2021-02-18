package Enemy;

import com.badlogic.gdx.graphics.Texture;

public class MurderHornet extends Enemy{

    public MurderHornet(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public float getSpeed() {
        return 2;
    }

    @Override
    public String bullet() {
        return "Stinger";
    }

    @Override
    public float getTimeBetweenShots() {
        return  0.5f;
    }

    @Override
    public String getName() {
        return "MurderHornet";
    }

    @Override
    public Texture getImage() {
        return new Texture("MurderHornet.png");
    }
}
