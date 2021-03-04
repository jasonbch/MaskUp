package MovementPatterns;

import Enemy.Enemy;

public class PatternTwo extends Pattern {

    @Override
    public String getName() {
        return "PatternTwo";
    }

    @Override
    public void Move(Enemy enemy, float deltaTime) {
        if (enemy.getXPosition() >= enemy.getWorldWidth() - enemy.getImageWidth() || enemy.getXPosition() <= 0) {
            enemy.revertXMultiplier();
        }

        enemy.setxPosition(enemy.getXPosition() + enemy.getSpeed() * enemy.getXMultiplier() * deltaTime);
    }
}
