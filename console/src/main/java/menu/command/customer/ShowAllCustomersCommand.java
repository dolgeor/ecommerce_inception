package menu.command.customer;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import com.tk.model.Customer;
import com.tk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static view.CustomerView.SHOW_ALL_CUSTOMERS;


public class ShowAllCustomersCommand implements Command {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShowMenuCommand showCustomerMenuCommand;

    @Override
    public Command execute() {
        List<Customer> customers = customerService.findAll();
        System.out.print(SHOW_ALL_CUSTOMERS);
        customers.forEach(c -> System.out.println(c));
        return showCustomerMenuCommand;
    }
}
