package Ammo;

import GameEngine.GameResources;
import com.badlogic.gdx.graphics.Texture;

/**
 * Bullet class that extends Ammo.
 */
public class Bullet extends Ammo {
    /**
     * Create a new instance of a Ammo at the xPos and yPos.
     *
     * @param xPosition        initial x position.
     * @param yPosition        initial y position.
     * @param patternAttribute
     */
    public Bullet(float xPosition, float yPosition, PatternAttribute patternAttribute) {
        super(xPosition, yPosition, patternAttribute);
        this.name = "Bullet";
        this.speed = 300;
        this.acceptableTargets = new String[]{"Bat", "Hornet"};
        this.texture = GameResources.getAssetsManager().get("Bullet.png", Texture.class);
        this.damage = 1;
    }
}
