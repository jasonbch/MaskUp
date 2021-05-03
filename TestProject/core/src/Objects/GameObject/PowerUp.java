package Objects.GameObject;

import GameEngine.Resource.GameResources;
import com.badlogic.gdx.graphics.Texture;

public class PowerUp extends GameObject{
    protected String name = "PowerUp";
    protected float speed =  5;
    protected String[] acceptableTargets = new String[]{"Player"};
    protected Texture texture = GameResources.getAssetsManager().get("StimulusPack.png", Texture.class);
    protected int damage = -1;
    protected float originalX;
    protected float originalY;

    public PowerUp(float xPosition, float yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.originalX = xPosition;
        this.originalY = yPosition;
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
