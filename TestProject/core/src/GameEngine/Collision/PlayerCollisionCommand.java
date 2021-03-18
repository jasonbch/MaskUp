package GameEngine.Collision;

import GameEngine.Spawning.BulletSpawningController;
import GameObject.Entity;

public class PlayerCollisionCommand implements Command {
    private static final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private Entity player;

    public PlayerCollisionCommand(Entity player) {
        this.player = player;
    }

    public void execute() {
        if (player.collide(bulletSpawningController.getEnemyAmmoList().listIterator())) {
            bulletSpawningController.clearEnemyAmmoList();
        }
    }
}
