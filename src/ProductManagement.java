import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductManagement extends OnlineOrderQueue {
    static List<Product> products = new ArrayList<>();
    static HashMap<String, Product> productHashMap = new HashMap<>();
    static HashMap<String, Product> nameProductHashMap = new HashMap<>();
    private static int productID = 1;

    public static void addProduct(Product product){
        products.add(product);
        productHashMap.put("Product"+productID,product);
        productID++;
        nameProductHashMap.put(product.getName(), product);
    }

    public static void removeProduct(Product product)
    {

        products.remove(product);
    }

    public static List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        StringBuilder productsString = new StringBuilder();
        productsString.append(System.getProperty("line.separator"));
        for (Product product : products)
        {
            productsString.append(product).append(System.getProperty("line.separator"));
        }
        return "Products: " + productsString +
                "\n";
    }
}