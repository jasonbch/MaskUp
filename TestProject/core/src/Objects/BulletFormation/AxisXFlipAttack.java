package Objects.BulletFormation;

import GameEngine.UI.UIController;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;

import java.util.ArrayList;
import java.util.List;

public class AxisXFlipAttack extends BulletFormation {

    private final UIController uiController = UIController.instance();


    @Override
    public String getName() {
        return "AxisXFlipAttack";
    }

    @Override
    public List<Ammo> shoot(Entity entity, float xShootPosition, float yShootPosition) {

        uiController.flipScreenOnXAxis();

        List<Ammo> ammo = new ArrayList<>();

        return ammo;
    }
}
