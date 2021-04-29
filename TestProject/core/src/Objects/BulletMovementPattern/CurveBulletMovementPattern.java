package Objects.BulletMovementPattern;

import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.GameObject;

public class CurveBulletMovementPattern extends BulletMovementPattern {
    @Override
    public String getName() {
        return "CurveBulletMovementPattern";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {
        Ammo ammo = (Ammo) obj;
        ammo.setScaling(ammo.getScaling() + 10);
        Ammo.PatternAttribute bulletPattern = ammo.getPatternAttribute();
        float newX = ammo.getXPosition() + bulletPattern.getXMultiplier() * ammo.getSpeed() * deltaTime + ammo.getScaling() * deltaTime;
        float newY = ammo.getYPosition() + bulletPattern.getYMultiplier() * ammo.getSpeed() * deltaTime - ammo.getScaling() * deltaTime;

        ammo.setXPosition(newX);
        ammo.setYPosition(newY);
    }
}
