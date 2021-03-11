package GameEngine;

import Ammo.Ammo;
import Entity.GameObject;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class DrawController {

    private final ShootController shootController = ShootController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private Batch batch;

    public DrawController (Batch batch)
    {
        this.batch = batch;
    }

    public void draw(String type)
    {
        ListIterator<GameObject> iterator;
        List<GameObject> objectList;
        switch (type)
        {
            case "ammoPlayer":
                objectList = (List<GameObject>)(List<?>)shootController.getPlayerAmmoList();
                break;
            case "ammoEnemy":
                objectList = (List<GameObject>)(List<?>)shootController.getEnemyAmmoList();
                break;
            case "enemy":
                objectList = (List<GameObject>)(List<?>)enemySpawningController.getEnemyList();
                break;
            default:
                return;

        }
        iterator = objectList.listIterator();
        while(iterator.hasNext()){
            GameObject object = iterator.next();
            object.draw(batch);
        }

    }

}
