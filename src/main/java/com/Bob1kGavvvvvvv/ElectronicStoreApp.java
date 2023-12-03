package com.Bob1kGavvvvvvv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElectronicStoreApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Electronic Store");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel mainPanel = new JPanel(new BorderLayout());
            DefaultListModel<String> productList = new DefaultListModel<>();
            JList<String> productJList = new JList<>(productList);
            productList.addElement("Товар 1");
            productList.addElement("Товар 2");
            productList.addElement("Товар 3");
            JButton productsButton = new JButton("Товары");
            JButton ordersButton = new JButton("Заказы");
            JButton cartButton = new JButton("Корзина");
            JButton stockAdjustmentButton = new JButton("Регулировка запасов");
            JButton warehouseButton = new JButton("Склад");
            JButton purchaseHistoryButton = new JButton("История покупок");
            productsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });

            ordersButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = productJList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedProduct = productList.getElementAt(selectedIndex);
                        System.out.println("Товар добавлен в корзину: " + selectedProduct);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Выберите товар для заказа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
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
}

