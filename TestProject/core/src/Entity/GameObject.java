package Entity;

import GameEngine.GameResources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * GameObject class that has the x and y position. It also has an image and can
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
     * Return the x position.
     *
     * @return x position
     */
    public float getXPosition() {
        return this.xPosition;
    }

    /**
     * Return the speed of the entity.
     */
    public abstract float getSpeed();

    /**
     * setter for the object xPosition
     * @param xPos
     */
    public void setxPosition(float xPos)
    {
        this.xPosition = xPos;
    }

    /**
     * setter for the object xPosition
     * @param yPos
     */
    public void setyPosition(float yPos)
    {
        this.yPosition = yPos;
    }

    /**
     * Move the entity up.
     *
     * @param  deltaTime delta time.
     */
    public void moveUp(float deltaTime) {
        this.yPosition += getSpeed() * deltaTime;
    }

    /**
     * Move the entity down.
     *
     * @param  deltaTime delta time.
     */
    public void moveDown(float deltaTime) {
        this.yPosition -= getSpeed() * deltaTime;
    }

    /**
     * Move the entity left.
     *
     * @param  deltaTime delta time.
     */
    public void moveLeft(float deltaTime) {
        this.xPosition -= getSpeed() * deltaTime;
    }

    /**
     * Move the entity right.
     *
     * @param  deltaTime delta time.
     */
    public void moveRight(float deltaTime) {
        this.xPosition += getSpeed() * deltaTime;
    }

    /**\
     * @return returns if the game object is above the screen.
     */
    public boolean isAboveScreen() {
        boolean retVal = false;
        if (this.yPosition >= getWorldHeight() - getImageHeight()) {
            retVal = true;
        }

        return retVal;
    }

    /**\
     * @return returns if the game object is below the screen.
     */
    public boolean isBelowScreen() {
        boolean retVal = false;
        if (this.yPosition <= 0) {
            retVal = true;
        }

        return retVal;
    }

    /**\
     * @return returns if the game object is left of the screen.
     */
    public boolean isLeftOfScreen() {
        return this.xPosition <= 20;
    }

    /**\
     * @return returns if the game object is right of the screen.
     */
    public boolean isRightOfScreen() {
        return this.xPosition >= getWorldWidth() - getImageWidth() - 20;
    }

    /**
     * Return the Texture image.
     */
    public abstract Texture getImage();

    /**
     * Return the world width.
     */
    public int getWorldWidth() {
        return Gdx.graphics.getWidth();
    }

    /**
     * Return the world height.
     */
    public int getWorldHeight() {
        return Gdx.graphics.getHeight();
    }

    /**
     * Return the image width.
     */
    public int getImageWidth() {
        return getImage().getWidth();
    }

    /**
     * Return the image height.
     */
    public int getImageHeight() {
        return getImage().getHeight();
    }
}
