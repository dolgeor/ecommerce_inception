package menu.command.product;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import org.springframework.dao.DataIntegrityViolationException;
import util.DataProvider;
import util.extraction.Extractor;
import util.extraction.type.ValidStringExtractor;
import util.extraction.userdefined.ValidPriceExtractor;
import com.tk.model.Product;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static util.Report.insertReport;
import static view.ProductView.*;

public class CreateProductCommand implements Command {
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShowMenuCommand showProductMenuCommand;


    @Override
    public Command execute() {

        System.out.println(CREATE_NEW_PRODUCT);

        final Extractor<String> validString = (ValidStringExtractor) dataProvider.getExtractor("String");
        final Extractor<BigDecimal> validPrice = (ValidPriceExtractor) dataProvider.getExtractor("Price");

        Product product = getProduct(validString, validPrice);
        Product insertedProduct = null;
        try {
            insertedProduct = insertProduct(product);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations()
                    .forEach(constraintViolation ->
                            System.out.println(constraintViolation.getMessage()));
        } catch (Exception e) {
        }
        insertReport(insertedProduct);

        return showProductMenuCommand;
    }

    protected Product insertProduct(Product product) {
        return productService.insert(product);
    }

    protected Product getProduct(Extractor<String> validString, Extractor<BigDecimal> validPrice) {
        String name = validString.get(PRODUCT_NAME);
        String sku = validString.get(PRODUCT_SKU);
        BigDecimal price = validPrice.get(PRODUCT_PRICE);

        return new Product(name, sku, price);
    }
}
