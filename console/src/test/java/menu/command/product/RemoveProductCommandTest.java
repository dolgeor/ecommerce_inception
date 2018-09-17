package menu.command.product;

import com.tk.model.Product;
import com.tk.service.ProductService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import menu.command.product.RemoveProductCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.DataProvider;
import util.extraction.userdefined.ValidIdExtractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RemoveProductCommandTest {

    @Mock
    ProductService productService;

    @Mock
    ShowMenuCommand commandMenu;

    @Mock
    DataProvider dataProvider;

    @Mock
    ValidIdExtractor validIdExtractor;

    @InjectMocks
    RemoveProductCommand removeProductCommand;

    @Test
    public void shouldRetrieveExistingProduct() {
        final Long id = 1L;
        final Product expected = new Product();

        doReturn(expected).when(productService).findById(id);
        doReturn(validIdExtractor).when(dataProvider).getExtractor("Id");

        final Product actual = removeProductCommand.tryRetrieveProduct(id);

        Command command = removeProductCommand.execute();

        assertThat(actual, notNullValue());
        assertThat(actual, equalTo(expected));
        assertThat(command, notNullValue());

        verify(productService).findById(id);
        verify(dataProvider).getExtractor("Id");
    }

    @Test
    public void shouldReturnNullOnRetrievingInexistingProductForDeletion() {
        final Long id = 1L;
        final Product expected = null;

        doReturn(expected).when(productService).findById(id);
        doReturn(validIdExtractor).when(dataProvider).getExtractor("Id");

        final Product actual = removeProductCommand.tryRetrieveProduct(id);

        Command command = removeProductCommand.execute();

        assertThat(actual, nullValue());
        assertThat(actual, equalTo(expected));
        assertThat(command, notNullValue());

        verify(productService).findById(id);
    }

    @Test
    public void shoudDeleteExistingProduct() {
        final Product product = new Product();
        doNothing().when(productService).delete(product);
        removeProductCommand.deleteProduct(product);
        verify(productService).delete(product);
    }

}
