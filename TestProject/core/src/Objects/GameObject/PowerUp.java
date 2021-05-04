package Objects.GameObject;

import GameEngine.Observer.GameSubject;
import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PowerUp extends GameObject implements GameSubject {
    protected String name = "PowerUp";
    protected int speed = 0;
    protected Texture texture = GameResources.getAssetsManager().get("PowerUp.png", Texture.class);
    protected int health = 1;
    protected boolean isDone = false;

    public PowerUp(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(xPosition, yPosition, getImageWidth(), getImageHeight() - 10);
    }

    public void setIsDone() {
        this.isDone = true;
        notifyGameObserver("deletePowerUp");
    }

    public int getHealth() {
        return this.health;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public Texture getTexture() {
        return this.texture;
    }
}
