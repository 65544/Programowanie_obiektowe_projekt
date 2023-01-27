import java.util.List;

public interface DownloadInterface {
    public List<Product> downloadProductData(String fileName);
    public List<Customer> downloadCustomerData(String fileName);
}