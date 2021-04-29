package Objects.BulletFormation;

import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Ammo.Ammo.PatternAttribute;
import Objects.GameObject.Entity;

import java.util.Arrays;
import java.util.List;

public class FanBulletFormation extends BulletFormation {
    @Override
    public String getName() {
        return "FanBulletFormation";
    }

    @Override
    public List<Ammo> shoot(Entity entity, float xShootPosition, float yShootPosition) {
        List<Ammo> ammoList;
        PatternAttribute patternAttribute1 = new PatternAttribute("LinearBulletMovementPattern", -1, -1);
        PatternAttribute patternAttribute2 = new PatternAttribute("LinearBulletMovementPattern", 0, -1);
        PatternAttribute patternAttribute3 = new PatternAttribute("LinearBulletMovementPattern", 1, -1);

        Ammo ammo1 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute1);
        Ammo ammo2 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute2);
        Ammo ammo3 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute3);

        float oldSpeed = ammo1.getSpeed();

        ammo1.setSpeed((float) (oldSpeed / Math.sqrt(2)));
        ammo3.setSpeed((float) (oldSpeed / Math.sqrt(2)));

        ammoList = Arrays.asList(ammo1, ammo2, ammo3);

        return ammoList;
    }
}
