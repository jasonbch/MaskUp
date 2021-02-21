package Ammo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Mask class that extends Ammo.
 */
public class Mask extends Ammo{
    private final String name = "Mask";
    private final String[] acceptableTargets = {"Karen"};
    private final float speed = 150;
    private final Texture texture = new Texture("Mask.png");

    /**
     * Create a new instance of a Mask at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Mask(float xPos, float yPos) {
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