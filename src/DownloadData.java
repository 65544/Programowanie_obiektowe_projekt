import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadData implements DownloadInterface {
    Map<String, List<Product>> productMap = new HashMap<>();
    Map<String, List<Customer>> customerMap = new HashMap<>();

    @Override
    public List<Product> downloadProductData(String fileName) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Product product = new Product(values[0], Double.parseDouble(values[1]));
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        productMap.put(fileName, products);
        return products;
    }

    @Override
    public List<Customer> downloadCustomerData(String fileName) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Customer customer = new Customer(values[0], values[1]);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerMap.put(fileName, customers);
        return customers;
    }
}