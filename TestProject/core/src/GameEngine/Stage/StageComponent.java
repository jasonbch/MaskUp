package GameEngine.Stage;

import GameEngine.Observer.GameObserver;
import org.graalvm.compiler.phases.common.NodeCounterPhase;

public class StageComponent implements GameObserver {
    protected String section;
    protected String enemyName;
    protected int startTimeFromStage;
    protected String enemyMovementPattern;
    protected String bulletFormation;
    protected int startTime;
    protected boolean isRan;
    protected int stageNumber = 0;

    public StageComponent(String section, String enemyName, int startTimeFromStage, String enemyMovementPattern, String bulletFormation) {
        this.section = section;
        this.enemyName = enemyName;
        this.startTimeFromStage = startTimeFromStage;
        this.enemyMovementPattern = enemyMovementPattern;
        this.bulletFormation = bulletFormation;
        this.isRan = false;
        this.setStageNumber();
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getSection() {
        return this.section;
    }

    public int getStageNumber() {
        return this.stageNumber;
    }

    public int getStartTimeFromStage() {
        return this.startTimeFromStage;
    }

    private void setStageNumber() {
        this.stageNumber = Integer.parseInt(String.valueOf(section.toCharArray()[0]));
    }

    @Override
    public void update(Object object, String args) {
        if (args.equals("fastForward")) {
            if (object instanceof StageController) {
                StageController stageController = (StageController) object;

                if (this.stageNumber == 3) {
                    this.setStartTime(stageController.getStageThreeStart() + this.startTimeFromStage);
                } else if (this.stageNumber == 4) {
                    this.setStartTime(stageController.getStageFourStart() + this.startTimeFromStage);
                }
            }
        }
    }
}
