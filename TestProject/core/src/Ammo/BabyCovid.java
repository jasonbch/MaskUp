package Ammo;

import com.badlogic.gdx.graphics.Texture;

/**
 * BabyCovid class that extends Ammo.
 */
public class BabyCovid extends Ammo {
    private final String name = "BabyCovid";
    private final String[] acceptableTargets = {"Player"};
    private final float speed = 150;
    private final Texture texture = new Texture("BabyCovid.png");

    /**
     * Create a new instance of a BabyCovid at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public BabyCovid(float xPos, float yPos) {
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
     * Return the array of acceptable targets.
     */
    @Override
    public String[] getAcceptableTargets() {
        return this.acceptableTargets;
    }

    /**
     * Return the speed.
     */
    @Override
    public float getSpeed() {

        return this.speed;
    }

    /**
     * Return the Texture image.
     */
    @Override
    public Texture getImage() {
        return this.texture;
    }
}
