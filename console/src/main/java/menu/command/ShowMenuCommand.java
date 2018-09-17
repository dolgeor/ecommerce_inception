package menu.command;

import menu.Menu;
import util.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;


public class ShowMenuCommand implements Command {
    @Autowired
    private DataProvider dataProvider;

    private Menu menu;

    @Autowired
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Command execute() {
        menu.display();
        String value = getProcessableOptionKey();
        return menu.process(value);
    }

    private String getProcessableOptionKey() {
        String value;
        while (true) {
            value = (String) dataProvider.getExtractor("String").get("-> ");
            if (menu.canProcess(value))
                break;
            System.out.println("Illegal input, try again ...");
        }
        return value;
    }
}
