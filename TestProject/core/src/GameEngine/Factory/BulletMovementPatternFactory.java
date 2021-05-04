package GameEngine.Factory;

import Objects.MovementPattern.BulletMovementPattern.CurveBulletMovementPattern;
import Objects.MovementPattern.BulletMovementPattern.LinearBulletMovementPattern;
import Objects.MovementPattern.MovementPattern;

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
