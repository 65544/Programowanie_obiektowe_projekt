import java.util.Scanner;



public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        OnlineOrderQueue orderQueue = new OnlineOrderQueue();

        DownloadData downloadData = new DownloadData();

        ProductManagement products = new ProductManagement();

        CustomerManagement customers = new CustomerManagement();

        CustomerGUI customerGUI = new CustomerGUI();

        EmployeeGUI employeeGUI = new EmployeeGUI();

    }
}