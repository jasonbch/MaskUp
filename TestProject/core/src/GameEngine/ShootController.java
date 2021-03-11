package GameEngine;

import Ammo.*;
import Ammo.Ammo.*;
import Entity.Entity;
import Factories.AmmoFactory;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;
import java.util.List;

public class ShootController {
    private AmmoFactory ammoFactory = new AmmoFactory();

    public ShootController() {
    }

    public List<Ammo> create(Entity entity, String pattern) {
        List<Ammo> ammoList = null;

        // Get shoot position
        GridPoint2 shootPosition = entity.getShootingPosition();
        int xShootPosition = shootPosition.x;
        int yShootPosition = shootPosition.y;

        if (pattern.equals("FanPattern")) {
            ammoList = shootFanPattern(entity, xShootPosition, yShootPosition);
        } else  if (pattern.equals("LinearPattern")) {
            ammoList = shootLinearPattern(entity, xShootPosition, yShootPosition);
        }

        return ammoList;
    }

    private List<Ammo> shootFanPattern(Entity entity, int xShootPosition, int yShootPosition) {
        List<Ammo> ammoList;

        PatternAttribute patternAttribute1 = new PatternAttribute("LinearPattern", -1, -1);
        PatternAttribute patternAttribute2 = new PatternAttribute("LinearPattern", 0, -1);
        PatternAttribute patternAttribute3 = new PatternAttribute("LinearPattern", 1, -1);

        Ammo ammo1 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute1);
        Ammo ammo2 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute2);
        Ammo ammo3 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute3);

       ammoList = Arrays.asList(ammo1, ammo2, ammo3);

        return ammoList;
    }

    private List<Ammo> shootLinearPattern(Entity entity, int xShootPosition, int yShootPosition) {
        List<Ammo> ammoList;

        PatternAttribute patternAttribute = new PatternAttribute("LinearPattern", 0, -1);

        Ammo ammo = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute);
        ammoList = Arrays.asList(ammo);

        return ammoList;
    }
}
