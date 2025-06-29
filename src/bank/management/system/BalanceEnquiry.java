package bank.management.system;//package bank.management.system;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;

public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton backButton;
    JLabel balanceLabel, accountHolderLabel, accountNumberLabel, accountTypeLabel, panLabel, aadharLabel;
    JPanel loanPanel;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;
        setTitle("Balance Enquiry");
        setSize(900, 700); // Increased height
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        // Header Section
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 900, 120);
        headerPanel.setBackground(new Color(30, 80, 150));
        headerPanel.setLayout(null);
        add(headerPanel);

        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("icn/bank_logo.png"));
        Image logo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        logoLabel.setBounds(240, 5, 100, 100);
        headerPanel.add(logoLabel);

        JLabel bankName = new JLabel("  SDPS Bank");
        bankName.setFont(new Font("Poppins", Font.BOLD, 46));
        bankName.setForeground(Color.BLACK);
        bankName.setBounds(330, 35, 400, 40);
        headerPanel.add(bankName);

        JLabel l5 = new JLabel("Secure Digital Banking Solutions");
        l5.setForeground(Color.BLACK);
        l5.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        l5.setBounds(375,68,500,40);
        headerPanel.add(l5);

        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(50, 150, 800, 450);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        add(mainPanel);

        // Balance Section
        JLabel balanceTitle = new JLabel("CURRENT BALANCE");
        balanceTitle.setFont(new Font("Poppins", Font.BOLD, 20));
        balanceTitle.setForeground(new Color(30, 80, 150));
        balanceTitle.setBounds(50, 30, 300, 25);
        mainPanel.add(balanceTitle);

        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Poppins", Font.BOLD, 36));
        balanceLabel.setForeground(new Color(40, 120, 40));
        balanceLabel.setBounds(50, 60, 700, 40);
        mainPanel.add(balanceLabel);

        // Account Details Section
        JPanel accountPanel = new JPanel();
        accountPanel.setBounds(50, 115, 700, 230);
        accountPanel.setBackground(new Color(245, 245, 245));
        accountPanel.setLayout(null);
        accountPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Account Information", TitledBorder.LEADING, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16))); // Change font & size here

        mainPanel.add(accountPanel);

        accountHolderLabel = createDetailLabel(20, 30);
        accountNumberLabel = createDetailLabel(20, 70);
        accountTypeLabel = createDetailLabel(20, 110);
        panLabel = createDetailLabel(20, 150);
        aadharLabel = createDetailLabel(20, 190);

        accountPanel.add(accountHolderLabel);
        accountPanel.add(accountNumberLabel);
        accountPanel.add(accountTypeLabel);
        accountPanel.add(panLabel);
        accountPanel.add(aadharLabel);

        // Loan Details Section
        loanPanel = new JPanel();
        loanPanel.setBounds(50, 360, 700, 90);
        loanPanel.setBackground(new Color(245, 245, 245));
        loanPanel.setLayout(new BoxLayout(loanPanel, BoxLayout.Y_AXIS));
//        loanPanel.setBorder(BorderFactory.createTitledBorder("Active Loans"));
        loanPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Active Loans", TitledBorder.LEADING, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16))); // Change font & size here
        mainPanel.add(loanPanel);

        // Back Button
        backButton = new JButton("Back to Home");
        backButton.setBounds(650, 620, 200, 35);
        backButton.addActionListener(this);
        backButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        add(backButton);

        loadDataFromDatabase();
        setVisible(true);
    }

    private JLabel createDetailLabel(int x, int y) {
        JLabel label = new JLabel();
        label.setFont(new Font("Poppins", Font.PLAIN, 14));
        label.setForeground(Color.DARK_GRAY);
        label.setBounds(x, y, 600, 25);
        return label;
    }

    private void loadDataFromDatabase() {
        Con c = new Con();
        try{
            // Load Balance
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            double balance = 0;
            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                if (type.equals("Deposit") || type.equals("loanAmount")) {
                    balance += amount;
                }
                else if (type.equals("Withdrawal") || type.equals("Transfer")) {
                    balance -= Math.abs(amount);
                }
            }

            DecimalFormat indianFormat = new DecimalFormat("##,##,##0.00");
            balanceLabel.setText("₹ " + indianFormat.format(balance));

            // Load Account Details
            rs = c.statement.executeQuery(
                    "SELECT s.name, l.card_number, t.account_Type, st.pan, st.aadhar " +
                            "FROM login l " +
                            "JOIN signup s ON l.form_no = s.form_no " +
                            "JOIN signupthree t ON l.form_no = t.form_no " +
                            "JOIN signuptwo st ON l.form_no = st.form_no " +
                            "WHERE l.pin = '" + pin + "'"
            );

            if (rs.next()) {
                accountHolderLabel.setText("Account Holder: " + rs.getString("name"));
                accountNumberLabel.setText("Account Number: " + rs.getString("card_number"));
                accountTypeLabel.setText("Account Type: " + rs.getString("account_Type"));

                String pan = rs.getString("pan");
                panLabel.setText("PAN Number: *****" + pan.substring(pan.length() - 4));

                String aadhar = rs.getString("aadhar");
                aadharLabel.setText("Aadhar Number: ****-****-" + aadhar.substring(aadhar.length() - 4));
            }

            // Load Loan Details
            rs = c.statement.executeQuery("SELECT * FROM loans WHERE pin = '" + pin + "'");
            loanPanel.removeAll();
            if (!rs.isBeforeFirst()) {
                JLabel noLoans = new JLabel("No active loans found");
                noLoans.setFont(new Font("Poppins", Font.ITALIC, 14));
                noLoans.setForeground(Color.GRAY);
                loanPanel.add(noLoans);
            } else {
                while (rs.next()) {
                    JPanel loanCard = new JPanel(new GridLayout(0, 1));
                    loanCard.setBackground(Color.WHITE);
                    loanCard.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                    JLabel loanInfo = new JLabel(String.format(
                            "<html><b>%s Loan</b> - Amount: ₹%,.2f | Tenure: %d months | Rate: %.2f%%</html>",
                            rs.getString("loan_type"),
                            rs.getDouble("amount"),
                            rs.getInt("tenure"),
                            rs.getDouble("interest_rate")
                    ));
                    loanInfo.setFont(new Font("Poppins", Font.BOLD, 16));
                    loanCard.add(loanInfo);
                    loanPanel.add(loanCard);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Home(pin).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
