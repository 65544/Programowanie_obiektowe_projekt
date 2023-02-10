import java.util.List;

public class Order {
    private List<Product> listOfProducts;
    private Customer customer;



    public Order(List<Product> listOfProducts, Customer customer) {

        this.listOfProducts = listOfProducts;
            this.customer = customer;
    }
    public List<Product> getListOfProducts() {
        return listOfProducts;
    }
    public Customer getCustomer() {
        return customer;
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