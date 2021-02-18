package Enemy;

import Ammo.*;

public class EnemyFactory {

    public Enemy create(String enemy, float xPos, float yPos)
    {
        switch(enemy)
        {
            case "MuderHornet":
                return new MurderHornet(xPos, yPos);
            case "Karen":
                return new Karen(xPos, yPos);
            case "Bat":
                return new Bat(xPos, yPos);
            case "Covid":
                return new Covid(xPos, yPos);
        }
        return null;
    }
}
