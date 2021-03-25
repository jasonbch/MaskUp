package GameEngine.Spawning.BulletFormation;

import GameObject.Ammo.Ammo;
import GameObject.Ammo.Ammo.PatternAttribute;
import GameObject.Entity;
import GameObject.Player;

import java.util.Arrays;
import java.util.List;

public class TargetDownwardLinearBulletFormation extends BulletFormation {
    private Player player = Player.instance();

    @Override
    public String getName() {
        return "TargetDownwardLinearBulletFormation";
    }

    @Override
    public List<Ammo> shoot(Entity entity, int xShootPosition, int yShootPosition) {
        // Set up multiply
        float playerXPosition = player.getXPosition();
        float playerYPosition = player.getYPosition();

        float currentXPosition = entity.getXPosition();
        float currentYPosition = entity.getYPosition();

        double angle = Math.atan((currentXPosition - playerXPosition) / (currentYPosition - playerYPosition));

        double hypotenuse = 1;
        double opposite = Math.sin(angle) * hypotenuse;
        double near = Math.cos(angle) * hypotenuse;

        double newX = near;
        double newY = opposite;

        // Set up ammo
        List<Ammo> ammoList;

        PatternAttribute patternAttribute = new PatternAttribute(
                "LinearBulletMovementPattern",
                (float) -newY,
                (float) -newX);

        Ammo ammo = ammoFactory.create(entity.getBullet(), xShootPosition, yShootPosition, patternAttribute);
        ammoList = Arrays.asList(ammo);

        return ammoList;
    }
}
