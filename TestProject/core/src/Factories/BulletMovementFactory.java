package Factories;

import BulletMovementPattern.*;

public class BulletMovementFactory {

    public BulletMovementPattern create(String pattern) {
        switch(pattern) {
            case "LinearPattern":
                return new BulletMovementPatternLinear();
            default:
                return new BulletMovementPatternLinear();
        }
    }

}
