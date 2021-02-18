package Ammo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Ammo {
    // Position and dimension
    public float xPos;
    public float yPos;

    public Ammo(float xPos, float yPos ) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public abstract String getName();
    public abstract String[] getAcceptableTargets();

    // Physical characteristics
    public abstract float getSpeed();

    // Graphics
    public abstract Texture getImage();

    public Boolean isAcceptableTarget(String name) {
        for (String target: getAcceptableTargets()) {
            if(target.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnemyBullet() {
        boolean retval = true;
        String currentBullet = this.getName();

        if(currentBullet == "Mask" || currentBullet == "Syrenge" || currentBullet == "Bullet" ) {
            retval = false;
        }

        return retval;
    }


    public void draw(Batch batch) {
        int yOffset = 10;

        if(isEnemyBullet()) {
            yOffset = 0;
        }

        batch.draw(getImage(), xPos + 4, yPos + yOffset, 2, 2);
    }
}
