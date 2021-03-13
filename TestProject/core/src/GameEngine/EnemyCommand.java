package GameEngine;

import Entity.Enemy;

import java.util.ListIterator;

public class EnemyCommand implements Command{

    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private static final BulletSpawningController bsc = BulletSpawningController.instance();


    public EnemyCommand()
    {

    }

    public void execute()
    {
        ListIterator<Enemy> iter = enemySpawningController.getEnemyList().listIterator();
        while (iter.hasNext())
        {
            Enemy enemy = iter.next();
            enemy.collide(bsc.getPlayerAmmoList().listIterator());
            iter.remove();
        }

    }
}
