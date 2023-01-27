import java.io.IOException;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        OnlineOrderQueue orderQueue = new OnlineOrderQueue();

        DownloadData downloadData = new DownloadData();

        ProductManagement products = new ProductManagement();

        CustomerManagement customers = new CustomerManagement();


        System.out.println();
        // Tworzymy produkty
        Product Product1 = new Product("Jeans",10.95);
        Product Product2 = new Product("Shirt",10.00);
        Product Product3 = new Product("Shorts", 5.99);
        Product Product4 = new Product("Questionable product", 99.99);
        // Tworzymy klientów
        Customer Customer1 = new Customer("Jan Kowalski", "Bydgoszcz, Fiołkowa 33");
        Customer Customer2 = new Customer("Paweł Nowak", "Warszawa, Grójecka 23/33");
        Customer Customer3 = new Customer("Karol Witowski", "Łódź, Morska 77b");
        Customer Customer4 = new Customer("Łukasz Łukaszczyk", "Zakopane, Górska 44");
        customers.addCustomer(Customer1);
        customers.addCustomer(Customer2);
        customers.addCustomer(Customer3);
        customers.addCustomer(Customer4);
        System.out.println("____ Wypisanie klientów");
        System.out.println(customers);
        // Usuwamy klienta
        customers.removeCustomer(Customer4);
        // Ponownie wypisujemy klientów
        System.out.println("____ Wypisanie klientów");
        System.out.println(customers);

        // Dodajemy produkty do listy produktów
        products.addProduct(Product1);
        products.addProduct(Product2);
        products.addProduct(Product3);
        products.addProduct(Product4);
        // Wypisanie produktów
        System.out.println("____ Wypisanie produktów");
        System.out.println(products);
        // Usuwamy produkt
        products.removeProduct(Product4);
        // Ponownie wypisujemy produkty
        System.out.println("____ Wypisanie produktów");
        System.out.println(products);
        // Customer1 dodał do koszyka produkty
        Customer1.addProductToCart(Product1, 4);
        // Customer2 dodał do koszyka produkty
        Customer2.addProductToCart(Product2, 3);
        Customer2.addProductToCart(Product3,2);
        // Wypisujemy liste produktów klientów
        System.out.println("____ Listy produktów klientów");
        Customer1.showListOfProducts();
        Customer2.showListOfProducts();





        // Klienci złożyli zamówienie
        Order order1 = new Order(Customer1.getListOfProducts(), Customer1);
        Order order2 = new Order(Customer2.getListOfProducts(), Customer2);
        // Dodajemy zamówienia do kolejki zamówień
        orderQueue.addOrder(order1);
        orderQueue.addOrder(order2);
        // Wypisujemy kolejkę
        System.out.println(orderQueue);
        // Obsługujemy zamówienie
        System.out.println("Retrieved order : "+orderQueue.getOrder());
        // Ponownie wypisujemy kolejkę
        System.out.println(orderQueue);
        //
        // Klient próbuje złożyć zamówienie, ale jego koszyk jest pusty
        Order order3 = new Order(Customer3.getListOfProducts(),Customer3);
        // Tutaj walidacja danych nie pozwala na złożenie pustego zamówienia
        orderQueue.addOrder(order3);
        // Wyświetlamy kolejkę
        System.out.println(orderQueue);
        // Obsługujemy zamówienie i wyświetlamy kolejkę
        System.out.println("Retrieved order : "+orderQueue.getOrder());
        System.out.println(orderQueue);
        // Klient któremu nie udało się złożyć zamówienia dodał produkty do koszyka
        Customer3.addProductToCart(Product3, 7);
        orderQueue.addOrder(order3);
        // I wyświetlamy kolejkę
        System.out.println(orderQueue);
        // Możemy też zmienić dane produktów
        System.out.println(Product1);
        Product1.changeName("Blue Jeans");
        Product1.changePrice(9.99);
        // Sprawdzamy zedytowany produkt
        System.out.println(Product1);

        // Pobieramy dane użytkowników z pliku .csv
        List<Customer> downloadedCustomerData = downloadData.downloadCustomerData("customerData1.csv");
        System.out.println(downloadData.customerMap.get("customerData1.csv"));
        System.out.println(downloadedCustomerData.get(1));

        // Pobieramy dane produktów z pliku .csv
        List<Product> downloadedProductData = downloadData.downloadProductData("productData1.csv");
        System.out.println(downloadData.productMap.get("productData1.csv"));
        System.out.println(downloadedProductData.get(2));

    }
}