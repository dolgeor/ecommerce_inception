package menu.command.order;

import com.tk.model.order.Item;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.ConsolePrinter;
import util.DataProvider;
import util.extraction.Extractor;
import view.OrderView;
import com.tk.model.order.Order;
import com.tk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ShowOrderByCustomerIdCommand implements Command {

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

        Extractor<Long> longExtractor = dataProvider.getExtractor("id");

        Long inputCustomerId = longExtractor.get(OrderView.searchOrderByCustomerId);

        List<Order> orders = orderService.findByCustomerId(inputCustomerId);

        if (orders != null) {
            orders.forEach(order -> {
                consolePrinter.out(order, orderService.findItemsByOrderId(order.getId()));
            });
        } else {
            System.out.println("Item with CustomerId:'" + inputCustomerId + "' not found.");
        }

        return showOrderMenuCommand;
    }
}
