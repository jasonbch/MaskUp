package Objects.BulletFormation;

import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Ammo.Ammo.PatternAttribute;
import Objects.GameObject.Entity;

import java.util.Arrays;
import java.util.List;

public class UpwardLinearBulletFormation extends BulletFormation {
    @Override
    public String getName() {
        return "UpwardLinearBulletFormation";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
        List<Ammo> ammoList;

        PatternAttribute patternAttribute = new PatternAttribute("LinearBulletMovementPattern", 0, 1);

        Ammo ammo = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute);
        ammoList = Arrays.asList(ammo);

        return ammoList;
    }
}
