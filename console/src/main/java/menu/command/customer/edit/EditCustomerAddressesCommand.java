package menu.command.customer.edit;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import org.springframework.beans.factory.annotation.Autowired;
import util.DataProvider;

public class EditCustomerAddressesCommand implements Command {
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShowMenuCommand showCustomerMenuCommand;

    @Override
    public Command execute() {
        Long id = (Long) dataProvider.getExtractor("id").getValue();
        Customer customerToUpdate = customerService.findById(id);

        System.out.println("Ask developer to implement this feature.");
//TODO   implement
        Customer updatedCustomer= customerService.update(customerToUpdate);
//        updateReport( updatedCustomer);

        return showCustomerMenuCommand;
    }
}
