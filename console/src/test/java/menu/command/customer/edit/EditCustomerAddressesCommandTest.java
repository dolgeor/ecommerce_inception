package menu.command.customer.edit;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;

import static view.CustomerView.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.type.ValidStringExtractor;
import util.extraction.userdefined.ValidIdExtractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditCustomerAddressesCommandTest {

    @Mock
    CustomerService customerService;

    @Mock
    ValidStringExtractor validStringExtractor;

    @Mock
    ValidIdExtractor validIdExtractor;

    @Mock
    DataProvider dataProvider;

    @Mock
    ShowMenuCommand showMenuCommand;

    @InjectMocks
    EditCustomerAddressesCommand editCustomerAddressesCommand;

    @Test
    public void shouldExecuteAndReturnShowCustomerEditMenuCommand() {
        final Long id = 17L;
        Customer customer = new Customer();

        when(validIdExtractor.getValue()).thenReturn(id);
        when(dataProvider.getExtractor("id")).thenReturn(validIdExtractor);

        when(customerService.findById(id)).thenReturn(customer);
        when(customerService.update(customer)).thenReturn(customer);

        Command command = editCustomerAddressesCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command, equalTo(showMenuCommand));

        verify(dataProvider).getExtractor("id");
        verify(validIdExtractor).getValue();
        verify(customerService).findById(id);
        verify(customerService).update(customer);
    }
}
