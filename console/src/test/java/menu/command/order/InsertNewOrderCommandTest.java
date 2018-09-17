package menu.command.order;

import com.tk.model.Address;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import com.tk.model.Customer;
import com.tk.service.CustomerService;
import com.tk.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.type.ValidLongExtractor;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InsertNewOrderCommandTest {

    @Mock
    private DataProvider dataProvider;

    @Mock
    private ShowMenuCommand showMenuCommand;

    @Mock
    private OrderService orderService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private InsertNewOrderCommand insertNewOrderCommand = new InsertNewOrderCommand();

    @Mock
    private ValidLongExtractor longExtractor;

    @Test
    public void shouldExecuteSuccesfully() {
        Long inputAnyId = 1L;
        when(dataProvider.getExtractor("id")).thenReturn(longExtractor);
        String ask = "Enter customer id: ";
        List<Address> addresses = new ArrayList<>();
        Address e = new Address();
        addresses.add(e);
        addresses.add(e);

        when(longExtractor.get(ask)).thenReturn(inputAnyId);
        Customer customer = new Customer(122L, "name");
        customer.setId(1L);
        when(customerService.findById(inputAnyId)).thenReturn(customer);
        when(customerService.findAddressesByCustomerId(1L)).thenReturn(addresses);


        Command command = insertNewOrderCommand.execute();
        Assert.assertNotNull(command);

        verify(dataProvider).getExtractor("id");
        verify(longExtractor).get(ask);
        verify(customerService).findById(inputAnyId);
        verify(customerService).findAddressesByCustomerId(inputAnyId);


    }
}
