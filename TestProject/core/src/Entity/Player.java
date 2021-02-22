package Entity;

import Ammo.Ammo;
import Entity.Entity;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Player class that extends from Entity that can move and fire.
 */
public class Player extends Entity {
    private final String name = "Player";
    private final float speed = 250;
    private final float timeBetweenShot = 0.25f;
    private final Texture texture = new Texture("Player.png");

    /**
     * Create a new instance of a Player at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Player(float xPos, float yPos) {
        super(xPos, yPos);
    }

    /**
     * Return the name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Return the speed.
     */
    @Override
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Return the time between shot.
     */
    @Override
    public float getTimeBetweenShots() {
        return this.timeBetweenShot;
    }

    /**
     * Return the Texture image.
     */
    @Override
    public Texture getImage() {
        return this.texture;
    }

//    /**
//     * Return the given bullet ammo that the entity fires.
//     *
//     * @param  bullet  The name of the type of bullet.
//     * @return The ammo that the entity fires.
//     */
//    public Ammo fire(String bullet) {
//        Ammo ammo = factory.create(bullet, xPos + (getImageWidth() / 2), yPos + getImageHeight());
//        timeSinceLastShot = 0;
//        return  ammo;
//    }

    /**
     * Return the ammo that the entity fires.
     */
    @Override
    public Ammo fire() {
        return null;
    }
}
