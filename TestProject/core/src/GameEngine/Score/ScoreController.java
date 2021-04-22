package GameEngine.Score;

import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Spawning.EnemySpawningController;
import Objects.GameObject.Enemy.*;

import java.util.ArrayList;

public class ScoreController implements GameObserver {
    // Implement Singleton
    private static ScoreController uniqueInstance = null;
    private int score;

    private ScoreController() {
        // Initialize Score to 0
        this.score = 0;
    }

    /**
     * Return the instance of ScoreController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of ScoreController.
     */
    public static ScoreController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ScoreController();
        }

        return uniqueInstance;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int addedScore) {
        this.score = this.score + addedScore;
    }

    public void addScore(Enemy enemy) {
        int score = 0;

        if (enemy instanceof Bat) {
            score = 100;
        } else if (enemy instanceof MurderHornet) {
            score = 200;
        } else if (enemy instanceof Karen) {
            score = 1500;
        } else if (enemy instanceof Covid) {
            score = 3000;
        }
        this.addScore(score);
    }


    @Override
    public void update(Object o, String args) {
        if(o instanceof Enemy){
            this.addScore((Enemy) o);
        }
    }
}
