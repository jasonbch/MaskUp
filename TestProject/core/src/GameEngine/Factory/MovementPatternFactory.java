package GameEngine.Factory;

import Objects.MovementPattern.MovementPattern;

public abstract class MovementPatternFactory {

    public abstract MovementPattern create(String pattern);
}
