package Enemy;

import Entity.Enemy;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Covid class that extends from Enemy that can move and fire.
 * Covid is the final boss of the game.
 */
public class Covid extends Enemy {
    private final String name = "Covid";
    private final float speed = 150;
    private final String bullet = "BabyCovid";
    private final float timeBetweenShot = 0.5f;
    private final Texture texture = new Texture("BigCovid.png");
    private int moveCounter = 0;
    private int maxLifespan = 60;
    private int maxHealth = 20;


    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Covid(float xPos, float yPos, String pattern) {
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

    /**
     *
     * Return maxLifeSpan
     */
    @Override
    public int getMaxLifeSpan(){
        return this.maxLifespan;
    }

    /**
     *
     * @param bulletDamage
     */
    @Override
    public void setHealth(int bulletDamage) { this.maxHealth -= bulletDamage; }
    /**
     *
     */
    @Override
    public int getHealth() { return this.maxHealth; }

}
