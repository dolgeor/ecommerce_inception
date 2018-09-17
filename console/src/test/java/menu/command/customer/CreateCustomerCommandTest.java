package menu.command.customer;

import com.tk.service.CustomerService;
import com.tk.model.Customer;
import menu.command.Command;
import menu.command.ShowMenuCommand;

import static view.CustomerView.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.userdefined.ValidAgeExtractor;
import util.extraction.type.ValidStringExtractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateCustomerCommandTest {

    @Mock
    CustomerService customerService;

    @Mock
    ValidStringExtractor validStringExtractor;

    @Mock
    ValidAgeExtractor validAgeExtractor;

    @Mock
    DataProvider dataProvider;

    @Mock
    ShowMenuCommand showMenuCommand;

    @InjectMocks
    CreateCustomerCommand createCustomerCommand;

    Customer customer;

    @Before
    public void setUp() {
        final String name = "name";
        final String ba = "ba";
        final String sa = "sa";
        final Long age = 134L;
        customer = new Customer( age,name);
        doReturn(name).when(validStringExtractor).get(CUSTOMER_NAME);
        doReturn(age).when(validAgeExtractor).get(CUSTOMER_AGE);
    }

    @Test
    public void shouldExecuteSuccessfully() {

        doReturn(validStringExtractor).when(dataProvider).getExtractor("String");
        doReturn(validAgeExtractor).when(dataProvider).getExtractor("age");
        doReturn(customer).when(customerService).insert(customer);

        Command command = createCustomerCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command,equalTo(showMenuCommand));

        verify(dataProvider).getExtractor("String");
        verify(dataProvider).getExtractor("age");

    }

    @Test
    public void shouldGetCustomer() {

        final Customer actualCustomer = createCustomerCommand.createCustomer(validStringExtractor, validAgeExtractor);

        assertThat(actualCustomer, notNullValue());
        assertThat(actualCustomer.getName(), equalTo(customer.getName()));
        assertThat(actualCustomer.getAge(), equalTo(customer.getAge()));

        verify(validStringExtractor).get(CUSTOMER_NAME);
        verify(validAgeExtractor).get(CUSTOMER_AGE);
    }
}
