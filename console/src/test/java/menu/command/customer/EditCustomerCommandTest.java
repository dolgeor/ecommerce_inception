package menu.command.customer;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;

import static view.CustomerView.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.userdefined.ValidIdExtractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditCustomerCommandTest {

    @Mock
    ValidIdExtractor validIdExtractor;

    @Mock
    DataProvider dataProvider;

    @Mock(name = "showCustomerMenuCommand")
    ShowMenuCommand showCustomerMenuCommand;

    @Mock
    CustomerService customerService;

    @Mock(name = "showEditCustomerMenuCommand")
    ShowMenuCommand showEditCustomerMenuCommand;

    EditCustomerCommand editCustomerCommand;

    @Before
    public void setUp() {
        editCustomerCommand = new EditCustomerCommand();
        editCustomerCommand.setCustomerService(customerService);
        editCustomerCommand.setShowCustomerEditMenuCommand(showEditCustomerMenuCommand);
        editCustomerCommand.setShowCustomerMenuCommand(showCustomerMenuCommand);
        editCustomerCommand.setDataProvider(dataProvider);
    }

    @Test
    public void shouldExecuteAndReturnShowCustomerEditMenuCommand() {
        final Long id = 17L;
        Customer customer = new Customer();

        when(validIdExtractor.get(EDIT_CUSTOMER)).thenReturn(id);
        when(dataProvider.getExtractor("id")).thenReturn(validIdExtractor);
        when(customerService.findById(id)).thenReturn(customer);

        Command command = editCustomerCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command, equalTo(showEditCustomerMenuCommand));

        verify(dataProvider).getExtractor("id");
        verify(validIdExtractor).get(EDIT_CUSTOMER);
        verify(customerService).findById(id);
    }

    @Test
    public void shouldExecuteAndReturnShowCustomerMenuCommand() {
        final Long id = 17L;
        Customer customer = new Customer();

        when(validIdExtractor.get(EDIT_CUSTOMER)).thenReturn(id);
        when(dataProvider.getExtractor("id")).thenReturn(validIdExtractor);
        when(customerService.findById(17L)).thenReturn(customer);

        Command command = editCustomerCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command, equalTo(showEditCustomerMenuCommand));

        verify(dataProvider).getExtractor("id");
        verify(validIdExtractor).get(EDIT_CUSTOMER);
        verify(customerService).findById(id);
    }
}
