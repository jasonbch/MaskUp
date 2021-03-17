package Entity;

import Ammo.Ammo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ListIterator;
// Flicker the player for a couple of seconds
// Possibly delete enemy bullets

/**
 * The Player class that extends from Entity that can move and fire.
 */
public class Player extends Entity {
    private int maxHealth = 3;
    private Batch batch = new SpriteBatch();
    private boolean invulnerable;

    private long startInvulnerabilityTime;

    // Implement Singleton
    private static Player uniqueInstance = null;

    private Player() {
        invulnerable = false;
    }

    public static Player instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Player();
        }
        return uniqueInstance;
    }

    public boolean getInvulnerable() {
        return this.invulnerable;
    }

    public long getStartInvulnerabilityTime() {
        return this.startInvulnerabilityTime;
    }

    public void setInvulnerable(boolean invulnerableStatus) {
        this.invulnerable = invulnerableStatus;
        if (invulnerable) {
            this.setxPosition(Gdx.graphics.getWidth() / 2);
            this.setyPosition(10);
            this.startInvulnerabilityTime = TimeUtils.millis();
        }
    }

    /**
     * Return the name.
     *
     * @return the name of the player.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Return the speed.
     *
     * @return the speed of the player.
     */
    @Override
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Return the bullet string that the player fires.
     *
     * @return the bullet string the player use.
     */
    @Override
    public String getBullet() {
        return this.bullet;
    }

    @Override
    public int getHealth() {
        return this.maxHealth;
    }

    @Override
    public void setHealth(int bulletDamage) {
        this.maxHealth -= bulletDamage;
    }

    public void setInvulnerabilityStartTime(long time) {
        this.startInvulnerabilityTime = time;

    }

    /**
     * Return the time between shot.
     *
     * @return the time between shot of the player.
     */
    @Override
    public float getTimeBetweenShots() {
        return this.timeBetweenShot;
    }

    /**
     * Return the Texture image.
     *
     * @return the texture of the player.
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
     * TODO: Feed Gdx.input as an parameter?
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

    /**
     * Set the states of ammo, player
     * Set player health
     *
     * @param enemyAmmoList
     */
    @Override
    public boolean collide(ListIterator<Ammo> enemyAmmoList) {
        ListIterator<Ammo> iter = enemyAmmoList;

        boolean retval = false;
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            boolean didIntersect = intersects(ammo.getBoundingBox());
            if (didIntersect) {
                setInvulnerable(true);
                boolean test = getInvulnerable();
                ammo.setIsDone();
                setHealth(ammo.getBulletDamage());
                retval = true;
            }
        }

        return retval;
    }
}
