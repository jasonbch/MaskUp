package MaskGame;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import Enemy.Enemy;
import Enemy.EnemyFactory;
import Enemy.MurderHornet;
import Entity.Player;
import Entity.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * GameScreen class that implements from Screen that let the user play
 * the game.
 */
public class GameScreen implements Screen {
    // Screen
    private Camera camera;
    private Viewport viewport;

    // Graphic
    private SpriteBatch batch;
    private Texture[] backgrounds;

    //timing stuff

    private float[] backgroundOffsets = {0,0,0,0};
    private float maxScrollingSpeed;

    private int backgroundOffset;
    private long elapsedTime;
    private long startTime;
    private long lastSpawnTime = 0;
    private long lastMidBossTime = 0;
    private long lastFinalBossTime = 0;





    // World dimension
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    // Game Objects
    private Entity player;
    private Enemy bee;
    private Enemy bat;
    private Enemy covid;

    private EnemyFactory enemyFactory = new EnemyFactory();
    private LinkedList<Ammo> enemyAmmoList;
    private LinkedList<Ammo> playerAmmoList;
    private LinkedList<Enemy> enemyList;

    // Slow mode
    private boolean isSlowMode;
    private float gameSpeed;    // Current game speed

    /**
     * Create a GameScreen that let the user play a game of bullet hell.
     */
    public GameScreen(){
        // Set up camera
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

//        background = new Texture("bluebackground.png");
//        backgroundOffset = 0;
        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("BlueBackground.png");
        backgrounds[1] = new Texture("Clouds1.png");
        backgrounds[2] = new Texture("Clouds2.png");
        backgrounds[3] = new Texture("Cloud4.png");
        maxScrollingSpeed = (float)(WORLD_HEIGHT)/4;

        // Set up game objects
        player = new Player(WORLD_WIDTH/2, WORLD_HEIGHT/4);
        bee = enemyFactory.create("MurderHornet", WORLD_WIDTH/2, WORLD_HEIGHT*3/4);


        // Set up mode
        this.isSlowMode = false;
        // Set current game speed to normal speed
        this.gameSpeed = 1;


        bat = enemyFactory.create("Bat", WORLD_WIDTH/2 - 5, WORLD_HEIGHT*3/5);
        covid = enemyFactory.create("Covid", WORLD_WIDTH/2-10, WORLD_HEIGHT*3/6);

        playerAmmoList = new LinkedList<>();
        enemyAmmoList = new LinkedList<>();
        enemyList = new LinkedList<>();

        startTime = TimeUtils.millis();

        // add initial enemies to list
        enemyList.add(bee);
        enemyList.add(bat);
        enemyList.add(covid);

        batch = new SpriteBatch();
    }

    @Override
    public void render(float deltaTime) {
        // If the L key is just press and it is not slow down mode
        if (Gdx.input.isKeyJustPressed(Input.Keys.L) && !isSlowMode) {
            // Change the slow mode to true
            isSlowMode = true;

            // Change the game speed to slow speed
            gameSpeed = 0.1f;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L) && isSlowMode) {
            // If the L key is just press and it is slow down mode
            // Change slow mode to false
            isSlowMode = false;
            // Change the game speed to normal speed
            gameSpeed = 1;
        }
        deltaTime *= gameSpeed;

        batch.begin();

        elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;



        //System.out.println(lastSpawnTime);
        if(elapsedTime % 5 == 0 && elapsedTime != 1 && elapsedTime != 0 && elapsedTime - lastSpawnTime > 3)
        {
            System.out.println("spawning enemies");
            spawnEnemies();
            lastSpawnTime = elapsedTime;
        }

        if(elapsedTime % 20 == 0 && elapsedTime != 1 && elapsedTime != 0 && elapsedTime - lastMidBossTime > 3)
        {
            System.out.println("spawning mid boss");
            spawnMidboss();
            lastMidBossTime = elapsedTime;
        }

        if(elapsedTime % 40 == 0 && elapsedTime != 1 && elapsedTime != 0 && elapsedTime - lastFinalBossTime > 3)
        {
            System.out.println("spawning final boss");
            spawnFinalBoss();
            lastFinalBossTime = elapsedTime;
        }

        //Update objects (movements and bullet times)
        player.update(deltaTime);
        bee.update(deltaTime);
        bat.update(deltaTime);
        covid.update(deltaTime);

        //scrolling background
        renderBackground(deltaTime);


        //draw the player
        player.draw(batch);

        // fire enemy bullets, if they can fire.
        enemyFire(deltaTime);

        //draw and update all bullets(enemy and player) and enemies.
        drawAndUpdateBulletsAndEnemies(deltaTime);

        // delete enemies, if they need deleted
        deleteEnemies();


        // Check player movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.xPos -= player.getSpeed() * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.xPos += player.getSpeed() * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            player.yPos += player.getSpeed() * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.yPos -= player.getSpeed() * deltaTime;

        // make sure the bucket stays within the screen bounds
        if (player.xPos < 0)
            player.xPos = 0;
        if (player.xPos > WORLD_WIDTH - 10)
            player.xPos = WORLD_WIDTH - 10;
        if (player.yPos < 0)
            player.yPos = 0;
        if (player.yPos > WORLD_HEIGHT - 10)
            player.yPos = WORLD_HEIGHT - 10;

        // check player shooting input

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(player.canFire()) {
                Ammo ammo = player.fire("Bullet");
                playerAmmoList.add(ammo);
            }
        }

        batch.end();
    }


    private void renderBackground(float deltaTime)
    {
        backgroundOffsets[0] += deltaTime * maxScrollingSpeed/ 8;
        backgroundOffsets[1] += deltaTime * maxScrollingSpeed/ 4;
        backgroundOffsets[2] += deltaTime * maxScrollingSpeed/ 2;
        backgroundOffsets[3] += deltaTime * maxScrollingSpeed;

        for(int layer = 0; layer < backgroundOffsets.length; layer ++) {
            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        }

    }


    private void deleteEnemies() {
        ListIterator<Enemy> iter2 = enemyList.listIterator();
        while (iter2.hasNext()) {
            Enemy currEnemy = iter2.next();
            if (currEnemy.yPos > WORLD_HEIGHT) {
                iter2.remove();
            }
        }
    }

    private void spawnEnemies()
    {
        enemyList.add(enemyFactory.create("MurderHornet", WORLD_WIDTH/2, WORLD_HEIGHT*3/4));
        enemyList.add(enemyFactory.create("Bat", WORLD_WIDTH/2 - 5, WORLD_HEIGHT*3/5));
    }

    private void spawnMidboss() {
        enemyList.add(enemyFactory.create("Karen", WORLD_WIDTH/2, WORLD_HEIGHT*3/4));
    }

    private void spawnFinalBoss()
    {
        enemyList.add(enemyFactory.create("Covid", WORLD_WIDTH/2, WORLD_HEIGHT*3/4));
    }

    // TODO

    // fix
    private void enemyFire(float deltaTime)
    {
        ListIterator<Enemy> iterator = enemyList.listIterator();
        while(iterator.hasNext())
        {
            Enemy currEnemy = iterator.next();
            currEnemy.update(deltaTime);
            if(currEnemy.canFire()) {
                Ammo ammo = currEnemy.fire(currEnemy.bullet());
                enemyAmmoList.add(ammo);
            }
        }
    }

    private void drawAndUpdateBulletsAndEnemies(float deltaTime)
    {
        //Player bullets
        ListIterator<Ammo> iterator = playerAmmoList.listIterator();
        while(iterator.hasNext()) {
            Ammo ammo = iterator.next();
            ammo.draw(batch);
            ammo.yPos += ammo.getSpeed()*deltaTime;

            if(ammo.yPos > WORLD_HEIGHT) {
                iterator.remove();
            }
        }

        // Enemy bullets
        ListIterator<Ammo> iter = enemyAmmoList.listIterator();
        while(iter.hasNext()) {
            Ammo ammo = iter.next();
            ammo.draw(batch);
            ammo.yPos -= ammo.getSpeed()*deltaTime;

            if(ammo.yPos < 0) {
                iter.remove();
            }
        }

        //draw Enemies
        ListIterator<Enemy> iter2 = enemyList.listIterator();
        while(iter2.hasNext()) {
            Enemy currEnemy = iter2.next();
            currEnemy.updateMovement(deltaTime);
            currEnemy.draw(batch);
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width,height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    @Override
    public void show() {

    }
    @Override
    public void dispose() {

    }
}
