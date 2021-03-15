package Entity;

import Ammo.Ammo;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ListIterator;

/**
 * The Enemy abstract class that extends from Entity that can move and fire.
 */
public abstract class Enemy extends Entity {
    private float xMultiplier = 1;
    private float yMultiplier = 1;
    private boolean isSpawned = false;
    private long spawnTime = TimeUtils.millis();
    private long timeAlive;
    private String movingPattern;

    public boolean isSpawned() {
        return isSpawned;
    }

    public void setIsSpawned(boolean value) {
        this.isSpawned = value;
    }

    /**
     *
     * Return maxLifeSpan
     */
    public abstract int getMaxLifeSpan();

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

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param xPos initial x position.
     * @param yPos initial y position.
     */
    public Enemy(float xPos, float yPos, String movingPattern) {
        super(xPos, yPos);
        this.movingPattern = movingPattern;
    }

    /**
     * @param playerAmmolist
     */
    public void collide(ListIterator<Ammo> playerAmmolist) {
        ListIterator<Ammo> iter = playerAmmolist;
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            if (intersects(ammo.getBoundingBox())) {
                setIsDone();
                ammo.setIsDone();
                setHealth(ammo.getBulletDamage());
            }
        }
    }
}
