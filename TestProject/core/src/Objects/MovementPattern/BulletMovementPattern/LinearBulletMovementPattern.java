package Objects.MovementPattern.BulletMovementPattern;

import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.GameObject;

public class LinearBulletMovementPattern extends BulletMovementPattern {
    @Override
    public String getName() {
        return "LinearBulletMovementPattern";
    }

    @Override
    public void move(GameObject obj, float deltaTime) {
        Ammo ammo = (Ammo) obj;
        Ammo.PatternAttribute bulletPattern = ammo.getPatternAttribute();
        float newX = ammo.getXPosition() + bulletPattern.getXMultiplier() * ammo.getSpeed() * deltaTime;
        float newY = ammo.getYPosition() + bulletPattern.getYMultiplier() * ammo.getSpeed() * deltaTime;

        ammo.setXPosition(newX);
        ammo.setYPosition(newY);
    }
}
