package Implementation.GUI;

import Implementation.Clothing;
import Implementation.Electronics;
import Implementation.Product;
import Implementation.WestminsterShoppingManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Gui implements ActionListener {

    private JFrame westminsterFrame;
    private JComboBox<String> comboBox;
    private DefaultTableModel productTableModel;
    private JTable productTable;
    private JTextArea bottomDetails; // Make bottomDetails a class-level variable
    private JPanel bottomPanel;

    public Gui() {
        // Button for opening cart window
        JButton viewButton = new JButton("Shopping Cart");
        viewButton.setSize(150, 50);
        int frameWidth1 = 1000;
        int buttonWidth1 = 150;
        int xPosition1 = frameWidth1 - buttonWidth1 - 20; // 20px padding from the right edge
        int yPosition1 = 20; // 20px padding from the top edge
        viewButton.setLocation(xPosition1, yPosition1);

        // Button for opening Westminster Shopping window
        JButton backToHomeButton = new JButton("Back To Home");
        backToHomeButton.setSize(150, 50);
        int frameWidth2 = 1000;
        int buttonWidth2 = 150;
        int xPosition2 = frameWidth2 - buttonWidth2 - 20; // 20px padding from the right edge
        int yPosition2 = 20; // 20px padding from the top edge
        backToHomeButton.setLocation(xPosition2, yPosition2);

        // Combo Box for product categories
        String[] products = {"All", "Electronics", "Clothing"};
        comboBox = new JComboBox<>(products);
        comboBox.setSize(200, 50);
        int comboBoxXPosition = (frameWidth1 - comboBox.getWidth()) / 2;
        comboBox.setLocation(comboBoxXPosition, 20);
        comboBox.addActionListener(this);

        // Label for selecting product category
        JLabel selectProductLabel = new JLabel();
        selectProductLabel.setText("Select Product Category");
        selectProductLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        selectProductLabel.setSize(200, 50);
        selectProductLabel.setLocation(comboBoxXPosition - selectProductLabel.getWidth() - 10, 20);

        // Create Table for Westminster Frame
        String[] productTableHead = {"Product ID", "Product Name", "Category", "Price", "Details"};
        Object[][] productTableData = {};
        productTableModel = new DefaultTableModel(productTableData, productTableHead);
        productTable = new JTable(productTableModel);
        JScrollPane productTableScrollpane = new JScrollPane(productTable);
        productTableScrollpane.setBounds(10, 80, 980, 350);

        // Bottom panel with BoxLayout for stacking details
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setBackground(Color.white);
        bottomPanel.setBounds(10, 440, 980, 250);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // Adding components to the Bottom panel
        bottomDetails = new JTextArea();
        bottomDetails.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        String details = """
                Selected Product - Details
                Product ID:
                Category:
                Name:
                Price:
                Brand:
                Warranty:
                Items Available:
                """;
        bottomDetails.setText(details);
        bottomDetails.setEditable(false);
        bottomDetails.setPreferredSize(new Dimension(550, 200));
        bottomPanel.add(bottomDetails);

        // Button for adding products to the cart
        JButton addToCartButton = new JButton("Add To Cart");
        addToCartButton.setPreferredSize(new Dimension(150, 50));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addToCartButton);
        bottomPanel.add(buttonPanel);

        // Westminster Shopping Window Details
        westminsterFrame = new JFrame();
        westminsterFrame.setTitle("Westminster Shopping Manager");
        westminsterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit out of application
        westminsterFrame.setResizable(false); // Prevent resize
        westminsterFrame.setSize(1000, 700);
        westminsterFrame.setLayout(null);
        westminsterFrame.setLocationRelativeTo(null);
        westminsterFrame.add(viewButton);
        westminsterFrame.add(comboBox);
        westminsterFrame.add(selectProductLabel);
        westminsterFrame.add(productTableScrollpane);
        westminsterFrame.add(bottomPanel);
        westminsterFrame.setVisible(true);

        ImageIcon image = new ImageIcon("src/Implementation/GUI/img.png"); // Add a logo
        westminsterFrame.setIconImage(image.getImage());
        westminsterFrame.getContentPane().setBackground(Color.lightGray);

        // Shopping Cart window Details
        JFrame shoppingCartFrame = new JFrame();
        shoppingCartFrame.setSize(1000, 900);
        shoppingCartFrame.setLayout(null);
        shoppingCartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shoppingCartFrame.add(backToHomeButton);
        shoppingCartFrame.setLocationRelativeTo(null);
        shoppingCartFrame.setResizable(false);
        shoppingCartFrame.setVisible(false);

        ImageIcon image2 = new ImageIcon("src/Implementation/GUI/img.png"); // Add a logo
        shoppingCartFrame.setIconImage(image2.getImage());
        shoppingCartFrame.getContentPane().setBackground(Color.lightGray);

        // Add action listener for 'view shopping cart' button
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCartFrame.setVisible(true);
                westminsterFrame.setVisible(false); // Hide the Westminster window
            }
        });

        // Action listener for backToHomeButton button
        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCartFrame.setVisible(false);
                westminsterFrame.setVisible(true); // Show the Westminster window
            }
        });

        // Action listener for addToCartButton button
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle adding items to the cart
                JOptionPane.showMessageDialog(westminsterFrame, "Product Added to Cart!");
            }
        });

        // Add data to the table
        updateTableWhenClickComboBox(showAllProducts());

        // Add table operations
        tableOperations();
    }

    public static void main(String[] args) {
        new Gui();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            if (comboBox.getSelectedIndex() == 0) {
                updateTableWhenClickComboBox(showAllProducts());
            } else if (comboBox.getSelectedIndex() == 1) {
                updateTableWhenClickComboBox(showElectronicProducts());
            } else if (comboBox.getSelectedIndex() == 2) {
                updateTableWhenClickComboBox(showClothingProducts());
            }
        }
    }
    private void updateTableWhenClickComboBox(ArrayList<Product> list) {
        productTableModel.setRowCount(0); // Clear existing rows
        for (Product product : list) {
            String id = product.getProductID();
            String name = product.getProductName();
            double price = product.getProductPrice();
            if (product instanceof Electronics) {
                String category = "Electronic";
                String brand = ((Electronics) product).getBrand();
                int warranty = ((Electronics) product).getWarrantyPeriod();
                String[] tableRow = {id, name, category, String.valueOf(price), brand + " , " + warranty};
                productTableModel.addRow(tableRow);
            } else if (product instanceof Clothing) {
                String category = "Clothing";
                String size = ((Clothing) product).getSize();
                String color = ((Clothing) product).getColor();
                String[] tableRow = {id, name, category, String.valueOf(price), size + " , " + color};
                productTableModel.addRow(tableRow);
            }
        }
    }

    private ArrayList<Product> showAllProducts() {
        return WestminsterShoppingManager.AllProducts;
    }

    private ArrayList<Product> showElectronicProducts() {
        ArrayList<Product> electronics = new ArrayList<>();
        for (Product product : WestminsterShoppingManager.AllProducts) {
            if (product instanceof Electronics) {
                electronics.add(product);
            }
        }
        return electronics;
    }

    private ArrayList<Product> showClothingProducts() {
        ArrayList<Product> clothing = new ArrayList<>();
        for (Product product : WestminsterShoppingManager.AllProducts) {
            if (product instanceof Clothing) {
                clothing.add(product);
            }
        }
        return clothing;
    }

    //Handle Table Actions
    private void tableOperations() {
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) productTable.getValueAt(selectedRow, 0);
                    String category = (String) productTable.getValueAt(selectedRow, 2);
                    String name = (String) productTable.getValueAt(selectedRow, 1);
                    String price = (String) productTable.getValueAt(selectedRow, 3);
                    String details = (String) productTable.getValueAt(selectedRow, 4);

                    String detailsText = String.format("""
                            Selected Product - Details :
                            Product ID: %s
                            Category: %s
                            Name: %s
                            Price: %s
                            Details: %s
                            """, id, category, name, price, details);

                    bottomDetails.setText(detailsText);
                }
            }
        });





    }
}





