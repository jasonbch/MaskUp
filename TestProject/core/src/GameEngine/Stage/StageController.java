package GameEngine.Stage;

import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Resource.GameResources;
import GameEngine.Time.TimeController;
import Objects.GameObject.Enemy.Covid;
import Objects.GameObject.Enemy.Karen;
import Objects.GameObject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * StageController class that implements Singleton.
 * The class can create stages for a game.
 */
public class StageController implements GameObserver, GameSubject {
    private static final TimeController timeController = TimeController.instance();
    private static final GameResources gameResources = GameResources.instance();
    // Implement Singleton
    private static final StageController instance = new StageController();
    private final Player player = Player.instance();
    // Stages duration
    public int stageBuffer = 5;
    public int stageOneStart;
    public int stageTwoStart;
    public int stageThreeStart;
    public int stageFourStart;

    private int stageOneDuration;
    private int stageTwoDuration;
    private int stageThreeDuration;
    private int stageFourDuration = 120;

    private int stageOneEnd;
    private int stageTwoEnd;
    private int stageThreeEnd;
    private int stageFourEnd;

    private List<Wave> waves = new ArrayList<>();
    private List<Behavior> behaviors = new ArrayList<>();
    private ArrayList<GameObserver> myObservers = new ArrayList<>();

    private StageController() {
        this.initialize();
    }

    /**
     * Return the instance of StageController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of StageController.
     */
    public static StageController instance() {
        return instance;
    }

    public int getStageOneEnd() {
        return stageOneEnd;
    }

    public int getStageTwoEnd() {
        return stageTwoEnd;
    }

    public int getStageThreeEnd() {
        return stageThreeEnd;
    }

    public int getStageFourEnd() {
        return stageFourEnd;
    }

    private void initialize() {
        this.initializeHelper();
    }

    private void initializeHelper() {
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(gameResources.getGameJSON()));

        // Initialize Stage times
        JsonValue stageStartTimes = base.get("StageStartTimes");
        this.stageOneStart = stageStartTimes.getInt("stageOne");
        this.stageTwoStart = stageStartTimes.getInt("stageTwo");
        this.stageThreeStart = stageStartTimes.getInt("stageThree");
        this.stageFourStart = stageStartTimes.getInt("stageFour");
        this.stageFourEnd = stageFourStart + stageFourDuration;

        // Calculate duration
        this.stageOneDuration = stageTwoStart - stageBuffer;
        this.stageTwoDuration = stageThreeStart - stageBuffer;
        this.stageThreeDuration = stageFourStart - stageBuffer;

        // Initialize all the waves
        for (JsonValue wave : base.get("waves")) {
            String section = wave.name;
            String enemyType = wave.getString("enemyType");
            int enemyAmount = wave.getInt("enemyAmount");
            int startTimeFromStage = wave.getInt("startTimeFromStage");
            String enemyMovementPattern = wave.getString("enemyMovementPattern");
            String bulletFormation = wave.getString("bulletFormation");

            Wave waveObject = new Wave(section, enemyType, startTimeFromStage, enemyMovementPattern, bulletFormation, enemyAmount);

            // Calculate start time
            this.calculateWaveStartTime(waveObject.getStageNumber(), waveObject);

            // Attach observer
            this.attachGameObserver(waveObject);

            // Add to list
            this.waves.add(waveObject);
        }

        // Initialize all the behaviors
        for (JsonValue behavior : base.get("behaviors")) {
            String section = behavior.name;
            String enemyName = behavior.getString("enemyName");
            int speed = behavior.getInt("speed");
            float timeBetweenShot = behavior.getFloat("timeBetweenShot");
            int startTimeFromStage = behavior.getInt("startTimeFromStage");
            String enemyMovementPattern = behavior.getString("enemyMovementPattern");
            String bulletFormation = behavior.getString("bulletFormation");

            Behavior newBehavior = new Behavior(section, enemyName, startTimeFromStage, enemyMovementPattern, bulletFormation, speed, timeBetweenShot);
            this.behaviors.add(newBehavior);
        }
    }

    public void makeStages() {
        this.changePlayerBulletType();
        for (Wave wave : this.waves) {
            if (timeController.getElapsedTime() == wave.getStartTime()) {
                wave.run();
            }
        }

        for (Behavior behavior : this.behaviors) {
            if (timeController.getElapsedTime() == behavior.getStartTime()) {
                behavior.change();
            }
        }
    }

    private void changePlayerBulletType() {
        if (timeController.getElapsedTime() == stageOneStart) {
            player.setBullet("Bullet");
        }
        if (timeController.getElapsedTime() == stageTwoStart) {
            player.setBullet("Mask");
        }
        if (timeController.getElapsedTime() == stageThreeStart) {
            player.setBullet("Bullet");
        }
        if (timeController.getElapsedTime() == stageFourStart) {
            player.setBullet("Syringe");
        }
    }

    public void calculateWaveStartTime(int stageNumber, Wave wave) {
        switch (stageNumber) {
            case 1:
                wave.setStartTime(this.stageOneStart + wave.getStartTimeFromStage());
                break;
            case 2:
                wave.setStartTime(this.stageTwoStart + wave.getStartTimeFromStage());
                break;
            case 3:
                wave.setStartTime(this.stageThreeStart + wave.getStartTimeFromStage());
                break;
            case 4:
                wave.setStartTime(this.stageFourStart + wave.getStartTimeFromStage());
                break;
            default:
                break;
        }
    }

    /**
     * Fast forward the game to the start of stage three if the player
     * kills the mid boss before the stage two end.
     */
    private void fastForwardToStageThree() {
        this.stageThreeStart = (int) timeController.getElapsedTime();
        this.stageThreeEnd = stageThreeStart + stageThreeDuration;
        this.stageFourStart = stageThreeEnd + stageBuffer;
        this.stageFourEnd = stageFourStart + stageFourDuration;
        this.notifyGameObserver((this.stageThreeStart) + "," + (this.stageFourStart));
    }

    @Override
    public void update(Object object, String args) {
        if (object instanceof Karen) {
            if (args.equals("deleteEnemy")) {
                this.fastForwardToStageThree();
            }
        }

        if (object instanceof Covid) {
            if (args.equals("deleteEnemy")) {
                // Todo: Fast forward to winning screen
                // Notify the gameController? to set winning screen
            }
        }
    }

    @Override
    public void attachGameObserver(GameObserver object) {
        myObservers.add(object);
    }

    @Override
    public void detachGameObserver(GameObserver object) {
        myObservers.remove(object);
    }

    @Override
    public void notifyGameObserver(String args) {
        for (int i = 0; i < this.myObservers.size(); i++) {
            this.myObservers.get(i).update(this, args);
        }
    }
}