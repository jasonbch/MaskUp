package Enemy;

import Entity.Enemy;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Bat class that extends from Enemy that can move and fire.
 */
public class Bat extends Enemy {
    private int maxLifespan = 10;
    private int maxHealth = 1;

    /**
     * Create a new instance of an Enemy at the xPos and yPos.
     *
     * @param xPos    initial x position.
     * @param yPos    initial y position.
     * @param pattern
     */
    public Bat(float xPos, float yPos, String pattern) {
        super(xPos, yPos, pattern);
        this.name = "Bat";
        this.bullet = "CovidGerm";
        this.texture = new Texture("Bat.png");

    }



    /**
     *
     * Return maxLifeSpan
     */
    @Override
    public int getMaxLifeSpan(){ return this.maxLifespan; }

    /**
     *
     * @param bulletDamage
     */
    @Override
    public void setHealth(int bulletDamage) { this.maxHealth -= bulletDamage; }
    /**
     *
     */
    @Override
    public int getHealth() { return this.maxHealth; }

}
