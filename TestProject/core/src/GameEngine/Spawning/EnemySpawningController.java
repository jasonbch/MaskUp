package GameEngine.Spawning;

import GameEngine.Factory.EnemyFactory;
import GameEngine.Observer.GameObserver;
import GameEngine.Observer.GameSubject;
import GameEngine.Resource.GameResources;
import GameEngine.Score.ScoreController;
import GameEngine.Stage.StageController;
import Objects.GameObject.BulletSpawner;
import Objects.GameObject.Enemy.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * EnemySpawningController class that implements Singleton.
 * The class is in charged of spawning enemies. The class can
 * also create different type of enemies.
 */
public class EnemySpawningController implements GameObserver {
    private static final GameResources gameResources = GameResources.instance();
    private static final ScoreController scoreController = ScoreController.instance();
    private static final BulletSpawnerSpawningController bulletSpawnerSpawningController = BulletSpawnerSpawningController.instance();
    private static final StageController stageController = StageController.instance();

    // Implement Singleton
    private static EnemySpawningController uniqueInstance = null;

    private final EnemyFactory enemyFactory = new EnemyFactory();
    CopyOnWriteArrayList enemyList = new CopyOnWriteArrayList<Enemy>();
    private final Random rand = new Random();
    private Enemy currentEnemy;

    private EnemySpawningController() {
    }

    /**
     * Return the instance of EnemySpawningController.
     * Create the instance if the instance has not been initialized.
     *
     * @return the instance of EnemySpawningController.
     */
    public static EnemySpawningController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EnemySpawningController();
        }

        return uniqueInstance;
    }

    public CopyOnWriteArrayList<Enemy> getEnemyList() {
        return this.enemyList;
    }

    /**
     * Delete the enemies if they got out of the screen.
     */
    public void deleteEnemies(Enemy enemy) {
        enemyList.remove(enemy);
    }

    /**
     * Spawn a given enemy with the given pattern
     *
     * @param enemy   the enemy's name
     * @param pattern the movement pattern of the enemy
     */
    public Enemy spawnEnemies(String enemy, String pattern) {
        int xPosition = rand.nextInt(gameResources.getScreenOneEnd() - 200) + 100;
        int yPosition = gameResources.getWorldHeight();
        Enemy concreteEnemy = enemyFactory.create(enemy, xPosition, yPosition, pattern);

        // Add an observer for the enemy
        BulletSpawner bulletSpawner = bulletSpawnerSpawningController.addSpawner(concreteEnemy, "1");

        // Make the bulletSpawner observe the enemy
        concreteEnemy.addObserver(bulletSpawner);

        // Increase the bullet spawner count
        concreteEnemy.setBulletSpawnerCount(1);

        // Get the position for y
        float randomY = rand.nextInt(300) + 700;
        concreteEnemy.setYAxis(randomY);

        // Attach Observers
        concreteEnemy.Attach(this);
        concreteEnemy.Attach(scoreController);
        if(enemy.equals("Karen") || enemy.equals("Covid")){
            concreteEnemy.Attach(stageController);
        }

        // Add enemy to the list
        enemyList.add(concreteEnemy);

        // Add bullet spawner to the list
        bulletSpawnerSpawningController.getBulletSpawnerList().add(bulletSpawner);

        return concreteEnemy;
    }

    public Enemy findEnemy(String enemyName) {
        ListIterator<Enemy> iterator = this.getEnemyList().listIterator();
        while (iterator.hasNext()) {
            Enemy currentEnemy = iterator.next();
            if (enemyName.equals("Karen")) {
                if (currentEnemy instanceof Karen) {
                    return currentEnemy;
                }
            } else if (enemyName.equals("Covid")) {
                if (currentEnemy instanceof Covid) {
                    return currentEnemy;
                }
            } else if (enemyName.equals("Bat")) {
                if (currentEnemy instanceof Bat) {
                    return currentEnemy;
                }
            } else if (enemyName.equals("MurderHornet")) {
                if (currentEnemy instanceof MurderHornet) {
                    return currentEnemy;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Object o, String args) {
        if(o instanceof Enemy)
        {
            if(args.equals("deleteEnemy"))
            {
                deleteEnemies((Enemy) o);
            }
        }
    }

//
//    @Override
//    public void Attach(GameObserver o) {
//        this.myObs.add(o);
//    }
//
//    @Override
//    public void Dettach(GameObserver o) {
//        this.myObs.remove(o);
//    }
//
//    @Override
//    public void Notify() {
//        for(int i = 0; i < this.myObs.size(); i++)
//        {
//            this.myObs.get(i).update(this, currentEnemy);
//        }
//    }


}
