package Objects.GameObject;

import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * Objects.GameObject class that has the x and y position. It also has an image and can
 * draw the image on a batch.
 */
public abstract class GameObject {
    protected final GameResources gameResources = GameResources.instance();
    protected float xPosition;  // Initial x position
    protected float yPosition;  // Initial y position

    /**
     * Return the name.
     */
    public abstract String getName();

    /**
     * Return the y position.
     *
     * @return y position
     */
    public float getYPosition() {
        return this.yPosition;
    }

    /**
     * setter for the object xPosition
     *
     * @param yPos
     */
    public void setYPosition(float yPos) {
        this.yPosition = yPos;
    }

    /**
     * Return the x position.
     *
     * @return x position
     */
    public float getXPosition() {
        return this.xPosition;
    }

    /**
     * setter for the object xPosition
     *
     * @param xPos
     */
    public void setXPosition(float xPos) {
        this.xPosition = xPos;
    }

    /**
     * Return the speed of the entity.
     */
    public abstract float getSpeed();

    /**
     * Move the entity up.
     *
     * @param deltaTime delta time.
     */
    public void moveUp(float deltaTime) {
        this.yPosition += getSpeed() * deltaTime;
    }

    /**
     * Move the entity down.
     *
     * @param deltaTime delta time.
     */
    public void moveDown(float deltaTime) {
        this.yPosition -= getSpeed() * deltaTime;
    }

    /**
     * Move the entity left.
     *
     * @param deltaTime delta time.
     */
    public void moveLeft(float deltaTime) {
        this.xPosition -= getSpeed() * deltaTime;
    }

    /**
     * Move the entity right.
     *
     * @param deltaTime delta time.
     */
    public void moveRight(float deltaTime) {
        this.xPosition += getSpeed() * deltaTime;
    }

    /**
     * \
     *
     * @return returns if the game object is above the screen.
     */
    public boolean isAboveScreen() {
        return this.yPosition >= gameResources.getWorldHeight() - getImageHeight();
    }

    /**
     * \
     *
     * @return returns if the game object is below the screen.
     */
    public boolean isBelowScreen() {
        return this.yPosition <= 0;
    }

    /**
     * \
     *
     * @return returns if the game object is left of the screen.
     */
    public boolean isLeftOfScreen() {
        return this.xPosition <= 20;
    }

    /**
     * \
     *
     * @return returns if the game object is right of the screen.
     */
    public boolean isRightOfScreen() {
        return this.xPosition + getImageWidth() >= gameResources.getScreenOneEnd();
    }

    /**
     * Return the Texture image.
     */
    public abstract Texture getTexture();

    /**
     * Return the image width.
     */
    public int getImageWidth() {
        return getTexture().getWidth();
    }

    /**
     * Return the image height.
     */
    public int getImageHeight() {
        return getTexture().getHeight();
    }
}
