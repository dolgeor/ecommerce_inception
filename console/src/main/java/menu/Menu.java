package menu;

import menu.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<MenuOption> options;

    public void addOption(MenuOption option) {
        if(options == null)
            options  = new ArrayList<>();
        options.add(option);
    }

    public void setOptions(List<MenuOption> options) {
        this.options = options;
    }

    public void display() {
        System.out.print( "\n\n========================================================\n\n");
        for (MenuOption option : options) {
            option.display();
        }
        System.out.print( "\n========================================================\n\n");
    }

    public Command process(String value) {
        for (MenuOption option : options) {
            if (option.canProcess(value)) {
                return option.process();
            }
        }
        return null;
    }

    public boolean canProcess(String value) {
        for (MenuOption option : options) {
            if (option.canProcess(value)) {
                return true;
            }
        }
        return false;
    }
}
