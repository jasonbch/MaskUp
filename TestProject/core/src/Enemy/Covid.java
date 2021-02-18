package Enemy;

import com.badlogic.gdx.graphics.Texture;

public class Covid extends Enemy{
    @Override
    public float getSpeed() {
        return 2;
    }

    @Override
    public String bullet() {
        return "BabyCovid";
    }

    @Override
    public float getTimeBetweenShots() {
        return  0.5f;
    }

    @Override
    public String getName() {
        return "Covid";
    }

    public Covid(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public Texture getImage() {
        return new Texture("BigCovid.png");
    }


}
