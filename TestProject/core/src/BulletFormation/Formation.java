package BulletFormation;

import Ammo.Ammo;
import Entity.Entity;
import Factories.AmmoFactory;

import java.util.List;

public abstract class FormationPattern {
    protected AmmoFactory ammoFactory = new AmmoFactory();

    /**
     * Return the name of the formation pattern.
     */
    public abstract String getName();

    /**
     * Return ammo list to shoot
     */
    public abstract List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition);
}
