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

public class GameScreen implements Screen {
    //screen stuff
    private Camera camera;
    private Viewport viewport;

    //graphic stuff
    private SpriteBatch batch;
    private Texture background;

    //timing stuff
    private int backgroundOffset;

    //world parameters
    private final int WORLD_WIDTH = 576;
    private final int WORLD_HEIGHT = 1024;

    //Game Objects
    private Entity player;
    private Entity bee;
    private Entity bat;
    private Entity covid;

    private EnemyFactory enemyFactory = new EnemyFactory();
    private LinkedList<Ammo> enemyAmmoList;
    private LinkedList<Ammo> playerAmmoList;
    private long elapsedTime;
    private long starttime;




    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("bluebackground.png");
        backgroundOffset = 0;


        player = new Player(WORLD_WIDTH/2, WORLD_HEIGHT/4);
        bee = enemyFactory.create("MurderHornet", WORLD_WIDTH/2, WORLD_HEIGHT*3/4);
        bat = enemyFactory.create("Bat", WORLD_WIDTH/2 - 10, WORLD_HEIGHT*3/5);
        covid = enemyFactory.create("Covid", WORLD_WIDTH/2 - 30, WORLD_HEIGHT*3/6);

        playerAmmoList = new LinkedList<>();
        enemyAmmoList = new LinkedList<>();

        batch = new SpriteBatch();
    }


    @Override
    public void render(float deltaTime) {
        batch.begin();
        player.update(deltaTime);
        bee.update(deltaTime);
        bat.update(deltaTime);
        covid.update(deltaTime);

        //scrolling background

        backgroundOffset++;
        if(backgroundOffset%WORLD_HEIGHT == 0)
        {
            backgroundOffset = 0;
        }
        batch.draw(background, 0, -backgroundOffset, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background, 0, -backgroundOffset+WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);

        //enemy
        bee.draw(batch);
        bat.draw(batch);
        covid.draw(batch);

        //player
        player.draw(batch);


        //bullets
        //create new lasers
        //bee
        if(bee.canFire())
        {
            //System.out.println("bee can fire");
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
        while(iterator.hasNext())
        {
            //System.out.println ("player shooting");

            Ammo ammo = iterator.next();
            ammo.draw(batch);
            ammo.yPos += ammo.getSpeed()*deltaTime;
            if(ammo.yPos > WORLD_HEIGHT)
            {
                iterator.remove();
            }
        }

        ListIterator<Ammo> iter = enemyAmmoList.listIterator();
        while(iter.hasNext())
        {
            Ammo ammo = iter.next();
            ammo.draw(batch);
            ammo.yPos -= ammo.getSpeed()*deltaTime;
            if(ammo.yPos < 0)
            {
                iter.remove();
            }
        }

        // checking if the bullets intersect
        detectCollision();

        // Check player movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.xPos -= player.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.xPos += player.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            player.yPos += player.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.yPos -= player.getSpeed() * Gdx.graphics.getDeltaTime();

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
            if(player.canFire())
            {
                Ammo ammo = player.fire("Bullet");
                playerAmmoList.add(ammo);
            }
        }

        batch.end();

    }
    public void detectCollision()
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
