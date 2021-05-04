package Objects.GameObject;

import GameEngine.Resource.GameResources;
import Objects.GameObject.Ammo.Ammo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ListIterator;
// Flicker the player for a couple of seconds
// Possibly delete enemy bullets

/**
 * The Player class that extends from Entity that can move and fire.
 */
public class Player extends Entity {
    // Implement Singleton
    private static Player uniqueInstance = null;
    private boolean invulnerable;
    private long startInvulnerabilityTime;
    private int defaultKeySet[] = {Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN};
    private int arrowKeySet[] = {Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN};
    private int wasdKeySet[] = {Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S};
    private boolean isGod;

    /**
     * Constructor for player
     */
    private Player() {
        super();
        this.xPosition = (gameResources.getScreenOneEnd()) / 2 - (getImageWidth());
        this.yPosition = gameResources.getWorldHeight() / 6;

        // Initialize
        initialize();

        // Pre-defined attributes
        this.name = "Player";
        this.invulnerable = false;
    }

    public static Player instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Player();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize() {
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(gameResources.getGameJSON()));

        // Initialize all the waves
        JsonValue element = base.get("entities").get("Player");
        this.maxHealth = element.getInt("maxHealth");
        this.speed = element.getInt("speed");
        this.bullet = element.getString("bullet");
        this.texture = GameResources.getAssetsManager().get(element.getString("texture"), Texture.class);
        this.timeBetweenShot = element.getFloat("timeBetweenShot");
        this.bulletFormation = element.getString("bulletFormation");
    }

    public boolean isInvulnerable() {
        return this.invulnerable;
    }

    public void setInvulnerable(boolean invulnerableStatus) {
        this.invulnerable = invulnerableStatus;

        if (invulnerable) {
            this.setXPosition((gameResources.getScreenOneEnd()) / 2 - (getImageWidth()) + 30);
            this.setYPosition(gameResources.getWorldHeight() / 6);
            this.startInvulnerabilityTime = TimeUtils.millis();
        }
    }

    public long getStartInvulnerabilityTime() {
        return this.startInvulnerabilityTime;
    }

    public void setInvulnerabilityStartTime(long time) {
        this.startInvulnerabilityTime = time;
    }

    public boolean isGod() {
        return this.isGod;
    }

    public void setGodMode(boolean mode) {
        this.isGod = mode;
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
        if (Gdx.input.isKeyPressed(defaultKeySet[0]) && getXPosition() > 0) {
            // Move left
            moveLeft(deltaTime);
        }
        if (Gdx.input.isKeyPressed(defaultKeySet[1]) && getXPosition() < gameResources.getScreenOneEnd() - getImageWidth()) {
            // Move right
            moveRight(deltaTime);
        }
        if (Gdx.input.isKeyPressed(defaultKeySet[2]) && getYPosition() < gameResources.getWorldHeight() - getImageHeight()) {
            // Move up
            moveUp(deltaTime);
        }
        if (Gdx.input.isKeyPressed(defaultKeySet[3]) && getYPosition() > 0) {
            // Move down
            moveDown(deltaTime);
        }
    }

    public void changeDefaultKeySet() {
        if (isDefaultKeySet()) {
            this.defaultKeySet = wasdKeySet;
        } else {
            this.defaultKeySet = arrowKeySet;
        }
    }

    public boolean isDefaultKeySet() {
        if (this.defaultKeySet == arrowKeySet) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean intersects(Rectangle otherRectangle) {
        Rectangle rectangle = new Rectangle(xPosition + (getImageWidth() / 4), yPosition + (getImageHeight() / 4), getTexture().getWidth() / 2, getTexture().getHeight() / 2);
        return rectangle.overlaps(otherRectangle);
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
        boolean returnValue = false;

        while (iter.hasNext()) {
            Ammo ammo = iter.next();

            // Check if the two objects are near each other
            if (Math.abs(ammo.getXPosition() - getXPosition()) <= 200 && (Math.abs(ammo.getYPosition() - getYPosition()) <= 200)) {

                // Check for intersect
                if (!isInvulnerable()) {
                    if (intersects(ammo.getBoundingBox())) {
                        setInvulnerable(true);
                        ammo.setIsDone();
                        takeDamage(ammo.getBulletDamage());
                        if (this.getMaxHealth() <= 0) {
                            this.setIsDone();
                        }
                        returnValue = true;
                    }
                }
            }
        }

        return returnValue;
    }

    // check collision, increase health, notify when to delete powerup
    public boolean collideWithPowerUp(ListIterator<PowerUp> powerUpList) {
        ListIterator<PowerUp> iter = powerUpList;
        boolean returnValue = false;

        while (iter.hasNext()) {
            PowerUp powerUp = iter.next();

            // Check if the two objects are near each other
            if (Math.abs(powerUp.getXPosition() - getXPosition()) <= 200 && (Math.abs(powerUp.getYPosition() - getYPosition()) <= 200)) {

                // Check for intersect
                if (!isInvulnerable()) {
                    if (intersects(powerUp.getBoundingBox())) {
                        powerUp.setIsDone();
                        addHealth(powerUp.getHealth());
                        returnValue = true;
                    }
                }
            }
        }

        return returnValue;
    }
}
