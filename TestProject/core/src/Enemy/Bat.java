package Enemy;

import com.badlogic.gdx.graphics.Texture;

public class Bat extends Enemy{

    public Bat(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public float getSpeed() {
        return  2;
    }

    @Override
    public String bullet() {
        return "CovidGerm";
    }

    @Override
    public float getTimeBetweenShots() {
        return 0.5f;
    }

    @Override
    public String getName() {
        return "Bat";
    }

    @Override
    public Texture getImage() {
        return new Texture("Bat.png");
    }
}
