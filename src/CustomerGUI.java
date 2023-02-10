import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CustomerGUI{
    JFrame jframe = new JFrame();
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JButton createCustomerButton;
    private JList productsList;
    private JButton addToCartButton;
    private JList customerProductsList;

    private JButton loginButton;
    private JTextField loginTextField;
    private JPasswordField passwordField1;
    private JTextField addressTextField;
    private JPasswordField passwordField2;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JLabel loggedAsLabel;
    private JLabel userLoggedInLabel;
    private JButton placeOrderButton;


    // Declare HashMap here
    HashMap<Customer,List<Product>> customerProductsMap = new HashMap<>();
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
        title = BorderFactory.createTitledBorder("Cart");
        customerProductsList.setBorder(title);
        Timer refreshTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productsList.setListData(ProductManagement.getProducts().toArray());
            }
        });
        refreshTimer.start();

        jframe.setContentPane(mainPanel);
        jframe.setSize(1500, 500);
        jframe.setTitle("Customer GUI");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

            // Populate product list
            productsList.setListData(ProductManagement.getProducts().toArray());

        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CustomerManagement.usernameCustomerHashMap.containsKey(usernameTextField.getText()))
                {
                    JOptionPane.showMessageDialog(jframe, "Username already taken!",
                            "Choose other username", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Customer customer = new Customer(nameTextField.getText(), addressTextField.getText(), usernameTextField.getText(), String.valueOf(passwordField2.getPassword()));
                    CustomerManagement.addCustomer(customer);
                    customerProductsMap.put(customer, new ArrayList<>());
                    usernamePasswordMap.put(usernameTextField.getText(), String.valueOf(passwordField2.getPassword()));
                    CustomerManagement.usernameCustomerHashMap.put(usernameTextField.getText(), customer);
                }
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = CustomerManagement.usernameCustomerHashMap.get(userLoggedInLabel.getText());

                Product product = (Product) productsList.getSelectedValue();

                if (productsList.isSelectionEmpty())
                {
                    JOptionPane.showMessageDialog(jframe, "Please choose a product!",
                            "Product not chosen", JOptionPane.ERROR_MESSAGE);
                }
                else if (userLoggedInLabel.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(jframe, "Please log in!",
                            "User not logged in", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    List<Product> products = customer.getListOfProducts();
                    if (products == null) {
                        products = new ArrayList<>();
                        customerProductsMap.put(customer, products);
                    }
                    customer.addProductToCart(product, 1);
                    customerProductsList.setListData(products.toArray());
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginTextField.getText();
                String password = String.valueOf(passwordField1.getPassword());
                if (CustomerManagement.usernameCustomerHashMap.containsKey(loginTextField.getText()))
                {
                    if((CustomerManagement.usernamePasswordHashMap.get(loginTextField.getText()).equals(password)))
                    {
                        userLoggedInLabel.setText(loginTextField.getText());
                        customerProductsList.setListData(CustomerManagement.usernameCustomerHashMap.get(userLoggedInLabel.getText()).getListOfProducts().toArray());
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
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = CustomerManagement.usernameCustomerHashMap.get(userLoggedInLabel.getText());
                List<Product> products = customer.getListOfProducts();

                Order order = new Order(products, customer);
                if(OnlineOrderQueue.orderQueue.stream().noneMatch(n -> n.getCustomer().equals(customer))) {
                    OnlineOrderQueue.addOrder(order);
                }
                customerProductsList.setModel(new DefaultListModel());
            }
        });
    }}