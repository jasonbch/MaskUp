package GameEngine.Stage;

import GameEngine.Resource.GameResources;
import GameEngine.Time.TimeController;
import Objects.GameObject.Enemy.Enemy;
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
public class StageController {
    private static final TimeController timeController = TimeController.instance();
    private static final GameResources gameResources = GameResources.instance();

    // Implement Singleton
    private static StageController uniqueInstance = null;
    private final Player player = Player.instance();

    private String difficulty;

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

    public String getGameDifficulty() { return this.difficulty; }

    public void setGameDifficulty(String difficulty) { this.difficulty = difficulty; }

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

   /* private void initialize() {
        this.initializeHelper();
    }*/

    public void initialize() {
        System.out.println("StageController: " + getGameDifficulty());
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(gameResources.getGameJSON()));

        // Initialize all the waves
        for (JsonValue wave : base.get("waves")) {
            String enemyType = wave.getString("enemyType");
            int enemyAmount = wave.getInt("enemyAmount");
            int startTime = wave.getInt("startTime");
            String enemyMovementPattern = wave.getString("enemyMovementPattern");
            String bulletFormation = wave.getString("bulletFormation");

            Wave newWave = new Wave(enemyType, enemyAmount, startTime, enemyMovementPattern, bulletFormation);
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
                wave.run();
            }
        }

        for (Behavior behavior : this.behaviors) {
            if (timeController.getElapsedTime() == behavior.getStartTime()) {
                behavior.change();
            }
        }

        this.fastForwardToStageThree();
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

    /**
     * TODO: Does not work
     * Fast forward the game to the start of stage three if the player
     * kills the mid boss before the stage two end.
     */
    private void fastForwardToStageThree() {
        if (this.karen != null) {
            if (this.stageThreeStart > (int) timeController.getElapsedTime() && this.karen.isDone()) {
                this.stageThreeStart = (int) timeController.getElapsedTime();
            }
        }
    }
}