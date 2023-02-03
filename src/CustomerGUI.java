import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerGUI{
    JFrame jframe = new JFrame();
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JButton createCustomerButton;
    private JList productsList;
    private JButton addToCartButton;
    private JTextField customerNameTextField;
    private JList customerProductsList;
    private JButton refreshButton;

    // Declare HashMap here
    HashMap<Customer,List<Product>> customerProductsMap = new HashMap<>();




    public CustomerGUI() {
        Timer refreshTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productsList.setListData(ProductManagement.getProducts().toArray());
            }
        });
        refreshTimer.start();

        jframe.setContentPane(mainPanel);
        jframe.setSize(500, 500);
        jframe.setTitle("Customer GUI");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

            // Populate product list
            productsList.setListData(ProductManagement.getProducts().toArray());

        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create customer with data from text fields
                Customer customer = new Customer(nameTextField.getText(), addressTextField.getText());
                CustomerManagement.addCustomer(customer);
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get product and customer from GUI
                Product product = (Product) productsList.getSelectedValue();
                Customer customer = CustomerManagement.getCustomer(customerNameTextField.getText());

                // Add product to customer's list of products
                List<Product> products = customerProductsMap.get(customer);
                if (products == null) {
                    products = new ArrayList<>();
                    customerProductsMap.put(customer, products);
                }
                products.add(product);

                // Update customer's product list
                customerProductsList.setListData(products.toArray());
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productsList.setListData(ProductManagement.getProducts().toArray());
            }
        });
    }}