package menu.command.customer;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import menu.command.customer.RemoveCustomerCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.userdefined.ValidIdExtractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static view.CustomerView.REMOVE_CUSTOMER;

@RunWith(MockitoJUnitRunner.class)
public class RemoveCustomerCommandTest {

    @Mock
    CustomerService customerService;

    @Mock
    ValidIdExtractor validIdExtractor;

    @Mock
    DataProvider dataProvider;

    @Mock
    ShowMenuCommand showMenuCommand;

    @InjectMocks
    RemoveCustomerCommand removeCustomerCommand;

    @Test
    public void shouldExecuteSuccessfully() {
        final Long id = 17L;
        Customer customer = new Customer(22L, "name");

        when(validIdExtractor.get(REMOVE_CUSTOMER)).thenReturn(id);
        when(dataProvider.getExtractor("id")).thenReturn(validIdExtractor);
        when(customerService.findById(id)).thenReturn(customer);
        doNothing().when(customerService).delete(customer);

        Command command = removeCustomerCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command, equalTo(showMenuCommand));

        verify(dataProvider).getExtractor("id");
        verify(validIdExtractor).get(REMOVE_CUSTOMER);
        verify(customerService).findById(id);
        verify(customerService, times(1)).delete(customer);

    }
}
