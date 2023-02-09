import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerGUI{
    JFrame jframe = new JFrame();
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JButton createCustomerButton;
    private JList productsList;
    private JButton addToCartButton;
    private JTextField customerNameTextField;
    private JList customerProductsList;

    private JButton loginButton;
    private JTextField loginTextField;
    private JPasswordField passwordField1;
    private JTextField addressTextField;
    private JPasswordField passwordField2;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JLabel loggedAsLabel;

    // Declare HashMap here
    HashMap<String,List<Product>> customerProductsMap = new HashMap<>();
    HashMap<String, String> usernamePasswordMap = new HashMap<>();




    public CustomerGUI() {
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Registration");
        registerPanel.setBorder(title);
        title = BorderFactory.createTitledBorder("Login");
        loginPanel.setBorder(title);
        title = BorderFactory.createTitledBorder("Name and Surname");
        nameTextField.setBorder(title);
        title = BorderFactory.createTitledBorder("Address");
        addressTextField.setBorder(title);
        title = BorderFactory.createTitledBorder("Username");
        loginTextField.setBorder(title);
        usernameTextField.setBorder(title);
        title = BorderFactory.createTitledBorder("Password");
        passwordField1.setBorder(title);
        passwordField2.setBorder(title);
        title = BorderFactory.createTitledBorder("List of products");
        productsList.setBorder(title);
        Timer refreshTimer = new Timer(10000, new ActionListener() {
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
                if(usernamePasswordMap.containsKey(usernameTextField.getText()))
                {
                    JOptionPane.showMessageDialog(jframe, "Username already taken!",
                            "Choose other username", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Create customer with data from text fields
                    Customer customer = new Customer(nameTextField.getText(), addressTextField.getText(), usernameTextField.getText(), String.valueOf(passwordField2.getPassword()));
                    CustomerManagement.addCustomer(customer);
                    customerProductsMap.put(customer.getUsername(), new ArrayList<>());
                    usernamePasswordMap.put(usernameTextField.getText(), String.valueOf(passwordField2.getPassword()));
                }
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get product and customer from GUI
                Product product = (Product) productsList.getSelectedValue();

                Customer customer = CustomerManagement.getCustomer(customerNameTextField.getText());

                // Add product to customer's list of products
                assert customer != null;
                List<Product> products = customerProductsMap.get(customer.getUsername());
                if (products == null) {
                    products = new ArrayList<>();
                    customerProductsMap.put(customer.getUsername(), products);
                }
                products.add(product);

                // Update customer's product list
                customerProductsList.setListData(products.toArray());
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginTextField.getText();
                String password = String.valueOf(passwordField1.getPassword());
                if (usernamePasswordMap.containsKey(loginTextField.getText()))
                {
                    if((usernamePasswordMap.get(loginTextField.getText()).equals(password)))
                    {
                        loggedAsLabel.setText("Logged as: " + loginTextField.getText());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jframe, "Wrong password. Try again!",
                                "Wrong password", JOptionPane.ERROR_MESSAGE);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(jframe, "Username doesn't exist!",
                            "Wrong username", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }}