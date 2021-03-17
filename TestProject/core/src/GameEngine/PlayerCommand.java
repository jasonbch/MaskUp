package GameEngine;

import Entity.Entity;

public class PlayerCommand implements Command {

    private static final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();

    Entity player;

    public PlayerCommand(Entity player) {
        this.player = player;
    }

    public void execute() {
        if(player.collide(bulletSpawningController.getEnemyAmmoList().listIterator())){
            bulletSpawningController.getEnemyAmmoList().clear();
        }
    }
}
