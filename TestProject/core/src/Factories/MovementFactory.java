package Factories;

import MovementPatterns.*;

public class MovementFactory {
    public Pattern create(String pattern) {
        switch(pattern) {
            case "PatternOne":
                return new PatternOne();
            case "PatternTwo":
                return new PatternTwo();
            case "PatternThree":
                return new PatternThree();
            case "PatternFour":
                return new PatternFour();
            case "PatternExit":
                return new PatternExit();
            default:
                return null;
        }
    }
}
