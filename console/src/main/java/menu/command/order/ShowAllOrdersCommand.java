package menu.command.order;

import com.tk.model.order.Item;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.ConsolePrinter;
import util.DataProvider;
import com.tk.model.order.Order;
import com.tk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShowAllOrdersCommand implements Command {

    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ConsolePrinter<Order, Item> consolePrinter;
    @Autowired
    private ShowMenuCommand showOrderMenuCommand;

    @Override
    public Command execute() {

        orderService.findAll()
                .forEach(order -> {
                    consolePrinter.out(order, (orderService.findItemsByOrderId(order.getId())));
                });

        return showOrderMenuCommand;
    }
}
