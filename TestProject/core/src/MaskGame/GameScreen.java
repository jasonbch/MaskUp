package MaskGame;

import Ammo.Ammo;
import Ammo.AmmoFactory;
import Enemy.Enemy;
import Enemy.Player;
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
    private Enemy player;
    private Enemy bee;
    private AmmoFactory ammoFactory = new AmmoFactory();
    private LinkedList<Ammo> enemyAmmoList;
    private LinkedList<Ammo> playerAmmoList;



    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("bluebackground.png");
        backgroundOffset = 0;

        //set up game objects
        player = new Player(WORLD_WIDTH/2, WORLD_HEIGHT/4);
        bee = new Player((WORLD_WIDTH/2)-10, WORLD_HEIGHT*3/4);

        playerAmmoList = new LinkedList<>();
        enemyAmmoList = new LinkedList<>();

        batch = new SpriteBatch();
    }


    @Override
    public void render(float deltaTime) {
        batch.begin();
        player.update(deltaTime);
        //bee.update(deltaTime);
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

        //player
        player.draw(batch);

        //bullets
        //create new lasers
        //player lasers
        if(player.canFire())
        {
            Ammo ammo = player.fire();
            playerAmmoList.add(ammo);
        }
        //player lasers
        if(bee.canFire())
        {
            Ammo ammo = player.fire();
            playerAmmoList.add(ammo);
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
