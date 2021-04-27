package Objects.GameObject;

import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Resource.GameResources;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Enemy.Enemy;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Objects.GameObject class that has the x and y position. It also has an image and can
 * draw the image on a batch.
 */
public abstract class GameObject implements GameSubject {
    protected final GameResources gameResources = GameResources.instance();
    protected float xPosition;
    protected float yPosition;
    protected ArrayList<GameObserver> myObs = new ArrayList<>();

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
        if (this instanceof Ammo) {
            if (this.getYPosition() > gameResources.getWorldHeight() || this.getYPosition() < 0) {
                this.notifyGameObserver("deleteAmmo");
            }
        } else if (this instanceof Enemy) {
            if (this.getYPosition() > gameResources.getWorldHeight() + 100 || this.getYPosition() < 0) {
                ((Enemy) this).setIsDone();
            }
        }
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
        if (this instanceof Ammo) {
            if ((this.getXPosition() + this.getImageWidth()) > (gameResources.getScreenOneEnd()) || this.getXPosition() < 0) {
                this.notifyGameObserver("deleteAmmo");
            }
        }
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
     * @return returns if the game object is above the screen.
     */
    public boolean isAboveScreen() {
        return this.yPosition >= gameResources.getWorldHeight() - getImageHeight();
    }

    /**
     * @return returns if the game object is below the screen.
     */
    public boolean isBelowScreen() {
        return this.yPosition <= 0;
    }

    /**
     * @return returns if the game object is left of the screen.
     */
    public boolean isLeftOfScreen() {
        return this.xPosition <= 20;
    }

    /**
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

    @Override
    public void attachGameObserver(GameObserver o) {
        this.myObs.add(o);
    }

    @Override
    public void detachGameObserver(GameObserver o) {
        this.myObs.remove(o);
    }

    @Override
    public void notifyGameObserver(String args) {
        for (int i = 0; i < this.myObs.size(); i++) {
            if (myObs.get(i) != null) {
                this.myObs.get(i).update(this, args);
            }
        }
    }
}
