import java.util.ArrayList;
import java.util.List;

public class CustomerManagement extends OnlineOrderQueue {

    List<Customer> customers = new ArrayList<>();


    public void addCustomer(Customer customer){

        customers.add(customer);
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

