package Objects.BulletFormation;

import GameEngine.UI.UIController;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Ammo.Bullet;
import Objects.GameObject.Entity;

import java.util.ArrayList;
import java.util.List;

public class AxisXFlipAttackRevert extends BulletFormation {

    private final UIController uiController = UIController.instance();

    @Override
    public String getName() {
        return "AxisXFlipAttackRevert";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
        uiController.revertXAxis();

        List<Ammo> ammo = new ArrayList<>();

        return ammo;
    }
}
