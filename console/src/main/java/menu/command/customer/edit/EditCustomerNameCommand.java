package menu.command.customer.edit;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.type.ValidStringExtractor;
import com.tk.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import com.tk.service.CustomerService;

import static util.Report.updateReport;
import static view.CustomerView.CUSTOMER_NAME;

public class EditCustomerNameCommand implements Command {
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShowMenuCommand showCustomerMenuCommand;

    @Override
    public Command execute() {
        Long id = (Long) dataProvider.getExtractor("id").getValue();
        ValidStringExtractor validStringExtractor = (ValidStringExtractor) dataProvider.getExtractor("string");
        Customer customerToUpdate = customerService.findById(id);

        String name = validStringExtractor.get(CUSTOMER_NAME);

        customerToUpdate.setName(name);

        Customer updatedCustomer = customerService.update(customerToUpdate);
        updateReport(updatedCustomer);

        return showCustomerMenuCommand;
    }

}

