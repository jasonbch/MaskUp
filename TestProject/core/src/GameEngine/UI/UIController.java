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
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.sql.Time;
import java.util.List;
import java.util.ListIterator;

public class UIController {
    // Singleton attribute
    private static UIController uniqueInstance = null;
    private final BulletSpawningController bulletSpawningController = BulletSpawningController.instance();
    private final EnemySpawningController enemySpawningController = EnemySpawningController.instance();
    private final BulletSpawnerSpawningController bulletSpawnerSpawningController = BulletSpawnerSpawningController.instance();
    private final GameObject player = Player.instance();
    private final GameResources gameResources = GameResources.instance();
    private final TimeController timeController = TimeController.instance();
    private final GameController gameController = GameController.instance();
    private final AssetManager assetManager = GameResources.getAssetsManager();
    private final ScoreController scoreController = ScoreController.instance();

    // Background
    private final float maxScrollingSpeed = (float) (gameResources.getWorldHeight()) / 4;
    private final Texture[] backgrounds = {assetManager.get("BlueBackground.png", Texture.class), assetManager.get("Clouds1.png", Texture.class), assetManager.get("Clouds2.png", Texture.class), assetManager.get("Cloud4.png", Texture.class), assetManager.get("PlayerHudBackground.png", Texture.class)};
    private final float[] backgroundOffsets = {0, 0, 0, 0};
    private final Music backgroundMusic = assetManager.get("BackgroundMusic.mp3", Music.class);

    // Texture
    private final Texture stage1 = new Texture("stage1.png");
    private final Texture stage2 = new Texture("stage2.png");
    private final Texture stage3 = new Texture("stage3.png");
    private final Texture stage4 = new Texture("stage4.png");
    private final BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"));

    BitmapFont fontFlipped = new BitmapFont(Gdx.files.internal("arial.fnt"), true);
    private OrthographicCamera camera;
    private Batch batch;
    private boolean yAxisFlipAttack = false;
    private boolean xAxisFlipAttack = false;
    private long time;
    private long min;
    private String timePlaceHolder;

    // Stage Message
    private int stageMessageWidth = 421;
    private int stageMessageHeight = 132;
    private int WORLD_WIDTH = Gdx.graphics.getWidth();
    private int WORLD_HEIGHT = Gdx.graphics.getHeight();
    private StageController stageController = StageController.instance();


    //BitmapFont font = new BitmapFont();

    private UIController() {

    }

    public static UIController instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UIController();
        }

        return uniqueInstance;
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
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
        drawBulletSpawners(); // Only for testing
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public void flipScreenOnXAxis() {
        this.camera.setToOrtho(true, WORLD_WIDTH, WORLD_HEIGHT);
        batch.setProjectionMatrix(camera.combined);
        xAxisFlipAttack = true;
    }

    public void flipScreenOnYAxis() {

        this.yAxisFlipAttack = true;
    }

    public void revertYAxis() {

        this.yAxisFlipAttack = false;
    }

    public void revertXAxis() {
        this.camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        batch.setProjectionMatrix(camera.combined);
        xAxisFlipAttack = false;
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

        if (yAxisFlipAttack) {

            // need the center x value of screen
            int screenMiddleX = gameResources.getScreenOneWidth() / 2;

            int objectX = (int) object.getXPosition();

            // calculate diff. between objectX and ScreenX
            int difference = screenMiddleX - objectX;

            // we can just add the difference directly no matter what side the object
            // was on. the sign of difference handles whether we add or subtract
            int newObjectX = screenMiddleX + difference;

            batch.draw(object.getTexture(), newObjectX, object.getYPosition(), -object.getTexture().getWidth(), object.getTexture().getHeight());
        } else {
            batch.draw(object.getTexture(), object.getXPosition(), object.getYPosition(), object.getTexture().getWidth(), object.getTexture().getHeight());
        }
    }

    public void updateAndRenderHealthBar() {
        int xoffset = 70;
        Texture LivesFont = assetManager.get("Lives.png", Texture.class);
        Texture image = assetManager.get("toiletPaper.png", Texture.class);
        Sprite paperSprite = new Sprite(image);
        int toiletPaperY;

        //System.out.println(LivesFont.getHeight());

        // world height = 1024
        // world width = 576

        if (xAxisFlipAttack) {
            Sprite sprite = new Sprite(LivesFont);
            sprite.flip(false, true);
            paperSprite.flip(false, true);
            batch.draw(sprite, gameResources.getScreenTwoStart() - 20, 3, LivesFont.getWidth(), LivesFont.getHeight());
            toiletPaperY = 32;
        } else {
            batch.draw(LivesFont, gameResources.getScreenTwoStart() - 20, gameResources.getWorldHeight() - 145, LivesFont.getWidth(), LivesFont.getHeight());
            toiletPaperY = 940;
        }

        //Texture image = assetManager.get("toiletPaper.png", Texture.class);
        for (int i = 0; i < ((Player) player).getMaxHealth(); i++) {
            if (((Player) player).getMaxHealth() != 0) {
                //Texture image = assetManager.get("toiletPaper.png", Texture.class);
                batch.draw(paperSprite, xoffset * i + gameResources.getScreenTwoStart() + 150, toiletPaperY, image.getWidth(), image.getHeight());
            }
        }
    }

    public void updateTimeElapsed() {
        Texture TimeElapsed = assetManager.get("TimeElapsed.png", Texture.class);

        //First draw "Time Elapsed"
        if(xAxisFlipAttack){
            Sprite sprite = new Sprite(TimeElapsed);
            sprite.flip(false, true);
            batch.draw(sprite, gameResources.getScreenTwoStart() - 20, 210, TimeElapsed.getWidth(), TimeElapsed.getHeight());

        }
        else {
            batch.draw(TimeElapsed, gameResources.getScreenTwoStart() - 20, gameResources.getWorldHeight() - 350, TimeElapsed.getWidth(), TimeElapsed.getHeight());
        }

        // Then calculate string and draw the time
        time = timeController.getElapsedTime();
        if ((time % 60) < 10) {
            min = time / 60;
            time = (time % 60);
            timePlaceHolder = ":0";
        } else if ((time % 60) >= 10) {
            min = time / 60;
            timePlaceHolder = ":";
            time = (time % 60);
        }

        font.draw(batch, min + timePlaceHolder + time, gameResources.getScreenTwoStart() + TimeElapsed.getWidth() - 20, gameResources.getWorldHeight() - 235);

        if(xAxisFlipAttack){
            fontFlipped.draw(batch, min + timePlaceHolder + time, gameResources.getScreenTwoStart() + TimeElapsed.getWidth() - 20,235);
        }
        else {
            font.draw(batch, min + timePlaceHolder + time, gameResources.getScreenTwoStart() + TimeElapsed.getWidth() - 20, gameResources.getWorldHeight() - 235);
        }
    }

    public void updateScore() {
        Texture PlayerScore = assetManager.get("Score.png", Texture.class);

        if (xAxisFlipAttack) {

            Sprite sprite = new Sprite(PlayerScore);
            sprite.flip(false, true);
            batch.draw(sprite, gameResources.getScreenTwoStart() - 20, 108, PlayerScore.getWidth(), PlayerScore.getHeight());
            fontFlipped.draw(batch, String.valueOf(scoreController.getScore()), gameResources.getScreenTwoStart() + PlayerScore.getWidth() - 20, 132);

        } else {
            batch.draw(PlayerScore, gameResources.getScreenTwoStart() - 20, gameResources.getWorldHeight() - 250, PlayerScore.getWidth(), PlayerScore.getHeight());
            font.draw(batch, String.valueOf(scoreController.getScore()), gameResources.getScreenTwoStart() + PlayerScore.getWidth() - 20, gameResources.getWorldHeight() - 133);
        }
    }

    // TODO - Observer
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

    public void drawPowerUp() {

    }
}