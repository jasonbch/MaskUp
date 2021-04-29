package GameEngine.Factory;

import Objects.BulletMovementPattern.CurveBulletMovementPattern;
import Objects.BulletMovementPattern.LinearBulletMovementPattern;
import Objects.MovementPattern;

public class BulletMovementPatternFactory extends MovementPatternFactory {

    public MovementPattern create(String pattern) {
        switch (pattern) {
            case "LinearBulletMovementPattern":
                return new LinearBulletMovementPattern();
            case "CurveBulletMovementPattern":
                return new CurveBulletMovementPattern();
            default:
                return new LinearBulletMovementPattern();
        }
    }
}
