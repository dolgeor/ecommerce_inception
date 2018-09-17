package view;

public class CustomerView {

    public static final String MENU =
            "\n\n========================================================\n\n"
                    + "a. Show all the customers registered in system\n"
                    + "b. Search the customers by name\n"
                    + "c. Create a new customer\n"
                    + "d. Edit customer \n"
                    + "e. Remove customer\n"
                    + "f. Go back to the initial view\n"
                    + "\n\n========================================================\n\n";

    public static final String updateOption =
            "\n\n========================================================\n\n"
                    + "a. Update name\n"
                    + "b. Update addresses\n"
                    + "\n\n========================================================\n\n";

    public static final String PROMPT = "-> ";

    public static final String EXIT = "Exit from Manage Customers\n";

    public static final String ILLEGAL_INPUT = "Illegal input, try again ...\n";

    public static final String NO_CUSTOMERS = "There are no customers with name: %s%n";

    public static final String NO_CUSTOMER_WITH_ID = "There is no customer with id: %d%n";

    public static final String SHOW_ALL_CUSTOMERS = "Customers\n\n";

    public static final String SEARCH_CUSTOMERS_BY_NAME = "Enter customer name for searching: ";

    public static final String CREATE_NEW_CUSTOMER = "Creating new customer\n";

    public static final String CUSTOMER_NAME = "Enter customer name: ";

    public static final String CUSTOMER_AGE = "Enter customer age: ";

    public static final String CUSTOMER_BILLING_ADDRESS = "Enter customer Billing Address: ";

    public static final String CUSTOMER_SHIPPING_ADDRESS = "Enter customer Shipping Address: ";

    public static final String EDIT_CUSTOMER = "Enter customer id to edit: ";

    public static final String REMOVE_CUSTOMER = "Enter customer id for removing: ";


}
