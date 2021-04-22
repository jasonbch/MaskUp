package GameEngine.Stage;

import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Resource.GameResources;
import GameEngine.Time.TimeController;
import Objects.GameObject.Enemy.Covid;
import Objects.GameObject.Enemy.Enemy;
import Objects.GameObject.Enemy.Karen;
import Objects.GameObject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;

import java.util.ArrayList;
import java.util.List;
/**
 * StageController class that implements Singleton.
 * The class can create stages for a game.
 */
public class StageController implements GameObserver, GameSubject {
    private static final TimeController timeController = TimeController.instance();
    private static final GameResources gameResources = GameResources.instance();
    private ArrayList<GameObserver> myObs = new ArrayList<GameObserver>();
    // Implement Singleton
    private static StageController uniqueInstance = null;
    private final Player player = Player.instance();

    // Stages duration
    public int stageBuffer = 5;
    public int stageOneStart = 10;

    private int stageOneDuration = 30;
    private int stageTwoDuration = 45;
    private int stageThreeDuration = 30;
    private int stageFourDuration = 60;

    private int stageOneEnd = stageOneStart + stageOneDuration;
    public int stageTwoStart = stageOneEnd + stageBuffer;
    private int stageTwoEnd = stageTwoStart + stageTwoDuration;
    public int stageThreeStart = stageTwoEnd + stageBuffer;
    private int stageThreeEnd = stageThreeStart + stageThreeDuration;
    public int stageFourStart = stageThreeEnd + stageBuffer;
    private int stageFourEnd = stageFourStart + stageFourDuration;

    // Final Boss
    private Enemy covid;

    // Mid Boss
    private Enemy karen;

    private List<Wave> waves = new ArrayList<>();
    private List<Behavior> behaviors = new ArrayList<>();

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
        if (uniqueInstance == null) {
            uniqueInstance = new StageController();
        }
        return uniqueInstance;
    }

    public Enemy getKaren() {
        return this.karen;
    }

    public Enemy getCovid() {
        return this.covid;
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
        JsonValue stageStartTimes  = base.get("StageStartTimes");
            this.stageOneStart = stageStartTimes.getInt("stageOne");
            this.stageTwoStart = stageStartTimes.getInt("stageTwo");
            this.stageThreeStart = stageStartTimes.getInt("stageThree");
            this.stageFourStart = stageStartTimes.getInt("stageFour");


        // Initialize all the waves
        for (JsonValue wave : base.get("waves")) {
            String section = wave.name;
            String enemyType = wave.getString("enemyType");
            int enemyAmount = wave.getInt("enemyAmount");
            int startTimeFromStage = wave.getInt("startTimeFromStage");
            String enemyMovementPattern = wave.getString("enemyMovementPattern");
            String bulletFormation = wave.getString("bulletFormation");

            Wave newWave = new Wave(section, enemyType, enemyAmount, startTimeFromStage, enemyMovementPattern, bulletFormation);
            this.Attach(newWave);
            newWave = this.resetWaveStartTime(newWave.getStageNumber(), newWave);
            this.waves.add(newWave);
        }

        // Initialize all the behaviors
        for (JsonValue behavior : base.get("behaviors")) {
            String enemyName = behavior.getString("enemyName");
            int startTime = behavior.getInt("startTime");
            int speed = behavior.getInt("speed");
            float timeBetweenShot = behavior.getFloat("timeBetweenShot");
            String enemyMovementPattern = behavior.getString("enemyMovementPattern");
            String bulletFormation = behavior.getString("bulletFormation");

            Behavior newBehavior = new Behavior(enemyName, startTime, speed, timeBetweenShot, enemyMovementPattern, bulletFormation);
            this.behaviors.add(newBehavior);
        }



    }

    public void makeStages() {
        this.changePlayerBulletType();

        for (Wave wave : this.waves) {
            if (timeController.getElapsedTime() == wave.getStartTime()) {
                System.out.println("Stage " + wave.getSection());
                System.out.println("Wave start time " + wave.getStartTime());
                System.out.println("Elapsed time " + timeController.getStartTime());
                wave.run();
            }
        }

        for (Behavior behavior : this.behaviors) {
            if (timeController.getElapsedTime() == behavior.getStartTime()) {
                behavior.change();
            }
        }

        //this.fastForwardToStageThree();
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

    public Wave resetWaveStartTime(int stageNumber, Wave wave){
        switch (stageNumber)
        {
            case 1:
                wave.setStartTime(this.stageOneStart + wave.getStartTimeFromStage());
                return wave;
            case 2:
                wave.setStartTime(this.stageTwoStart + wave.getStartTimeFromStage());
                return wave;
            case 3:
                wave.setStartTime(this.stageThreeStart + wave.getStartTimeFromStage());
                return wave;
            case 4:
                wave.setStartTime(this.stageFourStart + wave.getStartTimeFromStage());
                return wave;
            default:
                return null;
        }
    }

    /**
     * TODO: Does not work
     * Fast forward the game to the start of stage three if the player
     * kills the mid boss before the stage two end.
     */
    private void fastForwardToStageThree() {
        this.stageThreeStart = (int) timeController.getElapsedTime();
        this.stageThreeEnd = stageThreeStart + stageOneDuration;
        this.stageFourStart = stageThreeEnd + stageBuffer;
        this.stageFourEnd = stageFourStart + stageFourDuration;
        this.Notify((this.stageThreeStart)+","+ (this.stageFourStart));

    }

    @Override
    public void update(Object o, String args) {
        if(o instanceof Karen){
            if(args.equals("deleteEnemy")){
                this.fastForwardToStageThree();
            }
        }
        if(o instanceof Covid){
            if(args.equals("deleteEnemy")){

            }
        }
    }

    @Override
    public void Attach(GameObserver o) {
        myObs.add(o);
    }

    @Override
    public void Dettach(GameObserver o) {

    }

    @Override
    public void Notify(String args) {
        for(int i = 0; i < this.myObs.size(); i++)
        {
            this.myObs.get(i).update(this, args);
        }
    }
}