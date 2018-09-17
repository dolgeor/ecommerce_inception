package menu.command.order;


import com.tk.model.Address;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.Extractor;
import view.OrderView;
import com.tk.model.Customer;
import com.tk.model.order.Order;
import com.tk.model.Product;
import com.tk.service.CustomerService;
import com.tk.service.OrderService;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class InsertNewOrderCommand implements Command {

    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShowMenuCommand showOrderMenuCommand;

    @Override
    public Command execute() {
        Extractor<Long> longExtractor = dataProvider.getExtractor("id");

        long inputCustomerId = longExtractor.get(OrderView.customerId);
        Customer inputCustomer = null;
        try {
            inputCustomer = customerService.findById(inputCustomerId);
        } catch (NoSuchElementException e) {
            System.out.println("There is no customer with id: " + inputCustomerId);
            return showOrderMenuCommand;
        }

        Order order = null;
        try {
            List<Address> addressesByCustomerId = customerService.findAddressesByCustomerId(inputCustomer.getId());
            Address address = addressesByCustomerId.get(0);
            order = orderService.insert(new Order(inputCustomer, address, address));
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations()
                    .forEach(constraintViolation ->
                            System.out.println(constraintViolation.getMessage()));
        }
        if (order != null)
            System.out.println(order + " was successfully added.");

        return showOrderMenuCommand;
    }
}
