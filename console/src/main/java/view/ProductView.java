package view;

public class ProductView {

    public static final String menu =
            "\n\n========================================================\n\n"
                    + "a. Show all the products registered in system\n"
                    + "b. Search the product by name\n"
                    + "c. Create a new product\n"
                    + "d. Edit product \n"
                    + "e. Remove product\n"
                    + "f. Go back to the initial view\n"
                    + "\n\n========================================================\n\n";


    public static final String UPDATE_OPTION =
            "\n\n========================================================\n\n"
                    + "a. Update name\n"
                    + "b. Update sku\n"
                    + "c. Update price\n"
                    + "\n\n========================================================\n\n";

    public static final String PROMPT = "-> ";

    public static final String EXIT = "Exit from Manage products\n";

    public static final String ILLEGAL_INPUT = "Illegal input, try again ...\n";

    public static final String NO_PRODUCTS_WITH_NAME = "There are no products with name: %s%n";

    public static final String NO_PRODUCTS_WITH_SKU = "There are no products with sku: %s%n";

    public static final String NO_PRODUCT_WITH_ID = "There is no product with id: %d%n";

    public static final String SHOW_ALL_PRODUCTS = "products\n\n";

    public static final String SEARCH_PRODUCTS_BY_NAME = "Enter product name for searching: ";

    public static final String CREATE_NEW_PRODUCT = "Creating new product\n";

    public static final String PRODUCT_NAME = "Enter product name: ";

    public static final String PRODUCT_PRICE = "Enter product price: ";

    public static final String PRODUCT_SKU = "Enter product sku: ";

    public static final String EDIT_PRODUCT = "Enter product id to edit: ";

    public static final String REMOVE_PRODUCT = "Enter product id for removing: ";
}
