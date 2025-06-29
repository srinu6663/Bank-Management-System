package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {
    JComboBox comboBox,comboBox2,comboBox3,comboBox4,comboBox5;
    JTextField textPan,textAadhar;
    JRadioButton r1,r2, e1,e2;
    JButton next;
    String formno;
    Signup2(String formno){
        super("APPLICATION FORM");

        ImageIcon logo=new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        setIconImage(logo.getImage());


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(250,5,100,100);
        add(image);

        JLabel label1 = new JLabel("SDPS Bank");
        label1.setBounds(375,35,600,40);
        label1.setFont(new Font("Raleway",Font.BOLD,38));
        label1.setForeground(Color.black);
        add(label1);

        JLabel l15 = new JLabel("Secure Digital Banking Solutions");
        l15.setForeground(Color.BLACK);
        l15.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        l15.setBounds(375,68,500,40);
        add(l15);

        this.formno = formno;

        JLabel l1 = new JLabel("Page:-2");
        l1.setFont(new Font("Ralway",Font.BOLD, 18));
        l1.setBounds(655,30,350,30);
        l1.setForeground(Color.black);
        add(l1);

//        JLabel label3 = new JLabel("Additonal Details");
//        label3.setFont(new Font("Raleway", Font.BOLD,26));
//        label3.setBounds(350,125,600,30);
//        label3.setForeground(Color.black);
//        add(label3);

        JLabel l3 = new JLabel("Religion :");
        l3.setFont(new Font("Raleway", Font.BOLD,18));
        l3.setBounds(150,120,100,30);
        l3.setForeground(Color.black);
        add(l3);

        String religion[] = {"Hindu","Muslim","Sikh", "Christian", "Other"};
        comboBox = new JComboBox(religion);
        comboBox.setBackground(new Color(252,208,76));
        comboBox.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox.setBounds(350,120,320,30);
        comboBox.setForeground(Color.black);
        add(comboBox);

        JLabel l4 = new JLabel("Category : ");
        l4.setFont(new Font("Raleway", Font.BOLD,18));
        l4.setBounds(150,170,100,30);
        l4.setForeground(Color.black);
        add(l4);

        String Category [] = {"General","OBC","SC", "ST", "Other"};
        comboBox2 = new JComboBox(Category);
        comboBox2.setBackground(new Color(252,208,76));
        comboBox2.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox2.setBounds(350,170,320,30);
        comboBox2.setForeground(Color.black);
        add(comboBox2);

        JLabel l5 = new JLabel("Income : ");
        l5.setFont(new Font("Raleway", Font.BOLD,18));
        l5.setBounds(150,220,100,30);
        l5.setForeground(Color.black);
        add(l5);

        String income [] = {"Null","<50,000","1,00,000","Above 10,00,000"};
        comboBox3 = new JComboBox(income);
        comboBox3.setBackground(new Color(252,208,76));
        comboBox3.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox3.setBounds(350,220,320,30);
        comboBox3.setForeground(Color.black);
        add(comboBox3);

        JLabel l6 = new JLabel("Education : ");
        l6.setFont(new Font("Raleway", Font.BOLD,18));
        l6.setBounds(150,270,150,30);
        l6.setForeground(Color.black);
        add(l6);

        String educational [] = {"Non-Graduate","Graduate","Post-Graduate", "Doctrate", "Others"};
        comboBox4 = new JComboBox(educational);
        comboBox4.setBackground(new Color(252,208,76));
        comboBox4.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox4.setBounds(350,270,320,30);
        comboBox4.setForeground(Color.black);
        add(comboBox4);


        JLabel l7 = new JLabel("Occupation : ");
        l7.setFont(new Font("Raleway", Font.BOLD,18));
        l7.setBounds(150,340,150,30);
        l7.setForeground(Color.black);
        add(l7);

        String Occupation [] = {"Salaried","Self-Employed","Business", "Student", "Retired", "Other"};
        comboBox5 = new JComboBox(Occupation);
        comboBox5.setBackground(new Color(252,208,76));
        comboBox5.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox5.setBounds(350,340,320,30);
        comboBox5.setForeground(Color.black);
        add(comboBox5);

        JLabel l8 = new JLabel("PAN Number : ");
        l8.setFont(new Font("Raleway", Font.BOLD,18));
        l8.setBounds(150,390,150,30);
        l8.setForeground(Color.black);
        add(l8);

        textPan = new JTextField();
        textPan.setFont(new Font("Raleway", Font.BOLD,18));
        textPan.setBounds(350,390,320,30);
        add(textPan);

        JLabel l9 = new JLabel("Aadhar Number : ");
        l9.setFont(new Font("Raleway", Font.BOLD,18));
        l9.setBounds(150,440,180,30);
        l9.setForeground(Color.black);
        add(l9);

        textAadhar = new JTextField();
        textAadhar.setFont(new Font("Raleway", Font.BOLD,18));
        textAadhar.setBounds(350,440,320,30);
        add(textAadhar);


        JLabel l10 = new JLabel("Senior Citizen : ");
        l10.setFont(new Font("Raleway", Font.BOLD,18));
        l10.setBounds(150,490,180,30);
        l10.setForeground(Color.black);
        add(l10);

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD,14));
        r1.setBackground(new Color(252,208,76));
        r1.setBounds(350,490,100,30);
        r1.setForeground(Color.black);
        add(r1);
        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD,14));
        r2.setBackground(new Color(252,208,76));
        r2.setForeground(Color.black);
        r2.setBounds(460,490,100,30);
        add(r2);

        JLabel l11 = new JLabel("Existing Account : ");
        l11.setFont(new Font("Raleway", Font.BOLD,18));
        l11.setBounds(150,540,180,30);
        l11.setForeground(Color.black);
        add(l11);

        e1 = new JRadioButton("Yes");
        e1.setFont(new Font("Raleway", Font.BOLD,14));
        e1.setBackground(new Color(252,208,76));
        e1.setBounds(350,540,100,30);
        e1.setForeground(Color.black);
        add(e1);
        e2 = new JRadioButton("No");
        e2.setFont(new Font("Raleway", Font.BOLD,14));
        e2.setBackground(new Color(252,208,76));
        e2.setBounds(460,540,100,30);
        e2.setForeground(Color.black);
        add(e2);

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

        next = new JButton("Next");
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setFocusable(false);
        next.setBounds(570,640,100,30);
        next.addActionListener(this);
        add(next);


        setLayout(null);
        setSize(850,750);
        setLocation(450,80);
        getContentPane().setBackground(new Color(252,208,76));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Database:bankSystem
        //table:signuptwo;


        String rel = (String) comboBox.getSelectedItem();
        String cate = (String) comboBox2.getSelectedItem();
        String inc = (String) comboBox3.getSelectedItem();
        String edu = (String) comboBox4.getSelectedItem();
        String occ = (String) comboBox5.getSelectedItem();

        String pan = textPan.getText();
        String aadhar = textAadhar.getText();

        String scitizen = " ";
        if ((r1.isSelected())){
            scitizen = "Yes";
        } else if (r2.isSelected()) {
            scitizen ="No";
        }
        String eAccount = " ";
        if ((r1.isSelected())){
            eAccount = "Yes";
        } else if (r2.isSelected()) {
            eAccount ="No";
        }

        try {
            if (pan.isEmpty() || aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            } else if (!validatePAN(pan)) {
                JOptionPane.showMessageDialog(null, "Invalid PAN Card number. It should be in the format ABCDE1234F.");
            } else if (!validateAadhar(aadhar)) {
                JOptionPane.showMessageDialog(null, "Invalid Aadhar Number. It should be a 12-digit numeric value.");
            } else {
                Con c = new Con();
                String q = "insert into signuptwo values('" + formno + "', '" + rel + "', '" + cate + "','" + inc + "','" + edu + "','" + occ + "','" + pan + "','" + aadhar + "','" + scitizen + "','" + eAccount + "')";
                c.statement.executeUpdate(q); // Insert values into the database
                new Signup3(formno);
                setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private boolean validateAadhar(String aadhar) {
        String regex="\\d{12}"; // Aadhar format: 12 digits
        return aadhar.matches(regex);
    }

    private boolean validatePAN(String pan) {
        String regex="[A-Z]{5}[0-9]{4}[A-Z]{1}"; // PAN format: 5 letters, 4 digits, 1 letter
        return pan.matches(regex);
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
        new Signup2("");
    }
}
