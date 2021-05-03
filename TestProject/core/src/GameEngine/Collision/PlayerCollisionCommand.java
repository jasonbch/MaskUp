package GameEngine.Collision;

import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.PowerUpController;
import Objects.GameObject.Entity;

public class PlayerCollisionCommand implements Command {
    private static final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private static final PowerUpController powerUpController = PowerUpController.instance();
    private Entity player;

    public PlayerCollisionCommand(Entity player) {
        this.player = player;
    }

    public void execute() {
        if (player.collide(bulletSpawningController.getEnemyAmmoList().listIterator())) {
            bulletSpawningController.clearEnemyAmmoList();
        }
        if (player.collide(powerUpController.getPowerUpList().listIterator())){
            //powerUpController.remove();
        }
    }
}
