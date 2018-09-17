package view;

public class OrderView {

    public static final String menu =
            "\n\n========================================================\n\n"
                    + "a. Show all the orders registered in system\n"
                    + "b. Search the order by \"Order Id\"\n"
                    + "c. Create a new order\n"
                    + "d. Search for orders by a certain product id\n"
                    + "e. Search for orders by a certain customer id\n"
                    + "f. Search for orders by which were placed on a certain date\n"
                    + "g. Go back to the initial view\n"
                    + "\n\n========================================================\n\n";

    public static final String prompt = "-> ";

    public static final String showOrderFormat = "%4d%30s%4d%4d%4d%10.2f%50s%n";

    public static final String showAllOrders = "Orders\n";

    public static final String searchOrderByOrderId = "Enter Order Id for searching: ";

    public static final String searchOrderByProductId = "Enter Product Id for searching: ";

    public static final String searchOrderByCustomerId = "Enter Customer Id for searching: ";

    public static final String searchOrderByDate = "Enter Date for searching: ";


    public static final String createNewOrder = "Creating new order\n";

    public static final String orderId = "Enter order id: ";

    public static final String orderIdStringIdentifier = "Enter Order Id: ";

    public static final String customerId = "Enter customer id: ";

    public static final String productId = "Enter product id: ";

    public static final String orderQuantity = "Enter number of products: ";


    /*
        Can be not used
    */

    public static final String orderPrice= "Enter price : ";

    public static final String orderDate = "Enter Date[yyyy-mm-dd]: ";


}
