package GameEngine.Factory;

import Objects.BulletFormation.*;

public class BulletFormationFactory {
    public BulletFormation create(String pattern) {
        switch (pattern) {
            case "UpwardLinearBulletFormation":
                return new UpwardLinearBulletFormation();
            case "DownwardLinearBulletFormation":
                return new DownwardLinearBulletFormation();
            case "FanBulletFormation":
                return new FanBulletFormation();
            case "CircularBulletFormation":
                return new CircularBulletFormation();
            case "TargetDownwardLinearBulletFormation":
                return new TargetDownwardLinearBulletFormation();
            case "TriangleTargetBulletFormation":
                return new TriangleTargetBulletFormation();
            case "AxisXFlipAttack":
                return new AxisXFlipAttack();
            case "AxisXFlipAttackRevert":
                return new AxisXFlipAttackRevert();
            case "AxisYFlipAttack":
                return new AxisYFlipAttack();
            case "AxisYFlipAttackRevert":
                return new AxisYFlipAttackRevert();
            default:
                return null;
        }
    }
}
