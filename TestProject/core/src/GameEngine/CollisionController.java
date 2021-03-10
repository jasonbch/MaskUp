package GameEngine;

public class CollisionController {
    private Command command;

    public void setCommand(Command command) { this.command = command;}

    public void CollisionDetection() { command.execute();}
}
