package MovementPatterns;

import Enemy.Enemy;

public class PatternOne extends Pattern{

    @Override
    public String getName() {
        return "PatternOne";
    }

    @Override
    public void Move(Enemy enemy, float deltaTime) {
        float val = 600 + (float)(50 * Math.sin(enemy.getXPosition() * .5 * Math.PI / 80));
        enemy.setyPosition(val);

        enemy.setxPosition(enemy.getXPosition() + (enemy.getXMultiplier() * enemy.getSpeed() * deltaTime));

        if (enemy.isLeftOfScreen() || enemy.isRightOfScreen()){
            enemy.revertXMultiplier();
        }
    }
}
