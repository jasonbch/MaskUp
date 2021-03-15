package GameEngine;

import com.badlogic.gdx.Gdx;

public class GameResources {
    // Implement Singleton
    private static GameResources uniqueInstance = null;

    public static GameResources instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameResources();
        }
        return uniqueInstance;
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
