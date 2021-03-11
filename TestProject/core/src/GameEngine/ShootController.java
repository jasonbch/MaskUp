package GameEngine;

import Ammo.*;
import Ammo.Ammo.*;
import Enemy.Enemy;
import Factories.AmmoFactory;
import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;
import java.util.List;

public class ShootController {
    // Implement Singleton
    private static ShootController uniqueInstance = null;

    //private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();

    private AmmoFactory factory = new AmmoFactory();

    public ShootController() {

    }

    public List<Ammo> create(Enemy enemy, String pattern) {
        List<Ammo> ammos = null;

        if (pattern.equals("FanPattern")) {
            ammos = shootFanPattern(enemy);
        }

        System.out.println(ammos.size());

        return ammos;
    }

    public List<Ammo> shootFanPattern(Enemy enemy) {
        PatternAttribute patternAttribute1 = new PatternAttribute("LinearPattern", -1, -1);
        PatternAttribute patternAttribute2 = new PatternAttribute("LinearPattern", 0, -1);
        PatternAttribute patternAttribute3 = new PatternAttribute("LinearPattern", 1, -1);

        // Get shoot position
        GridPoint2 shootPosition = enemy.getShootingPosition();
        int xShootPosition = shootPosition.x;
        int yShootPosition = shootPosition.y;

        Ammo ammo1 = factory.create(enemy.getBullet(), xShootPosition, yShootPosition, patternAttribute1);
        Ammo ammo2 = factory.create(enemy.getBullet(), xShootPosition, yShootPosition, patternAttribute2);
        Ammo ammo3 = factory.create(enemy.getBullet(), xShootPosition, yShootPosition, patternAttribute3);

        List<Ammo> result = Arrays.asList(ammo1, ammo2, ammo3);


        return result;
    }
}
