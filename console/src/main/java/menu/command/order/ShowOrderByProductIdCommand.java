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


public class ShowOrderByProductIdCommand implements Command {

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

        Extractor<Long> longExtractor = dataProvider.getExtractor("id");

        Long inputProdutId = longExtractor.get(OrderView.searchOrderByProductId);

        List<Order> orders = orderService.findOrdersByProductId(inputProdutId);

        if (orders != null) {
            orders.forEach(order -> {
                consolePrinter
                        .out(order, orderService.findItemsByOrderId(order.getId()));
            });
        } else {
            System.out.println("Item with CustomerId:'" + inputProdutId + "' not found.");
        }
        return showOrderMenuCommand;
    }
}
