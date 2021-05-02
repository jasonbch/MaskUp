package Objects.BulletFormation;

import GameEngine.UI.UIController;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;

import java.util.ArrayList;
import java.util.List;

public class AxisYFlipAttack extends BulletFormation {

    private final UIController uiController = UIController.instance();


    @Override
    public String getName() {
        return "AxisYFlipAttack";
    }

    @Override
    public List<Ammo> shoot(Entity entity, float xShootPosition, float yShootPosition, boolean isPowerUp) {

        uiController.flipScreenOnYAxis();

        List<Ammo> ammo = new ArrayList<>();

        return ammo;
    }
}
