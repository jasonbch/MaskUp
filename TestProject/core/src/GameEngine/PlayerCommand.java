package GameEngine;

import Entity.Entity;

public class PlayerCommand implements Command{

    Entity player;
    //Get enemy list to check if player ammo intersects enemy box.

    public PlayerCommand(Entity player) { this.player = player;}

    public void execute()
    {
        //player.
        // need to call player function that checks if the player ammo is hit
        System.out.println("Calling player execute");
    }
}
