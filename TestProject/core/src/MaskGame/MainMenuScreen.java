package MaskGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MainMenuScreen implements Screen {

    private MaskGame game;
    private Batch batch;
    private Texture exitButtonActive;
    private Texture exitButtonInActive;
    private Texture playButtonActive;
    private Texture getPlayButtonInActive;

    public MainMenuScreen(MaskGame game, Batch batch){
        this.game = game;
        this.batch = batch;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
    public void dispose() {

    }
}
