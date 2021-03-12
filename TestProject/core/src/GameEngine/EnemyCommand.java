package GameEngine;

import Enemy.Enemy;

import java.util.ListIterator;

public class EnemyCommand implements Command{

    private static final EnemySpawningController enemySpawningController = EnemySpawningController.instance();

    public EnemyCommand()
    {

    }

    public void execute()
    {
        ListIterator<Enemy> iter = enemySpawningController.getEnemyList().listIterator();
        while (iter.hasNext())
        {
            Enemy enemy = iter.next();
            boolean isShot = enemy.enemyCollidedWith();
            if (isShot)
            {
                // set enemy state
                // remove iter.remove
                iter.remove();
            }
        }

    }
}
