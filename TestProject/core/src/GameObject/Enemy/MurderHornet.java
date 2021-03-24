package GameObject.Enemy;

import GameEngine.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * The MurderHornet class that extends from Entity.Enemy that can move and fire.
 */
public class MurderHornet extends Enemy {
    private int maxLifespan = 10;
    private int maxHealth = 1;

    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public MurderHornet(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "MurderHornet";
        this.speed = 150;
        this.bullet = "Stinger";
        this.texture = GameResources.getAssetsManager().get("MurderHornet.png", Texture.class);
        this.timeBetweenShot = 0.6f;
        setFormationPattern("DownwardLinearBulletFormation");
    }

    /**
     * Return the name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Return the speed.
     */
    @Override
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Return the bullet string that the enemy fires.
     */
    @Override
    public String getBullet() {
        return this.bullet;
    }

    /**
     * Return the time between shot.
     */
    @Override
    public float getTimeBetweenShots() {
        return this.timeBetweenShot;
    }

    /**
     * Return the Texture image.
     */
    @Override
    public Texture getImage() {
        return this.texture;
    }

    /**
     * Return maxLifeSpan
     */
    @Override
    public int getMaxLifeSpan() {
        return this.maxLifespan;
    }

    /**
     *
     */
    @Override
    public int getHealth() {
        return this.maxHealth;
    }

    /**
     * @param bulletDamage
     */
    @Override
    public void setHealth(int bulletDamage) {
        this.maxHealth -= bulletDamage;
    }
}
