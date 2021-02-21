package Ammo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Abstract class for the Ammo that can be shoot from the player
 * or the enemies.
 */
public abstract class Ammo {
    protected final String name = "Ammo";
    protected final String[] acceptableTargets = {};
    protected final float speed = 150;
    protected final Texture texture = new Texture("Bullet.png");

    public float xPos;  // Initial x position
    public float yPos;  // Initial y position

    /**
     * Create a new instance of a Ammo at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Ammo(float xPos, float yPos ) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Return the name.
     */
    public abstract String getName();

    /**
     * Return the array of acceptable targets.
     */
    public abstract String[] getAcceptableTargets();

    /**
     * Return the speed.
     */
    public abstract float getSpeed();

    /**
     * Return the Texture image.
     */
    public abstract Texture getImage();

    /**
     * Return True if the given name is an acceptable target, otherwise False.
     */
    public Boolean isAcceptableTarget(String name) {
        for (String target: getAcceptableTargets()) {
            if(target.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return True if the bullet is enemy's bullet, otherwise False.
     */
    public boolean isEnemyBullet() {
        boolean returnValue = true;
        String currentBullet = this.getName();

        if(currentBullet == "Mask"
                || currentBullet == "Syrenge"
                || currentBullet == "Bullet" ) {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Draw the object.
     *
     * @param batch the current batch.
     */
    public void draw(Batch batch) {
        int yOffset = 10;

        if(isEnemyBullet()) {
            yOffset = 0;
        }

        Texture image = getImage();
        batch.draw(getImage(), xPos + 4, yPos + yOffset, image.getWidth(), image.getHeight());
    }

    /**
     * Return the world width.
     */
    public int getWordWidth() {
        return Gdx.graphics.getWidth();
    }

    /**
     * Return the world height.
     */
    public int getWorldHeight() {
        return Gdx.graphics.getWidth();
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
