package Ammo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Ammo {
    public abstract String getName();
    public abstract String[] getAcceptableTargets();

    //physical characterstics
    public abstract float getSpeed();

    //position and dimension
    public float xPos;
    public float yPos;

    public Ammo(float xPos, float yPos ) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    //graphics
    public abstract Texture getImage();

    public Boolean isAcceptableTarget(String name)
    {
        for (String target: getAcceptableTargets())
        {
            if(target.equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public void draw(Batch batch)
    {
        batch.draw(getImage(), xPos+3, yPos+10, 2, 2);
    }


}
