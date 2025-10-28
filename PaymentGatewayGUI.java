package javaWorkshop2025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PaymentGatewayGUI extends JFrame implements ActionListener {
    private JLabel titleLabel, amountLabel, modeLabel, statusLabel;
    private JTextField amountField, cardField, cvvField, upiField;
    private JButton payButton, clearButton;
    private JRadioButton cardOption, upiOption;
    private ButtonGroup paymentGroup;
    private JPanel mainPanel;

    public PaymentGatewayGUI() {
        // Frame setup
        setTitle("Payment Gateway Simulation");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Custom Paytm-like theme (Blue + White)
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(230, 240, 255));
        mainPanel.setLayout(null);
        add(mainPanel);

        // Title
        titleLabel = new JLabel("Payment Gateway Simulation", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 51, 153));
        titleLabel.setBounds(40, 20, 350, 30);
        mainPanel.add(titleLabel);

        // Amount Label and Field
        amountLabel = new JLabel("Enter Amount (₹):");
        amountLabel.setBounds(60, 70, 150, 25);
        mainPanel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(210, 70, 150, 25);
        mainPanel.add(amountField);

        // Payment Mode
        modeLabel = new JLabel("Select Payment Mode:");
        modeLabel.setBounds(60, 110, 200, 25);
        mainPanel.add(modeLabel);

        cardOption = new JRadioButton("Card");
        upiOption = new JRadioButton("UPI");
        cardOption.setBackground(new Color(230, 240, 255));
        upiOption.setBackground(new Color(230, 240, 255));

        cardOption.setBounds(210, 110, 70, 25);
        upiOption.setBounds(290, 110, 70, 25);

        paymentGroup = new ButtonGroup();
        paymentGroup.add(cardOption);
        paymentGroup.add(upiOption);

        mainPanel.add(cardOption);
        mainPanel.add(upiOption);

        // Card Fields
        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setBounds(60, 150, 150, 25);
        mainPanel.add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(210, 150, 150, 25);
        mainPanel.add(cardField);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setBounds(60, 185, 150, 25);
        mainPanel.add(cvvLabel);

        cvvField = new JTextField();
        cvvField.setBounds(210, 185, 150, 25);
        mainPanel.add(cvvField);

        // UPI Field
        JLabel upiLabel = new JLabel("UPI ID:");
        upiLabel.setBounds(60, 220, 150, 25);
        mainPanel.add(upiLabel);

        upiField = new JTextField();
        upiField.setBounds(210, 220, 150, 25);
        mainPanel.add(upiField);

        // Buttons
        payButton = new JButton("Pay Now");
        payButton.setBackground(new Color(0, 102, 204));
        payButton.setForeground(Color.WHITE);
        payButton.setBounds(100, 270, 100, 30);
        payButton.addActionListener(this);
        mainPanel.add(payButton);

        clearButton = new JButton("Clear");
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setBounds(230, 270, 100, 30);
        clearButton.addActionListener(e -> clearFields());
        mainPanel.add(clearButton);

        // Status Label
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setBounds(50, 320, 350, 25);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mainPanel.add(statusLabel);

        setVisible(true);
    }

    private void clearFields() {
        amountField.setText("");
        cardField.setText("");
        cvvField.setText("");
        upiField.setText("");
        paymentGroup.clearSelection();
        statusLabel.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String amount = amountField.getText();
        String cardNo = cardField.getText();
        String cvv = cvvField.getText();
        String upi = upiField.getText();

        if (amount.isEmpty()) {
            showMessage("Please enter an amount.");
            return;
        }

        if (cardOption.isSelected()) {
            if (cardNo.length() == 16 && cvv.length() == 3) {
                processPayment("Card");
            } else {
                showFailure("Invalid Card Details! Try again.");
            }
        } else if (upiOption.isSelected()) {
            if (upi.contains("@")) {
                processPayment("UPI");
            } else {
                showFailure("Invalid UPI ID! Try again.");
            }
        } else {
            showMessage("Please select a payment mode.");
        }
    }

    private void processPayment(String mode) {
        Random rand = new Random();
        int txnId = 100000 + rand.nextInt(900000);

        showSuccess("Payment Successful via " + mode + 
                   "\nTransaction ID: TXN" + txnId + 
                   "\nAmount: ₹" + amountField.getText());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.WARNING_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
        statusLabel.setText("✅ Payment Successful!");
        statusLabel.setForeground(new Color(0, 153, 51));
    }

    private void showFailure(String message) {
        JOptionPane.showMessageDialog(this, message, "Payment Failed", JOptionPane.ERROR_MESSAGE);
        statusLabel.setText("❌ Payment Failed!");
        statusLabel.setForeground(Color.RED);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentGatewayGUI());
    }
}
