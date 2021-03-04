package Ammo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Syringe class that extends Ammo.
 */
public class Syringe extends Ammo{
    private final String name = "Syringe";
    private final String[] acceptableTargets = {"Covid"};
    private final float speed = 500;
    private final Texture texture = new Texture("Syringe.png");

    /**
     * Create a new instance of a Syringe at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Syringe(float xPos, float yPos) {
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