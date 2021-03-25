package GameObject.Enemy;

import GameEngine.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Bat class that extends from Entity.Enemy that can move and fire.
 */
public class Bat extends Enemy {

    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Bat(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "Bat";
        this.speed = 350;
        this.bullet = "CovidGerm";
        this.texture = GameResources.getAssetsManager().get("Bat.png", Texture.class);
        this.timeBetweenShot = 0.9f;
        this.maxTimeAlive = 5;
        setFormationPattern("TriangleTargetBulletFormation");
        this.maxHealth = 3;
    }
}
