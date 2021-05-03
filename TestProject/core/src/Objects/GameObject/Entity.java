package Objects.GameObject;

import GameEngine.Resource.GameResources;
import Objects.GameObject.Ammo.Ammo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ListIterator;

/**
 * The Entity abstract class that can move and fire.
 */
public abstract class Entity extends GameObject {
    protected float timeSinceLastShot = 0;
    protected String name = "GameObject";
    protected float speed = 250;
    protected String bullet = "Bullet";
    protected Texture texture = GameResources.getAssetsManager().get("CovidGerm.png", Texture.class);
    protected float timeBetweenShot = 0.1f;
    protected boolean isDone = false;
    protected String bulletFormation = "";
    protected int maxHealth = 1;
    protected int maxTimeAlive = 0;
    private float EasyDifficultyMultiplier = 0.8f;
    private float HardDiffifcultyMultiplier = 1.6f;
    //private int HardHealthMultiplier = 2;
    //private int EasyHealthMultiplier = (1/2);

    /**
     * Create a new instance of an Entity at the xPos and yPos.
     *
     * @param xPosition initial x position.
     * @param yPosition initial y position.
     */
    public Entity(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.initialize();
    }

    public Entity() {
    }

    public void initialize() {
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(gameResources.getGameJSON()));

        System.out.println("Entity: " + gameResources.getDifficulty());

        // Initialize all the waves
        JsonValue element = base.get("entities").get(getClass().getSimpleName());
        if (element != null) {
            if (gameResources.getDifficulty() == "Easy") {
                EasyMode(element);
            } else if (gameResources.getDifficulty() == "Medium") {
                MediumMode(element);
            } else {
                HardMode(element);
            }

        }
    }

    private void EasyMode(JsonValue element) {
        this.speed = (element.getInt("speed") * EasyDifficultyMultiplier);
        this.bullet = element.getString("bullet");
        this.texture = GameResources.getAssetsManager().get(element.getString("texture"), Texture.class);
        this.timeBetweenShot = (element.getFloat("timeBetweenShot") * HardDiffifcultyMultiplier);
        this.bulletFormation = element.getString("bulletFormation");
        this.maxTimeAlive = element.getInt("maxTimeAlive");
        this.maxHealth = (int) (element.getInt("maxHealth") * EasyDifficultyMultiplier);
    }

    private void MediumMode(JsonValue element) {
        this.speed = element.getInt("speed");
        this.bullet = element.getString("bullet");
        this.texture = GameResources.getAssetsManager().get(element.getString("texture"), Texture.class);
        this.timeBetweenShot = element.getFloat("timeBetweenShot");
        this.bulletFormation = element.getString("bulletFormation");
        this.maxTimeAlive = element.getInt("maxTimeAlive");
        this.maxHealth = element.getInt("maxHealth");
    }

    private void HardMode(JsonValue element) {
        this.speed = (element.getInt("speed") * HardDiffifcultyMultiplier);
        this.bullet = element.getString("bullet");
        this.texture = GameResources.getAssetsManager().get(element.getString("texture"), Texture.class);
        this.timeBetweenShot = (element.getFloat("timeBetweenShot") * EasyDifficultyMultiplier);
        this.bulletFormation = element.getString("bulletFormation");
        this.maxTimeAlive = element.getInt("maxTimeAlive");
        this.maxHealth = (int) (element.getInt("maxHealth") * HardDiffifcultyMultiplier);

    }

    /**
     * @return
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void addHealth(int health) {
        this.maxHealth += health;
    }

    public void takeDamage(int bulletDamage) {
        this.maxHealth -= bulletDamage;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setIsDone() {
        this.isDone = true;
        notifyGameObserver("die");
    }

    public void updateTimeSinceLastShot(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    public void resetTimeSinceLastShot() {
        timeSinceLastShot = 0;
    }

    public void setTimeBetweenShot(float time) {
        this.timeBetweenShot = time;
    }

    /**
     * Return True if the entity can fire, otherwise false.
     *
     * @return if the entity can fie.
     */
    public boolean canFire() {
        return (timeSinceLastShot - getTimeBetweenShots() >= 0);
    }

    public boolean intersects(Rectangle otherRectangle) {
        Rectangle rectangle = new Rectangle(xPosition, yPosition, getTexture().getWidth(), getTexture().getHeight());
        return rectangle.overlaps(otherRectangle);
    }

    public abstract boolean collide(ListIterator<Ammo> entityAmmolist);

    public GridPoint2 getShootingPosition() {
        float xShootPosition = getXPosition() + (float) getImageWidth() / 2;
        float yShootPosition = getYPosition();
        GridPoint2 shootPosition = new GridPoint2((int) xShootPosition, (int) yShootPosition);
        return shootPosition;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getBullet() {
        return this.bullet;
    }

    public void setBullet(String bullet) {
        this.bullet = bullet;
    }

    public float getTimeBetweenShots() {
        return this.timeBetweenShot;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getBulletFormation() {
        return bulletFormation;
    }

    public void setBulletFormation(String bulletFormation) {
        this.bulletFormation = bulletFormation;
    }

    public int getMaxTimeAlive() {
        return this.maxTimeAlive;
    }

    public void setMaxTimeAlive(int time) {
        this.maxTimeAlive = time;
    }
}
