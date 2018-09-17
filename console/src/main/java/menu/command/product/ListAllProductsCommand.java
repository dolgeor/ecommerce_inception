package menu.command.product;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import com.tk.model.Product;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ListAllProductsCommand implements Command {

    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShowMenuCommand showProductMenuCommand;


    @Override
    public Command execute() {
        showProducts();
        return showProductMenuCommand;
    }

    protected List<Product> retrieveAllProducts() {
        List<Product> products = productService.findAll();

        return products;
    }

    protected void showProducts() {
        retrieveAllProducts()
                .forEach(p -> System.out.println(p));
    }
}
