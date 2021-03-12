package Entity;

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
    private String movingPattern;

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

    public String getMovingPattern() {
        return movingPattern;
    }

    public void setMovingPattern(String movingPattern) {
        this.movingPattern = movingPattern;
    }

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param  xPos initial x position.
     * @param  yPos initial y position.
     */
    public Enemy(float xPos, float yPos, String movingPattern) {
        super(xPos, yPos);
        this.movingPattern = movingPattern;
    }
}
