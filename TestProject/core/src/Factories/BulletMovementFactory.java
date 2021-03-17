package Factories;

import BulletMovementPattern.BulletMovementPattern;
import BulletMovementPattern.BulletMovementPatternLinear;

public class BulletMovementFactory {

    public BulletMovementPattern create(String pattern) {
        switch (pattern) {
            case "LinearPattern":
                return new BulletMovementPatternLinear();
            default:
                return new BulletMovementPatternLinear();
        }
    }

}
