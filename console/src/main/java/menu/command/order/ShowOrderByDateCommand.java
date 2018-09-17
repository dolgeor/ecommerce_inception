package menu.command.order;

import com.tk.model.order.Item;
import com.tk.model.order.Order;
import com.tk.service.OrderService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.ConsolePrinter;
import util.DataProvider;
import util.extraction.Extractor;
import view.OrderView;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

public class ShowOrderByDateCommand implements Command {

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
        Extractor<String> dateExtractor = dataProvider.getExtractor("date");
        String date;
        date = dateExtractor.get(OrderView.orderDate);
        Date inputOrderDate = Date.valueOf(date);
        List<Order> orders = orderService.findByDate(inputOrderDate);

        if (orders == null || orders.isEmpty())
            System.out.println("Items ordered on: '" + inputOrderDate + "' not found.");
        else
            orders.forEach(p -> {
                consolePrinter.out(p, orderService.findItemsByOrderId(p.getId()));
            });
        return showOrderMenuCommand;
    }

}
