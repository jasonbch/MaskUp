package Objects.GameObject.Enemy;

import GameEngine.Observer.BulletSpawnerObserver;
import GameEngine.Observer.EnemySubject;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * The Entity.Enemy abstract class that extends from Entity that can move and fire.
 */
public abstract class Enemy extends Entity implements EnemySubject {
    protected float xMultiplier = 1;
    protected float yMultiplier = 1;
    protected boolean isSpawned = false;
    protected long spawnTime = TimeUtils.millis();
    protected long currentTimeAlive;
    protected String movingPattern;
    protected float yAxis;
    protected float xAxis;
    protected int bulletSpawnerCount;

    private List<BulletSpawnerObserver> spawners = new ArrayList<>();

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

    public void setIsDone() {
        this.isDone = true;
        notifyObservers();
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

    public void updateCurrentTimeAlive() {
        currentTimeAlive = TimeUtils.timeSinceMillis(spawnTime) / 1000;
    }

    public long getCurrentTimeAlive() {
        return this.currentTimeAlive;
    }

    public String getMovingPattern() {
        return movingPattern;
    }

    public void setMovingPattern(String movingPattern) {
        // TODO: Fix for pattern four not reset bullet spawner count
        if (this.movingPattern.equals("PatternFour") && !movingPattern.equals("PatternFour") && this.bulletSpawnerCount == 2) {
            this.setBulletSpawnerCount(this.getBulletSpawnerCount() - 1);
        }

        this.movingPattern = movingPattern;
        notifyObservers();
    }

    public float getYAxis() {
        return this.yAxis;
    }

    public void setYAxis(float yAxis) {
        this.yAxis = yAxis;
        notifyObservers();
    }

    public float getXAxis() {
        return this.xAxis;
    }

    public void setXAxis(float xAxis) {
        this.xAxis = xAxis;
        notifyObservers();
    }

    public int getBulletSpawnerCount() {
        return this.bulletSpawnerCount;
    }

    public void setBulletSpawnerCount(int amount) {
        this.bulletSpawnerCount = amount;
    }

    public void setBulletFormation(String bulletFormation) {
        this.bulletFormation = bulletFormation;
        notifyObservers();
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
            if (Math.abs(ammo.getXPosition() - getXPosition()) <= 150 && (Math.abs(ammo.getYPosition() - getYPosition()) <= 150)) {
                // Check for intersect
                if (intersects(ammo.getBoundingBox())) {
                    ammo.setIsDone();
                    takeDamage(ammo.getBulletDamage());
                    if(this.getMaxHealth() <= 0){
                        this.setIsDone();
                        Notify("deleteEnemy");
                    }
                    returnValue = true;
                }
            }
        }

        return returnValue;
    }

    // Observer pattern
    public void addObserver(BulletSpawnerObserver spawner) {
        this.spawners.add(spawner);
    }

    public void removeObserver(BulletSpawnerObserver spawner) {
        this.spawners.remove(spawner);
    }

    public void notifyObservers() {
        for (BulletSpawnerObserver spawner : this.spawners) {
            spawner.update(this);
        }
    }
}
