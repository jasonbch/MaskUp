package GameEngine;

import Entity.Entity;

import java.util.ListIterator;

public class PlayerCommand implements Command {

    private static final BulletSpawningController bsc = BulletSpawningController.instance();

    // pass in enemyammolist
    // switch to player type
    Entity player;

    public PlayerCommand(Entity player) { this.player = player;}

    public void execute()
    {
        // set player state
        player.collide(bsc.getEnemyAmmoList().listIterator());
    }
}
