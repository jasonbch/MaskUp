package GameEngine.Movement.BulletMovementPattern;

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
