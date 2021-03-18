package BulletMovementPattern;

import GameObject.Ammo.Ammo;

public class LinearBulletMovementPattern extends BulletMovementPattern {
    @Override
    public String getName() {
        return "LinearBulletMovementPattern";
    }

    @Override
    public void move(Ammo ammo, float deltaTime, float xMultiplier, float yMultiplier) {
        float newX = ammo.getXPosition() + xMultiplier * ammo.getSpeed() * deltaTime;
        float newY = ammo.getYPosition() + yMultiplier * ammo.getSpeed() * deltaTime;

        ammo.setXPosition(newX);
        ammo.setYPosition(newY);
    }
}
