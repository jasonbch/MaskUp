package GameEngine;

import Ammo.*;
import Ammo.Ammo.*;
import Entity.Entity;
import Factories.AmmoFactory;

import Factories.BulletFormationFactory;
import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;
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
