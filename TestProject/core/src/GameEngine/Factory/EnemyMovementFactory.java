package GameEngine.Factory;

import Objects.EnemyMovementPattern.*;

public class EnemyMovementFactory {
    public EnemyMovementPattern create(String pattern) {
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
            case "PatternFive":
                return new EnemyMovementPatternFive();
            default:
                return null;
        }
    }
}
