package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Transaction extends JFrame implements ActionListener {

    String senderPin;
    JButton transferButton, backButton;
    JTextField amountField, recipientAccountField;
    JPasswordField senderPinField;
    JProgressBar progressBar;
    JLabel progressLabel;
    private static final double TRANSFER_LIMIT = 50000;

    double balance = 0;

    public Transaction(String pin) {
        this.senderPin = pin;

        // Set frame properties
        setTitle("Transaction - SDPS Bank");
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
        bcc3.setBackground(new Color(240, 248, 255)); // BACKGROUND_GRAY
        mainPanel.add(bcc3, BorderLayout.CENTER);

        // "Enter Amount" Label
        JLabel enterAmountLabel = new JLabel("Enter Amount:");
        enterAmountLabel.setBounds(150, 100, 190, 30);
        enterAmountLabel.setFont(new Font("System", Font.BOLD, 28));
        enterAmountLabel.setForeground(Color.BLACK);
        bcc3.add(enterAmountLabel);

        // Amount Text Field
        amountField = new JTextField();
        amountField.setBounds(350, 100, 275, 35);
        amountField.setFont(new Font("System", Font.BOLD, 26));
        bcc3.add(amountField);

        // "Recipient Account No" Label
        JLabel recipientAccountLabel = new JLabel("Recipient Account No:");
        recipientAccountLabel.setBounds(150, 160, 300, 30);
        recipientAccountLabel.setFont(new Font("System", Font.BOLD, 28));
        recipientAccountLabel.setForeground(Color.BLACK);
        bcc3.add(recipientAccountLabel);

        // Recipient Account Text Field
        recipientAccountField = new JTextField();
        recipientAccountField.setBounds(450, 160, 275, 35);
        recipientAccountField.setFont(new Font("System", Font.BOLD, 26));
        bcc3.add(recipientAccountField);

        // "Enter Your PIN" Label
        JLabel senderPinLabel = new JLabel("Enter Your PIN:");
        senderPinLabel.setBounds(150, 220, 300, 30);
        senderPinLabel.setFont(new Font("System", Font.BOLD, 28));
        senderPinLabel.setForeground(Color.BLACK);
        bcc3.add(senderPinLabel);

        // PIN Text Field
        senderPinField = new JPasswordField();
        senderPinField.setBounds(360, 220, 275, 35);
        senderPinField.setFont(new Font("System", Font.BOLD, 26));
        bcc3.add(senderPinField);

        // Transfer Button
        transferButton = new JButton("Transfer");
        transferButton.setBounds(300, 300, 125, 30);
        transferButton.setBackground(Color.BLACK);
        transferButton.setForeground(Color.white);
        transferButton.addActionListener(this);
        transferButton.setFocusable(false);
        bcc3.add(transferButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(520, 300, 125, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        bcc3.add(backButton);

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(250, 350, 300, 20);
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        bcc3.add(progressBar);

        // Progress Label
        progressLabel = new JLabel("");
        progressLabel.setBounds(325, 380, 300, 30);
        progressLabel.setFont(new Font("System", Font.BOLD, 14));
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setVisible(false);
        bcc3.add(progressLabel);

        setVisible(true);
    }

    private void updateBalance() {
        try {
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + senderPin + "'");

            while (resultSet.next()) {
                String transactionType = resultSet.getString("type");
                double transactionAmount = Double.parseDouble(resultSet.getString("amount"));

                if (transactionType.equals("Deposit") || transactionType.equals("loanAmount")) {
                    balance += transactionAmount;
                } else if (transactionType.equals("Withdrawal") || transactionType.equals("Transfer")) {
                    balance -= Math.abs(transactionAmount);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            new Home(senderPin);
            return;
        }

        if (e.getSource() == transferButton) {
            String amountStr = amountField.getText();
            String cardNumber = recipientAccountField.getText();
            String enteredPin = new String(senderPinField.getPassword());

            // Input validation
            if (amountStr.isEmpty() || cardNumber.isEmpty() || enteredPin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            // Disable transfer button during processing
            transferButton.setEnabled(false);

            // Show progress bar and label
            progressBar.setVisible(true);
            progressLabel.setText("Processing Transfer...");
            progressLabel.setVisible(true);

            // Use ExecutorService for background processing
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    // Simulate loading process
                    updateProgress(0, "Validating Transaction...");
                    Thread.sleep(500);

                    double amount = Double.parseDouble(amountStr);

                    // Validate PIN
                    if (!enteredPin.equals(senderPin)) {
                        throw new Exception("Incorrect PIN");
                    }

                    updateProgress(25, "Checking Transfer Limit...");
                    // Transfer limit check
                    if (amount > TRANSFER_LIMIT) {
                        throw new Exception("Transfer limit exceeded");
                    }

                    updateProgress(50, "Connecting to Bank System...");
                    Con c = new Con();
                    Date date = new Date();

                    // Verify recipient account
                    ResultSet rs = c.statement.executeQuery("SELECT pin FROM login WHERE card_number = '" + cardNumber + "'");
                    if (!rs.next()) {
                        throw new Exception("Recipient account not found");
                    }

                    String recipientPin = rs.getString("pin");

                    updateProgress(75, "Processing Transfer...");
                    // Perform transfer
                    c.statement.executeUpdate("INSERT INTO bank (pin, date, type, amount) VALUES('" + senderPin + "', '" + date + "', 'Transfer', -" + amount + ")");
                    c.statement.executeUpdate("INSERT INTO bank (pin, date, type, amount) VALUES('" + recipientPin + "', '" + date + "', 'Deposit', " + amount + ")");

                    // Send email notifications (existing email notification code)
                    sendEmailNotifications(c, senderPin, recipientPin, amount, cardNumber);

                    updateProgress(100, "Transfer Successful!");

                    // Swing updates must be on EDT
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Rs." + amount + " Transferred Successfully to Account No: " + cardNumber);
                        setVisible(false);
                        new Home(senderPin);
                    });

                } catch (Exception ex) {
                    // Handle different types of exceptions
                    String errorMessage = "Transfer Failed: ";
                    if (ex.getMessage().equals("Incorrect PIN")) {
                        errorMessage += "Incorrect PIN";
                    } else if (ex.getMessage().equals("Transfer limit exceeded")) {
                        errorMessage += "Max limit is ₹" + TRANSFER_LIMIT;
                    } else if (ex.getMessage().equals("Recipient account not found")) {
                        errorMessage += "Recipient account not found";
                    } else {
                        errorMessage += "An unexpected error occurred";
                    }

                    // Swing updates must be on EDT
                    String finalErrorMessage = errorMessage;
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, finalErrorMessage);
                        progressBar.setVisible(false);
                        progressLabel.setVisible(false);
                        transferButton.setEnabled(true);
                    });
                    ex.printStackTrace();
                } finally {
                    executor.shutdown();
                }
            });
        }
    }

    // Email notification method
    private void sendEmailNotifications(Con c, String senderPin, String recipientPin, double amount, String cardNumber) throws Exception {
        // Fetch sender email
        String senderFormNo = null;
        ResultSet senderFormRS = c.statement.executeQuery("SELECT form_no FROM login WHERE pin = '" + senderPin + "'");
        if (senderFormRS.next()) {
            senderFormNo = senderFormRS.getString("form_no");
        }

        String senderEmail = null;
        if (senderFormNo != null) {
            ResultSet senderEmailRS = c.statement.executeQuery("SELECT email FROM signup WHERE form_no = '" + senderFormNo + "'");
            if (senderEmailRS.next()) {
                senderEmail = senderEmailRS.getString("email");
            }
        }

        // Fetch recipient email
        String recipientFormNo = null;
        ResultSet recipientFormRS = c.statement.executeQuery("SELECT form_no FROM login WHERE pin = '" + recipientPin + "'");
        if (recipientFormRS.next()) {
            recipientFormNo = recipientFormRS.getString("form_no");
        }

        String recipientEmail = null;
        if (recipientFormNo != null) {
            ResultSet recipientEmailRS = c.statement.executeQuery("SELECT email FROM signup WHERE form_no = '" + recipientFormNo + "'");
            if (recipientEmailRS.next()) {
                recipientEmail = recipientEmailRS.getString("email");
            }
        }

        String senderSubject = "SDPS Bank: Transfer Successful";

        String senderBody =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; color: #333; padding: 20px; text-align: center;'>" +
                        "<h2 style='color: #1565C0;'>✅ Transfer Successful</h2>" +
                        "<p>Dear Customer,</p>" +
                        "<p>Your transfer of <strong>Rs. " + amount + "</strong> to account <strong>XXXX-" + cardNumber.substring(cardNumber.length() - 4) + "</strong> was successful.</p>" +
                        "<p>Thank you for banking with <strong>SDPS Bank</strong>.</p>" +
                        "<p style='color: #666; font-size: 14px;'>Best Regards,<br><strong>SDPS Bank</strong></p>" +
                        "</body>" +
                        "</html>";


        String recipientSubject = "SDPS Bank: Amount Received";

        String recipientBody =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; color: #333; padding: 20px; text-align: center;'>" +
                        "<h2 style='color: #1565C0;'>💰 Amount Received</h2>" +
                        "<p>Dear Customer,</p>" +
                        "<p>You have received <strong>Rs. " + amount + "</strong> from another account.</p>" +
                        "<p>Thank you for banking with <strong>SDPS Bank</strong>.</p>" +
                        "<p style='color: #666; font-size: 14px;'>Best Regards,<br><strong>SDPS Bank</strong></p>" +
                        "</body>" +
                        "</html>";


        EmailNotificationService.sendEmail(senderEmail, senderSubject, senderBody);
        EmailNotificationService.sendEmail(recipientEmail, recipientSubject, recipientBody);
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
        new Transaction("");
    }
}
