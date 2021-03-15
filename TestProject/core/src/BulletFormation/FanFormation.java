package BulletFormation;

import Ammo.Ammo;
import Entity.Entity;

import java.util.Arrays;
import java.util.List;
import Ammo.Ammo.PatternAttribute;

public class FanFormation extends FormationPattern{

    @Override
    public String getName() {
        return "FanPattern";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
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
}
