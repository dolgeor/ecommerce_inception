package menu.command.product;

import menu.command.Command;
import menu.command.ShowMenuCommand;
import util.DataProvider;
import util.extraction.Extractor;
import util.extraction.userdefined.ValidIdExtractor;
import com.tk.model.Product;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import static util.Report.deleteReport;
import static view.ProductView.NO_PRODUCT_WITH_ID;
import static view.ProductView.REMOVE_PRODUCT;

public class RemoveProductCommand implements Command {
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShowMenuCommand showProductMenuCommand;


    @Override
    public Command execute() {
        final Extractor<Long> validId = (ValidIdExtractor) dataProvider.getExtractor("Id");
        final Long id = validId.get(REMOVE_PRODUCT);
        Product product = tryRetrieveProduct(id);
        try {
            deleteProduct(product);
        } catch (Exception e) {
            product = null;
        }
        deleteReport(product);
        return showProductMenuCommand;
    }

    public Product tryRetrieveProduct(Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            System.out.format(NO_PRODUCT_WITH_ID, id);
        }
        return product;
    }

    protected void deleteProduct(Product product) {
        productService.delete(product);
    }
}
