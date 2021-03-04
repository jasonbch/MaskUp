package Enemy;

import Entity.Entity;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * The Enemy abstract class that extends from Entity that can move and fire.
 */
public abstract class Enemy extends Entity {
    private float xMultiplier = 1;
    private float yMultiplier = 1;
    private boolean isSpawned = false;
    private long spawnTime = TimeUtils.millis();
    private long timeAlive;

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

    public long getTimeAlive(){
        return this.timeAlive;
    }

    public abstract int getMaxLifespan();

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Enemy(float xPos, float yPos) {
        super(xPos, yPos);
    }

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
}
