package util;

public class Report {

    public static void insertReport(Object object) {
        printMessage(object, "inserted");
    }

    public static void updateReport(Object object) {
        printMessage(object, "updated");
    }

    public static void deleteReport(Object object) {
        printMessage(object, "deleted");
    }

    private static void printMessage(Object object, String reportType) {
        if (object == null) {
            System.out.format("Can't be %s%n", reportType);
        } else {
            System.out.format("%s is %s%n", object, reportType);
        }
    }
}
