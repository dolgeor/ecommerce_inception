package something;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import org.springframework.beans.factory.annotation.Autowired;

public class App {

    @Autowired
    ShowMenuCommand showInitialMenuCommand;

    private boolean running;

    public void run() {
        executeCommands(showInitialMenuCommand);
    }

    private void executeCommands(Command initialCommand) {
        running = true;
        Command nextCommand = initialCommand;
        do {
            nextCommand = nextCommand.execute();
        } while (running);
    }

    public void setRunning(boolean value) {
        running = value;
    }
}
