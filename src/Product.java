public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void changeName(String newName)
    {
        this.name = newName;
    }
    public void changePrice(double newPrice)
    {
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Price: " + price + "$";
    }
}