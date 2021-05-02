package Objects.BulletFormation;

import Objects.GameObject.Ammo.Ammo;
import Objects.GameObject.Entity;
import Objects.GameObject.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TargetThreeDirectionBulletFormation extends BulletFormation {
    private Player player = Player.instance();

    @Override
    public String getName() {
        return "TargetThreeDirectionBulletFormation";
    }

    @Override
    public List<Ammo> shoot(Entity entity, float xShootPosition, float yShootPosition, boolean isPowerUp) {
        // Set up multiply
        float playerXPosition = player.getXPosition();
        float playerYPosition = player.getYPosition();

        float currentXPosition = entity.getXPosition();
        float currentYPosition = entity.getYPosition();

        float angle = (float) Math.atan((currentXPosition - playerXPosition) / (currentYPosition - playerYPosition));
        float angle2 = (float) (angle + 35.5);
        float angle3 = angle - 130;

        List<Ammo> ammoList = new ArrayList<>();

        List<Ammo> ammo1 = makeAmmo(entity.getBullet(), angle, xShootPosition, yShootPosition);
        List<Ammo> ammo2 = makeAmmo(entity.getBullet(), angle2, xShootPosition, yShootPosition);
        List<Ammo> ammo3 = makeAmmo(entity.getBullet(), angle3, xShootPosition, yShootPosition);

        ammoList.addAll(ammo1);
        ammoList.addAll(ammo2);
        ammoList.addAll(ammo3);

        return ammoList;
    }

    private List<Ammo> makeAmmo(String bullet, float angle, float xShootPosition, float yShootPosition) {
        float hypotenuse = 1;
        float opposite = (float) (Math.sin(angle) * hypotenuse);
        float near = (float) (Math.cos(angle) * hypotenuse);

        // Set up ammo
        List<Ammo> ammoList;

        Ammo.PatternAttribute patternAttribute = new Ammo.PatternAttribute("LinearBulletMovementPattern", -opposite, -near);

        Ammo ammo = ammoFactory.create(bullet, xShootPosition, yShootPosition, patternAttribute);
        ammo.setSpeed(250);
        ammoList = Arrays.asList(ammo);

        return ammoList;
    }
}
