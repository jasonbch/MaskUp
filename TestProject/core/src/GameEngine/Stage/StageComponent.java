package GameEngine.Stage;

import GameEngine.Observer.GameObserver;

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
        if (object instanceof StageController) {
            String[] array = args.split(",");

            if (this.stageNumber == 3) {
                System.out.println("three: " + Integer.parseInt(array[0]));
                this.setStartTime(Integer.parseInt(array[0]) + this.startTimeFromStage);
            } else if (this.stageNumber == 4) {
                System.out.println("four: " + Integer.parseInt(array[1]));
                this.setStartTime(Integer.parseInt(array[1]) + this.startTimeFromStage);
            }
        }
    }
}
