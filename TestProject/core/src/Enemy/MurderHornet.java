package Enemy;

import com.badlogic.gdx.graphics.Texture;

/**
 * The MurderHornet class that extends from Enemy that can move and fire.
 */
public class MurderHornet extends Enemy{
    private final String name = "MurderHornet";
    private final float speed = 2;
    private final String bullet = "Stinger";
    private final float timeBetweenShot = 0.25f;
    private final Texture texture = new Texture("MurderHornet.png");

    /**
     * Create a new instance of a MurderHornet at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public MurderHornet(float xPos, float yPos) {
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
     * Return the bullet string that the enemy fires.
     */
    @Override
    public String bullet() {
        return this.bullet;
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
}