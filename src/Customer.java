import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    private String name;
    private String address;
    private double totalPrice;
    private List<Product> listOfProducts = new ArrayList<>();


        public void addProductToCart(Product product, int quantity) {
            for (int i = 0; i < quantity; i++) {
                listOfProducts.add(product);
                totalPrice += product.getPrice();
            }
        }


    public Customer(String name, String address) {
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }
    public void showListOfProducts()
    {
        System.out.println(this);
        for (Product product: getListOfProducts())
        {
            System.out.println("\u2022"+product);
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Total price of the order: " + decfor.format(totalPrice);
    }

}