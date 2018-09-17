package menu;

import menu.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuOption {

    private final String option;
    private final String message;
    private Command command;

    public MenuOption(String option, String message) {
        this.option = option;
        this.message = message;
    }

    @Autowired
    public void setCommand(Command command) {
        this.command = command;
    }

    public void display() {
        System.out.println(option + " " + message);
    }

    public boolean canProcess(String value) {
        return value.equalsIgnoreCase(option);
    }

    public Command process() {
        return command.execute();
    }

}
