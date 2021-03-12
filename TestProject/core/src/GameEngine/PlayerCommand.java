package GameEngine;

import Entity.Entity;

public class PlayerCommand implements Command {

    Entity player;

    public PlayerCommand(Entity player) { this.player = player;}

    public void execute()
    {
        player.playerCollidedWith(player);
    }
}
