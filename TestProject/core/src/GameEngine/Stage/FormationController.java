package GameEngine.Stage;

import GameEngine.Factory.BulletFormationFactory;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;
import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

public class FormationController {
    private BulletFormationFactory bulletFormationFactory = new BulletFormationFactory();

    public FormationController() {
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
