package Objects.GameObject.Enemy;

import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * The MurderHornet class that extends from Entity.Enemy that can move and fire.
 */
public class MurderHornet extends Enemy {
    /**
     * Create a new instance of an Entity.Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern movement pattern for enemy.
     */
    public MurderHornet(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "MurderHornet";
        this.speed = 300;
        this.bullet = "Stinger";
        this.texture = GameResources.getAssetsManager().get("MurderHornet.png", Texture.class);
        this.timeBetweenShot = 0.6f;
        this.maxTimeAlive = 6;
        this.maxHealth = 5;
        setFormationPattern("DownwardLinearBulletFormation");
    }
}
