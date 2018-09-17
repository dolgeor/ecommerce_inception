package menu.command.order;


import com.tk.model.order.Item;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@RunWith(MockitoJUnitRunner.class)
public class ShowAllOrdersCommandTest {
    @Mock
    private DataProvider dataProvider;

    @Mock
    private ShowMenuCommand showMenuCommand;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ShowAllOrdersCommand showAllOrdersCommand;

    @Test
    public void shouldExecuteSuccessfully(){
        List<Order> expected = new ArrayList<>();

        doReturn(expected).when(orderService).findAll();

        Command command = showAllOrdersCommand.execute();

        Assert.assertNotNull(command);
        assertThat(command, equalTo(showMenuCommand));
        verify(orderService, times(1)).findAll();
    }
}
