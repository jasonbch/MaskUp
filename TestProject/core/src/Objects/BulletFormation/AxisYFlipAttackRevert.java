package Objects.BulletFormation;

import GameEngine.UI.UIController;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;

import java.util.ArrayList;
import java.util.List;

public class AxisYFlipAttackRevert extends BulletFormation {

    private final UIController uiController = UIController.instance();


    @Override
    public String getName() {
        return "AxisYFlipAttackRevert";
    }

    @Override
    public List<Ammo> shoot(Entity entity, float xShootPosition, float yShootPosition) {
        uiController.revertYAxis();

        List<Ammo> ammo = new ArrayList<>();

        return ammo;
    }
}
