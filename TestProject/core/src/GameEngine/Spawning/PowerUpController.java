package GameEngine.Spawning;

import GameEngine.Factory.PowerUpFactory;
import GameEngine.Observer.GameObserver;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.PowerUp;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PowerUpController implements GameObserver {
    private PowerUpFactory powerUpFactory = new PowerUpFactory();
    private CopyOnWriteArrayList powerUpList = new CopyOnWriteArrayList<PowerUp>();

    public PowerUp spawnPowerUp(Enemy enemy, int xPosition, int yPosition){
        
    }

    @Override
    public void update(Object object, String args) {

    }
}
