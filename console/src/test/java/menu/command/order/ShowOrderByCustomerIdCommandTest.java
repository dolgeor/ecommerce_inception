package menu.command.order;

import com.tk.model.order.Order;
import com.tk.service.OrderService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.Extractor;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowOrderByCustomerIdCommandTest {
    @Mock
    private DataProvider dataProvider;

    @Mock
    private ShowMenuCommand orderMenuCommand;

    @Mock
    private OrderService orderService;

    @Mock
    private Extractor<Long> longExtractor;

    @InjectMocks
    private ShowOrderByCustomerIdCommand showOrderByCustomerIdCommand;

    @Test
    public void shouldExecuteSuccessfully(){
        Long inputCustomerId = 10L;
        List<Order> ordersReturned = new ArrayList<>();

        doReturn(longExtractor).when(dataProvider).getExtractor("id");
        doReturn(inputCustomerId).when(longExtractor).get("Enter Customer Id for searching: ");
        doReturn(ordersReturned).when(orderService).findByCustomerId(inputCustomerId);

        Command command = showOrderByCustomerIdCommand.execute();

        assertThat(command, equalTo(orderMenuCommand));
        Assert.assertNotNull(command);

        verify(dataProvider).getExtractor("id");
        verify(longExtractor).get("Enter Customer Id for searching: ");
        verify(orderService).findByCustomerId(inputCustomerId);
    }
}
