package GameEngine.Factory;

import Objects.BulletMovementPattern.BulletMovementPattern;
import Objects.BulletMovementPattern.LinearBulletMovementPattern;

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
