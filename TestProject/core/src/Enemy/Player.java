package Enemy;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Enemy{

    public Player(float xPos, float yPos) {
        super(xPos, yPos);
    }



    @Override
    public float getSpeed() {
        return 2;
    }

    @Override
    public String bullet() {
        return "Bullet";
    }

    @Override
    public float getTimeBetweenShots() {
        return 0.5f;
    }

    @Override
    public String getName() {
        return "Player";
    }


    @Override
    public Texture getImage() {
        return new Texture("Player.png");
    }
}
