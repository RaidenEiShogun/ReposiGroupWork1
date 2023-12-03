package com.Bob1kGavvvvvvv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ElectronicStoreApp {
    private static Map<String, String> productCategories = new HashMap<>();
    private static Map<String, Integer> warehouse = new HashMap<>();
    private static DefaultListModel<String> cartModel = new DefaultListModel<>();

    static {
        productCategories.put("IPHONE 15PRO MAX", "Electronics");
        productCategories.put("Laptop Asus ROG", "Electronics");
        productCategories.put("Benena", "Groceries");
        productCategories.put("Apple", "Groceries");
        productCategories.put("Coat", "Clothes");
        productCategories.put("Boots", "Clothes");
        productCategories.put("Hat", "Clothes");
        productCategories.put("Winter Tire", "Things for car");
        warehouse.put("IPHONE 15PRO MAX", 10);
        warehouse.put("Laptop Asus ROG", 20);
        warehouse.put("Benena", 50);
        warehouse.put("Apple", 50);
        warehouse.put("Coat", 25);
        warehouse.put("Boots", 10);
        warehouse.put("Hat", 15);
        warehouse.put("Winter Tire", 20);
    }

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

            productsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayProductsByCategory(productList);
                }
            });

            ordersButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orderProduct(productList, productJList.getSelectedValue());
                }
            });

            cartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayCart();
                }
            });

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
        JOptionPane.showMessageDialog(null, new JScrollPane(cartJList), "Корзина", JOptionPane.PLAIN_MESSAGE);
    }
}
