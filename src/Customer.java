import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    private String username;
    private String password;
    private String name;
    private String address;
    private static double totalPrice;
    private static List<Product> listOfProducts = new ArrayList<>();


        public static void addProductToCart(Product product, int quantity) {
            for (int i = 0; i < quantity; i++) {
                listOfProducts.add(product);
                totalPrice += product.getPrice();
            }
        }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Customer(String name, String address, String username, String password) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
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
        return "Name: " + name + ", Address: " + address + ", Total price of the order: " + decfor.format(totalPrice)
                + "\n \t " + getListOfProducts();
    }

}