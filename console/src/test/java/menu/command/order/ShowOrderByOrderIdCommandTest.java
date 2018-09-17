package menu.command.order;

import com.tk.model.Customer;
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
import util.ConsolePrinter;
import util.DataProvider;
import util.extraction.Extractor;
import view.OrderView;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowOrderByOrderIdCommandTest {

    @Mock
    private OrderService orderService;

    @Mock
    private ShowMenuCommand showMenuCommand;

    @Mock
    private DataProvider dataProvider;

    @Mock
    private Extractor<String> stringExtractor;

    @Mock
    ConsolePrinter consolePrinter;

    @InjectMocks
    private ShowOrderByOrderIdCommand showOrderByOrderIdCommand;

    @Test
    public void shouldExecuteSuccessfully(){
        String inputOrderId = "123";
        Date date = new Date(12345);

        Order expectedOrder = new Order();

        doReturn(stringExtractor).when(dataProvider).getExtractor("string");
        doReturn(inputOrderId).when(stringExtractor).get(OrderView.searchOrderByOrderId);
        doReturn(expectedOrder).when(orderService).findByOrderId(inputOrderId);

        Command command = showOrderByOrderIdCommand.execute();

        assertThat(command, equalTo(showMenuCommand));

        Assert.assertNotNull(command);

        verify(dataProvider).getExtractor("string");
        verify(stringExtractor).get("Enter Order Id for searching: ");
        verify(orderService).findByOrderId(inputOrderId);
    }
}
