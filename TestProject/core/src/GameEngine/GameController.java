package GameEngine;

import Entity.Player;
import com.badlogic.gdx.utils.TimeUtils;


public class GameController {

    private static Player player = Player.instance();
    private static GameController uniqueInstance = null;

    private long startTime;
    private long elapsedTime = 0;


    public static GameController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameController();
        }
        return uniqueInstance;
    }

    private GameController() {
        this.startTime = TimeUtils.millis();
    }

    public void updateElapsedTime() {
        elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public long getStartTime() {
        return this.startTime;
    }


    public void reinitializeStartTime() {
        this.startTime = TimeUtils.millis();

    }

    public void checkInvulnerabilityTime() {
        if (player.getInvulnerable()) {
            long elapsedTime = TimeUtils.timeSinceMillis(player.getStartInvulnerabilityTime()) / 1000;
            if (elapsedTime >= 3) {
                player.setInvulnerable(false);
            }
        }
    }
}
