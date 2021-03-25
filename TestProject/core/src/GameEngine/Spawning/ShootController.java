package GameEngine.Spawning;

import GameEngine.Spawning.BulletFormation.BulletFormationFactory;
import GameObject.Ammo.Ammo;
import GameObject.Ammo.AmmoFactory;
import GameObject.Entity;
import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

public class ShootController {
    private AmmoFactory ammoFactory = new AmmoFactory();
    private BulletFormationFactory bulletFormationFactory = new BulletFormationFactory();

    public ShootController() {
    }

    public List<Ammo> create(Entity entity, String pattern) {
        List<Ammo> ammoList = null;

        // Get shoot position
        GridPoint2 shootPosition = entity.getShootingPosition();
        int xShootPosition = shootPosition.x;
        int yShootPosition = shootPosition.y;

        ammoList = bulletFormationFactory.create(pattern).shoot(entity, xShootPosition, yShootPosition);

        return ammoList;
    }
}
