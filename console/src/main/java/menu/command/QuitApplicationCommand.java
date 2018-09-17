package menu.command;

import something.App;
import org.springframework.beans.factory.annotation.Autowired;

public class QuitApplicationCommand implements Command {

    private App app;

    @Autowired
    public void setApp(App app) {
        this.app = app;
    }

    @Override
    public Command execute() {
        System.out.println("Bye");
        app.setRunning(false);
        return null;
    }
}
