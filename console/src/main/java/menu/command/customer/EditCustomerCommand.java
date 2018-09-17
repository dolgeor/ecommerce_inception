package menu.command.customer;

import com.tk.model.Customer;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.Extractor;
import util.extraction.userdefined.ValidIdExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import com.tk.service.CustomerService;

import java.util.NoSuchElementException;

import static view.CustomerView.EDIT_CUSTOMER;
import static view.CustomerView.NO_CUSTOMER_WITH_ID;

//import model.Customer;


public class EditCustomerCommand implements Command {
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShowMenuCommand showCustomerMenuCommand;
    @Autowired
    private ShowMenuCommand showCustomerEditMenuCommand;

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setShowCustomerMenuCommand(ShowMenuCommand showCustomerMenuCommand) {
        this.showCustomerMenuCommand = showCustomerMenuCommand;
    }

    public void setShowCustomerEditMenuCommand(ShowMenuCommand showCustomerEditMenuCommand) {
        this.showCustomerEditMenuCommand = showCustomerEditMenuCommand;
    }

    @Override
    public Command execute() {
        final Extractor<Long> validId = (ValidIdExtractor) dataProvider.getExtractor("id");
        Long id = validId.get(EDIT_CUSTOMER);
        Customer customerToUpdate = null;
        try {
            customerToUpdate = customerService.findById(id);
        } catch (NoSuchElementException e) {
            System.out.format(NO_CUSTOMER_WITH_ID, id);
            return showCustomerMenuCommand;
        }
        System.out.println(customerToUpdate);
        return showCustomerEditMenuCommand;
    }

}
