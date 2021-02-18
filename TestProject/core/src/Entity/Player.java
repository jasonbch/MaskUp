package Entity;

import Ammo.Ammo;
import Entity.Entity;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {

    public Player(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public float getSpeed() {
        return 100;
    }


    @Override
    public float getTimeBetweenShots() {
        return 0.25f;
    }

    @Override
    public String getName() {
        return "Player";
    }


    @Override
    public Texture getImage() {
        return new Texture("Player.png");
    }

    public Ammo fire(String bullet)
    {
        Ammo ammo = factory.create(bullet, xPos, yPos);
        timeSinceLastShot = 0;
        return  ammo;
    }

    @Override
    public Ammo fire() {
        return null;
    }

}
