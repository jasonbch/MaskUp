package Objects.BulletFormation;

import GameEngine.Resource.GameResources;
import GameEngine.UI.UIController;
import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class AxisXFlipAttack extends BulletFormation {

    private final UIController uiController = UIController.instance();


    @Override
    public String getName() {
        return "AxisXFlipAttack";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {

        uiController.flipScreenOnXAxis();
        
        List<Ammo> ammo = new ArrayList<>();

        return ammo;
    }
}
