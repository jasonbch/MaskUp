package GameEngine;

import Entity.Entity;

import java.util.ListIterator;

public class PlayerCommand implements Command {

    private static final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    boolean isHit = false;

    Entity player;

    public PlayerCommand(Entity player) { this.player = player;}

    public void execute()
    {
        // set player state
        isHit = player.collide(bulletSpawningController.getEnemyAmmoList().listIterator());
        if (isHit)
        {
            player.setIsDone(isHit);
        }
    }
}
