package menu.command.customer;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import com.tk.model.Customer;
import com.tk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static util.Report.deleteReport;
import static view.CustomerView.REMOVE_CUSTOMER;

public class RemoveCustomerCommand implements Command {
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShowMenuCommand showCustomerMenuCommand;

    @Override
    public Command execute() {
        Long id = (Long) dataProvider.getExtractor("id").get(REMOVE_CUSTOMER);
        Customer customer = customerService.findById(id);
        try {
            customerService.delete(customer);
        } catch (Exception e) {
            customer = null;
        }
        deleteReport(customer);
        return showCustomerMenuCommand;
    }
}
