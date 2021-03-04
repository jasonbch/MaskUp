package Enemy;

import com.badlogic.gdx.graphics.Texture;

/**
 * The Karen class that extends from Enemy that can move and fire.
 */
public class Karen extends Enemy{
    private final String name = "Karen";
    private final float speed = 150;
    private final String bullet = "GreenCloud";
    private final float timeBetweenShot = 0.5f;
    private final Texture texture = new Texture("Karen.png");
    private int moveCounter = 0;
    private boolean isDone = false;

    /**
     * Create a new instance of a Karen at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Karen(float xPos, float yPos) {
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
    public String getBullet() {
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
