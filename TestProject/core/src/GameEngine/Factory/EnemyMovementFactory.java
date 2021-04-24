package GameEngine.Factory;

import Objects.EnemyMovementPattern.*;
import Objects.MovementPattern;

public class EnemyMovementFactory extends MovementPatternFactory {
    public MovementPattern create(String pattern) {
        switch (pattern) {
            case "PatternOne":
                return new EnemyMovementPatternOne();
            case "PatternTwo":
                return new EnemyMovementPatternTwo();
            case "PatternThree":
                return new EnemyMovementPatternThree();
            case "PatternFour":
                return new EnemyMovementPatternFour();
            case "PatternExit":
                return new EnemyMovementPatternExit();
            case "PatternEnter":
                return new EnemyMovementPatternEnter();
            case "PatternDiamond":
                return new EnemyMovementPatternDiamond();
            default:
                return null;
        }
    }
}
