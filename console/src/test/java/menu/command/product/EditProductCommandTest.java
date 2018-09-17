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
import util.DataProvider;
import util.extraction.userdefined.ValidIdExtractor;
import util.extraction.userdefined.ValidPriceExtractor;
import util.extraction.type.ValidStringExtractor;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EditProductCommandTest {

    @Mock
    ProductService productService;

    @Mock
    DataProvider dataProvider;

    @Mock
    ValidIdExtractor validIdExtractor;

    @Mock
    ShowMenuCommand productMenuCommand;

    @Mock
    ValidPriceExtractor validPriceExtractor;

    @Mock
    ValidStringExtractor validStringExtractor;

    @InjectMocks
    EditProductCommand editProductCommand;

    @Test
    public void shouldReturnTrueForExistingProductFindById() {
        Product product = new Product();
        final Long id = 42L;

        final boolean expected = true;

        when(productService.findById(id)).thenReturn(product);

        final boolean actual = editProductCommand.productExists(id);

        assertThat(actual, equalTo(expected));
        verify(productService).findById(id);
    }

    @Test
    public void shouldReturnFalseForInexistingProductFindById() {
        Product product = null;
        final Long id = 42L;

        final boolean expected = true;

        when(productService.findById(id)).thenReturn(product);

        final boolean actual = editProductCommand.productExists(id);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldExecuteInsertSuccessfully() {

        Product product = new Product();
        Long id = 2L;

        when(dataProvider.getExtractor("Id")).thenReturn(validIdExtractor);
        when(dataProvider.getExtractor("String")).thenReturn(validStringExtractor);
        when(dataProvider.getExtractor("Price")).thenReturn(validPriceExtractor);

        when(validIdExtractor.get("")).thenReturn(id);


        doReturn(product).when(productService).findById(id);
        doReturn(product).when(productService).update(product);

        assertThat(productService.update(product), equalTo(product));

        Command command = editProductCommand.execute();
        assertThat(command, notNullValue());

        verify(dataProvider).getExtractor("Id");
        verify(dataProvider).getExtractor("String");
        verify(dataProvider).getExtractor("Price");
        verify(validIdExtractor).get("");
        verify(productService).findById(id);
        verify(productService,times(2)).update(product);
    }

    @Test
    public void shouldPrepareProductSuccessfully() {
        final String name = "";
        final String sku = "";
        final BigDecimal price = new BigDecimal(0);
        final Long id = 1L;

        doReturn(name).when(validStringExtractor).get("Enter product name: ");
        doReturn(sku).when(validStringExtractor).get("Enter product sku: ");
        doReturn(price).when(validPriceExtractor).get("Enter product price: ");

        final Product actual = editProductCommand.pepareProduct(id, validStringExtractor, validPriceExtractor);

        assertThat(actual, notNullValue());

        assertThat(actual.getName(), equalTo(name));
        assertThat(actual.getPrice(), equalTo(price));
        assertThat(actual.getSku(), equalTo(sku));

        verify(validStringExtractor).get("Enter product name: ");
        verify(validStringExtractor).get("Enter product sku: ");
        verify(validPriceExtractor).get("Enter product price: ");
    }
}
