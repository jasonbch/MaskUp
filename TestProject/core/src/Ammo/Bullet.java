package Ammo;

import com.badlogic.gdx.graphics.Texture;

public class Bullet extends Ammo{
    public Bullet(float xPos, float yPos) {
        super(xPos, yPos);
    }

    @Override
    public String getName() {
        return "Bullet";
    }

//    @Override
//    public int getDamage() {
//        return 1;
//    }

    @Override
    public String[] getAcceptableTargets() {
        return new String[]{"Bat", "Hornet"};
    }

    @Override
    public float getSpeed() {
        return 45;
    }

    @Override
    public Texture getImage() {
        return new Texture("Bullet.png");
    }
}
