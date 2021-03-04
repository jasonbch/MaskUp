package MovementPatterns;

import Enemy.Enemy;

public class PatternThree extends Pattern {
    @Override
    public String getName() {
        return "PatternThree";
    }

    @Override
    public void Move(Enemy enemy, float deltaTime) {
        if (enemy.getXPosition() >= enemy.getWorldWidth() - enemy.getImageWidth() || enemy.getXPosition() <= 0) {
            enemy.revertXMultiplier();
        }

        if (enemy.getYPosition() >= enemy.getWorldHeight() - enemy.getImageHeight() || enemy.getYPosition() <= 0) {
            enemy.revertYMultiplier();
        }

        enemy.setxPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.getXMultiplier() * deltaTime);
        enemy.setyPosition(enemy.getYPosition() + enemy.getSpeed() * enemy.getYMultiplier() * deltaTime);
    }
}
