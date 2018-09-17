package menu.command.product;

import menu.command.Command;
import com.tk.model.Product;
import com.tk.service.ProductService;
import menu.command.ShowMenuCommand;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetProductsBySkuCommandTest {
    @Mock
    ProductService productDAO;

    @Mock
    ValidStringExtractor validStringExtractor;

    @Mock
    ShowMenuCommand commandMenu;

    @Mock
    DataProvider dataProvider;

    @InjectMocks
    GetProductsBySkuCommand productsBySkuCommand;

    @Test
    public void shouldExecuteSuccessfully() {

        final String sku = "";
        doReturn(validStringExtractor).when(dataProvider).getExtractor("String");
        doReturn(sku).when(validStringExtractor).get("Enter product sku: ");

        final List<Product> actual = productsBySkuCommand.retrieveProductsBySku(sku);

        Command command = productsBySkuCommand.execute();

        assertThat(actual, notNullValue());
        assertThat(command, notNullValue());

        verify(dataProvider).getExtractor("String");
        verify(validStringExtractor).get("Enter product sku: ");
    }

    @Test
    public void shouldRetrieveProductsBySku() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product());

        final String sku = "";
        doReturn(expected).when(productDAO).findBySku(sku);

        final List<Product> actual = productsBySkuCommand.retrieveProductsBySku(sku);

        assertThat(actual, equalTo(expected));
        assertFalse(actual.isEmpty());

        verify(productDAO).findBySku(sku);

    }

    @Test
    public void shouldReturnEmptyListForSkuNotFound() {
        List<Product> expected = new ArrayList<>();
        final String sku = "";
        doReturn(expected).when(productDAO).findBySku(sku);

        final List<Product> actual = productsBySkuCommand.retrieveProductsBySku(sku);

        assertThat(actual, equalTo(expected));

        assertThat(expected, notNullValue());
        assertTrue(actual.isEmpty());

        verify(productDAO).findBySku(sku);
    }
}
