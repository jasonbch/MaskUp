package GameEngine;

import Entity.GameObject;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class DrawController {

    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    GameObject player;
    private Batch batch;

    // TODO come back later to think about passing in the player
    public DrawController(Batch batch, GameObject player) {
        this.batch = batch;
        this.player = player;
    }

    public void draw(String type) {
        ListIterator<GameObject> iterator;
        List<GameObject> objectList;
        switch (type) {
            case "ammoPlayer":
                objectList = (List<GameObject>) (List<?>) bulletSpawningController.getPlayerAmmoList();
                break;
            case "ammoEnemy":
                objectList = (List<GameObject>) (List<?>) bulletSpawningController.getEnemyAmmoList();
                break;
            case "enemy":
                objectList = (List<GameObject>) (List<?>) enemySpawningController.getEnemyList();
                break;
            case "player":
                objectList = Arrays.asList(player);
                break;
            default:
                return;
        }

        iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            GameObject object = iterator.next();
            object.draw(batch);
        }
    }
}
