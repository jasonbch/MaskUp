package Ammo;

import Entity.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Abstract class for the Ammo that can be shoot from the player
 * or the enemies.
 */
public abstract class Ammo extends GameObject {
    protected final String[] acceptableTargets = {};
    private PatternAttribute patternAttribute;

    public PatternAttribute getPatternAttribute() {
        return this.patternAttribute;
    }

    /**
     * Create a new instance of a Ammo at the xPos and yPos.
     *
     * @param  xPosition initial x position.
     * @param  yPosition initial y position.
     */
    public Ammo(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.patternAttribute = patternAttribute;
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
            if (target.equals(name)) {
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

        if (currentBullet == "Mask"
                || currentBullet == "Syringe"
                || currentBullet == "Bullet" ) {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Return True if the bullet is boss's bullet, otherwise False.
     */
    public boolean isBossBullet() {
        boolean returnValue = false;
        String currentBullet = this.getName();

        if (currentBullet == "BabyCovid"
                || currentBullet == "GreenCloud") {
            returnValue = true;
        }

        return returnValue;
    }

    public Rectangle getBoundingBox()
    {
        return new Rectangle(xPosition,yPosition, getImageWidth(), getImageHeight() - 10);
    }

    /**
     * Draw the object.
     *
     * @param batch the current batch.
     */
    public void draw(Batch batch) {
        Texture image = getImage();
        batch.draw(getImage(), getXPosition(), getYPosition(), image.getWidth(), image.getHeight());
    }

    public static class PatternAttribute {
        private String name;
        private float x;
        private float y;

        public String getName() {
            return this.name;
        }

        public float getXMultiplier() {
            return this.x;
        }

        public float getYMultiplier() {
            return this.y;
        }

        public PatternAttribute(String name, float x, float y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}
