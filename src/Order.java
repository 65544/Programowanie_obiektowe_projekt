import java.text.DecimalFormat;
import java.util.List;

public class Order {
    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    private List<Product> listOfProducts;
    private Customer customer;



    public Order(List<Product> listOfProducts, Customer customer) {

            this.listOfProducts = listOfProducts;
            this.customer = customer;

    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setProduct(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return customer.getUsername()+ "'s" + " Order{" +
                "Name: " + customer.getName() + ", Address: " + customer.getAddress()
                + ", Total price of the order: " + customer.getTotalPrice()
                + "$ " + customer.getListOfProducts()+
                '}';
    }
}