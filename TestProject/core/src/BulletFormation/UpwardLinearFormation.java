package BulletFormation;

import Ammo.Ammo;
import Entity.Entity;
import Ammo.Ammo.PatternAttribute;
import java.util.Arrays;
import java.util.List;

public class UpwardLinearFormation extends FormationPattern{
    @Override
    public String getName() {
        return "UpwardLinear";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
        List<Ammo> ammoList;

        PatternAttribute patternAttribute = new PatternAttribute("LinearPattern", 0, 1);

        Ammo ammo = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute);
        ammoList = Arrays.asList(ammo);

        return ammoList;
    }
}
