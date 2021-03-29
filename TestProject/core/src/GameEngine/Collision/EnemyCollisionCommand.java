package GameEngine.Collision;

import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.Enemy;

import java.util.ListIterator;

public class EnemyCollisionCommand implements Command {
    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private static final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();

    public void execute() {
        ListIterator<Enemy> iter = enemySpawningController.getEnemyList().listIterator();
        while (iter.hasNext()) {
            Enemy enemy = iter.next();
            enemy.collide(bulletSpawningController.getPlayerAmmoList().listIterator());
        }
    }
}
