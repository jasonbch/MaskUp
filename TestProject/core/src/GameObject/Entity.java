package GameObject;

import GameEngine.GameResources;
import GameObject.Ammo.Ammo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import java.util.ListIterator;

/**
 * The Entity abstract class that can move and fire.
 */
public abstract class Entity extends GameObject {
    protected float timeSinceLastShot = 0;
    protected String name = "GameObject";
    protected float speed = 250;
    protected String bullet = "Bullet";
    protected Texture texture = GameResources.getAssetsManager().get("CovidGerm.png", Texture.class);
    protected float timeBetweenShot = 0.5f;
    private boolean isDone = false;
    private String formationPattern = "FanFormation";

    /**
     * Create a new instance of an Entity at the xPos and yPos.
     *
     * @param xPosition initial x position.
     * @param yPosition initial y position.
     */
    public Entity(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }


    public Entity() {
    }


    /**
     * @return
     */
    public abstract int getHealth();

    /**
     * @param bulletDamage
     */
    public abstract void setHealth(int bulletDamage);

    /**
     * Returns the state
     */
    public boolean IsDone() {
        return this.isDone;
    }

    /**
     * Set state
     */
    public void setIsDone() {
        this.isDone = true;
    }


    /**
     * Update time since last shot.
     *
     * @param deltaTime The current delta time.
     */
    public void updateTimeSinceLastShot(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    /**
     * Reset time since last shot to 0.
     */
    public void resetTimeSinceLastShot() {
        timeSinceLastShot = 0;
    }

    /**
     * Set time between shots.
     */
    public void setTimeBetweenShot(float time ) {this.timeBetweenShot = time;}

    /**
     * Return True if the entity can fire, otherwise false.
     *
     * @return if the entity can fie.
     */
    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    public boolean intersects(Rectangle otherRectangle) {
        Rectangle rectangle = new Rectangle(xPosition, yPosition, getImage().getWidth(), getImage().getHeight());
        return rectangle.overlaps(otherRectangle);
    }

    public abstract boolean collide(ListIterator<Ammo> entityAmmolist);

    /**
     * Return the coordinate for shooting position.
     *
     * @return shooting position.
     */
    public GridPoint2 getShootingPosition() {
        float xShootPosition = getXPosition() + (float) getImageWidth() / 2;
        float yShootPosition = getYPosition();
        GridPoint2 shootPosition = new GridPoint2((int) xShootPosition, (int) yShootPosition);
        return shootPosition;
    }

    /**
     * Return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the speed.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Return the bullet string that the enemy fires.
     */
    public String getBullet() {
        return this.bullet;
    }

    /**
     * Return the time between shot.
     */
    public float getTimeBetweenShots() {
        return this.timeBetweenShot;
    }

    /**
     * Return the Texture image.
     */
    public Texture getImage() {
        return this.texture;
    }

    public String getFormationPattern() {
        return formationPattern;
    }

    public void setFormationPattern(String formationPattern) {
        this.formationPattern = formationPattern;
    }
}
