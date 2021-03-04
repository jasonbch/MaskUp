package MovementPatterns;

import Enemy.Enemy;

public class PatternExit extends Pattern {
    @Override
    public String getName() {
        return "PatternExit";
    }

    @Override
    public void Move(Enemy enemy, float deltaTime) {
        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * deltaTime);
    }
}
