package Ammo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Ammo {
    public abstract String getName();
    public abstract String[] getAcceptableTargets();

    //physical characterstics
    public abstract float getSpeed();

    //position and dimension
    public float xPos;
    public float yPos;
    public float width = 2;
    public float height = 2;

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

    public boolean isEnemyBullet()
    {
        boolean retval = true;
        String currentBullet = this.getName();

        if(currentBullet == "Mask" || currentBullet == "Syrenge" ||
                currentBullet == "Bullet" )
        {
            retval = false;
        }
        return retval;
    }

    public Rectangle boundingBox()
    {
        return new Rectangle(xPos,yPos,width,height);
    }

    public void draw(Batch batch)
    {
        int yOffset = 10;
        if(isEnemyBullet())
        {
            yOffset = 0;
        }

        batch.draw(getImage(), xPos + 4, yPos + yOffset, width, height);
    }



}
