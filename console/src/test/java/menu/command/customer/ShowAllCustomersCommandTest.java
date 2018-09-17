package menu.command.customer;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import menu.command.customer.ShowAllCustomersCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllCustomersCommandTest {

    @Mock
    CustomerService customerService;

    @Mock
    ShowMenuCommand showMenuCommand;

    @InjectMocks
    ShowAllCustomersCommand showAllCustomersCommand;


    @Test
    public void shouldExecuteSuccessfully() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());

        when(customerService.findAll()).thenReturn(customers);

        Command command = showAllCustomersCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command, equalTo(showMenuCommand));

        verify(customerService).findAll();

    }
}
