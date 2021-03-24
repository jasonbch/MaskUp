package GameObject.Ammo;

import GameEngine.GameResources;
import GameObject.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Abstract class for the Entity.Ammo that can be shoot from the player
 * or the enemies.
 */
public abstract class Ammo extends GameObject {
    protected String name = "GameObject/Ammo";
    protected float speed = 0;
    protected String[] acceptableTargets = {};
    protected Texture texture = GameResources.getAssetsManager().get("Bullet.png", Texture.class);
    protected int damage = 0;
    protected PatternAttribute patternAttribute;
    protected boolean isDone = false;

    /**
     * Create a new instance of a Entity.Ammo at the xPos and yPos.
     *
     * @param xPosition initial x position.
     * @param yPosition initial y position.
     */
    public Ammo(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.patternAttribute = patternAttribute;
    }

    public PatternAttribute getPatternAttribute() {
        return this.patternAttribute;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setIsDone() {
        this.isDone = true;
    }

    /**
     * Return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the array of acceptable targets.
     */
    public String[] getAcceptableTargets() {
        return this.acceptableTargets;
    }

    /**
     * Return the speed.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Return the speed.
     */
    public float setSpeed(float speed) {
        return this.speed = speed;
    }

    /**
     * Return the Texture image.
     */
    @Override
    public Texture getImage() {
        return this.texture;
    }

    /**
     * Return damage
     */
    public int getBulletDamage() {
        return this.damage;
    }

    /**
     * Return ammo bounding box
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(xPosition, yPosition, getImageWidth(), getImageHeight() - 10);
    }

    public static class PatternAttribute {
        private final String name;
        private final float x;
        private final float y;

        public PatternAttribute(String name, float x, float y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return this.name;
        }

        public float getXMultiplier() {
            return this.x;
        }

        public float getYMultiplier() {
            return this.y;
        }
    }
}
