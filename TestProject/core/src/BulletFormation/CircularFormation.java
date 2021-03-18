package BulletFormation;

import Ammo.Ammo;
import Ammo.Ammo.PatternAttribute;
import Entity.Entity;

import java.util.Arrays;
import java.util.List;

public class CircularFormation extends Formation {
    private String name = "CircularFormation";

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
        List<Ammo> ammoList;
        PatternAttribute patternAttribute1 = new PatternAttribute("LinearPattern", -1, -1);
        PatternAttribute patternAttribute2 = new PatternAttribute("LinearPattern", 0, -1);
        PatternAttribute patternAttribute3 = new PatternAttribute("LinearPattern", 1, -1);
        PatternAttribute patternAttribute4 = new PatternAttribute("LinearPattern", 1, 1);
        PatternAttribute patternAttribute5 = new PatternAttribute("LinearPattern", 0, 1);
        PatternAttribute patternAttribute6 = new PatternAttribute("LinearPattern", -1, 1);
        PatternAttribute patternAttribute7 = new PatternAttribute("LinearPattern", 1, 0);
        PatternAttribute patternAttribute8 = new PatternAttribute("LinearPattern", -1, 0);

        // Shoot bottom left
        Ammo ammo1 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute1);
        // Shoot bottom
        Ammo ammo2 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute2);
        // Shoot bottom right
        Ammo ammo3 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute3);
        // Shoot top right
        Ammo ammo4 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute4);
        // Shoot top
        Ammo ammo5 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute5);
        // Shoot top left
        Ammo ammo6 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute6);
        // Shoot right
        Ammo ammo7 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute7);
        // Shoot left
        Ammo ammo8 = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute8);

        ammo1.setSpeed(212);
        ammo3.setSpeed(212);
        ammo4.setSpeed(212);
        ammo6.setSpeed(212);

        ammoList = Arrays.asList(ammo1, ammo2, ammo3, ammo4, ammo5, ammo6, ammo7, ammo8);

        return ammoList;
    }
}
