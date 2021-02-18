package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class Syrenge extends Ammo{
    private final String name = "Syrenge";
    private final String[] acceptableTargets = {"Covid"};
    private final float speed = 0;
    private final Texture texture = new Texture("Syrenge.png");

    public Syrenge(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String[] getAcceptableTargets() {
        return this.acceptableTargets;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public Texture getImage() {
        return texture;
    }
}
