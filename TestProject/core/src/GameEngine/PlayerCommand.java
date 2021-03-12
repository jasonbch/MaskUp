package GameEngine;

import Entity.Entity;

public class PlayerCommand implements Command {

    // pass in enemyammolist
    // switch to player type
    Entity player;

    public PlayerCommand(Entity player) { this.player = player;}

    public void execute()
    {
        // set player state
        player.playerCollidedWith();
    }
}
