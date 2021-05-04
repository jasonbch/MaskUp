package GameEngine.Spawning;

import GameEngine.Factory.PowerUpFactory;
import GameEngine.Observer.GameObserver;
import GameEngine.Resource.GameResources;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.PowerUp;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class PowerUpController implements GameObserver {
    private static final GameResources gameResources = GameResources.instance();
    private static final PowerUpController uniqueInstance = new PowerUpController();
    private PowerUpFactory powerUpFactory = new PowerUpFactory();
    private CopyOnWriteArrayList powerUpList = new CopyOnWriteArrayList<PowerUp>();

    public static PowerUpController instance() {
        return uniqueInstance;
    }

    public void add(Enemy enemy) {
        float xPos = enemy.getXPosition() + enemy.getImageWidth()/2;
        float yPos = enemy.getYPosition() + enemy.getImageHeight()/2;
        PowerUp powerUp = powerUpFactory.create(xPos, yPos);
        powerUp.attachGameObserver(this);
        powerUpList.add(powerUp);
    }

    public void remove(PowerUp powerUp) {
        if (powerUpList.contains(powerUp)) {
            powerUpList.remove(powerUp);
        }
    }

    public CopyOnWriteArrayList<PowerUp> getPowerUpList() {
        return this.powerUpList;
    }

    @Override
    public void update(Object object, String args) {
        if (object instanceof PowerUp) {
            if (args.equals("deletePowerUp")) {
                this.remove((PowerUp) object);
            }
        }
        if (object instanceof Enemy) {
            if (args.equals("deleteEnemy")) {
                Random random = new Random();
                float chance = random.nextFloat();
                if (chance < 0.1f) {
                    this.add((Enemy)object);
                }
            }
        }
    }
}
