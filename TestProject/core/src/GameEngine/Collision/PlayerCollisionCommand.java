package GameEngine.Collision;

import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.PowerUpController;
import Objects.GameObject.Entity;
import Objects.GameObject.Player;

public class PlayerCollisionCommand implements Command {
    private static final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private static final PowerUpController powerUpController = PowerUpController.instance();
    private Player player;

    public PlayerCollisionCommand(Player player) {
        this.player = player;
    }

    public void execute() {
        if (!player.isGod()) {
            if (player.collide(bulletSpawningController.getEnemyAmmoList().listIterator())) {
                bulletSpawningController.clearEnemyAmmoList();
            }
        }
        if (player.collideWithPowerUp(powerUpController.getPowerUpList().listIterator())) {
        }
    }
}
