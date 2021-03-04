package Factories;

import EnemyMovementPattern.*;

public class EnemyMovementFactory {
    public EnemyMovementPattern create(String pattern) {
        switch(pattern) {
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
            default:
                return null;
        }
    }
}
