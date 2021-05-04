package GameEngine.Factory;

import GameEngine.Resource.GameResources;
import Objects.GameObject.PowerUp.PowerUp;
import com.badlogic.gdx.graphics.Texture;

public class PowerUpFactory {
    private final Texture texture = GameResources.getAssetsManager().get("PowerUp.png", Texture.class);

    public PowerUp create(float xPos, float yPos) {
        return new PowerUp(xPos - (texture.getWidth() / 2), yPos - texture.getHeight());
    }
}
