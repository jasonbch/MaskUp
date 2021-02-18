package MaskGame;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import Enemy.Bat;
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
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    //Game Objects
    private Entity player;
    private Entity bee;
    private Entity bat;
    private Entity covid;
    private EnemyFactory enemyFactory = new EnemyFactory();
    private LinkedList<Ammo> enemyAmmoList;
    private LinkedList<Ammo> playerAmmoList;



    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("bluebackground.png");
        backgroundOffset = 0;

        //set up game objects
        player = new Player(WORLD_WIDTH/2 - 5, WORLD_HEIGHT/4);
        bee = enemyFactory.create("MuderHornet",(WORLD_WIDTH/2)-5, WORLD_HEIGHT*3/4);
        bat = enemyFactory.create("Bat",(WORLD_WIDTH/2) - 10, WORLD_HEIGHT*3/5);
        covid = enemyFactory.create("Covid", (WORLD_WIDTH/2) - 8, WORLD_HEIGHT*3/6);

        playerAmmoList = new LinkedList<>();
        enemyAmmoList = new LinkedList<>();

        batch = new SpriteBatch();
    }


    @Override
    public void render(float deltaTime) {
        batch.begin();
        player.update(deltaTime);
        bee.update(deltaTime);
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


        //player lasers
        if(bee.canFire())
        {
            Ammo ammo = bee.fire("Stinger");
            enemyAmmoList.add(ammo);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            //bullets
            //create new lasers
            //player lasers
            if(player.canFire())
            {
                Ammo ammo = player.fire("Syrenge");
                playerAmmoList.add(ammo);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            player.yPos += player.getSpeed() * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            player.xPos += player.getSpeed() * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            player.xPos += -(player.getSpeed()) * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            player.yPos += -(player.getSpeed()) * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            //bullets
            //create new lasers
            //player lasers
            if(player.canFire())
            {
                Ammo ammo = player.fire("Bullet");
                playerAmmoList.add(ammo);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            player.yPos += player.getSpeed() * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            player.xPos += player.getSpeed() * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            player.xPos += -(player.getSpeed()) * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            player.yPos += -(player.getSpeed()) * deltaTime;
        }

        if(player.xPos < 0)
        {
            player.xPos = 0;
        }
        if(player.xPos > WORLD_WIDTH - 10)
        {
            player.xPos = WORLD_WIDTH - 10;
        }
        if(player.yPos > WORLD_HEIGHT - 10)
        {
            player.yPos = WORLD_HEIGHT - 10;
        }
        if(player.yPos < 0)
        {
            player.yPos = 0;
        }

        //draw lasers

        //remove old lasers
        ListIterator<Ammo> iterator = playerAmmoList.listIterator();
        while(iterator.hasNext())
        {
            Ammo ammo = iterator.next();
            ammo.draw(batch);
            ammo.yPos += ammo.getSpeed()*deltaTime;
            if(ammo.yPos > WORLD_HEIGHT)
            {
                iterator.remove();
            }
        }

        iterator = enemyAmmoList.listIterator();
        while(iterator.hasNext())
        {
            Ammo ammo = iterator.next();
            ammo.draw(batch);
            ammo.yPos -= ammo.getSpeed()*deltaTime;
            if(ammo.yPos < 0)
            {
                iterator.remove();
            }
        }



        batch.end();

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
