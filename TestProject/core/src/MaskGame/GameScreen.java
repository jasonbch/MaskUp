package MaskGame;

import Ammo.Ammo;
import Ammo.AmmoFactory;

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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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


    // World dimension
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    // Game Objects
    private Entity player;
    private Entity bee;
    private Entity bat;
    private Entity covid;

    private EnemyFactory enemyFactory = new EnemyFactory();
    private LinkedList<Ammo> enemyAmmoList;
    private LinkedList<Ammo> playerAmmoList;
    private long elapsedTime;
    private long starttime;

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


        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("BlueBackground.png");
        backgrounds[1] = new Texture("Clouds1.png");
        backgrounds[2] = new Texture("Clouds2.png");
        backgrounds[3] = new Texture("Cloud4.png");
        maxScrollingSpeed = (float)(WORLD_HEIGHT)/4;

        // Set up game objects
        player = new Player(WORLD_WIDTH/2, WORLD_HEIGHT/4);
        bee = enemyFactory.create("MurderHornet", WORLD_WIDTH/2, WORLD_HEIGHT*3/4);
        bat = enemyFactory.create("Bat", WORLD_WIDTH/2 - 10, WORLD_HEIGHT*3/5);
        covid = enemyFactory.create("Covid", WORLD_WIDTH/2 - 30, WORLD_HEIGHT*3/6);

        // Set up mode
        this.isSlowMode = false;
        // Set current game speed to normal speed
        this.gameSpeed = 1;

        playerAmmoList = new LinkedList<>();
        enemyAmmoList = new LinkedList<>();

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
        player.update(deltaTime);
        bee.update(deltaTime);
        bat.update(deltaTime);
        covid.update(deltaTime);

        //scrolling background


        //scrolling background
        renderBackground(deltaTime);

        //enemy
        bee.draw(batch);
        bat.draw(batch);
        covid.draw(batch);

        //player
        player.draw(batch);


        //bullets
        //create new lasers
        //bee

        if(bee.canFire()) {
            Ammo ammo = bee.fire("Stinger");
            enemyAmmoList.add(ammo);

        }
        if(bat.canFire())
        {
            Ammo ammo = bat.fire("CovidGerm");
            enemyAmmoList.add(ammo);
        }
        if(covid.canFire())
        {
            Ammo ammo = covid.fire("BabyCovid");
            enemyAmmoList.add(ammo);
        }

        //draw lasers

        //remove old lasers
        ListIterator<Ammo> iterator = playerAmmoList.listIterator();
        while(iterator.hasNext()) {
            Ammo ammo = iterator.next();
            ammo.draw(batch);
            ammo.yPos += ammo.getSpeed()*deltaTime;

            if(ammo.yPos > WORLD_HEIGHT) {
                iterator.remove();
            }
        }

        ListIterator<Ammo> iter = enemyAmmoList.listIterator();
        while(iter.hasNext()) {
            Ammo ammo = iter.next();
            ammo.draw(batch);
            ammo.yPos -= ammo.getSpeed()*deltaTime;

            if(ammo.yPos < 0) {
                iter.remove();
            }
        }

        // checking if the bullets intersect
        //detectCollision();

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
    /*public void detectCollision()
    {
        ListIterator<Ammo> iterator1 = playerAmmoList.listIterator();
        while(iterator1.hasNext()) {
            Ammo ammo = iterator1.next();
            //System.out.println("checking if bee is hit");
            if (bee.intersects(ammo.boundingBox())) {
                //System.out.println("hit");
                iterator1.remove();
            }
        }
        iterator1 = enemyAmmoList.listIterator();
        while(iterator1.hasNext()) {
            Ammo ammo = iterator1.next();
            //System.out.println("checking if bee is hit");
            if (player.intersects(ammo.boundingBox())) {
                //System.out.println("hit");
                iterator1.remove();
            }
        }
        iterator1 = playerAmmoList.listIterator();
        while(iterator1.hasNext()) {
            Ammo ammo = iterator1.next();
            //System.out.println("checking if bee is hit");
            if (bat.intersects(ammo.boundingBox())) {
                //System.out.println("hit");
                iterator1.remove();
            }
        }
        iterator1 = playerAmmoList.listIterator();
        while(iterator1.hasNext()) {
            Ammo ammo = iterator1.next();
            //System.out.println("checking if bee is hit");
            if (covid.intersects(ammo.boundingBox())) {
                iterator1.remove();
            }
        }



    }*/


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
