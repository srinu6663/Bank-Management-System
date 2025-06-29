package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loans extends JFrame implements ActionListener {
    private String pin;
    private JTextField amountField, tenureField, incomeField;
    private JButton calculateBtn, applyBtn, backBtn;
    private JComboBox<String> loanTypeBox;
    private JProgressBar progressBar;
    private JLabel progressLabel;
    private static final DecimalFormat df = new DecimalFormat("##,##,###.00");

    // Loan Interest Rates
    private static final double HOME_LOAN_RATE = 8.5;
    private static final double PERSONAL_LOAN_RATE = 12.0;
    private static final double EDUCATION_LOAN_RATE = 6.5;

    public Loans(String pin) {
        this.pin = pin;
        setTitle("Apply for Loan");
        setSize(800, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(null);
        mainContent.setBackground(new Color(30, 80, 150)); // PRIMARY_BLUE
        mainContent.setPreferredSize(new Dimension(800, 110));
        mainPanel.add(mainContent, BorderLayout.NORTH);

        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel image = new JLabel(ii3);
        image.setBounds(195, 5, 100, 100);
        mainContent.add(image);

        JLabel label1 = new JLabel("  SDPS Bank");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 46));
        label1.setBounds(300, 35, 400, 40);
        mainContent.add(label1);

        JLabel l5 = new JLabel("Secure Digital Banking Solutions");
        l5.setForeground(Color.BLACK);
        l5.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        l5.setBounds(350,68,500,40);
        mainContent.add(l5);

        JPanel bcc3 = new JPanel();
        bcc3.setLayout(null);
        bcc3.setBackground(new Color(224, 247, 250)); // BACKGROUND_GRAY
        mainPanel.add(bcc3, BorderLayout.CENTER);

        JLabel title = new JLabel("Loan Application");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(275, 15, 300, 30);
        bcc3.add(title);

        JLabel loanTypeLabel = new JLabel("Loan Type:");
        loanTypeLabel.setBounds(240, 70, 150, 30);
        loanTypeLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        bcc3.add(loanTypeLabel);

        String[] loanTypes = {"Home Loan", "Personal Loan", "Education Loan"};
        loanTypeBox = new JComboBox<>(loanTypes);
        loanTypeBox.setBounds(350, 70, 200, 30);
        bcc3.add(loanTypeBox);

        JLabel amountLabel = new JLabel("Loan Amount:");
        amountLabel.setBounds(220, 120, 150, 30);
        amountLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        bcc3.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(350, 120, 200, 30);
        bcc3.add(amountField);

        JLabel tenureLabel = new JLabel("Tenure (months):");
        tenureLabel.setBounds(195, 170, 150, 30);
        tenureLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        bcc3.add(tenureLabel);

        tenureField = new JTextField();
        tenureField.setBounds(350, 170, 200, 30);
        bcc3.add(tenureField);

        JLabel incomeLabel = new JLabel("Monthly Income:");
        incomeLabel.setBounds(200, 220, 150, 30);
        incomeLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        bcc3.add(incomeLabel);

        incomeField = new JTextField();
        incomeField.setBounds(350, 220, 200, 30);
        bcc3.add(incomeField);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(255, 420, 335, 20);
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        bcc3.add(progressBar);

        // Add Progress Label
        progressLabel = new JLabel("");
        progressLabel.setBounds(330, 450, 300, 30);
        progressLabel.setFont(new Font("System", Font.BOLD, 14));
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setVisible(false);
        bcc3.add(progressLabel);

        calculateBtn = new JButton("Calculate EMI");
        calculateBtn.setBounds(255, 300, 150, 30);
        calculateBtn.setBackground(Color.BLACK);
        calculateBtn.setForeground(Color.WHITE);
        calculateBtn.setFocusable(false);
        calculateBtn.addActionListener(this);
        bcc3.add(calculateBtn);

        applyBtn = new JButton("Apply for Loan");
        applyBtn.setBounds(440, 300, 150, 30);
        applyBtn.setBackground(Color.BLACK);
        applyBtn.setForeground(Color.WHITE);
        applyBtn.setFocusable(false);
        applyBtn.setEnabled(false);
        applyBtn.addActionListener(this);
        bcc3.add(applyBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(350, 360, 150, 30);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);
        bcc3.add(backBtn);

        setVisible(true);
    }
    private void updateProgress(int progress, String status) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(progress);
            progressLabel.setText(status);
        });
    }


    private void calculateEMI() {
        try {
            double principal = Double.parseDouble(amountField.getText());
            int tenure = Integer.parseInt(tenureField.getText());
            double income = Double.parseDouble(incomeField.getText());

            double interestRate;
            switch ((String) loanTypeBox.getSelectedItem()) {
                case "Home Loan": interestRate = HOME_LOAN_RATE; break;
                case "Personal Loan": interestRate = PERSONAL_LOAN_RATE; break;
                case "Education Loan": interestRate = EDUCATION_LOAN_RATE; break;
                default: interestRate = HOME_LOAN_RATE;
            }

            double monthlyRate = interestRate / 12 / 100;
            double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenure))
                    / (Math.pow(1 + monthlyRate, tenure) - 1);
            double totalAmount = emi * tenure;

            // Eligibility check
            boolean isEligible = emi <= 0.4 * income;
            applyBtn.setEnabled(isEligible);

            // Show EMI calculation in message box
            String message = "<html><body style='font-family: Arial, sans-serif; font-size:14px; padding:20px; color:#333;'>"
                    + "<h2 style='color:#0066cc; text-align:left;'>Loan Calculation Summary</h2>"
                    + "<hr style='border:1px solid #ddd;'>"
                    + "<p style='text-align:left;'><b>Loan Type:</b> " + loanTypeBox.getSelectedItem() + "</p>"
                    + "<p style='text-align:left;'><b>Loan Amount:</b> <span style='color:#008000;'>₹" + df.format(principal) + "</span></p>"
                    + "<p style='text-align:left;'><b>Tenure:</b> " + tenure + " months</p>"
                    + "<p style='text-align:left;'><b>Interest Rate:</b> <span style='color:#ff6600;'>" + interestRate + "%</span></p>"
                    + "<hr style='border:1px solid #ddd;'>"
                    + "<p style='text-align:left;'><b>Monthly EMI:</b> <span style='color:#cc0000; font-size:16px;'>₹" + df.format(emi) + "</span></p>"
                    + "<p style='text-align:left;'><b>Total Amount Payable:</b> <span style='color:#cc0000; font-size:16px;'>₹" + df.format(totalAmount) + "</span></p>"
                    + "<hr style='border:1px solid #ddd;'>"
                    + (isEligible
                    ? "<p style='color:green; font-weight:bold; text-align:left;'>✔ You are Eligible for this Loan!</p>"
                    : "<p style='color:red; font-weight:bold; text-align:left;'>❌ Not Eligible (EMI exceeds 40% of your income)</p>")
                    + "</body></html>";

            JLabel label = new JLabel(message);
            JOptionPane.showMessageDialog(this, label, "EMI Calculation", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    private void applyForLoan() {
//        double loanAmount = Double.parseDouble(amountField.getText());
//        int tenure = Integer.parseInt(tenureField.getText());
//        double income = Double.parseDouble(incomeField.getText());
//        double interestRate;
//        String loanType = (String) loanTypeBox.getSelectedItem();
//
//        try {
//            // Loan Limits
//            final double HOME_LOAN_LIMIT = 5000000;  // ₹50 Lakhs
//            final double PERSONAL_LOAN_LIMIT = 1500000;  // ₹15 Lakhs
//            final double EDUCATION_LOAN_LIMIT = 1000000;  // ₹10 Lakhs
//
//            // Validate Loan Amount Based on Loan Type
//            if (loanType.equals("Home Loan") && loanAmount > HOME_LOAN_LIMIT) {
//                JOptionPane.showMessageDialog(null, "Loan Request Failed: Max limit for Home Loan is ₹" + HOME_LOAN_LIMIT);
//                return;
//            } else if (loanType.equals("Personal Loan") && loanAmount > PERSONAL_LOAN_LIMIT) {
//                JOptionPane.showMessageDialog(null, "Loan Request Failed: Max limit for Personal Loan is ₹" + PERSONAL_LOAN_LIMIT);
//                return;
//            } else if (loanType.equals("Education Loan") && loanAmount > EDUCATION_LOAN_LIMIT) {
//                JOptionPane.showMessageDialog(null, "Loan Request Failed: Max limit for Education Loan is ₹" + EDUCATION_LOAN_LIMIT);
//                return;
//            }
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid numbers.");
//        }
//
//
//
//        // Assigning Interest Rates Based on Loan Type
//        switch (loanType) {
//            case "Home Loan":
//                interestRate = HOME_LOAN_RATE;
//                break;
//            case "Personal Loan":
//                interestRate = PERSONAL_LOAN_RATE;
//                break;
//            case "Education Loan":
//                interestRate = EDUCATION_LOAN_RATE;
//                break;
//            default:
//                interestRate = HOME_LOAN_RATE;
//        }
//
//        // EMI Calculation
//        double monthlyRate = interestRate / 12 / 100;
//        double emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, tenure)) /
//                (Math.pow(1 + monthlyRate, tenure) - 1);
//
//        double balance = 0;
//        Date date = new Date();
//
//        try {
//            Con c = new Con();
//
//            // Get the current balance
//            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
//            while (resultSet.next()) {
//                String transactionType = resultSet.getString("type");
//                double transactionAmount = Double.parseDouble(resultSet.getString("amount"));
//                if (transactionType.equals("Deposit")) {
//                    balance += transactionAmount;
//                } else {
//                    balance -= transactionAmount;
//                }
//            }
//
//            // Check eligibility
//            if (balance >= loanAmount || income >= 30000) {
//                // Insert the loan details into the loans table
//                String loanQuery = "INSERT INTO loans (pin, loan_type, amount, tenure, interest_rate, emi, status) " +
//                        "VALUES ('" + pin + "', '" + loanType + "', " + loanAmount + ", " + tenure + ", " +
//                        interestRate + ", " + emi + ", 'Approved')";
//                c.statement.executeUpdate(loanQuery);
//
//                // Deposit the loan amount into the bank table
//                String transactionQuery = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + pin + "', '" + date + "', 'loanAmount', " + loanAmount + ")";
//                c.statement.executeUpdate(transactionQuery);
//
//                sendEmailNotification(pin, loanAmount);
//
//                JOptionPane.showMessageDialog(this, "Loan of ₹" + df.format(loanAmount) + " has been successfully approved!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Loan application denied due to insufficient balance or income.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "An error occurred while processing your loan application.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }


    private void applyForLoan() {
        // Get input values
        double loanAmount = Double.parseDouble(amountField.getText());
        int tenure = Integer.parseInt(tenureField.getText());
        double income = Double.parseDouble(incomeField.getText());
        String loanType = (String) loanTypeBox.getSelectedItem();

        // Validate loan limits first
        final double HOME_LOAN_LIMIT = 5000000;
        final double PERSONAL_LOAN_LIMIT = 1500000;
        final double EDUCATION_LOAN_LIMIT = 1000000;

        if (loanType.equals("Home Loan") && loanAmount > HOME_LOAN_LIMIT) {
            JOptionPane.showMessageDialog(null, "Loan Request Failed: Max limit for Home Loan is ₹" + HOME_LOAN_LIMIT);
            return;
        } else if (loanType.equals("Personal Loan") && loanAmount > PERSONAL_LOAN_LIMIT) {
            JOptionPane.showMessageDialog(null, "Loan Request Failed: Max limit for Personal Loan is ₹" + PERSONAL_LOAN_LIMIT);
            return;
        } else if (loanType.equals("Education Loan") && loanAmount > EDUCATION_LOAN_LIMIT) {
            JOptionPane.showMessageDialog(null, "Loan Request Failed: Max limit for Education Loan is ₹" + EDUCATION_LOAN_LIMIT);
            return;
        }

        // Disable apply button and show progress
        applyBtn.setEnabled(false);
        progressBar.setVisible(true);
        progressLabel.setVisible(true);

        // Use ExecutorService for background processing
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                // Calculate interest rate
                double interestRate;
                switch (loanType) {
                    case "Home Loan": interestRate = HOME_LOAN_RATE; break;
                    case "Personal Loan": interestRate = PERSONAL_LOAN_RATE; break;
                    case "Education Loan": interestRate = EDUCATION_LOAN_RATE; break;
                    default: interestRate = HOME_LOAN_RATE;
                }

                updateProgress(20, "Calculating loan details...");
                Thread.sleep(500);

                // Calculate EMI
                double monthlyRate = interestRate / 12 / 100;
                double emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, tenure)) /
                        (Math.pow(1 + monthlyRate, tenure) - 1);

                updateProgress(40, "Checking eligibility...");
                Thread.sleep(500);

                double balance = 0;
                Date date = new Date();

                Con c = new Con();
                updateProgress(60, "Verifying account details...");
                Thread.sleep(500);

                // Get current balance
                ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
                while (resultSet.next()) {
                    String transactionType = resultSet.getString("type");
                    double transactionAmount = Double.parseDouble(resultSet.getString("amount"));
                    if (transactionType.equals("Deposit")) {
                        balance += transactionAmount;
                    } else {
                        balance -= transactionAmount;
                    }
                }

                // Check eligibility
                if (balance >= loanAmount || income >= 30000) {
                    updateProgress(80, "Processing loan application...");
                    Thread.sleep(500);

                    // Insert loan details
                    String loanQuery = "INSERT INTO loans (pin, loan_type, amount, tenure, interest_rate, emi, status) " +
                            "VALUES ('" + pin + "', '" + loanType + "', " + loanAmount + ", " + tenure + ", " +
                            interestRate + ", " + emi + ", 'Approved')";
                    c.statement.executeUpdate(loanQuery);

                    // Add loan amount to bank account
                    String transactionQuery = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + pin + "', '" + date + "', 'loanAmount', " + loanAmount + ")";
                    c.statement.executeUpdate(transactionQuery);

                    updateProgress(90, "Sending notification...");
                    sendEmailNotification(pin, loanAmount);

                    updateProgress(100, "Loan approved successfully!");
                    Thread.sleep(500);

                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Loan of ₹" + df.format(loanAmount) + " has been successfully approved!");
                        setVisible(false);
                        new Home(pin).setVisible(true);
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Loan application denied due to insufficient balance or income.");
                        progressBar.setVisible(false);
                        progressLabel.setVisible(false);
                        applyBtn.setEnabled(true);
                    });
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "An error occurred while processing your loan application.", "Error", JOptionPane.ERROR_MESSAGE);
                    progressBar.setVisible(false);
                    progressLabel.setVisible(false);
                    applyBtn.setEnabled(true);
                });
            } finally {
                executor.shutdown();
            }
        });
    }



    private void sendEmailNotification(String pin, double amount) {
        try {
            Con c = new Con();

            // Fetch the loan details using the pin
            ResultSet rsLoan = c.statement.executeQuery("SELECT loan_type FROM loans WHERE pin = '" + pin + "' ORDER BY issued_date DESC LIMIT 1");
            String loanType = null;

            if (rsLoan.next()) {
                loanType = rsLoan.getString("loan_type");
            }

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
                if (email != null && loanType != null) {
                    EmailNotificationService.sendEmail(email, "SDPS Bank: Loan Approved!",
                            "<html>" +
                                    "<body style='font-family: Arial, sans-serif; color: #333; padding: 20px; line-height: 1.6;'>" +
                                    "<div style='max-width: 500px; margin: auto; padding: 20px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);'>" +
                                    "<h2 style='color: #2e7d32; text-align: center;'>✅ Loan Approved!</h2>" +
                                    "<p>Dear Customer,</p>" +
                                    "<p>We are delighted to inform you that your <strong>" + loanType + " Loan</strong> has been <strong style='color: #2e7d32;'>approved!</strong></p>" +
                                    "<p><strong>💰 Loan Amount:</strong> <span style='color: #1565C0; font-size: 18px;'>Rs. " + amount + "</span></p>" +
                                    "<p>The amount has been <strong style='color: #2e7d32;'>successfully credited</strong> to your account.</p>" +
                                    "<p style='text-align: center; font-size: 14px; color: #555;'>Thank you for trusting <strong>SDPS Bank.</strong></p>" +
                                    "<hr style='border: none; border-top: 1px solid #ddd;'>" +
                                    "<p style='text-align: center; font-size: 14px; color: #666;'><strong>Best Regards,</strong><br>" +
                                    "<strong style='color: #333;'>SDPS Bank</strong></p>" +
                                    "</div>" +
                                    "</body>" +
                                    "</html>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateBtn) {
            calculateEMI();
        } else if (e.getSource() == applyBtn) {
            applyForLoan();
        }else if (e.getSource() == backBtn) {
            setVisible(false);  // Hide the current frame
            new Home(pin).setVisible(true); // Navigate back to the Home page and make it visible
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
        new Loans("");
    }

}
