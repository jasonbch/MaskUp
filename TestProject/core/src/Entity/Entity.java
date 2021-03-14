package Entity;

import Ammo.Ammo;
import GameEngine.BulletSpawningController;
import GameEngine.GameResources;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import java.util.ListIterator;

/**
 * The Entity abstract class that can move and fire.
 */
public abstract class Entity extends GameObject {
    protected float timeSinceLastShot = 0;
    private boolean isDone = false;
    private String formationPattern = "FanFormation";
    protected String name = "Entity";
    protected float speed = 250;
    protected String bullet = "Bullet";
    protected Texture texture = new Texture("CovidGerm.png");
    protected float timeBetweenShot = 0.5f;

    /**
     * Create a new instance of an Entity at the xPos and yPos.
     *
     * @param  xPosition initial x position.
     * @param  yPosition initial y position.
     */
    public Entity(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Constructor for player
     */
    public Entity(){
        this.xPosition = gameResources.getWorldWidth()/2;
        this.yPosition = gameResources.getWorldHeight()/4;
        setFormationPattern("UpwardLinearFormation");
        this.name = "Player";
        this.texture = new Texture("Player.png");
        this.timeBetweenShot = 0.25f;
    }


    /**
     *
     * Return maxLifeSpan
     */
    public abstract int getMaxLifeSpan();

    /**
     *
     * @param bulletDamage
     */
    public abstract void setMaxLifeSpan(int bulletDamage);

    /**
     *
     * Returns the state
     */
    public boolean IsDone() { return this.isDone;}

    /**
     * Set state
     */
    public void setIsDone() { this.isDone = true;}


    /**
     * Update time since last shot.
     *
     * @param  deltaTime The current delta time.
     */
    public void updateTimeSinceLastShot(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    /**
     * Reset time since last shot to 0.
     *
     */
    public void resetTimeSinceLastShot() {
        timeSinceLastShot = 0;
    }

    /**
     * Return True if the entity can fire, otherwise false.
     *
     * @return  if the entity can fie.
     */
    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    public boolean intersects(Rectangle otherRectangle)
    {
        Rectangle rectangle = new Rectangle(xPosition,yPosition,getImage().getWidth(), getImage().getHeight());
        return rectangle.overlaps(otherRectangle);
    }

    public abstract void collide(ListIterator<Ammo> entityAmmolist);

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
