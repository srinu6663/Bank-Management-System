package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3,l4,l5;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton forgotPinButton;

    JButton button1,button2,button3;
    Login(){
        super("SPDS BANK");

        // Main content panel setup
        JPanel mainContent = new JPanel();
        mainContent.setLayout(null);
        mainContent.setBackground(new Color(0, 150, 199)); // PRIMARY_BLUE
        mainContent.setPreferredSize(new Dimension(850, 480));
        add(mainContent);

        // Bank logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(250,13,100,100);
        mainContent.add(image); // Add to mainContent

        // Card icon
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icn/card.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(630,350,100,100);
        mainContent.add(iimage); // Add to mainContent

        l4 = new JLabel("SDPS Bank");
        l4.setForeground(Color.BLACK);
        l4.setFont(new Font("AvantGarde", Font.BOLD, 38));
        l4.setBounds(360,40,500,40);
        mainContent.add(l4);

        l5 = new JLabel("Secure Digital Banking Solutions");
        l5.setForeground(Color.BLACK);
        l5.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        l5.setBounds(360,68,500,40);
        mainContent.add(l5);

        // User Name label
        label2 = new JLabel("User Name:");
        label2.setFont(new Font("Ralway", Font.BOLD, 28));
        label2.setForeground(Color.BLACK);
        label2.setBounds(185,170,375,30);
        mainContent.add(label2);

        // Username text field
        textField2 = new JTextField(15);
        textField2.setBounds(350,170,230,30);
        textField2.setFont(new Font("Arial", Font.BOLD,14));
        mainContent.add(textField2);

        // PIN label
        label3 = new JLabel("PIN: ");
        label3.setFont(new Font("Ralway", Font.BOLD, 28));
        label3.setForeground(Color.BLACK);
        label3.setBounds(285,230,375,30);
        mainContent.add(label3);

        // PIN password field
        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(350,230,230,30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        mainContent.add(passwordField3);

        forgotPinButton = new JButton("Forgot PIN?");
        forgotPinButton.setFont(new Font("Arial", Font.ITALIC, 10));
        forgotPinButton.setForeground(Color.BLUE);
//        forgotPinButton.setBackground(new Color(0, 150, 199)); // Match background
        forgotPinButton.setBackground(new Color(0, 0, 0, 0)); // Fully transparent
        forgotPinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPinButton.setBounds(500, 260, 100, 16);
        forgotPinButton.setFocusable(false);
        forgotPinButton.addActionListener(this);
        forgotPinButton.setContentAreaFilled(false);
        forgotPinButton.setBorderPainted(false);
        forgotPinButton.setFocusPainted(false);
        forgotPinButton.setOpaque(false);
        mainContent.add(forgotPinButton);

        // SIGN IN button
        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        button1.setFocusable(false);
        button1.setBounds(350,280,100, 30);
        button1.addActionListener(this);
        mainContent.add(button1);

        // CLEAR button
        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setFocusable(false);
        button2.setBounds(480,280,100, 30);
        button2.addActionListener(this);
        mainContent.add(button2);

        // SIGN UP button
        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setFocusable(false);
        button3.setBounds(350,320,230, 30);
        button3.addActionListener(this);
        mainContent.add(button3);

        setSize(850,480);
        setLocation(350,150);
        setVisible(true);
    }


    @SuppressWarnings("all")
    @Override
    public void actionPerformed(ActionEvent e) {

        //Database:bankSystem
        //table:login;

        try{
            if (e.getSource()==button1){
                Con c = new Con();
                String uname = textField2.getText();
                String pin = passwordField3.getText();
                String q = "select * from login where username = '"+uname+"' and  pin = '"+pin+"'";
                ResultSet resultSet = c.statement.executeQuery(q);//to get values from Database
                if (resultSet.next()){
                    setVisible(false);
                    new Home(pin);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                }
            }else if (e.getSource() == button2){
                textField2.setText("");
                passwordField3.setText("");
            }else if (e.getSource() == button3){
                new Signup();
                setVisible(false);
            }
            else if (e.getSource()==forgotPinButton) {
                new Pin();
                setVisible(false);
            }
        }catch (Exception E){
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
