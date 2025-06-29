package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Pinc extends JFrame implements ActionListener {
    JButton b1, b2,b3;
    JPasswordField oldPinField, newPinField, reenterNewPinField;
    String pin;
    Pinc(String pin) {
    this.pin = pin;

    setTitle("Change PIN - SDPS Bank");
    setSize(800, 600);
    setLocation(250, 100); // Same as your Deposit frame
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

    // Bank Logo in Header
    ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
    Image ii2 = ii1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon ii3 = new ImageIcon(ii2);
    JLabel image = new JLabel(ii3);
    image.setBounds(225, 5, 100, 100);
    headerPanel.add(image);

    // Bank Name Label
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

    // Title Label
    JLabel labelTitle = new JLabel("CHANGE YOUR PIN");
    labelTitle.setForeground(Color.BLACK);
    labelTitle.setFont(new Font("System", Font.BOLD, 22));
    labelTitle.setBounds(290, 40, 300, 30);
    formPanel.add(labelTitle);

    // Labels & Input Fields
    JLabel oldPinLabel = new JLabel("Current PIN:");
    oldPinLabel.setFont(new Font("System", Font.BOLD, 18));
    oldPinLabel.setBounds(200, 100, 150, 30);
    formPanel.add(oldPinLabel);

    oldPinField = new JPasswordField();
    oldPinField.setBounds(400, 100, 200, 30);
    oldPinField.setFont(new Font("Raleway", Font.BOLD, 18));
    formPanel.add(oldPinField);

    JLabel newPinLabel = new JLabel("New PIN:");
    newPinLabel.setFont(new Font("System", Font.BOLD, 18));
    newPinLabel.setBounds(200, 150, 150, 30);
    formPanel.add(newPinLabel);

    newPinField = new JPasswordField();
    newPinField.setBounds(400, 150, 200, 30);
    newPinField.setFont(new Font("Raleway", Font.BOLD, 18));
    formPanel.add(newPinField);

    JLabel reenterNewPinLabel = new JLabel("Re-Enter New PIN:");
    reenterNewPinLabel.setFont(new Font("System", Font.BOLD, 18));
    reenterNewPinLabel.setBounds(200, 200, 200, 30);
    formPanel.add(reenterNewPinLabel);

    reenterNewPinField = new JPasswordField();
    reenterNewPinField.setBounds(400, 200, 200, 30);
    reenterNewPinField.setFont(new Font("Raleway", Font.BOLD, 18));
    formPanel.add(reenterNewPinField);

    // Buttons
    b1 = new JButton("CHANGE");
    b1.setBounds(250, 275, 150, 30);
    b1.setBackground(Color.BLACK);
    b1.setForeground(Color.WHITE);
    b1.setFocusable(false);
    b1.setFont(new Font("Arial", Font.BOLD, 16));
    b1.addActionListener(this);
    formPanel.add(b1);

    b2 = new JButton("BACK");
    b2.setBounds(430, 275, 150, 30);
    b2.setBackground(Color.BLACK);
    b2.setForeground(Color.WHITE);
    b2.setFocusable(false);
    b2.setFont(new Font("Arial", Font.BOLD, 16));
    b2.addActionListener(this);
    formPanel.add(b2);

    setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String currentPinInput = oldPinField.getText();
            String newPinInput = newPinField.getText();
            String reenterNewPinInput = reenterNewPinField.getText();

            if (e.getSource() == b1) {
                if (currentPinInput.isEmpty() || newPinInput.isEmpty() || reenterNewPinInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }
                if(newPinInput.length() != 4){
                    JOptionPane.showMessageDialog(null, "Please Enter 4 Digit Pin");
                    return;
                }

                // Check if old PIN matches the stored PIN
                Con c = new Con();
                ResultSet rs = c.statement.executeQuery("SELECT pin FROM login WHERE pin = '" + pin + "'");
                if (rs.next() && !currentPinInput.equals(pin)) {
                    JOptionPane.showMessageDialog(null, "Incorrect current PIN.");
                    return;
                }

                if (!newPinInput.equals(reenterNewPinInput)) {
                    JOptionPane.showMessageDialog(null, "New PIN entries do not match.");
                    return;
                }

                // Update the PIN in all relevant tables
                String q1 = "UPDATE bank SET pin = '" + newPinInput + "' WHERE pin = '" + pin + "'";
                String q2 = "UPDATE login SET pin = '" + newPinInput + "' WHERE pin = '" + pin + "'";
                String q3 = "UPDATE signupthree SET pin = '" + newPinInput + "' WHERE pin = '" + pin + "'";
                String q4 = "UPDATE loans SET pin = '" + newPinInput + "' WHERE pin = '" + pin + "'";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);
                c.statement.executeUpdate(q4);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Home(newPinInput);

            } else if (e.getSource() == b2) {
                setVisible(false);  // Hide the current frame
                new Home(pin).setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pinc("");
    }
}
