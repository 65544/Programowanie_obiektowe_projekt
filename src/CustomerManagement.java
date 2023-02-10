import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerManagement extends OnlineOrderQueue {

    static List<Customer> customers = new ArrayList<>();
    static HashMap<String, Customer> customerHashMap = new HashMap<>();
    static HashMap<String, Customer> usernameCustomerHashMap = new HashMap<>();
    static HashMap<String, String> usernamePasswordHashMap = new HashMap<>();
    private static int customerID = 1;



    public static void addCustomer(Customer customer){

        customers.add(customer);
        customerHashMap.put("Customer"+customerID, customer);
        customerID++;
        usernameCustomerHashMap.put(customer.getUsername(), customer);
        usernamePasswordHashMap.put(customer.getUsername(), customer.getPassword());
    }

    public static List<Customer> getCustomers() {
        return customers;
    }
    @Override
    public String toString() {
        StringBuilder customersString = new StringBuilder();
        customersString.append(System.getProperty("line.separator"));
        for (Customer customer : customers)
        {
            customersString.append(customer).append(System.getProperty("line.separator"));
        }
        return "Customers: " + customersString +
                "\n";
    }
}

