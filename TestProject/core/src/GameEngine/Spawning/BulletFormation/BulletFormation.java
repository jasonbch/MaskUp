package GameEngine.Spawning.BulletFormation;

import GameObject.Ammo.Ammo;
import GameObject.Ammo.AmmoFactory;
import GameObject.Entity;

import java.util.List;

public abstract class BulletFormation {
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
