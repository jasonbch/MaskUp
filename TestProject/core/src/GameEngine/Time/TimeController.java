package GameEngine.Time;

import com.badlogic.gdx.utils.TimeUtils;


public class TimeController {
    private static TimeController uniqueInstance = null;
    private long startTime;
    private long elapsedTime = 0;

    private TimeController() {
        this.startTime = TimeUtils.millis();
    }

    public static TimeController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TimeController();
        }
        return uniqueInstance;
    }

    public void updateElapsedTime() {
        elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public void reinitializeStartTime() {
        this.startTime = TimeUtils.millis();
    }
}
