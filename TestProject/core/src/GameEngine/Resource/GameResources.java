package GameEngine.Resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameResources {
    public static AssetManager assetsManager = new AssetManager();

    // Implement Singleton
    private static GameResources uniqueInstance = null;
    private static String gameJSON = "game.json";
    private final int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private final int WORLD_WIDTH = Gdx.graphics.getWidth();
    private SpriteBatch batch = null;
    private OrthographicCamera camera = null;

    private GameResources() {
        this.initializeAssets();
        assetsManager.finishLoading();
    }

    public static GameResources instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameResources();
        }

        return uniqueInstance;
    }

    public static AssetManager getAssetsManager() {
        return assetsManager;
    }

    public void initializeAssets() {
        assetsManager.load("BabyCovid.png", Texture.class);
        assetsManager.load("BackgroundMusic.mp3", Music.class);
        assetsManager.load("Bat.png", Texture.class);
        assetsManager.load("BigCovid.png", Texture.class);
        assetsManager.load("BlueBackground.png", Texture.class);
        assetsManager.load("Bullet.png", Texture.class);
        assetsManager.load("CircleHitBox.png", Texture.class);
        assetsManager.load("Cloud3.png", Texture.class);
        assetsManager.load("Cloud4.png", Texture.class);
        assetsManager.load("Clouds1.png", Texture.class);
        assetsManager.load("Clouds2.png", Texture.class);
        assetsManager.load("CovidGerm.png", Texture.class);
        assetsManager.load("GreenCloud.png", Texture.class);
        assetsManager.load("Karen.png", Texture.class);
        assetsManager.load("Mask.png", Texture.class);
        assetsManager.load("MurderHornet.png", Texture.class);
        assetsManager.load("Player.png", Texture.class);
        assetsManager.load("Stinger.png", Texture.class);
        assetsManager.load("Syringe.png", Texture.class);
        assetsManager.load("toiletPaper.png", Texture.class);
        assetsManager.load("Lives.png", Texture.class);
        assetsManager.load("Score.png", Texture.class);
        assetsManager.load("PlayerHudBackground.png", Texture.class);
        assetsManager.load("human.png", Texture.class);
    }

    public String getGameJSON() {
        return this.gameJSON;
    }

    public int getScreenOneStart() {
        return 0;
    }

    public int getScreenOneEnd() {
        return WORLD_WIDTH / 2;
    }

    public int getScreenTwoStart() {
        return WORLD_WIDTH / 2;
    }

    public int getScreenTwoEnd() {
        return WORLD_WIDTH;
    }

    // if you change this make sure you update the width in desktop launcher as well
    public int getScreenOneWidth() {
        return 576;
    }

    public int getScreenTwoWidth() {
        return 576;
    }

    /**
     * Return the world height.
     */
    public int getWorldHeight() {
        return WORLD_HEIGHT;
    }

    /**
     * Return the world width.
     */
    public int getWorldWidth() {
        return WORLD_WIDTH;
    }

    public void setBatch(SpriteBatch batch){
        this.batch = batch;
    }

    public SpriteBatch getBatch()
    {
        return this.batch;
    }

    public void setCamera(OrthographicCamera camera){
        this.camera = camera;
    }

    public OrthographicCamera getCamera(){
        return this.camera;
    }
}
