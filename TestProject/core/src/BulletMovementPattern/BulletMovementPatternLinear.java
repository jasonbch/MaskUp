package BulletMovementPattern;

import Ammo.Ammo;

public class BulletMovementPatternLinear extends BulletMovementPattern {


    @Override
    public String getName() {
        return null;
    }

    @Override
    public void Move(Ammo ammo, float deltaTime, float xMultiplier, float yMultiplier) {


        float newX = ammo.getXPosition() + xMultiplier * ammo.getSpeed() * deltaTime;
        float newY = ammo.getYPosition() + yMultiplier * ammo.getSpeed() * deltaTime;

        ammo.setxPosition(newX);
        ammo.setyPosition(newY);


    }
}
