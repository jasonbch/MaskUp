package GameEngine;

import Entity.GameObject;
import Entity.Player;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class UIController {

    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final GameObject player = Player.instance();
    private final GameResources gameResources = GameResources.instance();
    private final GameController gameController = GameController.instance();
    private Batch batch;
    private Batch healthBatch = new SpriteBatch();
    private final AssetManager assetManager = GameResources.getAssetsManager();
    // Background
    private float maxScrollingSpeed = (float) (gameResources.getWorldHeight()) / 4;
    ;
    private Texture[] backgrounds = {assetManager.get("BlueBackground.png", Texture.class), assetManager.get("Clouds1.png", Texture.class),
            assetManager.get("Clouds2.png", Texture.class), assetManager.get("Cloud4.png", Texture.class)};
    private final float[] backgroundOffsets = {0, 0, 0, 0};
    private final Music backgroundMusic = assetManager.get("BackgroundMusic.mp3", Music.class);


    // TODO come back later to think about passing in the player
    public UIController(Batch batch) {
        this.batch = batch;
    }

    public void drawWhiteDotInSlowMode() {
        if (gameController.getIsSlowMode()) {
            batch.draw(assetManager.get("CircleHitBox.png", Texture.class),
                    player.getXPosition() + (float) (player.getImageWidth() / 2) - (float) (player.getImageWidth() / 4),
                    player.getYPosition() + (float) (player.getImageHeight() / 2) - (float) (player.getImageWidth() / 4),
                    (float) player.getImageWidth() / 2,
                    (float) player.getImageWidth() / 2);
        }
    }

    public void playMusic() {
        backgroundMusic.setVolume((float) 0.01);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }


    public void drawGameObjects() {
        drawEnemies();
        drawPlayer();
        drawPlayerAmmo();
        drawEnemyAmmo();
    }

    /**
     * Render the background.
     *
     * @param deltaTime the delta time
     */
    public void drawBackground(float deltaTime) {
        backgroundOffsets[0] += deltaTime * maxScrollingSpeed / 8;
        backgroundOffsets[1] += deltaTime * maxScrollingSpeed / 4;
        backgroundOffsets[2] += deltaTime * maxScrollingSpeed / 2;
        backgroundOffsets[3] += deltaTime * maxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; layer++) {
            if (layer == 0) {
                batch.draw(backgrounds[layer], 0, 0, gameResources.getWorldWidth(), gameResources.getWorldHeight());
            } else {
                if (backgroundOffsets[layer] > gameResources.getWorldHeight()) {
                    backgroundOffsets[layer] = 0;
                }
                batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], gameResources.getWorldWidth(), gameResources.getWorldHeight());
                batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + gameResources.getWorldHeight(), gameResources.getWorldWidth(), gameResources.getWorldHeight());
            }
        }
    }

    private void drawPlayerAmmo() {
        List<GameObject> objectList = (List<GameObject>) (List<?>) bulletSpawningController.getPlayerAmmoList();
        drawList(objectList.listIterator());
    }

    private void drawEnemyAmmo() {
        List<GameObject> objectList = (List<GameObject>) (List<?>) bulletSpawningController.getEnemyAmmoList();
        drawList(objectList.listIterator());
    }

    private void drawEnemies() {
        List<GameObject> objectList = (List<GameObject>) (List<?>) enemySpawningController.getEnemyList();
        drawList(objectList.listIterator());
    }

    private void drawPlayer() {
        draw(player);
    }

    private void drawList(ListIterator<GameObject> iterator) {
        while (iterator.hasNext()) {
            GameObject object = iterator.next();
            draw(object);
        }
    }

    private void draw(GameObject object) {
        batch.draw(object.getImage(), object.getXPosition(), object.getYPosition(), object.getImage().getWidth(), object.getImage().getHeight());
    }

    private void dispose() {
        healthBatch.end();
    }


    public void updateAndRenderHUD() {
        int xoffset = 30;
        for (int i = 0; i < ((Player) player).getHealth(); i++) {
            if (((Player) player).getHealth() != 0) {
                Texture image = ((Player) player).getImage();
                healthBatch.begin();
                healthBatch.draw(image, xoffset * i, 1000, image.getWidth() / 2, image.getHeight() / 2);
                dispose();
            }
        }

    }


}
