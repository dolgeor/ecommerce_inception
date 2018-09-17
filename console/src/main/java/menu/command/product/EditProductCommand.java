package menu.command.product;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.Extractor;
import util.extraction.type.ValidStringExtractor;
import util.extraction.userdefined.ValidIdExtractor;
import util.extraction.userdefined.ValidPriceExtractor;
import com.tk.model.Product;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static util.Report.updateReport;
import static view.ProductView.*;


public class EditProductCommand implements Command {

    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShowMenuCommand showProductMenuCommand;

    @Override
    public Command execute() {

        System.out.println(EDIT_PRODUCT);

        final Extractor<Long> validId = (ValidIdExtractor) dataProvider.getExtractor("Id");
        final Extractor<String> validString = (ValidStringExtractor) dataProvider.getExtractor("String");
        final Extractor<BigDecimal> validPrice = (ValidPriceExtractor) dataProvider.getExtractor("Price");

        Long id = validId.get("");

        if (!productExists(id)) {
            return showProductMenuCommand;
        }

        Product product = pepareProduct(id, validString, validPrice);

        Product productUpdated = null;
        try {
            productUpdated = productService.update(product);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations()
                    .forEach(constraintViolation ->
                            System.out.println(constraintViolation.getMessage()));
        } catch (Exception e) {
        }
        updateReport(productUpdated);

        return showProductMenuCommand;
    }

    protected boolean productExists(Long id) {
        Product productToUpdate = null;
        try {
            productToUpdate = productService.findById(id);
        } catch (NoSuchElementException e) {
            System.out.format(NO_PRODUCT_WITH_ID, id);
            return false;
        }
        return true;
    }

    protected Product pepareProduct(Long id, Extractor<String> validString, Extractor<BigDecimal> validPrice) {
        String name = validString.get(PRODUCT_NAME);
        String sku = validString.get(PRODUCT_SKU);
        BigDecimal price = validPrice.get(PRODUCT_PRICE);

        return new Product(name, sku, price);

    }
}
