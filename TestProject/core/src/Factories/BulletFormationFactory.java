package Factories;

import BulletFormation.*;

public class BulletFormationFactory {
    public Formation create(String pattern) {
        switch (pattern) {
            case "UpwardLinearFormation":
                return new UpwardLinearFormation();
            case "DownwardLinearFormation":
                return new DownwardLinearFormation();
            case "FanFormation":
                return new FanFormation();
            case "CircularFormation":
                return new CircularFormation();
            default:
                return null;
        }
    }
}
