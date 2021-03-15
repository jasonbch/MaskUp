package Entity;

import Ammo.Ammo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ListIterator;
// Flicker the player for a couple of seconds
// Possibly delete enemy bullets
/**
 * The Player class that extends from Entity that can move and fire.
 */
public class Player extends Entity {
    private int maxHealth = 3;
    private Batch batch = new SpriteBatch();
    private boolean invulnerable = false;

    // Implement Singleton
    private static Player uniqueInstance = null;

    public static Player instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Player();
        }
        return uniqueInstance;
    }

    public boolean getInvulnerable() { return this.invulnerable; }

    public void setInvulnerable() { this.invulnerable = true; }

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
    public int getHealth() { return this.maxHealth; }

    @Override
    public void setHealth(int bulletDamage) { this.maxHealth-= bulletDamage; }

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
    public void collide(ListIterator<Ammo> enemyAmmoList) {
        ListIterator<Ammo> iter = enemyAmmoList;
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            if (intersects(ammo.getBoundingBox()))
            {
                setInvulnerable();
                ammo.setIsDone();
                setHealth(ammo.getBulletDamage());
            }
        }
    }
}
