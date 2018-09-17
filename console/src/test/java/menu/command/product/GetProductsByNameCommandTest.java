package menu.command.product;


import com.tk.model.Product;
import com.tk.service.ProductService;
import menu.command.Command;
import menu.command.ShowMenuCommand;
import menu.command.product.GetProductsByNameCommand;
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
public class GetProductsByNameCommandTest {

    @Mock
    ProductService productDAO;

    @Mock
    ValidStringExtractor validStringExtractor;

    @Mock
    ShowMenuCommand commandMenu;

    @Mock
    DataProvider dataProvider;

    @InjectMocks
    GetProductsByNameCommand productsByNameCommand;


    @Test
    public void shouldExecuteSuccessfully() {

        final String name = "";
        doReturn(validStringExtractor).when(dataProvider).getExtractor("String");
        doReturn(name).when(validStringExtractor).get("Enter product name for searching: ");

        final List<Product> actual = productsByNameCommand.retrieveProductsByName(name);

        Command command = productsByNameCommand.execute();

        assertThat(actual, notNullValue());
        assertThat(command, notNullValue());

        verify(dataProvider).getExtractor("String");
        verify(validStringExtractor).get("Enter product name for searching: ");
    }

    @Test
    public void shouldRetrieveProductsByName() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product());

        final String name  = "name";
        doReturn(expected).when(productDAO).findByName(name);

        final List<Product> actual = productsByNameCommand.retrieveProductsByName(name);

        assertThat(actual, equalTo(expected));
        assertFalse(actual.isEmpty());

        verify(productDAO).findByName(name);

    }

    @Test
    public void shouldReturnEmptyListForNameNotFound() {
        List<Product> expected = new ArrayList<>();
        final String name = "";
        doReturn(expected).when(productDAO).findByName(name);

        final List<Product> actual = productsByNameCommand.retrieveProductsByName(name);

        assertThat(actual, equalTo(expected));

        assertThat(expected, notNullValue());
        assertTrue(actual.isEmpty());

        verify(productDAO).findByName(name);
    }
}
