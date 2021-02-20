package Ammo;

import com.badlogic.gdx.graphics.Texture;

/**
 * GreenCloud class that extends Ammo.
 */
public class GreenCloud extends Ammo{
    private final String name = "GreenCloud";
    private final String[] acceptableTargets = {"Player"};
    private final float speed = 45;
    private final Texture texture = new Texture("GreenCloud.png");

    /**
     * Create a new instance of a GreenCloud at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public GreenCloud(float xPos, float yPos) {
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
