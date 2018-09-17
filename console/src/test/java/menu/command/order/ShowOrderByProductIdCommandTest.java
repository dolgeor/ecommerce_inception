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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowOrderByProductIdCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private ShowMenuCommand showMenuCommand;

    @Mock
    private DataProvider dataProvider;

    @Mock
    private Extractor<Long> longExtractor;

    @Mock
    ConsolePrinter consolePrinter;

    @InjectMocks
    private ShowOrderByProductIdCommand showOrderByProductIdCommand;



    @Test
    public void shouldExecuteSuccessfully(){
        Long inputOrderId = 1L;
        List<Order> expectedOrders = new ArrayList<>();

        doReturn(longExtractor).when(dataProvider).getExtractor("id");
        doReturn(inputOrderId).when(longExtractor).get(OrderView.searchOrderByProductId);
        doReturn(expectedOrders).when(orderService).findOrdersByProductId(inputOrderId);

        Command command = showOrderByProductIdCommand.execute();

        assertThat(command, equalTo(showMenuCommand));

        Assert.assertNotNull(command);

        verify(dataProvider).getExtractor("id");
        verify(longExtractor).get(OrderView.searchOrderByProductId);
        verify(orderService).findOrdersByProductId(inputOrderId);
    }

}
