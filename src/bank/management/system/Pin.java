package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class Pin extends JFrame implements ActionListener {
    JButton b1, b2, sendOtpBtn, verifyOtpBtn, checkEmailBtn;
    JPasswordField newPinField, reenterNewPinField, otpField;
    JTextField emailField;
    String generatedOTP = "";
    String userEmail;

    Pin() {
//        this.pin = pin;

        setTitle("Change PIN - SDPS Bank");
        setSize(800, 600);
        setLocation(250, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Bank Logo as Frame Icon
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        setIconImage(logo.getImage());

        // HEADER PANEL
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(30, 80, 150)); // PRIMARY_BLUE
        headerPanel.setBounds(0, 0, 800, 110);
        add(headerPanel);

        JLabel label1 = new JLabel("  SDPS Bank");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 46));
        label1.setBounds(325, 40, 800, 40);
        headerPanel.add(label1);

        // FORM PANEL
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(224, 247, 250)); // BACKGROUND_GRAY
        formPanel.setBounds(0, 100, 800, 500);
        add(formPanel);

        JLabel labelTitle = new JLabel("CHANGE YOUR PIN");
        labelTitle.setForeground(Color.BLACK);
        labelTitle.setFont(new Font("System", Font.BOLD, 22));
        labelTitle.setBounds(290, 20, 300, 30);
        formPanel.add(labelTitle);

        JLabel emailLabel = new JLabel("Enter Email:");
        emailLabel.setFont(new Font("System", Font.BOLD, 18));
        emailLabel.setBounds(275, 80, 150, 30);
        formPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(390, 80, 200, 30);
        emailField.setFont(new Font("Raleway", Font.BOLD, 18));
        formPanel.add(emailField);

        checkEmailBtn = new JButton("CHECK EMAIL");
        checkEmailBtn.setBounds(400, 120, 150, 30);
        checkEmailBtn.setBackground(Color.BLACK);
        checkEmailBtn.setForeground(Color.WHITE);
        checkEmailBtn.setFont(new Font("Arial", Font.BOLD, 16));
        checkEmailBtn.addActionListener(this);
        formPanel.add(checkEmailBtn);

        // OTP BUTTON (Initially Disabled)
        sendOtpBtn = new JButton("OTP");
        sendOtpBtn.setBounds(565, 168, 75, 25);
        sendOtpBtn.setBackground(Color.BLUE);
        sendOtpBtn.setForeground(Color.WHITE);
        sendOtpBtn.setFont(new Font("Arial", Font.BOLD, 16));
        sendOtpBtn.addActionListener(this);
        sendOtpBtn.setEnabled(false);
        formPanel.add(sendOtpBtn);

        JLabel otpLabel = new JLabel("Enter OTP:");
        otpLabel.setFont(new Font("System", Font.BOLD, 18));
        otpLabel.setBounds(285, 165, 150, 30);
        formPanel.add(otpLabel);

        otpField = new JPasswordField();
        otpField.setBounds(400, 165, 150, 30);
        otpField.setFont(new Font("Raleway", Font.BOLD, 18));
        otpField.setEnabled(false);
        formPanel.add(otpField);

        verifyOtpBtn = new JButton("VERIFY OTP");
        verifyOtpBtn.setBounds(400, 210, 150, 30);
        verifyOtpBtn.setBackground(Color.BLACK);
        verifyOtpBtn.setForeground(Color.WHITE);
        verifyOtpBtn.setFont(new Font("Arial", Font.BOLD, 16));
        verifyOtpBtn.setEnabled(false);
        verifyOtpBtn.addActionListener(this);
        formPanel.add(verifyOtpBtn);

        JLabel newPinLabel = new JLabel("New PIN:");
        newPinLabel.setFont(new Font("System", Font.BOLD, 18));
        newPinLabel.setBounds(300, 250, 150, 30);
        newPinLabel.setEnabled(false);
        formPanel.add(newPinLabel);

        newPinField = new JPasswordField();
        newPinField.setBounds(400, 250, 150, 30);
        newPinField.setFont(new Font("Raleway", Font.BOLD, 18));
        newPinField.setEnabled(false);
        formPanel.add(newPinField);

        JLabel reenterNewPinLabel = new JLabel("Re-Enter New PIN:");
        reenterNewPinLabel.setFont(new Font("System", Font.BOLD, 18));
        reenterNewPinLabel.setBounds(215, 300, 200, 30);
        reenterNewPinLabel.setEnabled(false);
        formPanel.add(reenterNewPinLabel);

        reenterNewPinField = new JPasswordField();
        reenterNewPinField.setBounds(400, 300, 150, 30);
        reenterNewPinField.setFont(new Font("Raleway", Font.BOLD, 18));
        reenterNewPinField.setEnabled(false);
        formPanel.add(reenterNewPinField);

        b1 = new JButton("CHANGE PIN");
        b1.setBounds(270, 350, 150, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFocusable(false);
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setEnabled(false);
        b1.addActionListener(this);
        formPanel.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(450, 350, 150, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b2.setFocusable(false);
        b2.addActionListener(this);
        formPanel.add(b2);

        setVisible(true);
    }

    private void checkEmail() {
        try {
            Con c = new Con();
            String enteredEmail = emailField.getText().trim(); // Remove spaces

            // Debugging - Check entered email
            System.out.println("Entered Email: " + enteredEmail);

            String query = "SELECT email FROM signup WHERE email = '" + enteredEmail + "'";
            ResultSet rs = c.statement.executeQuery(query);

            if (rs.next()) {
                userEmail = enteredEmail; // Store email for OTP process
                System.out.println("Database Email Found: " + rs.getString("email"));
                JOptionPane.showMessageDialog(null, "Email verified. You can now request OTP.");
                sendOtpBtn.setEnabled(true); // Enable OTP request button
            } else {
                JOptionPane.showMessageDialog(null, "Email not found in records. Try again.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void sendOTP() {
        generatedOTP = String.format("%04d", new Random().nextInt(10000));
        EmailNotificationService.sendEmail(userEmail, "SDPS Bank - OTP Verification",
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; text-align: center; padding: 20px;'>" +
                        "<h2 style='color: #333;'>OTP Verification</h2>" +
                        "<p>Your One-Time Password (OTP) is:</p>" +
                        "<h1 style='font-size: 40px; color: #ffffff; background-color: #333; display: inline-block; padding: 15px 30px; border-radius: 8px;'>"
                        + generatedOTP + "</h1>" +
                        "<p style='color: #D32F2F; font-weight: bold;'>⚠️ Do not share this OTP with anyone.</p>" +
                        "<p>Use this OTP to proceed with your transaction.</p>" +
                        "<p>Thank you for banking with <strong>SDPS Bank</strong>.</p>" +
                        "<p>Best Regards,<br><strong>SDPS Bank</strong></p>" +
                        "</body>" +
                        "</html>");
        JOptionPane.showMessageDialog(null, "OTP sent to " + userEmail);
        otpField.setEnabled(true);
        verifyOtpBtn.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkEmailBtn) {
            checkEmail();
        } else if (e.getSource() == sendOtpBtn) {
            sendOTP();
        } else if (e.getSource() == verifyOtpBtn) {
            if (otpField.getText().equals(generatedOTP)) {
                JOptionPane.showMessageDialog(null, "OTP Verified. You can reset your PIN.");
                newPinField.setEnabled(true);
                reenterNewPinField.setEnabled(true);
                b1.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid OTP.");
            }
        } else if (e.getSource() == b2) {
            setVisible(false);  // Hide the current frame
            new Login().setVisible(true);
        } else if (e.getSource() == b1) {
            try {
                String newPin = newPinField.getText();
                String reenterNewPin = reenterNewPinField.getText();

                if (newPin.equals(reenterNewPin)) {
                    try {
                        Con c = new Con();

                        // Assuming user entered email is stored in `email`
                        String email = emailField.getText(); // Replace with actual field name

                        // Step 1: Get form_no using email
                        String getFormNoQuery = "SELECT form_no FROM signup WHERE email = ?";
                        PreparedStatement formStmt = c.connection.prepareStatement(getFormNoQuery);
                        formStmt.setString(1, email);

                        ResultSet formRs = formStmt.executeQuery();

                        if (!formRs.next()) {
                            JOptionPane.showMessageDialog(null, "Email not found. Please check and try again.");
                            return;
                        }

                        String formNo = formRs.getString("form_no");
                        System.out.println("Form No: " + formNo); // Debugging

                        // Step 2: Get current PIN using form_no
                        String getPinQuery = "SELECT pin FROM login WHERE form_no = ?";
                        PreparedStatement pinStmt = c.connection.prepareStatement(getPinQuery);
                        pinStmt.setString(1, formNo);

                        ResultSet pinRs = pinStmt.executeQuery();

                        if (!pinRs.next()) {
                            JOptionPane.showMessageDialog(null, "PIN not found for the given email.");
                            return;
                        }

                        String currentPin = pinRs.getString("pin");
                        System.out.println("Current PIN: " + currentPin); // Debugging

                        // Step 3: Update PIN in all relevant tables
                        String newPinc = newPinField.getText();
                        String reenterNewPinc = reenterNewPinField.getText();

                        if (newPin.equals(reenterNewPinc)) {
                            String updateLogin = "UPDATE login SET pin = ? WHERE form_no = ?";
                            String updateBank = "UPDATE bank SET pin = ? WHERE pin = ?";
                            String updateLoans = "UPDATE loans SET pin = ? WHERE pin = ?";
                            String updateSignupThree = "UPDATE signupthree SET pin = ? WHERE pin = ?";

                            PreparedStatement pstmt1 = c.connection.prepareStatement(updateLogin);
                            pstmt1.setString(1, newPinc);
                            pstmt1.setString(2, formNo);
                            pstmt1.executeUpdate();

                            PreparedStatement pstmt2 = c.connection.prepareStatement(updateBank);
                            pstmt2.setString(1, newPinc);
                            pstmt2.setString(2, currentPin);
                            pstmt2.executeUpdate();

                            PreparedStatement pstmt3 = c.connection.prepareStatement(updateLoans);
                            pstmt3.setString(1, newPinc);
                            pstmt3.setString(2, currentPin);
                            pstmt3.executeUpdate();

                            PreparedStatement pstmt4 = c.connection.prepareStatement(updateSignupThree);
                            pstmt4.setString(1, newPinc);
                            pstmt4.setString(2, currentPin);
                            pstmt4.executeUpdate();

                            JOptionPane.showMessageDialog(null, "PIN Changed Successfully");
                            setVisible(false);
                            new Home(newPin);
                        } else {
                            JOptionPane.showMessageDialog(null, "PINs do not match.");
                        }

                    } catch (Exception a) {
                        a.printStackTrace();
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "PINs do not match.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main (String[] args){
        new Pin();
    }
}