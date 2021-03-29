package GameEngine.Movement;

import GameObject.BulletMovementPattern.BulletMovementPattern;
import GameObject.BulletMovementPattern.LinearBulletMovementPattern;

public class BulletMovementPatternFactory {

    public BulletMovementPattern create(String pattern) {
        switch (pattern) {
            case "LinearBulletMovementPattern":
                return new LinearBulletMovementPattern();
            default:
                return new LinearBulletMovementPattern();
        }
    }
}
