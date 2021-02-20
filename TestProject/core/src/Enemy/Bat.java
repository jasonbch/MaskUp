package Enemy;

import com.badlogic.gdx.graphics.Texture;

/**
 * The Bat class that extends from Enemy that can move and fire.
 */
public class Bat extends Enemy {
    private final String name = "Bat";
    private final float speed = 70;
    private final String bullet = "CovidGerm";
    private final float timeBetweenShot = 0.5f;
    private final Texture texture = new Texture("Bat.png");
    private int moveCounter = 0;
    private boolean isDone = false;

    /**
     * Create a new instance of a Bat at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Bat(float xPos, float yPos) {
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
        if(isDone) {
            this.exitScreen(deltaTime);
        }
        else {
            if (this.xPos >= 65 || this.xPos <= 0) {
                xMultiplier *= -1;
                moveCounter++;
            }

            if (this.yPos >= 120 || this.yPos <= 35) {
                yMultiplier *= -1;
                moveCounter++;
            }

            if(moveCounter == 10)
            {
                isDone = true;
            }

            this.xPos += (this.speed * xMultiplier) * deltaTime;
            this.yPos += (this.speed * yMultiplier) * deltaTime;
        }
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
