package GameEngine.Spawning.BulletFormation;

import GameObject.Ammo.Ammo;
import GameObject.Ammo.Ammo.PatternAttribute;
import GameObject.Entity;

import java.util.Arrays;
import java.util.List;

public class DownwardLinearBulletFormation extends BulletFormation {
    @Override
    public String getName() {
        return "DownwardLinearBulletFormation";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
        List<Ammo> ammoList;

        PatternAttribute patternAttribute = new PatternAttribute("LinearBulletMovementPattern", 0, -1);

        Ammo ammo = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute);
        ammoList = Arrays.asList(ammo);

        return ammoList;
    }
}
