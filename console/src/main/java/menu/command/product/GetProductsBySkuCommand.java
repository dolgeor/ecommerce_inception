package menu.command.product;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.Extractor;
import util.extraction.type.ValidStringExtractor;
import com.tk.model.Product;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static view.ProductView.NO_PRODUCTS_WITH_SKU;
import static view.ProductView.PRODUCT_SKU;

public class GetProductsBySkuCommand implements Command {

    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShowMenuCommand showProductMenuCommand;


    @Override
    public Command execute() {

        final Extractor<String> validString = (ValidStringExtractor) dataProvider.getExtractor("String");
        String sku = validString.get(PRODUCT_SKU);
        showProducts(sku);
        return showProductMenuCommand;
    }

    protected List<Product> retrieveProductsBySku(String sku) {
        List<Product> products = productService.findBySku(sku);
        if (products.isEmpty()) {
            System.out.format(NO_PRODUCTS_WITH_SKU, sku);
        }
        return products;
    }

    protected void showProducts(String name) {
        retrieveProductsBySku(name)
                .forEach(p -> System.out.println(p));
    }
}