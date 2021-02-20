package Ammo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Bullet class that extends Ammo.
 */
public class Bullet extends Ammo{
    private final String name = "Bullet";
    private final String[] acceptableTargets = {"Bat", "Hornet"};
    private final float speed = 70;
    private final Texture texture = new Texture("Bullet.png");

    /**
     * Create a new instance of a Bullet at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Bullet(float xPos, float yPos) {
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

    //    @Override
    //    public int getDamage() {
    //        return 1;
    //    }
}
