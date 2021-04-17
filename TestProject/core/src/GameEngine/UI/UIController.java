package GameEngine.UI;

import GameEngine.GameController;
import GameEngine.Resource.GameResources;
import GameEngine.Score.ScoreController;
import GameEngine.Spawning.BulletSpawnerSpawningController;
import GameEngine.Spawning.BulletSpawningController;
import GameEngine.Spawning.EnemySpawningController;
import GameEngine.Stage.StageController;
import GameEngine.Time.TimeController;
import Objects.GameObject.GameObject;
import Objects.GameObject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.List;
import java.util.ListIterator;

public class UIController {
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final BulletSpawnerSpawningController bulletSpawnerSpawningController = BulletSpawnerSpawningController.instance();

    private final GameObject player = Player.instance();
    private final GameResources gameResources = GameResources.instance();
    private final TimeController timeController = TimeController.instance();
    private final GameController gameController = GameController.instance();


    private final Batch batch;
    private final AssetManager assetManager = GameResources.getAssetsManager();
    private final ScoreController scoreController = ScoreController.instance();
    // Background
    private final float maxScrollingSpeed = (float) (gameResources.getWorldHeight()) / 4;
    private final Texture[] backgrounds = {assetManager.get("BlueBackground.png", Texture.class), assetManager.get("Clouds1.png", Texture.class), assetManager.get("Clouds2.png", Texture.class), assetManager.get("Cloud4.png", Texture.class), assetManager.get("PlayerHudBackground.png", Texture.class)};
    private final float[] backgroundOffsets = {0, 0, 0, 0};
    private final Music backgroundMusic = assetManager.get("BackgroundMusic.mp3", Music.class);
    Texture stage1 = new Texture("stage1.png");
    Texture stage2 = new Texture("stage2.png");
    Texture stage3 = new Texture("stage3.png");
    Texture stage4 = new Texture("stage4.png");
    BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"));

    // Stage Message
    private int stageMessageWidth = 421;
    private int stageMessageHeight = 132;
    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private StageController stageController = StageController.instance();

    //BitmapFont font = new BitmapFont();

    public UIController(Batch batch) {
        this.batch = batch;
    }

    public void drawWhiteDotInSlowMode() {
        if (gameController.getIsSlowMode()) {
            batch.draw(assetManager.get("CircleHitBox.png", Texture.class), player.getXPosition() + (float) (player.getImageWidth() / 2) - (float) (player.getImageWidth() / 4), player.getYPosition() + (float) (player.getImageHeight() / 2) - (float) (player.getImageWidth() / 4), (float) player.getImageWidth() / 2, (float) player.getImageWidth() / 2);
        }
    }

    public void playMusic() {
        backgroundMusic.setVolume((float) 0.01);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    public void drawGameObjects() {
        drawPlayerAmmo();
        drawEnemyAmmo();
        drawEnemies();
        drawPlayer();
        //drawBulletSpawners();
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

        batch.draw(backgrounds[4], gameResources.getScreenTwoStart(), 0, gameResources.getScreenTwoWidth(), gameResources.getWorldHeight());

        for (int layer = 0; layer < backgroundOffsets.length; layer++) {
            if (layer == 0) {
                batch.draw(backgrounds[layer], 0, 0, gameResources.getScreenOneWidth(), gameResources.getWorldHeight());
            } else {
                if (backgroundOffsets[layer] > gameResources.getWorldHeight()) {
                    backgroundOffsets[layer] = 0;
                }

                batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], gameResources.getScreenOneWidth(), gameResources.getWorldHeight());
                batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + gameResources.getWorldHeight(), gameResources.getScreenOneWidth(), gameResources.getWorldHeight());
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

    private void drawBulletSpawners() {
        List<GameObject> objectList = (List<GameObject>) (List<?>) bulletSpawnerSpawningController.getBulletSpawnerList();
        drawList(objectList.listIterator());
    }

    private void drawList(ListIterator<GameObject> iterator) {
        while (iterator.hasNext()) {
            GameObject object = iterator.next();
            draw(object);
        }
    }

    private void draw(GameObject object) {
        batch.draw(object.getTexture(), object.getXPosition(), object.getYPosition(), object.getTexture().getWidth(), object.getTexture().getHeight());
    }

    public void updateAndRenderHealthBar() {
        int xoffset = 70;
        Texture LivesFont = assetManager.get("Lives.png", Texture.class);
        batch.draw(LivesFont, gameResources.getScreenTwoStart() - 20, gameResources.getWorldHeight() - 145, LivesFont.getWidth(), LivesFont.getHeight());
        for (int i = 0; i < ((Player) player).getMaxHealth(); i++) {
            if (((Player) player).getMaxHealth() != 0) {
                Texture image = assetManager.get("toiletPaper.png", Texture.class);
                batch.draw(image, xoffset * i + gameResources.getScreenTwoStart() + 150, 940, image.getWidth(), image.getHeight());
            }
        }
    }

    public void updateScore() {
        Texture PlayerScore = assetManager.get("Score.png", Texture.class);
        batch.draw(PlayerScore, gameResources.getScreenTwoStart() - 20, gameResources.getWorldHeight() - 250, PlayerScore.getWidth(), PlayerScore.getHeight());
        font.draw(batch, String.valueOf(scoreController.getScore()), gameResources.getScreenTwoStart() + PlayerScore.getWidth() - 20, gameResources.getWorldHeight() - 133);
    }

    public void drawStageMessage() {
        drawStageMessageTexture(stage1, stageController.stageOneStart - stageController.stageBuffer, stageController.stageBuffer);
        drawStageMessageTexture(stage2, stageController.stageTwoStart - stageController.stageBuffer, stageController.stageBuffer);
        drawStageMessageTexture(stage3, stageController.stageThreeStart, stageController.stageBuffer);
        drawStageMessageTexture(stage4, stageController.stageFourStart - stageController.stageBuffer, stageController.stageBuffer);
    }

    public void drawStageMessageTexture(Texture stage, long start, long duration) {
        if (timeController.getElapsedTime() >= start && timeController.getElapsedTime() != 0 && timeController.getElapsedTime() < start + duration) {
            batch.draw(stage, WORLD_WIDTH / 4 - stageMessageWidth / 2, WORLD_HEIGHT - stageMessageHeight - 50, stageMessageWidth, stageMessageHeight);
        }
    }
}