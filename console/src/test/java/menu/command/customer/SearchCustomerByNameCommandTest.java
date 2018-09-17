package menu.command.customer;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import menu.command.customer.SearchCustomerByNameCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.type.ValidStringExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static view.CustomerView.SEARCH_CUSTOMERS_BY_NAME;

@RunWith(MockitoJUnitRunner.class)
public class SearchCustomerByNameCommandTest {

    @Mock
    CustomerService customerService;

    @Mock
    ValidStringExtractor validStringExtractor;

    @Mock
    DataProvider dataProvider;

    @Mock
    ShowMenuCommand showMenuCommand;

    @InjectMocks
    SearchCustomerByNameCommand searchCustomerByNameCommand;


    @Test
    public void shouldExecuteSuccessfully() {
        final String name = "name";
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());

        when(validStringExtractor.get(SEARCH_CUSTOMERS_BY_NAME)).thenReturn(name);
        when(dataProvider.getExtractor("String")).thenReturn(validStringExtractor);
        when(customerService.findByName(name)).thenReturn(customers);

        Command command = searchCustomerByNameCommand.execute();

        assertThat(command, notNullValue());
        assertThat(command, equalTo(showMenuCommand));

        verify(dataProvider).getExtractor("String");
        verify(validStringExtractor).get(SEARCH_CUSTOMERS_BY_NAME);
        verify(customerService).findByName(name);

    }
}
