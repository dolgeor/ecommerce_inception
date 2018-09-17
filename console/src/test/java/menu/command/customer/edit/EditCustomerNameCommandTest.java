package menu.command.customer.edit;


import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
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
import static view.CustomerView.CUSTOMER_NAME;


@RunWith(MockitoJUnitRunner.class)
public class EditCustomerNameCommandTest {

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
    EditCustomerNameCommand editCustomerNameCommand;

    @Test
    public void shouldExecuteAndReturnShowCustomerEditMenuCommand() {
        final Long id = 17L;
        final String name = "Test";
        Customer customer = new Customer();

        when(validIdExtractor.getValue()).thenReturn(id);
        when(dataProvider.getExtractor("id")).thenReturn(validIdExtractor);
        when(validStringExtractor.get(CUSTOMER_NAME)).thenReturn(name);
        when(dataProvider.getExtractor("string")).thenReturn(validStringExtractor);

        when(customerService.findById(id)).thenReturn(customer);
        when(customerService.update(customer)).thenReturn(customer);

        Command command = editCustomerNameCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command, equalTo(showMenuCommand));

        verify(dataProvider).getExtractor("id");
        verify(validIdExtractor).getValue();
        verify(dataProvider).getExtractor("string");
        verify(validStringExtractor).get(CUSTOMER_NAME);
        verify(customerService).findById(id);
        verify(customerService).update(customer);
    }
}
