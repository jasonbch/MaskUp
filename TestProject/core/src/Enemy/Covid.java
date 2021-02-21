package Enemy;

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
    private boolean isDone = false;

    /**
     * Create a new instance of a Covid at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Covid(float xPos, float yPos) {
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
    public void updateMovement(float deltaTime) {
        if (isDone) {
            this.exitScreen(deltaTime);
        }
        else {
            // move up and down the screen
            if (this.xPos >= getWordWidth() - getImageWidth() || this.xPos <= 0) {
                yMultiplier *= -1;
                moveCounter++;
            }

            if (moveCounter == 7) {
                isDone = true;
            }

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
