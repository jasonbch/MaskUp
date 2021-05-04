package GameEngine.Stage;

import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Resource.GameResources;
import GameEngine.Time.TimeController;
import Objects.GameObject.Enemy.Covid;
import Objects.GameObject.Enemy.Karen;
import Objects.GameObject.Player;

import Objects.GameObject.StageComponent.Behavior;
import Objects.GameObject.StageComponent.StageComponent;
import Objects.GameObject.StageComponent.Wave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
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


    public int getStageOneEnd() {
        return this.stageOneEnd;
    }

    public int getStageTwoEnd() {
        return this.stageTwoEnd;
    }

    public int getStageThreeEnd() {
        return this.stageThreeEnd;
    }

    public int getStageFourEnd() {
        return this.stageFourEnd;
    }

    public int getStageThreeStart() {
        return this.stageThreeStart;
    }

    public int getStageFourStart() {
        return this.stageFourStart;
    }

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
        this.stageOneDuration = stageTwoStart - stageOneStart;
        this.stageTwoDuration = stageThreeStart - stageTwoStart;
        this.stageThreeDuration = stageFourStart - stageThreeStart;

        // Initialize all the waves
        for (JsonValue wave : base.get("waves")) {
            String section = wave.name;
            String enemyType = wave.getString("enemyType");
            int enemyAmount = wave.getInt("enemyAmount");
            // get the spawnLocations list from json
            List<HashMap<String, Integer>> map = getSpawnLocations(wave);
            int startTimeFromStage = wave.getInt("startTimeFromStage");
            String enemyMovementPattern = wave.getString("enemyMovementPattern");
            String bulletFormation = wave.getString("bulletFormation");

            Wave waveObject = new Wave(section, enemyType, startTimeFromStage, enemyMovementPattern, bulletFormation, enemyAmount, map);

            // Calculate start time
            this.calculateStageComponentStartTime(waveObject.getStageNumber(), waveObject);

            // Attach observer
            this.attachGameObserver(waveObject);

            // Add to list
            this.waves.add(waveObject);
            // need to add in reading of xDrop and yStop. how will they be read in
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

            Behavior behaviorObject = new Behavior(section, enemyName, startTimeFromStage, enemyMovementPattern, bulletFormation, speed, timeBetweenShot);

            // Calculate start time
            this.calculateStageComponentStartTime(behaviorObject.getStageNumber(), behaviorObject);

            // Attach observer
            this.attachGameObserver(behaviorObject);

            // Add to list
            this.behaviors.add(behaviorObject);
        }
    }

    private List<HashMap<String, Integer>> getSpawnLocations(JsonValue jsonList) {
        List<HashMap<String, Integer>> positionList = new ArrayList<>();
        for (JsonValue spawnLocations : jsonList.get("spawnLocation")) {
            HashMap<String, Integer> map = new HashMap<>();
            int x = spawnLocations.getInt("x");
            int y = spawnLocations.getInt("y");

            map.put("x", x);
            map.put("y", y);
            positionList.add(map);
        }

        return positionList;
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

    public void calculateStageComponentStartTime(int stageNumber, StageComponent stageComponent) {
        switch (stageNumber) {
            case 1:
                stageComponent.setStartTime(this.stageOneStart + stageComponent.getStartTimeFromStage());
                break;
            case 2:
                stageComponent.setStartTime(this.stageTwoStart + stageComponent.getStartTimeFromStage());
                break;
            case 3:
                stageComponent.setStartTime(this.stageThreeStart + stageComponent.getStartTimeFromStage());
                break;
            case 4:
                stageComponent.setStartTime(this.stageFourStart + stageComponent.getStartTimeFromStage());
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
        this.notifyGameObserver("fastForward");
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
                notifyGameObserver("winingState");
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