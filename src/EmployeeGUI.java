import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeGUI {
    JFrame jframe = new JFrame();
    private JPanel mainPanel;
    private JList ordersList;
    private JList customersList;;
    private JButton retrieveOrderButton;
    private JList productsList;
    private JButton addProduct;
    private JButton deleteProduct;
    private JTextField nameOfProduct;
    private JTextField priceOfProduct;

    // Declare order queue here
    OnlineOrderQueue orderQueue = new OnlineOrderQueue();

    public EmployeeGUI() {
        jframe.setContentPane(mainPanel);
        jframe.setSize(500, 500);
        jframe.setTitle("Employee GUI");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

        Timer refreshTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersList.setListData(CustomerManagement.customerHashMap.entrySet().toArray());
            }
        });
        refreshTimer.start();



        // Populate customers list
        customersList.setListData(CustomerManagement.getCustomers().toArray());
        productsList.setListData(ProductManagement.getProducts().toArray());

        retrieveOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve order from queue
                Order order = orderQueue.getOrder();

                // Update orders list
                ordersList.setListData(orderQueue.getOrders().toArray());
            }
        });
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Product product = new Product(nameOfProduct.getText(), Double.parseDouble(priceOfProduct.getText()));
                ProductManagement.addProduct(product);
                productsList.setListData(ProductManagement.getProducts().toArray());
            }
        });
        deleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductManagement.removeProduct((Product) productsList.getSelectedValue());
                productsList.setListData(ProductManagement.getProducts().toArray());
            }
        });
    }
}