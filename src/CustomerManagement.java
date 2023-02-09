import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerManagement extends OnlineOrderQueue {

    static List<Customer> customers = new ArrayList<>();
    static HashMap<String, Customer> customerHashMap = new HashMap<>();
    private static int customerID = 1;


    public static void addCustomer(Customer customer){

        customers.add(customer);
        customerHashMap.put("Customer"+customerID, customer);
        customerID++;
    }

    public static Customer getCustomer(String customerName) {
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public void removeCustomer(Customer customer)
    {
        customers.remove(customer);
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

