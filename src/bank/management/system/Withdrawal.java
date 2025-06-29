package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Withdrawal extends JFrame implements ActionListener {

    String pin;
    JTextField amountField;
    JPasswordField pinField;

    JButton withdrawButton, backButton;
    JProgressBar progressBar;
    JLabel progressLabel;
    private static final double WITHDRAW_LIMIT  = 25000;   // Max per transfer

    Withdrawal(String pin) {
        this.pin = pin;

        // Set frame properties
        setTitle("Deposit - SDPS Bank");
        setSize(800, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bank Logo as Frame Icon
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        setIconImage(logo.getImage());

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Header Panel
        JPanel mainContent = new JPanel();
        mainContent.setLayout(null);
        mainContent.setBackground(new Color(30, 80, 150)); // PRIMARY_BLUE
        mainContent.setPreferredSize(new Dimension(800, 125));
        mainPanel.add(mainContent, BorderLayout.NORTH);

        // Bank Logo in Header
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel image = new JLabel(ii3);
        image.setBounds(225, 10, 100, 100);
        mainContent.add(image);

        // Bank Name Label
        JLabel label1 = new JLabel("  SDPS Bank");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 46));
        label1.setBounds(325, 40, 800, 40);
        mainContent.add(label1);


        JLabel l5 = new JLabel("Secure Digital Banking Solutions");
        l5.setForeground(Color.BLACK);
        l5.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        l5.setBounds(375,68,500,40);
        mainContent.add(l5);

        // Form Panel
        JPanel bcc3 = new JPanel();
        bcc3.setLayout(null);
        bcc3.setBackground(new Color(224, 247, 250)); // BACKGROUND_GRAY
        mainPanel.add(bcc3, BorderLayout.CENTER);

        // "Enter Amount" Label
        JLabel label2 = new JLabel("Enter Amount:");
        label2.setBounds(150, 125, 190, 30);
        label2.setFont(new Font("System", Font.BOLD, 28));
        label2.setForeground(Color.BLACK);
        bcc3.add(label2);

        // Amount Text Field
        amountField = new JTextField();
        amountField.setBounds(350, 126, 275, 35);
        amountField.setFont(new Font("System", Font.BOLD, 26));
        bcc3.add(amountField);

        // "Enter PIN" Label
        JLabel pinLabel = new JLabel("Enter PIN:");
        pinLabel.setBounds(205, 200, 140, 30);
        pinLabel.setFont(new Font("System", Font.BOLD, 28));
        pinLabel.setForeground(Color.BLACK);
        bcc3.add(pinLabel);

        // PIN Text Field
        pinField = new JPasswordField();
        pinField.setEchoChar('*');
        pinField.setBounds(350, 200, 275, 35);
        pinField.setFont(new Font("System", Font.BOLD, 26));
        bcc3.add(pinField);

        // Deposit Button
        withdrawButton = new JButton("WITHDRAWAL");
        withdrawButton.setBounds(300, 275, 125, 30);
        withdrawButton.setBackground(Color.BLACK);
        withdrawButton.setForeground(Color.white);
        withdrawButton.setFocusable(false);
        withdrawButton.addActionListener(this);
        bcc3.add(withdrawButton);

        // Back Button
        backButton = new JButton("BACK");
        backButton.setBounds(520, 275, 125, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        bcc3.add(backButton);

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(250, 320, 300, 20);
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        bcc3.add(progressBar);

        // Progress Label
        progressLabel = new JLabel("");
        progressLabel.setBounds(325, 350, 300, 30);
        progressLabel.setFont(new Font("System", Font.BOLD, 14));
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setVisible(false);
        bcc3.add(progressLabel);

        // Make Frame Visible
        setVisible(true);
    }

    @SuppressWarnings("all")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);  // Hide the current frame
            new Home(pin).setVisible(true); // Navigate back to the Home page and make it visible
        }

        if (e.getSource() == withdrawButton) {
            String amount = amountField.getText();
            String enteredPin = pinField.getText();
            double eamount= Double.parseDouble(amount);

            // Input validation
            if (amount.isEmpty() || enteredPin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both the amount and your PIN.");
                return;
            }

            if (eamount > WITHDRAW_LIMIT ) {
                JOptionPane.showMessageDialog(null, "Transfer Failed: Max limit is ₹" + WITHDRAW_LIMIT );
                return;
            }

            double amo;
            try {
                amo = Double.parseDouble(amount);
                if (amo <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Amount");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Amount. Please enter a valid number.");
                return;
            }

            if (!enteredPin.equals(pin)) {
                JOptionPane.showMessageDialog(null, "Incorrect PIN. Please try again.");
                return;
            }

            // Disable withdraw button during processing
            withdrawButton.setEnabled(false);

            // Show progress bar and label
            progressBar.setVisible(true);
            progressLabel.setText("Processing Withdrawal...");
            progressLabel.setVisible(true);

            // Use ExecutorService for background processing
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    // Simulate loading process
                    updateProgress(0, "Validating Transaction...");
                    Thread.sleep(500);

                    updateProgress(25, "Checking Account Balance...");
                    Thread.sleep(500);

                    Con c = new Con();
                    ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
                    double balance = 0;

                    while (resultSet.next()) {
                        String transactionType = resultSet.getString("type");
                        double transactionAmount = Double.parseDouble(resultSet.getString("amount"));

                        if (transactionType.equals("Deposit") || transactionType.equals("loanAmount")) {
                            balance += transactionAmount;
                        } else if (transactionType.equals("Withdrawl") || transactionType.equals("Transfer")) {
                            balance -= Math.abs(transactionAmount);
                        }
                    }

                    updateProgress(50, "Processing Withdrawal...");
                    Thread.sleep(500);

                    if (balance < amo) {
                        throw new Exception("Insufficient Balance");
                    }

                    // Withdraw money
                    Date date = new Date();
                    c.statement.executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdrawal', '" + amount + "')");

                    updateProgress(75, "Sending Notification...");
                    // Send email notification
                    sendEmailNotification(pin, amo);

                    updateProgress(100, "Withdrawal Successful!");

                    // Swing updates must be on EDT
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");
                        setVisible(false);
                        new Home(pin);
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Swing updates must be on EDT
                    SwingUtilities.invokeLater(() -> {
                        if (ex.getMessage().equals("Insufficient Balance")) {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        } else {
                            JOptionPane.showMessageDialog(null, "An error occurred while processing your withdrawal. Please try again.");
                        }
                        progressBar.setVisible(false);
                        progressLabel.setVisible(false);
                        withdrawButton.setEnabled(true);
                    });
                } finally {
                    executor.shutdown();
                }
            });
        }
    }

    // Method to update progress bar and label
    private void updateProgress(int progress, String status) throws Exception {
        // Swing updates must be on EDT
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(progress);
            progressLabel.setText(status);
        });
        Thread.sleep(500); // Simulated processing time
    }

    // Existing sendEmailNotification method remains the same
    private void sendEmailNotification(String pin, double amount) {
        try {
            Con c = new Con();

            // Step 1: Fetch the form_no using the pin
            String formNo = null;
            ResultSet rsForm = c.statement.executeQuery("SELECT form_no FROM login WHERE pin = '" + pin + "'");
            if (rsForm.next()) {
                formNo = rsForm.getString("form_no");
            }

            // Step 2: Fetch the email using the form_no
            String email = null;
            if (formNo != null) {
                ResultSet rsEmail = c.statement.executeQuery("SELECT email FROM signup WHERE form_no = '" + formNo + "'");
                if (rsEmail.next()) {
                    email = rsEmail.getString("email");
                }
            }

            // Step 3: Send email if email exists
            if (email != null) {
                EmailNotificationService.sendEmail(email, "SDPS Bank: Withdrawal Alert",
                        "<html>" +
                                "<body style='font-family: Arial, sans-serif; color: #333; padding: 20px;'>" +
                                "<h2 style='color: #D32F2F;'>Withdrawal Alert</h2>" +
                                "<p>Dear Customer,</p>" +
                                "<p>An amount of <strong>Rs. " + amount + "</strong> has been withdrawn from your account.</p>" +
                                "<p>If this was not you, please contact us immediately.</p>" +
                                "<p>Thank you for banking with <strong>SDPS Bank</strong>.</p>" +
                                "<p>Best Regards,<br><strong>SDPS Bank</strong></p>" +
                                "</body>" +
                                "</html>");
            } else {
                System.out.println("Email not found for the given PIN.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        try {
//            // Set Nimbus Look and Feel
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        new Withdrawal("");
    }
}