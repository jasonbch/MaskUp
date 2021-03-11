package Enemy;

import Entity.Enemy;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Bat class that extends from Enemy that can move and fire.
 */
public class Bat extends Enemy {
    private final String name = "Bat";
    private final float speed = 250;
    private final String bullet = "CovidGerm";
    private final float timeBetweenShot = 0.5f;
    private final Texture texture = new Texture("Bat.png");
    private int moveCounter = 0;
    private boolean isDone = false;
    private int maxLifespan = 10;

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Bat(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
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

    @Override
    public int getMaxLifespan(){
        return this.maxLifespan;
    }
}
