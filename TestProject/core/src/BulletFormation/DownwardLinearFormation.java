package BulletFormation;

import Ammo.Ammo;
import Ammo.Ammo.PatternAttribute;
import Entity.Entity;

import java.util.Arrays;
import java.util.List;

public class DownwardLinearFormation extends Formation {
    @Override
    public String getName() {
        return "DownwardLinear";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
        List<Ammo> ammoList;

        PatternAttribute patternAttribute = new PatternAttribute("LinearPattern", 0, -1);

        Ammo ammo = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute);
        ammoList = Arrays.asList(ammo);

        return ammoList;
    }
}
