package EnemyMovementPattern;


import Enemy.Enemy;

/**
 * The EnemyMovementPattern that move the enemy to the top of the screen.
 */
public class EnemyMovementPatternEnter extends EnemyMovementPattern {
    @Override
    public String getName() {
        return "PatternEnter";
    }

    @Override
    public void move(Enemy enemy, float deltaTime) {
        enemy.moveDown(deltaTime);

        if (enemy.getYPosition() <= 650) {
            enemy.setIsSpawned(true);
        }
    }
}
