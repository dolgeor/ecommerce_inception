package menu.command.customer;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.Extractor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static view.CustomerView.NO_CUSTOMERS;
import static view.CustomerView.SEARCH_CUSTOMERS_BY_NAME;

public class SearchCustomerByNameCommand implements Command {

    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShowMenuCommand showCustomerMenuCommand;

    @Override
    public Command execute() {
        Extractor<String> stringExtractor = dataProvider.getExtractor("String");
        String name = stringExtractor.get(SEARCH_CUSTOMERS_BY_NAME);
        List<Customer> customers = customerService.findByName(name);
        if (customers.isEmpty()) {
            System.out.format(NO_CUSTOMERS, name);
        }
        customers.forEach(c -> System.out.println(c));
        return showCustomerMenuCommand;
    }
}
