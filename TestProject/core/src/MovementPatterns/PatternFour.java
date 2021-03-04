package MovementPatterns;

import Enemy.Enemy;

public class PatternFour extends Pattern {
    @Override
    public String getName() {
        return "PatternFour";
    }

    @Override
    public void Move(Enemy enemy, float deltaTime) {
        if (enemy.getYPosition() >= enemy.getWorldHeight() - enemy.getImageHeight() || enemy.getYPosition() <= 0) {
            enemy.revertYMultiplier();
        }

        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * enemy.getYMultiplier() * deltaTime);
    }
}
