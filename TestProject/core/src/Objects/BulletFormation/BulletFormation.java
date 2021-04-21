package Objects.BulletFormation;

import GameEngine.Factory.AmmoFactory;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;

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
    public abstract List<Ammo> shoot(Entity entity, float xShootPosition, float yShootPosition);
}
