import java.util.ArrayList;
import java.util.List;

public class ProductManagement extends OnlineOrderQueue {
    List<Product> products = new ArrayList<>();


    public void addProduct(Product product){

        products.add(product);
    }

    public void removeProduct(Product product)
    {
        products.remove(product);
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