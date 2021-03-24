package BulletFormation;

import GameObject.Ammo.Ammo;
import GameObject.Ammo.Ammo.PatternAttribute;
import GameObject.Entity;
import GameObject.Player;

import java.util.Arrays;
import java.util.List;

public class TriangleTargetBulletFormation extends BulletFormation {
    private Player player = Player.instance();

    @Override
    public String getName() {
        return "TriangleTargetBulletFormation";
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

        PatternAttribute patternAttribute1 = new PatternAttribute(
                "LinearBulletMovementPattern",
                (float) -newY,
                (float) -newX);

        PatternAttribute patternAttribute2 = new PatternAttribute(
                "LinearBulletMovementPattern",
                (float) -newY,
                (float) -newX);

        PatternAttribute patternAttribute3 = new PatternAttribute(
                "LinearBulletMovementPattern",
                (float) -newY,
                (float) -newX);

        double xDistance = newY * 30;
        double yDistance = newX * 30;

        Ammo ammo1 = ammoFactory.create(entity.getBullet(), (float) xShootPosition, yShootPosition - (float) (2 * yDistance), patternAttribute1);
        Ammo ammo2 = ammoFactory.create(entity.getBullet(), xShootPosition - (float) yDistance, yShootPosition, patternAttribute2);
        Ammo ammo3 = ammoFactory.create(entity.getBullet(), xShootPosition + (float) yDistance, yShootPosition, patternAttribute3);

        ammoList = Arrays.asList(ammo1, ammo2, ammo3);

        return ammoList;
    }
}
