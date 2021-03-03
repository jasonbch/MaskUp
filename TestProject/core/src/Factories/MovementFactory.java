package Factories;

import Enemy.Enemy;
import MovementPatterns.Pattern;
import MovementPatterns.PatternOne;

public class MovementFactory {
    public Pattern create(String pattern) {
        switch(pattern)
        {
            case "PatternOne":
                return new PatternOne();
            default:
                return new PatternOne();
        }


    }

}
