package Ammo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Stinger class that extends Ammo.
 */
public class Stinger extends Ammo {
    private final String name = "Stinger";
    private final String[] acceptableTargets = {"Player"};
    private final float speed = 300;
    private final Texture texture = new Texture("Stinger.png");
    private int damage = 1;

    /**
     * Create a new instance of a Ammo at the xPos and yPos.
     *
     * @param xPosition        initial x position.
     * @param yPosition        initial y position.
     * @param patternAttribute
     */
    public Stinger(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        super(xPosition, yPosition, patternAttribute);
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

    /**
     *
     * Return damage
     */
    @Override
    public int getBulletDamage() { return this.damage; }
}