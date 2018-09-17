package menu.command.customer;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.Extractor;
import util.extraction.type.ValidStringExtractor;
import util.extraction.userdefined.ValidAgeExtractor;
import com.tk.model.Customer;
import com.tk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static util.Report.insertReport;
import static view.CustomerView.*;

public class CreateCustomerCommand implements Command {
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShowMenuCommand showCustomerMenuCommand;

    @Override
    public Command execute() {
        System.out.println(CREATE_NEW_CUSTOMER);

        final Extractor<String> validString = (ValidStringExtractor) dataProvider.getExtractor("String");
        final Extractor<Long> validAge = (ValidAgeExtractor) dataProvider.getExtractor("age");

        Customer customerToCreate = createCustomer(validString, validAge);
        Customer createdCustomer = null;
        try {
            createdCustomer = customerService.insert(customerToCreate);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations()
                    .forEach(constraintViolation ->
                            System.out.println(constraintViolation.getMessage()));
        } catch (Exception e) {
        }
        insertReport(createdCustomer);
        return showCustomerMenuCommand;
    }


    protected Customer createCustomer(Extractor<String> validString, Extractor<Long> validAge) {
        String name = validString.get(CUSTOMER_NAME);
        Long age = validAge.get(CUSTOMER_AGE);
        return new Customer(age, name);
    }
}
