package Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class GameObject {
    protected float xPosition;  // Initial x position
    protected float yPosition;  // Initial y position

    /**
     * Return the name.
     */
    public abstract String getName();

    /**
     * Return the y position.
     *
     * @return y position
     */
    public float getYPosition() {
        return this.yPosition;
    }

    /**
     * Return the x position.
     *
     * @return x position
     */
    public float getXPosition() {
        return this.xPosition;
    }

    /**
     * Return the Texture image.
     */
    public abstract Texture getImage();

    /**
     * Return the ammo that the entity fires.
     *
     * @param  batch  The current batch.
     */
    public void draw(Batch batch) {
        Texture image = getImage();
        batch.draw(image, xPosition, yPosition, image.getWidth(), image.getHeight());
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

    /**
     * Return the image width.
     */
    public int getImageWidth() {
        return getImage().getWidth();
    }

    /**
     * Return the image height.
     */
    public int getImageHeight() {
        return getImage().getHeight();
    }
}
