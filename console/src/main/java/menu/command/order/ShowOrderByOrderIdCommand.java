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


public class ShowOrderByOrderIdCommand implements Command {

    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShowMenuCommand showOrderMenuCommand;
    @Autowired
    private ConsolePrinter<Order, Item> consolePrinter;

    @Override
    public Command execute() {

        Extractor<String> stringExtractor = dataProvider.getExtractor("string");
        String inputOrderId = stringExtractor.get(OrderView.searchOrderByOrderId);
        Order order = orderService.findByOrderId(inputOrderId);

        if (order != null) {
            consolePrinter
                    .out(order, orderService.findItemsByOrderId(order.getId()));
        } else {
            System.out.println("Item with OrderId:'" + inputOrderId + "' not found.");
        }

        return showOrderMenuCommand;
    }
}
