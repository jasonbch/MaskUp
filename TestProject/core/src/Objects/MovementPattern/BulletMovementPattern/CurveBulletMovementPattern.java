package Objects.MovementPattern.BulletMovementPattern;

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

        float centerX = ammo.getOriginalX();
        float centerY = ammo.getOriginalY();

        // Initial radius
        float radius = 50f;
        float speed = 700f;
        float rate = deltaTime;

        // Scaling to expand circle
        float scaling = ammo.getScaling() + deltaTime * 3f;

        float circleX = (float) (Math.cos(ammo.getAngle()) * (radius * scaling) + centerX);
        float circleY = (float) (Math.sin(ammo.getAngle()) * (radius * scaling) + centerY);
        float angle = ammo.getAngle() + (speed * (rate / 1000)) % 360;

        if (angle >= 360) {
            angle = 0;
        }

        ammo.setAngle(angle);
        ammo.setScaling(scaling);
        ammo.setXPosition(circleX);
        ammo.setYPosition(circleY + (ammo.getImageHeight() / 2));
    }
}
