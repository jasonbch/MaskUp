package GameEngine;

import java.util.LinkedList;
import java.util.Queue;

public class CommandController {
    Queue<Command> command = new LinkedList<>();
    private Command cmd;

    public void addCommand(Command command) { this.command.add(command); }

    public void executeCommand()
    {
        while (command.size() > 0) {
               cmd =  command.poll();
               cmd.execute();
            }
        }
    }
