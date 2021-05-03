package GameEngine.Spawning;

import GameEngine.Factory.PowerUpFactory;
import GameEngine.Observer.GameObserver;
import GameEngine.Resource.GameResources;
import Objects.GameObject.GameObject;
import Objects.GameObject.PowerUp;

import java.util.concurrent.CopyOnWriteArrayList;

public class PowerUpController implements GameObserver {
    private static final GameResources gameResources = GameResources.instance();
    private static final PowerUpController uniqueInstance = new PowerUpController();
    private PowerUpFactory powerUpFactory = new PowerUpFactory();
    private CopyOnWriteArrayList powerUpList = new CopyOnWriteArrayList<PowerUp>();

    public static PowerUpController instance() {
        return uniqueInstance;
    }

    public void add() {

    }

    public void remove(PowerUp powerUp) {
        if (powerUpList.contains(powerUp)){
            powerUpList.remove(powerUp);
        }
    }

    public CopyOnWriteArrayList<PowerUp> getPowerUpList(){
        return this.powerUpList;
    }

    @Override
    public void update(Object object, String args) {
        if (object instanceof PowerUp){
            if (args.equals("deletePowerUp")){
                this.remove((PowerUp)object);
            }
        }
    }
}
