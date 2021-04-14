package GameEngine.Factory;

import Objects.GameObject.BulletSpawner;
import com.badlogic.gdx.graphics.Texture;

public class BulletSpawnerFactory {
    public BulletSpawner create(float xPos,
                                float yPos,
                                String movingPattern,
                                float speed,
                                String bullet,
                                Texture texture,
                                float timeBetweenShot,
                                String bulletFormation,
                                int maxTimeAlive,
                                int maxHealth) {
        return new BulletSpawner(xPos, yPos, movingPattern, speed, bullet, texture, timeBetweenShot, bulletFormation, maxTimeAlive, maxHealth);
    }
}
