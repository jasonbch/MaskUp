package GameEngine;

import Enemy.Enemy;

import java.util.ListIterator;

public class EnemyCommand implements Command{

    public EnemyCommand(ListIterator<Enemy> enemy)
    {

    }

    public void execute()
    {
        System.out.println("calling enemy execute");
    }
}
