package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton r1,r2;
    JLabel l6,l15;
    JCheckBox c1,c2,c3,c4,c5,c6;
    JButton s,c;
    JTextField textName;
    String formno;
    Signup3(String formno){

        this.formno = formno;

        ImageIcon logo=new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        setIconImage(logo.getImage());


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(250,5,100,100);
        add(image);

        JLabel label1 = new JLabel("SDPS Bank");
        label1.setBounds(360,35,600,40);
        label1.setFont(new Font("Raleway",Font.BOLD,38));
        label1.setForeground(Color.black);
        add(label1);

        l15 = new JLabel("Secure Digital Banking Solutions");
        l15.setForeground(Color.BLACK);
        l15.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        l15.setBounds(360,60,500,40);
        add(l15);

        JLabel l1 = new JLabel("Page:-3");
        l1.setFont(new Font("Ralway",Font.BOLD, 18));
        l1.setBounds(655,30,350,30);
        l1.setForeground(Color.black);
        add(l1);

        JLabel l2 = new JLabel("Account Details");
        l2.setForeground(Color.black);
        l2.setFont(new Font("Raleway", Font.BOLD,26));
        l2.setBounds(350,110,600,30);
        add(l2);

        JLabel l3 = new JLabel("Account Type:");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setForeground(Color.black);
        l3.setBounds(100, 160, 200, 30);
        add(l3);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(new Color(0, 150, 199));
        r1.setForeground(Color.black);
        r1.setFocusable(false);
        r1.setBounds(100, 200, 150, 30);
        add(r1);

        r2 = new JRadioButton("Current Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(new Color(0, 150, 199));
        r2.setForeground(Color.black);
        r2.setFocusable(false);
        r2.setBounds(100, 250, 150, 30);
        add(r2);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        JLabel l4 = new JLabel("Card Number:");
        l4.setFont(new Font("Raleway",Font.BOLD,18));
        l4.setForeground(Color.black);
        l4.setBounds(100,300,200,30);
        add(l4);

        JLabel l5 = new JLabel("(Your 16-digit Card Number)");
        l5.setFont(new Font("Raleway",Font.BOLD,12));
        l5.setForeground(Color.black);
        l5.setBounds(100,330,200,20);
        add(l5);

        l6 = new JLabel("XXXX-XXXX-XXXX-XXXX");
        l6.setFont(new Font("Raleway",Font.BOLD,18));
        l6.setForeground(Color.black);
        l6.setBounds(330,300,250,30);
        add(l6);

        JLabel l7 = new JLabel("(It would appear on atm card/cheque Book and Statements)");
        l7.setFont(new Font("Raleway",Font.BOLD,12));
        l7.setForeground(Color.black);
        l7.setBounds(330,330,500,20);
        add(l7);

        JLabel l8 = new JLabel("PIN:");
        l8.setFont(new Font("Raleway",Font.BOLD,18));
        l8.setForeground(Color.black);
        l8.setBounds(100,370,200,30);
        add(l8);

        JLabel l9 = new JLabel("XXXX");
        l9.setFont(new Font("Raleway",Font.BOLD,18));
        l9.setForeground(Color.black);
        l9.setBounds(330,370,200,30);
        add(l9);

        JLabel l10 = new JLabel("(4-digit Password)");
        l10.setFont(new Font("Raleway",Font.BOLD,12));
        l10.setForeground(Color.black);
        l10.setBounds(100,400,200,20);
        add(l10);

        JLabel l14 = new JLabel("Enter Username :");
        l14.setFont(new Font("Raleway",Font.BOLD,18));
        l14.setForeground(Color.black);
        l14.setBounds(100,450,200,30);
        add(l14);

        textName = new JTextField();
        textName.setFont(new Font("Raleway",Font.BOLD, 14));
        textName.setBounds(100,500,400,30);
        add(textName);


        JLabel l11 = new JLabel("Services Required:");
        l11.setFont(new Font("Raleway",Font.BOLD,18));
        l11.setForeground(Color.black);
        l11.setBounds(100,550,200,30);
        add(l11);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(new Color(0, 150, 199));
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setForeground(Color.black);
        c1.setBounds(100,600,200,30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(new Color(0, 150, 199));
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setForeground(Color.black);
        c2.setBounds(350,600,200,30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(new Color(0, 150, 199));
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setForeground(Color.black);
        c3.setBounds(100,630,200,30);
        add(c3);

        c4 = new JCheckBox("EMAIL Alerts");
        c4.setBackground(new Color(0, 150, 199));
        c4.setFont(new Font("Raleway",Font.BOLD,16));
        c4.setForeground(Color.black);
        c4.setBounds(350,630,200,30);
        add(c4);

        JCheckBox c7 = new JCheckBox("I here by decleares that the above entered details correct to the best of my knlowledge.",true);
        c7.setBackground(new Color(0, 150, 199));
        c7.setFont(new Font("Raleway",Font.BOLD,12));
        c7.setForeground(Color.black);
        c7.setBounds(100,680,600,20);
        add(c7);

//        JLabel l12 = new JLabel("Form No : ");
//        l12.setFont(new Font("Raleway", Font.BOLD,20));
//        l12.setBounds(655,60,600,30);
//        l12.setForeground(Color.black);
//        add(l12);
//
//        JLabel l13 = new JLabel(formno);
//        l13.setFont(new Font("Raleway", Font.BOLD,14));
//        l13.setBounds(760,10,60,30);
//        add(l13);

        s = new JButton("Submit");
        s.setFont(new Font("Raleway", Font.BOLD,14));
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setFocusable(false);
        s.setBounds(250,720,100,30);
        s.addActionListener(this);
        add(s);

        c = new JButton("Cancel");
        c.setFont(new Font("Raleway", Font.BOLD,14));
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.setBounds(420,720,100,30);
        c.addActionListener(this);
        add(c);

        getContentPane().setBackground(new Color(0, 150, 199));
        setSize(850,800);
        setLayout(null);
        setLocation(400,20);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Database:bankSystem
        //table:signupthree;

        String atype = null;
        if (r1.isSelected()){
            atype = "Saving Account";
        } else if (r2.isSelected()) {
            atype = "Fixed Deposit Account";
        }

        Random ran = new Random();
        long first7 = (ran.nextLong() % 90000000L) + 1404363000000000L;
        String cardno = "" + Math.abs(first7);

        // Extract last 4 digits from the card number
        String last4Digits = cardno.substring(cardno.length() - 4);



        long first3 = (ran.nextLong() % 9000L)+ 1000L;
        String pin = "" + Math.abs(first3);

        String fac = "";
        if(c1.isSelected()){
            fac += "ATM CARD ";
        } else if (c2.isSelected()) {
            fac += "Internet Banking";
        } else if (c3.isSelected()) {
            fac += "Mobile Banking";
        } else if (c4.isSelected()) {
            fac += "EMAIL Alerts";
        }

        String uname=textName.getText();
        // Username validation: only alphabets allowed, length 15 to 20 characters, and Unique Username
        try {
            if (!uname.matches("[a-zA-Z]{6,15}")) {
                JOptionPane.showMessageDialog(null, "Username should contain only alphabets (A-Z, a-z) and be 6 to 15 characters long.", "Invalid Username", JOptionPane.ERROR_MESSAGE);
                return; // Stop execution if validation fails
            }

            Con c1 = new Con();
            String query = "SELECT username FROM login WHERE username = ?";
            PreparedStatement pst = c1.statement.getConnection().prepareStatement(query);
            pst.setString(1, uname);
            ResultSet rs = pst.executeQuery();


            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Username Taken", JOptionPane.ERROR_MESSAGE);
                return; // Stop execution if username is taken
            }
            rs.close();
            pst.close();
        } catch (Exception f) {
            f.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error! Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }


        try {
            if (e.getSource() == s){
                if (atype.equals("")){
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                } else {
                    // Display the last 4 digits in the JLabel (l6)
                    l6.setText("XXXX-XXXX-XXXX-" + last4Digits);
                    Con c1 = new Con();
                    String q1 = "insert into signupthree values('"+formno+"', '"+atype+"','"+cardno+"','"+pin+"','"+fac+"')";
                    String q2 = "insert into login values('"+formno+"','"+cardno+"','"+pin+"','"+uname+"')";
                    c1.statement.executeUpdate(q1);
                    c1.statement.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Card Number : " + cardno + "\n Pin : " + pin );
                    new Deposit(pin);
                    setVisible(false);
                }
            } else if (e.getSource() == c) {
                System.exit(0);
            }

        } catch (Exception E) {
            E.printStackTrace();
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

        new Signup3("");
    }
}

