package GameEngine.Collision;

import java.util.LinkedList;
import java.util.Queue;

public class CommandController {
    private Queue<Command> commands = new LinkedList<>();
    private Command command;

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void executeCommand() {
        while (commands.size() > 0) {
            command = commands.poll();
            command.execute();
        }
    }
}
