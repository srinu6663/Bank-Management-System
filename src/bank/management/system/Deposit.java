//package bank.management.system;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Date;
//
//public class Deposit extends JFrame implements ActionListener {
//
//    TextField textField1;
//    JButton b1,b2;
//
//    String pin;
//    Deposit(String pin){
//
//        this.pin=pin;
//
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/background.png"));
//        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l3 = new JLabel(i3);
//        l3.setBounds(0,0,1550,150);
//        add(l3);
//
//        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icon/Bank_Logo.png"));
//        Image ii2 = ii1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
//        ImageIcon ii3 = new ImageIcon(ii2);
//        JLabel image = new JLabel(ii3);
//        image.setBounds(600,10,100,100);
//        l3.add(image);
//
//        JLabel label1 = new JLabel("  SDPS Bank");
//        label1.setForeground(Color.BLACK);
//        label1.setFont(new Font("AvantGarde", Font.BOLD, 46));
//        label1.setBounds(700,40,800,40);
//        l3.add(label1);
//
//        ImageIcon bc1 = new ImageIcon(ClassLoader.getSystemResource("icon/back2.png"));
//        Image bc2 = bc1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
//        ImageIcon bc3 = new ImageIcon(bc2);
//        JLabel bcc3 = new JLabel(bc3);
//        bcc3.setBounds(0,151,1550,800);
//        add(bcc3);
//
//        JLabel lable2=new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
//        lable2.setBounds(510, 100, 600, 30);
//        lable2.setFont(new Font("System", Font.BOLD, 28));
//        bcc3.add(lable2);
//
////        JLabel lable3=new JLabel("Enter Account No :");
////        lable3.setBounds(340, 400, 250, 30);
////        lable3.setFont(new Font("System", Font.BOLD, 28));
////        add(lable3);
//
//        textField1 = new TextField();
//        textField1.setBounds(525, 200, 500, 30);
////        textField.setBackground(new Color(215,252,252));
//        textField1.setFont(new Font("System", Font.BOLD, 26));
//        bcc3.add(textField1);
//
//        b1 = new JButton("DEPOSIT");
//        b1.setBounds(625, 275, 100,40);
//        b1.setBackground(Color.BLACK);
//        b1.setForeground(Color.white);
//        b1.addActionListener(this);
//        bcc3.add(b1);
//
//        b2 = new JButton("BACK");
//        b2.setBounds(850, 275, 100,40);
//        b2.setBackground(Color.BLACK);
//        b2.setForeground(Color.white);
//        b2.addActionListener(this);
//        bcc3.add(b2);
//
//        setLayout(null);
//        setSize(1550,1080);
//        setLocation(-10,0);
//        setVisible(true);
//    }
//
//   @Override
//    public void actionPerformed(ActionEvent e) {
//
//        //Database:bankSystem
//        //table:bank;
//        try {
//            String amount = textField1.getText();
//            Date date = new Date();
//            if(e.getSource()==b1){
//                if(textField1.getText().isEmpty()){
//                    JOptionPane.showMessageDialog(null,"Please enter the amount you want to Deposit");
//                }
//                else{
//                    Con c=new Con();
//                    c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"','Deposit', '"+amount+"')");
//                    JOptionPane.showMessageDialog(null,"Rs."+amount+" Deposited Successfully");
//                    setVisible(false);
//                    new Home(pin);
//                }
//            }
//            else if(e.getSource()==b2){
//                setVisible(false);
//                new Home(pin);
//            }
//        }
//        catch(Exception E){
//            E.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        new Deposit("");
//
//        /* Set the Nimbus look and feel */
//
//
//    }
//}

//package bank.management.system;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.util.Date;
//
//public class Deposit extends JFrame implements ActionListener {
//
//    TextField amountField, pinField;
//    JButton depositButton, backButton;
//    JProgressBar progressBar;
//
//    String pin;
//    Deposit(String pin) {
//        this.pin = pin;
//
//        ImageIcon logo=new ImageIcon(ClassLoader.getSystemResource("icon/Bank_Logo.png"));
//        setIconImage(logo.getImage());
//
//        // Background and header
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/background.png"));
//        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l3 = new JLabel(i3);
//        l3.setBounds(0, 0, 1550, 150);
//        add(l3);
//
//        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icon/Bank_Logo.png"));
//        Image ii2 = ii1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//        ImageIcon ii3 = new ImageIcon(ii2);
//        JLabel image = new JLabel(ii3);
//        image.setBounds(225, 10, 100, 100);
//        l3.add(image);
//
//        JLabel label1 = new JLabel("  SDPS Bank");
//        label1.setForeground(Color.BLACK);
//        label1.setFont(new Font("AvantGarde", Font.BOLD, 46));
//        label1.setBounds(325, 40, 800, 40);
//        l3.add(label1);
//
//        ImageIcon bc1 = new ImageIcon(ClassLoader.getSystemResource("icon/back2.png"));
//        Image bc2 = bc1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
//        ImageIcon bc3 = new ImageIcon(bc2);
//        JLabel bcc3 = new JLabel(bc3);
//        bcc3.setBounds(0, 151, 1550, 800);
//        add(bcc3);
//
//        JLabel label2 = new JLabel("Enter Amount:");
//        label2.setBounds(150, 125, 190, 30);
//        label2.setFont(new Font("System", Font.BOLD, 28));
//        label2.setForeground(Color.BLACK);
//        bcc3.add(label2);
//
//        amountField = new TextField();
//        amountField.setBounds(350, 126, 275, 30);
//        amountField.setFont(new Font("System", Font.BOLD, 26));
//        bcc3.add(amountField);
//
//        JLabel pinLabel = new JLabel("Enter PIN:");
//        pinLabel.setBounds(205, 200, 140, 30);
//        pinLabel.setFont(new Font("System", Font.BOLD, 28));
//        pinLabel.setForeground(Color.BLACK);
//        bcc3.add(pinLabel);
//
//        pinField = new TextField();
//        pinField.setEchoChar('*');
//        pinField.setBounds(350, 200, 275, 30);
//        pinField.setFont(new Font("System", Font.BOLD, 26));
//        bcc3.add(pinField);
//
//        depositButton = new JButton("DEPOSIT");
//        depositButton.setBounds(300, 275, 125, 30);
//        depositButton.setBackground(Color.BLACK);
//        depositButton.setForeground(Color.white);
//        depositButton.addActionListener(this);
//        bcc3.add(depositButton);
//
//        backButton = new JButton("BACK");
//        backButton.setBounds(520, 275, 125, 30);
//        backButton.setBackground(Color.BLACK);
//        backButton.setForeground(Color.white);
//        backButton.addActionListener(this);
//        bcc3.add(backButton);
//
//        setLayout(null);
//        setSize(800,600);
//        setLocation(5,15);
//        setVisible(true);
//    }
//@Override
//public void actionPerformed(ActionEvent e) {
//    if (e.getSource() == backButton) {
//        setVisible(false);
//        new Home(pin); // Navigate back to the Home page
//        return;
//    }
//    if (e.getSource() == depositButton) {
//        try {
//            // Get amount and PIN inputs
//            String amount = amountField.getText();
//            String enteredPin = pinField.getText();
//            double depositAmount;
//
//            // Validate amount and PIN are entered
//            if (amount.isEmpty() || enteredPin.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Please enter both the amount and your PIN.");
//                return;
//            }
//
//            // Parse and validate the amount
//            try {
//                depositAmount = Double.parseDouble(amount);
//                if (depositAmount <= 0) {
//                    JOptionPane.showMessageDialog(null, "Invalid Amount. Please enter a positive number.");
//                    return;
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(null, "Invalid Amount. Please enter a valid number.");
//                return;
//            }
//
//            // Check if the entered PIN matches the logged-in user's PIN
//            if (!enteredPin.equals(pin)) {
//                JOptionPane.showMessageDialog(null, "Incorrect PIN. Please try again.");
//                return;
//            }
//
//            // Insert the deposit transaction into the database
//            try {
//                Date date = new Date();
//                Con c = new Con();
//                String query = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + pin + "', '" + date + "', 'Deposit', '" + depositAmount + "')";
//                c.statement.executeUpdate(query);
//
//                // Send email notification
//                sendEmailNotification(pin, depositAmount);
//
//                // Success message
//                JOptionPane.showMessageDialog(null, "Rs. " + depositAmount + " Deposited Successfully!");
//                // Navigate back to the Home page
//                setVisible(false);
//                new Home(pin);
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "An error occurred while processing your deposit. Please try again.");
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "An unexpected error occurred. Please try again.");
//        }
//    }
//
//
//
//}
//
//    /**
//     * Sends an email notification for the deposit transaction.
//     */
//    private void sendEmailNotification(String pin, double amount) {
//        try {
//            Con c = new Con();
//
//            // Fetch the form number using the pin
//            ResultSet rsForm = c.statement.executeQuery("SELECT form_no FROM login WHERE pin = '" + pin + "'");
//            String formNo = null;
//
//            if (rsForm.next()) {
//                formNo = rsForm.getString("form_no");
//            }
//
//            if (formNo != null) {
//                // Fetch the email using the form number
//                ResultSet rsEmail = c.statement.executeQuery("SELECT email FROM signup WHERE form_no = '" + formNo + "'");
//                String email = null;
//
//                if (rsEmail.next()) {
//                    email = rsEmail.getString("email");
//                }
//
//                // Send email if email address exists
//                if (email != null) {
//                    EmailNotificationService.sendEmail(email, "Deposit Alert",
//                            "Dear Customer,\n\n" +
//                                    "An amount of Rs. " + amount + " has been deposited into your account.\n\n" +
//                                    "Thank you for banking with us.\n\n" +
//                                    "SDPS Bank");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//    public static void main(String[] args) {
//        new Deposit("");
//    }





package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Deposit extends JFrame implements ActionListener {

    JTextField amountField;
    JPasswordField pinField;
    JButton depositButton, backButton;
    JProgressBar progressBar;
    JLabel progressLabel;
    private static final double DEPOSIT_LIMIT = 100000;

    private final Color PRIMARY_BLUE = new Color(0, 102, 255);
    private final Color BACKGROUND_GRAY = new Color(245, 245, 245);

    String pin;
    public Deposit(String pin) {
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
        depositButton = new JButton("DEPOSIT");
        depositButton.setBounds(300, 275, 125, 30);
        depositButton.setBackground(Color.BLACK);
        depositButton.setForeground(Color.white);
        depositButton.setFocusable(false);
        depositButton.addActionListener(this);
        bcc3.add(depositButton);

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

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == backButton) {
//            setVisible(false);
//            new Home(pin); // Navigate back to the Home page
//            return;
//        }
        if (e.getSource() == backButton) {
            setVisible(false);  // Hide the current frame
            new Home(pin).setVisible(true); // Navigate back to the Home page and make it visible
        }

        if (e.getSource() == depositButton) {
            // Validate inputs first
            String amount = amountField.getText();
            String enteredPin = pinField.getText();

            // Input validation (existing validation code)
            if (amount.isEmpty() || enteredPin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both the amount and your PIN.");
                return;
            }
            Double eamount=Double.parseDouble(amount);

            if (eamount > DEPOSIT_LIMIT) {
                JOptionPane.showMessageDialog(null, "Deposit Failed: Max limit is ₹" + DEPOSIT_LIMIT);
                return;
            }

            double depositAmount;
            try {
                depositAmount = Double.parseDouble(amount);
                if (depositAmount <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Amount. Please enter a positive number.");
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

            // Disable deposit button during processing
            depositButton.setEnabled(false);

            // Show progress bar and label
            progressBar.setVisible(true);
            progressLabel.setText("Processing Deposit...");
            progressLabel.setVisible(true);

            // Use ExecutorService for background processing
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    // Simulate loading process
                    updateProgress(0, "Validating Transaction...");
                    Thread.sleep(500);

                    updateProgress(25, "Connecting to Bank System...");
                    Thread.sleep(500);

                    updateProgress(50, "Processing Deposit...");

                    // Actual deposit transaction
                    Date date = new Date();
                    Con c = new Con();
                    String query = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + pin + "', '" + date + "', 'Deposit', '" + depositAmount + "')";
                    c.statement.executeUpdate(query);

                    updateProgress(75, "Sending Notification...");
                    // Send email notification
                    sendEmailNotification(pin, depositAmount);

                    updateProgress(100, "Deposit Successful!");

                    // Swing updates must be on EDT
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Rs. " + depositAmount + " Deposited Successfully!");
                        setVisible(false);
                        new Home(pin);
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Swing updates must be on EDT
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "An error occurred while processing your deposit. Please try again.");
                        progressBar.setVisible(false);
                        progressLabel.setVisible(false);
                        depositButton.setEnabled(true);
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

            // Fetch the form number using the pin
            ResultSet rsForm = c.statement.executeQuery("SELECT form_no FROM login WHERE pin = '" + pin + "'");
            String formNo = null;

            if (rsForm.next()) {
                formNo = rsForm.getString("form_no");
            }

            if (formNo != null) {
                // Fetch the email using the form number
                ResultSet rsEmail = c.statement.executeQuery("SELECT email FROM signup WHERE form_no = '" + formNo + "'");
                String email = null;

                if (rsEmail.next()) {
                    email = rsEmail.getString("email");
                }

                // Send email if email address exists
                if (email != null) {
                    EmailNotificationService.sendEmail(email, "SDPS Bank: Deposit Alert",
                            "<html>" +
                                    "<body style='font-family: Arial, sans-serif; color: #333; padding: 20px; text-align: left;'>" +
                                    "<h2 style='color: #1565C0;'>💰 Deposit Alert</h2>" +
                                    "<p>Dear Customer,</p>" +
                                    "<p>An amount of <strong>Rs. " + amount + "</strong> has been deposited into your account.</p>" +
                                    "<p>Thank you for banking with <strong>SDPS Bank</strong>.</p>" +
                                    "<p style='color: #666; font-size: 14px;'>Best Regards,<br><strong>SDPS Bank</strong></p>" +
                                    "</body>" +
                                    "</html>");
                }

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
        new Deposit("");
    }
}