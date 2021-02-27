package Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;

/**
 * The Player class that extends from Entity that can move and fire.
 */
public class Player extends Entity {
    private final String name = "Player";
    private final float speed = 250;
    private final String bullet = "Bullet";
    private final float timeBetweenShot = 0.25f;
    private final Texture texture = new Texture("Player.png");

    /**
     * Create a new instance of a Player at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Player(float xPos, float yPos) {
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

    /**
     * Return the coordinate for shooting position.
     *
     * @return shooting position.
     */
    public GridPoint2 getShootingPosition() {
        float xShootPosition = getXPosition() + getImageWidth() / 2;
        float yShootPosition = getYPosition() + getImageHeight();
        GridPoint2 shootPosition = new GridPoint2((int) xShootPosition, (int) yShootPosition);
        return shootPosition;
    }

    /**
     * Restrict the player position inside the screen.
     * Move the player accordingly to the key presses.
     *
     * @param deltaTime the delta time
     */
    public void movePlayer(float deltaTime) {
        // Check player movement
        // Restrict player movement in the screen
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && getXPosition() > 0) {
            // Move left
            moveLeft(deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && getXPosition() < getWorldWidth() - getImageWidth()) {
            // Move right
            moveRight(deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && getYPosition() < getWorldHeight() - getImageHeight()) {
            // Move up
            moveUp(deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && getYPosition() > 0) {
            // Move down
            moveDown(deltaTime);
        }
    }
}
