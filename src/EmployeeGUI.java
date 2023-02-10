import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmployeeGUI {
    JFileChooser fileChooser = new JFileChooser();
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
    private JPanel readFromFilePanel;
    private JButton readCustomersButton;
    private JButton readProductsButton;

    // Declare order queue here
    OnlineOrderQueue orderQueue = new OnlineOrderQueue();
    DownloadData downloadData = new DownloadData();
    class MyCellRenderer extends DefaultListCellRenderer {
        public static final String HTML_1 = "<html><body style='width: ";
        public static final String HTML_2 = "px'>";
        public static final String HTML_3 = "</html>";
        private int width;

        public MyCellRenderer(int width) {
            this.width = width;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString()
                    + HTML_3;
            return super.getListCellRendererComponent(list, text, index, isSelected,
                    cellHasFocus);
        }

    }
    public EmployeeGUI() {

        TitledBorder title;
        title = BorderFactory.createTitledBorder("File reading");
        readFromFilePanel.setBorder(title);
        MyCellRenderer myCellRenderer = new MyCellRenderer(800);
        ordersList.setCellRenderer(myCellRenderer);
        title = BorderFactory.createTitledBorder("Orders");
        ordersList.setBorder(title);
        title = BorderFactory.createTitledBorder("Customers");
        customersList.setBorder(title);
        title = BorderFactory.createTitledBorder("Products");
        productsList.setBorder(title);
        title = BorderFactory.createTitledBorder("Product name");
        nameOfProduct.setBorder(title);
        title = BorderFactory.createTitledBorder("Product price");
        priceOfProduct.setBorder(title);
        jframe.setContentPane(mainPanel);
        jframe.setSize(1500, 500);
        jframe.setTitle("Employee GUI");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);


        Timer refreshTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersList.setListData(CustomerManagement.customerHashMap.entrySet().toArray());
                ordersList.setListData(OnlineOrderQueue.orderQueue.toArray());
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
                order.getCustomer().clearTotalPrice();
                order.getListOfProducts().clear();

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
        readCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Customer> skippedCustomers = new ArrayList<>();
                int returnVal = fileChooser.showOpenDialog(mainPanel);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filename = file.getName();
                    List<Customer> customers = downloadData.downloadCustomerData(filename);
                    for (Customer customer:customers) {
                        if (CustomerManagement.usernameCustomerHashMap.containsKey(customer.getUsername())) {
                            skippedCustomers.add(customer);
                        } else {
                            CustomerManagement.addCustomer(customer);
                        }
                    }
                    if (!skippedCustomers.isEmpty())
                    {
                        JOptionPane.showMessageDialog(jframe, skippedCustomers.toArray(),
                                "Skipped Customers", JOptionPane.ERROR_MESSAGE);
                    }
                    customersList.setListData(CustomerManagement.getCustomers().toArray());
            }
        }});
        readProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> skippedProducts = new ArrayList<>();
                int returnVal = fileChooser.showOpenDialog(mainPanel);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filename = file.getName();
                    List<Product> products = downloadData.downloadProductData(filename);
                    for (Product product: products) {
                        if (ProductManagement.nameProductHashMap.containsKey(product.getName())) {
                            skippedProducts.add(product);
                        } else {
                            ProductManagement.addProduct(product);
                        }
                    }
                    if (!skippedProducts.isEmpty())
                    {
                        JOptionPane.showMessageDialog(jframe, skippedProducts.toArray(),
                                "Skipped Products", JOptionPane.ERROR_MESSAGE);
                    }
                    productsList.setListData(ProductManagement.getProducts().toArray());
                }
            }
        });
    }
}