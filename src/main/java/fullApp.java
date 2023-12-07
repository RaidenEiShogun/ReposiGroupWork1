import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fullApp {

    private static Map<String, String> productCategories = new HashMap<>();
    private static Map<String, Integer> warehouse = new HashMap<>();
    private static DefaultListModel<String> cartModel = new DefaultListModel<>();
    private static DefaultListModel<String> purchaseHistoryModel = new DefaultListModel<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Electronic Store");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(240, 240, 240));

            DefaultListModel<String> productList = new DefaultListModel<>();
            JList<String> productJList = new JList<>(productList);
            productCategories.keySet().forEach(productList::addElement);

            JButton productsButton = createStyledButton("Товары", Color.BLUE);
            JButton ordersButton = createStyledButton("Заказать", Color.GREEN);
            JButton cartButton = createStyledButton("Корзина", Color.ORANGE);
            JButton stockAdjustmentButton = createStyledButton("Регулировка запасов", Color.RED);
            JButton warehouseButton = createStyledButton("Склад", Color.CYAN);
            JButton purchaseHistoryButton = createStyledButton("История покупок", Color.MAGENTA);

            productsButton.addActionListener(e -> displayProductsByCategory(productList));
            ordersButton.addActionListener(e -> orderProduct(productList, productJList.getSelectedValue()));
            cartButton.addActionListener(e -> displayCart());
            stockAdjustmentButton.addActionListener(e -> adjustStock());
            warehouseButton.addActionListener(e -> displayWarehouse());
            purchaseHistoryButton.addActionListener(e -> displayPurchaseHistory());

            JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
            buttonPanel.setBackground(new Color(200, 200, 200));
            buttonPanel.add(productsButton);
            buttonPanel.add(ordersButton);
            buttonPanel.add(cartButton);
            buttonPanel.add(stockAdjustmentButton);
            buttonPanel.add(warehouseButton);
            buttonPanel.add(purchaseHistoryButton);

            mainPanel.add(buttonPanel, BorderLayout.NORTH);
            mainPanel.add(new JScrollPane(productJList), BorderLayout.CENTER);
            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }

    private static JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        return button;
    }

    private static void displayProductsByCategory(DefaultListModel<String> productList) {
        productList.clear();
        productCategories.keySet().forEach(productList::addElement);
        productCategories.put("IPHONE", "Electronics");
        productCategories.put("Laptop", "Electronics");
        productCategories.put("Benena", "Groceries");
        productCategories.put("Apple", "Groceries");
        productCategories.put("Coat", "Clothes");
        productCategories.put("Boots", "Clothes");
        productCategories.put("Hat", "Clothes");
        productCategories.put("Winter Tire", "Things for car");
        productCategories.put("Speaker", "Electronics");
        productCategories.put("Coffee Maker", "Appliances");
        productCategories.put("Tomato", "Groceries");
        productCategories.put("Orange", "Groceries");
        productCategories.put("Sunglasses", "Accessories");
        productCategories.put("Backpack", "Accessories");
        productCategories.put("Gloves", "Clothes");
        productCategories.put("Scarf", "Clothes");
        productCategories.put("Flashlight", "Outdoor");
        productCategories.put("Tent", "Outdoor");
        productCategories.put("Book", "Entertainment");
        productCategories.put("DVD Player", "Electronics");
        productCategories.put("Chair", "Furniture");
        productCategories.put("Table", "Furniture");
        productCategories.put("Microwave", "Furniture");
        productCategories.put("Candle", "Home Decor");
        productCategories.put("Running Shoes", "Footwear");
        productCategories.put("Gaming Console", "Electronics");
        productCategories.put("Toothpaste", "Personal Care");
        productCategories.put("Shampoo", "Personal Care");
        productCategories.put("Blanket", "Home Decor");
        productCategories.put("Dumbbells", "Fitness");
        productCategories.put("Headphones", "Electronics");

        warehouse.put("IPHONE", 20);
        warehouse.put("Laptop", 20);
        warehouse.put("Benena", 20);
        warehouse.put("Apple", 20);
        warehouse.put("Coat", 20);
        warehouse.put("Boots", 20);
        warehouse.put("Hat", 20);
        warehouse.put("Winter Tire", 20);
        warehouse.put("Speaker", 20);
        warehouse.put("Coffee Maker", 20);
        warehouse.put("Tomato", 20);
        warehouse.put("Orange", 20);
        warehouse.put("Sunglasses", 20);
        warehouse.put("Backpack", 20);
        warehouse.put("Gloves", 20);
        warehouse.put("Scarf", 20);
        warehouse.put("Flashlight", 20);
        warehouse.put("Tent", 20);
        warehouse.put("Book", 20);
        warehouse.put("DVD Player", 20);
        warehouse.put("Chair", 20);
        warehouse.put("Table", 20);
        warehouse.put("Microwave", 20);
        warehouse.put("Candle", 20);
        warehouse.put("Running Shoes", 20);
        warehouse.put("Gaming Console", 20);
        warehouse.put("Toothpaste", 20);
        warehouse.put("Shampoo", 20);
        warehouse.put("Blanket", 20);
        warehouse.put("Dumbbells", 20);
        warehouse.put("Headphones", 20);
        }





    private static void orderProduct(DefaultListModel<String> productList, String selectedProduct) {
        if (selectedProduct != null) {
            if (warehouse.containsKey(selectedProduct) && warehouse.get(selectedProduct) > 0) {
                cartModel.addElement(selectedProduct);
                warehouse.put(selectedProduct, warehouse.get(selectedProduct) - 1);
                displayProductsByCategory(productList);
            } else {
                JOptionPane.showMessageDialog(null, "Товар '" + selectedProduct + "' не доступен на складе.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Выберите товар для заказа.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void displayCart() {
        JList<String> cartJList = new JList<>(cartModel);

        JButton confirmOrderButton = new JButton("Подтвердить заказ");
        confirmOrderButton.addActionListener(e -> confirmOrder());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmOrderButton);

        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.add(new JScrollPane(cartJList), BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, dialogPanel, "Корзина", JOptionPane.PLAIN_MESSAGE);
    }

    private static void confirmOrder() {
        if (cartModel.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Корзина порожня. Додайте товари перед замовленням.", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder orderDetails = new StringBuilder("Замовлення: ");
        for (int i = 0; i < cartModel.size(); i++) {
            orderDetails.append(cartModel.get(i));
            if (i < cartModel.size() - 1) {
                orderDetails.append(", ");
            }
        }

        purchaseHistoryModel.addElement(orderDetails.toString());
        cartModel.clear();
        JOptionPane.showMessageDialog(null, "Замовлення підтверджено!\n" + orderDetails, "Успіх", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayWarehouse() {
        StringBuilder warehouseDetails = new StringBuilder("Склад:\n");
        for (Map.Entry<String, Integer> entry : warehouse.entrySet()) {
            warehouseDetails.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, warehouseDetails.toString(), "Склад", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void adjustStock() {
        JTextField productField = new JTextField();
        JTextField quantityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Назва товару:"));
        panel.add(productField);
        panel.add(new JLabel("Скільки ви хочете додати до складу:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Регулювання запасів", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String productName = productField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                // ... (perform stock adjustment logic)
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Введено невірне значення для кількості товару.", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void displayPurchaseHistory(){
    }
}
