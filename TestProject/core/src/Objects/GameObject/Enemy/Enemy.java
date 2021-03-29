package Objects.GameObject.Enemy;

import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ListIterator;

/**
 * The Entity.Enemy abstract class that extends from Entity that can move and fire.
 */
public abstract class Enemy extends Entity {
    protected float xMultiplier = 1;
    protected float yMultiplier = 1;
    protected boolean isSpawned = false;
    protected long spawnTime = TimeUtils.millis();
    protected long timeAlive;
    protected String movingPattern;
    protected int maxTimeAlive = 0;
    protected int maxHealth = 1;

    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos initial x position.
     * @param yPos initial y position.
     */
    public Enemy(float xPos, float yPos, String movingPattern) {
        super(xPos, yPos);
        this.movingPattern = movingPattern;
    }

    public boolean isSpawned() {
        return isSpawned;
    }

    public void setIsSpawned(boolean value) {
        this.isSpawned = value;
    }

    public float getXMultiplier() {
        return xMultiplier;
    }

    public void revertXMultiplier() {
        xMultiplier *= -1;
    }

    public float getYMultiplier() {
        return yMultiplier;
    }

    public void revertYMultiplier() {
        yMultiplier *= -1;
    }

    public void updateTimeAlive() {
        timeAlive = TimeUtils.timeSinceMillis(spawnTime) / 1000;
    }

    public long getTimeAlive() {
        return this.timeAlive;
    }

    public String getMovingPattern() {
        return movingPattern;
    }

    public void setMovingPattern(String movingPattern) {
        this.movingPattern = movingPattern;
    }

    public int getMaxLifeSpan() {
        return this.maxTimeAlive;
    }

    public int getHealth() {
        return this.maxHealth;
    }

    public void setHealth(int bulletDamage) {
        this.maxHealth -= bulletDamage;
    }

    /**
     * If the enemy collide with the player ammo,
     * remove the bullet and damage the enemy.
     *
     * @param playerAmmoList the player ammo list.
     */
    public boolean collide(ListIterator<Ammo> playerAmmoList) {
        ListIterator<Ammo> iter = playerAmmoList;
        boolean returnValue = false;
        while (iter.hasNext()) {
            Ammo ammo = iter.next();

            // Check if the two objects are near each other
            if (Math.abs(ammo.getXPosition() - getXPosition()) <= 150
                    && (Math.abs(ammo.getYPosition() - getYPosition()) <= 150)) {
                // Check for intersect
                if (intersects(ammo.getBoundingBox())) {
                    ammo.setIsDone();
                    setHealth(ammo.getBulletDamage());
                    returnValue = true;
                }
            }
        }

        return returnValue;
    }
}