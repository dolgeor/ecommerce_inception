package menu.command.product;

import com.tk.model.Product;
import com.tk.service.ProductService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.doReturn;


@RunWith(MockitoJUnitRunner.class)
public class ListAllProductsCommandTest {

    @Mock
    ProductService productService;

    @Mock
    ShowMenuCommand commandMenu;

    @InjectMocks
    ListAllProductsCommand allProductsCommand;

    @Test
    public void shouldExecuteSuccessfully() {
        final List<Product> expected = new ArrayList<>();

        doReturn(expected).when(productService).findAll();


        final List<Product> actual = allProductsCommand.retrieveAllProducts();

        Command command = allProductsCommand.execute();

        assertThat(actual, notNullValue());
        assertThat(actual, equalTo(expected));
        assertThat(command, notNullValue());
    }
}
