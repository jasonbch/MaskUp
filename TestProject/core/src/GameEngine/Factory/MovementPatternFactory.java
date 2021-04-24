package GameEngine.Factory;

import Objects.MovementPattern;

public abstract class MovementPatternFactory {

    public abstract MovementPattern create(String pattern);
}
