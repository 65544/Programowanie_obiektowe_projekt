import java.util.LinkedList;
import java.util.Queue;

public class OnlineOrderQueue extends OrderQueue {
    static Queue<Order> orderQueue = new LinkedList<>();
    // Metoda pozwalająca dodać zamówienie do kolejki
    public static void addOrder(Order order) {
        try {
            if (order.getListOfProducts().isEmpty()) {
                throw new IllegalArgumentException ();
            }
            orderQueue.add(order);
        } catch (IllegalArgumentException e) {
            System.out.println("Koszyk jest pusty!");

        }
    }
    // Metoda pozwalająca obsłużyć zamówienie
    public Order getOrder() {

        return orderQueue.poll();

    }
@Override
public String toString() {
    StringBuilder queueString = new StringBuilder();
    queueString.append(System.getProperty("line.separator"));
    for (Order order : orderQueue)
    {
        queueString.append(order.getCustomer()).append(System.getProperty("line.separator"));
    }
    if(orderQueue.isEmpty()){return "Queue is empty!";}
    return "____ Queue:" +queueString +
            "\n";
}

    public Queue<Order> getOrders() {
        return orderQueue;
    }
}