package com.maskup.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameScreen implements Screen {
    final MaskUp game;

    public GameScreen(final MaskUp gam) {
        this.game = gam;
    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(255, 255, 255, 0);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}


