package GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class GameResources {
    // Implement Singleton
    private static GameResources uniqueInstance = null;

    public static AssetManager assetsManager = new AssetManager();

    public static GameResources instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameResources();
        }

        return uniqueInstance;
    }

    private GameResources() {
        this.initializeAssets();
        assetsManager.finishLoading();
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
    }

    /**
     * Return the world width.
     */
    public int getWorldWidth() {
        return Gdx.graphics.getWidth();
    }

    /**
     * Return the world height.
     */
    public int getWorldHeight() {
        return Gdx.graphics.getHeight();
    }
}
