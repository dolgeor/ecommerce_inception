package menu.command.order;


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
import util.extraction.type.ValidStringExtractor;

import java.sql.Date;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowOrderByDateCommandTest {
    @Mock
    private DataProvider dataProvider;

    @Mock
    private ShowMenuCommand orderMenuCommand;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ShowOrderByDateCommand showOrderByDateCommand;

    @Mock
    private ValidStringExtractor stringExtractor;

    @Test
    public void shouldExecuteSuccessfully(){

        System.out.println(dataProvider);
        System.out.println(stringExtractor);

        when(dataProvider.getExtractor("date")).thenReturn(stringExtractor);
        when(stringExtractor.get(anyString())).thenReturn("2018-03-20");
        Date date = new Date(118,02,20);
        Command command = showOrderByDateCommand.execute();


        Assert.assertNotNull(command);

        verify(dataProvider).getExtractor("date");
        verify(stringExtractor).get(anyString());
        verify(orderService).findByDate(date);
    }
}
