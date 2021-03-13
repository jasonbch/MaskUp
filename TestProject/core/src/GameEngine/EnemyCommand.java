package GameEngine;

import Entity.Enemy;

import java.util.ListIterator;

public class EnemyCommand implements Command{

    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private static final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    boolean isHit = false;

    public EnemyCommand()
    {

    }

    public void execute()
    {
        ListIterator<Enemy> iter = enemySpawningController.getEnemyList().listIterator();
        while (iter.hasNext())
        {
            Enemy enemy = iter.next();
            isHit = enemy.collide(bulletSpawningController.getPlayerAmmoList().listIterator());
            if (isHit) {
                enemy.setIsDone(isHit);
            }
        }

    }
}
