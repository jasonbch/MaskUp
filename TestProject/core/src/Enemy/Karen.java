package Enemy;

import com.badlogic.gdx.graphics.Texture;

/**
 * The Karen class that extends from Enemy that can move and fire.
 */
public class Karen extends Enemy{
    private final String name = "Karen";
    private final float speed = 50;
    private final String bullet = "GreenCloud";
    private final float timeBetweenShot = 0.5f;
    private final Texture texture = new Texture("Karen.png");

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
     * Update the enemies position.
     */
    @Override
    public void updateMovement(float deltaTime)
    {
        if(this.xPos >= 72 || this.xPos <= 0)
        {
            xMultiplier *= -1;
        }

        if(this.yPos >= 128 || this.yPos <= 35)
        {
            yMultiplier *= -1;
        }

        this.xPos += (this.speed * xMultiplier) * deltaTime;
        this.yPos += (this.speed * yMultiplier) * deltaTime;
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
