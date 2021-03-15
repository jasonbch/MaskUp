package Factories;

import BulletFormation.DownwardLinearFormation;
import BulletFormation.FanFormation;
import BulletFormation.FormationPattern;
import BulletFormation.UpwardLinearFormation;

public class BulletFormationFactory {
    public FormationPattern create(String pattern) {
        switch(pattern) {
            case "UpwardLinearFormation":
                return new UpwardLinearFormation();
            case "DownwardLinearFormation":
                return new DownwardLinearFormation();
            case "FanFormation":
                return new FanFormation();
            default:
                return null;
        }
    }
}
