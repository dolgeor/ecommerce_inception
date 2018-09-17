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
import util.extraction.userdefined.ValidPriceExtractor;
import util.extraction.type.ValidStringExtractor;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateProductCommandTest {

    @Mock
    ProductService productService;

    @Mock
    ValidStringExtractor validStringExtractor;

    @Mock
    ValidPriceExtractor validPriceExtractor;

    @Mock
    DataProvider dataProvider;

    @Mock
    ShowMenuCommand showMenuCommand;

    @InjectMocks
    CreateProductCommand createProductCommand;

    @Test
    public void shouldExecuteSuccessfully() {
        final Product product = new Product();

        doReturn(validStringExtractor).when(dataProvider).getExtractor("String");
        doReturn(validPriceExtractor).when(dataProvider).getExtractor("Price");
        doReturn(product).when(productService).insert(product);

        final Product actual = createProductCommand.insertProduct(product);

        Command command = createProductCommand.execute();

        assertThat(actual, equalTo(product));
        assertThat(command, notNullValue());

        verify(dataProvider).getExtractor("String");
        verify(dataProvider).getExtractor("Price");
        verify(productService, times(2)).insert(product);


    }

    @Test
    public void shouldGetProduct() {
        final String name = "";
        final String sku = "";
        final BigDecimal price = new BigDecimal(0);

        doReturn(name).when(validStringExtractor).get("Enter product name: ");
        doReturn(sku).when(validStringExtractor).get("Enter product sku: ");
        doReturn(price).when(validPriceExtractor).get("Enter product price: ");

        final Product product = createProductCommand.getProduct(validStringExtractor, validPriceExtractor);

        assertThat(product, notNullValue());
        assertThat(product.getName(), equalTo(name));
        assertThat(product.getSku(), equalTo(sku));
        assertThat(product.getPrice(), equalTo(price));

        verify(validStringExtractor).get("Enter product name: ");
        verify(validPriceExtractor).get("Enter product price: ");
    }
}
