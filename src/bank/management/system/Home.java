//package bank.management.system;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class Home extends JFrame implements ActionListener {
//    JButton b1,b2,b3,b4,b5,b6,b7,b8;
//    String pin;
//    JLabel dateLabel, timeLabel;
//    Home(String pin){
//        this.pin = pin;
//
//        ImageIcon logo=new ImageIcon(ClassLoader.getSystemResource("icon/Bank_Logo.png"));
//        setIconImage(logo.getImage());
//
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/back2.png"));
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
//        image.setBounds(420,10,100,100);
//        l3.add(image);
//
//        JLabel label1 = new JLabel("WELCOME TO SDPS Bank");
//        label1.setForeground(Color.BLACK);
//        label1.setFont(new Font("AvantGarde", Font.BOLD, 46));
//        label1.setBounds(550,40,800,40);
//        label1.setBackground(new Color(0, 102, 153, 180));
//        l3.add(label1);
//
//        ImageIcon bc1 = new ImageIcon(ClassLoader.getSystemResource("icon/background.png"));
//        Image bc2 = bc1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
//        ImageIcon bc3 = new ImageIcon(bc2);
//        JLabel bcc3 = new JLabel(bc3);
//        bcc3.setBounds(0,151,1550,800);
//        add(bcc3);
//
//        ImageIcon dt=new ImageIcon(ClassLoader.getSystemResource("icon/deadline.png"));
//        JLabel dt1=new JLabel(dt);
//        dt1.setBounds(1280,10,50,50);
//        bcc3.add(dt1);
//
//        dateLabel = new JLabel();
//        dateLabel.setForeground(Color.BLACK);
//        dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        dateLabel.setBounds(1340, 10, 200, 30);
//        bcc3.add(dateLabel);
//
//        timeLabel = new JLabel();
//        timeLabel.setForeground(Color.BLACK);
//        timeLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        timeLabel.setBounds(1340, 30, 200, 30);
//        bcc3.add(timeLabel);
//
//        // Set up Timer to update date and time every second
//        Timer timer = new Timer(1000, h -> updateDateTime());
//        timer.start();
//
//        ImageIcon di=new ImageIcon(ClassLoader.getSystemResource("icon/DEP.png"));
//        JLabel my1=new JLabel(di);
//        my1.setBounds(350,50,100,100);
//        bcc3.add(my1);
//
//        b1 = new JButton("DEPOSIT");
//        b1.setForeground(Color.WHITE);
//        b1.setBackground(Color.BLACK);
////        b1.setBackground(new Color(65,125,128));
//        b1.setBounds(475,100,150,35);
//        b1.addActionListener(this);
//        bcc3.add(b1);
//
//        ImageIcon wi=new ImageIcon(ClassLoader.getSystemResource("icon/withdraw.png"));
//        JLabel my=new JLabel(wi);
//        my.setBounds(880,50,100,100);
//        bcc3.add(my);
//
//        b2 = new JButton("CASH WITHDRAWL");
//        b2.setForeground(Color.WHITE);
//        b2.setBackground(Color.BLACK);
////        b2.setBackground(new Color(65,125,128));
//        b2.setBounds(1000,100,150,35);
//        b2.addActionListener(this);
//        bcc3.add(b2);
//
//        ImageIcon bi=new ImageIcon(ClassLoader.getSystemResource("icon/CB.png"));
//        JLabel my3=new JLabel(bi);
//        my3.setBounds(355,200,100,100);
//        bcc3.add(my3);
//
//        b3 = new JButton("CHECK BALANCE");
//        b3.setForeground(Color.WHITE);
//        b3.setBackground(Color.BLACK);
////        b3.setBackground(new Color(65,125,128));
//        b3.setBounds(475,230,150,35);
//        b3.addActionListener(this);
//        bcc3.add(b3);
//
//        ImageIcon mi=new ImageIcon(ClassLoader.getSystemResource("icon/MS.png"));
//        JLabel my4=new JLabel(mi);
//        my4.setBounds(880,195,100,100);
//        bcc3.add(my4);
//
//        b4 = new JButton("MINI STATEMENT");
//        b4.setForeground(Color.WHITE);
//        b4.setBackground(Color.BLACK);
////        b4.setBackground(new Color(65,125,128));
//        b4.setBounds(1000,230,150,35);
//        b4.addActionListener(this);
//        bcc3.add(b4);
//
//        ImageIcon pi=new ImageIcon(ClassLoader.getSystemResource("icon/PC.png"));
//        JLabel my5=new JLabel(pi);
//        my5.setBounds(355,350,100,100);
//        bcc3.add(my5);
//
//        b5 = new JButton("PIN CHANGE");
//        b5.setForeground(Color.WHITE);
//        b5.setBackground(Color.BLACK);
////        b5.setBackground(new Color(65,125,128));
//        b5.setBounds(475,380,150,35);
//        b5.addActionListener(this);
//        bcc3.add(b5);
//
//        ImageIcon li=new ImageIcon(ClassLoader.getSystemResource("icon/LOA.png"));
//        JLabel my6=new JLabel(li);
//        my6.setBounds(880,350,100,100);
//        bcc3.add(my6);
//
//        b6 = new JButton("LOAN");
//        b6.setForeground(Color.WHITE);
//        b6.setBackground(Color.BLACK);
////        b6.setBackground(new Color(65,125,128));
//        b6.setBounds(1000,380,150,35);
//        b6.addActionListener(this);
//        bcc3.add(b6);
//
//        ImageIcon tr=new ImageIcon(ClassLoader.getSystemResource("icon/TR.png"));
//        JLabel my7=new JLabel(tr);
//        my7.setBounds(350,500,100,100);
//        bcc3.add(my7);
//
//        b7 = new JButton("TRANSACTION");
//        b7.setForeground(Color.WHITE);
//        b7.setBackground(Color.BLACK);
////        b7.setBackground(new Color(65,125,128));
//        b7.setBounds(475,525,150,35);
//        b7.addActionListener(this);
//        bcc3.add(b7);
//
//        ImageIcon loa=new ImageIcon(ClassLoader.getSystemResource("icon/LO.png"));
//        JLabel my8=new JLabel(loa);
//        my8.setBounds(885,500,100,100);
//        bcc3.add(my8);
//
//        b8 = new JButton("LOGOUT");
//        b8.setForeground(Color.WHITE);
//        b8.setBackground(Color.BLACK);
////        b8.setBackground(new Color(65,125,128));
//        b8.setBounds(1000,525,150,35);
//        b8.addActionListener(this);
//        bcc3.add(b8);
//
//        setLayout(null);
//        setSize(1530,800);
//        setLocation(5,15);
//        setVisible(true);
//    }
//    private void updateDateTime() {
//        // Set the date and time formats
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//        Date now = new Date();
//        // Updating each label individually
//        dateLabel.setText("Date: " + dateFormat.format(now));
//        timeLabel.setText("Time: " + timeFormat.format(now));
//    }
//    @SuppressWarnings("all")
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource()==b1){
//            new Deposit(pin);
//            setVisible(false);
//        } else if (e.getSource()==b6){
//            new Loan(pin);
//            setVisible(false);
//
//        } else if (e.getSource()==b2) {
//            new Withdrawl(pin);
//            setVisible(false);
//        } else if (e.getSource()==b3) {
//            new BalanceEnquiry(pin);
//            setVisible(false);
//        }
//        else if (e.getSource()==b5) {
//            new Pin(pin);
//            setVisible(false);
//        }
//        else if (e.getSource()==b4) {
//            new mini(pin);
//        } else if (e.getSource()==b7) {
//            new Transaction(pin);
//            setVisible(false);
//        }
//        else if(e.getSource()==b8){
//            new Login();
//            setVisible(false);
//        }
//    }
//
//    public static void main(String[] args) {
//
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
//
//        new Home("");
//    }
//}


package bank.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.awt.Dimension;
import java.util.Locale;


public class Home extends JFrame implements ActionListener {
    private final String pin;
    private JLabel dateLabel,balanceLabel,timeLabel,accountLabel;
    private final Color PRIMARY_BLUE = new Color(0, 150, 199);
    private final Color BACKGROUND_GRAY = new Color(224, 247, 250);
    private JButton transferMoneyButton,downloadStatementButton,applyLoanButton;
    String username;

    public Home(String pin) {
        this.pin = pin;
        setTitle("Bank Home");
//        // Set modern look and feel
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Main layout
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Main content
        JPanel mainContent = new JPanel();
        mainContent.setLayout(null);
        mainContent.setBackground(BACKGROUND_GRAY);
        add(mainContent, BorderLayout.CENTER);

        // Top bar with notification and profile
        JPanel topBar = createTopBar();
        topBar.setBounds(0, 0, 1200, 70);
        mainContent.add(topBar);

        // Account Balance Card
        JPanel balanceCard = createBalanceCard();
        balanceCard.setBounds(30, 90, 1140, 200);
        mainContent.add(balanceCard);

        // Quick Actions
        JPanel quickActions = createQuickActions();
        quickActions.setBounds(30, 310, 1140, 100);
        mainContent.add(quickActions);

        // Recent Transactions
        JPanel transactions = createTransactionsPanel(pin);
        transactions.setBounds(30, 430, 1140, 300);
        mainContent.add(transactions);

        // Frame settings
        setSize(1440, 800);
        setLocation(5, 15);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(PRIMARY_BLUE);
        sidebar.setPreferredSize(new Dimension(200, 600)); // Adjust width as needed


        sidebar.add(Box.createVerticalGlue());
        sidebar.add(createMenuItem("Dashboard", "home"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Deposit", "wallet"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Withdraw", "withdraw"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Balance Check", "Balance"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Mini Statement", "statement"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Transactions", "send"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Loan Requests", "banknote"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Pin Change", "pi"));
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(createMenuItem("Logout", "log-out"));
        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    private JPanel createMenuItem(String text, String iconName) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10)); // Align left with spacing
        item.setBackground(PRIMARY_BLUE);
        item.setPreferredSize(new Dimension(180, 40)); // Set a smaller fixed size for uniformity

        // Load and resize the icon
        JLabel iconLabel = new JLabel();
        try {
            java.net.URL iconURL = getClass().getResource("/icn/" + iconName + ".png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH); // Resize icon
                iconLabel.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(Color.WHITE);

        item.add(iconLabel);
        item.add(label);
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add MouseListener for handling the click event
        item.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Handle actions based on the menu item clicked
                String labelText = label.getText();
                switch (labelText) {
                    case "Deposit":
                        new Deposit(pin);
                        setVisible(false);
                        break;
                    case "Withdraw":
                        new Withdrawal(pin);
                        setVisible(false);
                        break;
                    case "Balance Check":
                        new BalanceEnquiry(pin);
                        setVisible(false);
                        break;
                    case "Mini Statement":
                        new mini(pin);
                        setVisible(false);
                        break;
                    case "Transactions":
                        new Transaction(pin);
                        setVisible(false);
                        break;
                    case "Loan Requests":
                        new Loans(pin);
                        setVisible(false);
                        break;
                    case "Pin Change":
                        new Pinc(pin);
                        setVisible(false);
                        break;
                    case "Logout":
                        new Login();
                        setVisible(false);
                        break;
                    default:
                        break;
                }
            }
            public void mouseEntered(MouseEvent e) {
                item.setBackground(new Color(		0, 134, 179)); // Highlight on hover
            }
            public void mouseExited(MouseEvent e) {
                item.setBackground(PRIMARY_BLUE); // Reset on exit
            }
        });

        return item;
    }


    private JPanel createTopBar() {
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBackground(new Color(224, 247, 250)); // Background color

        // Bank Logo (Left Section)
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icn/ll.png"));
        Image ii2 = ii1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);// Adjust size as needed
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel bankLogo = new JLabel(ii3);
        bankLogo.setBounds(25,5,80,80);

        topBar.add(bankLogo);

        // Bank Name (Center Section)
        JLabel bankName = new JLabel("SDPS Bank");
        bankName.setForeground(Color.BLACK);
        bankName.setFont(new Font("AvantGarde", Font.BOLD, 36)); // Adjust font size as needed
        bankName.setBounds(115,25,800,40);
        topBar.add(bankName);

        dateLabel = new JLabel();
        timeLabel = new JLabel();
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        try {
            Con c = new Con();
            String q = "SELECT username FROM login WHERE pin = ?";

            // Use PreparedStatement for security and reliability
            PreparedStatement pstmt = c.connection.prepareStatement(q);
            pstmt.setString(1, pin);  // Set the PIN parameter
            ResultSet resultSet = pstmt.executeQuery(); // Execute query

            if (resultSet.next()) {
                username = resultSet.getString("username"); // Correct column name
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect PIN");
                username = "Unknown"; // Assign a default value to prevent null issues
            }
            // Close resources
            resultSet.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create JLabel
        JLabel userName = new JLabel("");
        userName.setText("Welcome "+username+"!"); // Ensure username is not null
        userName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        userName.setBounds(975, 28, 400, 30);

        topBar.add(userName);
        topBar.repaint(); // Refresh UI in case JLabel was not updating
        return topBar;
    }

    private JPanel createBalanceCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw rounded rectangle
                g2d.setColor(PRIMARY_BLUE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Adjust 30 for roundness
            }
        };

        card.setOpaque(false); // Ensure transparency so rounded corners are visible
        card.setLayout(null);
        card.setPreferredSize(new Dimension(350, 200));  // Set size if needed

        // Title Label
        JLabel title = new JLabel("Account Balance");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(30, 20, 300, 30);

        // Balance Label
        balanceLabel = new JLabel("₹");
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(30, 60, 300, 50);

        double balance = 0;
        try {
            Con c = new Con();
            String query = "SELECT type, amount FROM bank WHERE pin = ?";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setString(1, pin);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String transactionType = resultSet.getString("type");
                double transactionAmount = resultSet.getDouble("amount");

                if (transactionType.equals("Deposit") || transactionType.equals("loanAmount")) {
                    balance += transactionAmount;
                } else if (transactionType.equals("Withdrawal") || transactionType.equals("Transfer")) {
                    balance -= Math.abs(transactionAmount);
                }
            }

            resultSet.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NumberFormat indianFormat = NumberFormat.getInstance(new Locale("en", "IN"));
        String formattedBalance = indianFormat.format(balance);
        balanceLabel.setText("₹ " + formattedBalance);



        // Account Label
        accountLabel = new JLabel("Account Number: ************0000 12"); // Default placeholder
        accountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountLabel.setForeground(Color.WHITE);
        accountLabel.setBounds(30, 120, 300, 20);

        try {
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("SELECT card_number FROM login WHERE pin = '" + pin + "'");

            if (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");

                if (cardNumber.length() >= 6) {
                    String last4 = cardNumber.substring(cardNumber.length() - 6, cardNumber.length() - 2);
                    String last2 = cardNumber.substring(cardNumber.length() - 2);

                    String maskedCardNumber = "************" + last4 + " " + last2;
                    accountLabel.setText("Account Number: " + maskedCardNumber);
                } else {
                    accountLabel.setText("Account Number: " + cardNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // View Transactions Button
        JButton viewTransactions = new JButton("View Transactions");
        viewTransactions.setBounds(30, 150, 180, 35);
        viewTransactions.setBackground(Color.WHITE);
        viewTransactions.setForeground(PRIMARY_BLUE);
        viewTransactions.setFocusPainted(false);
        viewTransactions.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add components to card
        card.add(title);
        card.add(balanceLabel);
        card.add(accountLabel);
        card.add(viewTransactions);

        return card;
    }

    private JPanel createQuickActions() {
        JPanel actions = new JPanel();
        actions.setLayout(new GridLayout(1, 3, 20, 0));
        actions.setBackground(BACKGROUND_GRAY);

        String[][] quickActions = {
                {"Transfer Money", "send"},
                {"Apply for Loan", "banknote"},
                {"Download Statement", "statement"}
        };

        for (String[] action : quickActions) {
            JButton actionButton = createQuickActionButton(action[0], action[1]);
            actionButton.addActionListener(this);  // Add action listener to each button
            actions.add(actionButton);
        }

        return actions;
    }

    private JButton createQuickActionButton(String text, String iconName) {
        JButton button = new RoundedButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Poppins", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(180, 30));

        // Load and set icon
        ImageIcon icon = loadIcon(iconName, 24, 24); // Resize icon to 24x24 pixels
        if (icon != null) {
            button.setIcon(icon);
        }

        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Text on right side
        button.setIconTextGap(10); // Space between icon and text

        // Associate buttons with actions
        if (text.equals("Transfer Money")) {
            transferMoneyButton = button;
        } else if (text.equals("Apply for Loan")) {
            applyLoanButton = button;
        } else if (text.equals("Download Statement")) {
            downloadStatementButton = button;
        }

        return button;
    }

    private ImageIcon loadIcon(String iconName, int width, int height) {
        try {
            // Load icon from classpath correctly
            URL iconURL = getClass().getClassLoader().getResource("icn/" + iconName + ".png");

            ImageIcon icon = new ImageIcon(iconURL);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if an error occurs
        }
    }

    public class RoundedButton extends JButton {
        private Color hoverBackgroundColor = new Color(212, 216, 217); // Light Gray for hover effect
        private Color defaultBackgroundColor = Color.WHITE; // Default white background

        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);  // Transparent background
            setFocusPainted(false);       // Remove focus border
            setBorderPainted(false);      // Remove border
            setOpaque(false);             // Allow transparency
            setBackground(defaultBackgroundColor);

            // Add Hover Effect
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(hoverBackgroundColor);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(defaultBackgroundColor);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded rectangle with dynamic background color
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded border
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

            g2.dispose();
        }
    }


    private JPanel createTransactionsPanel(String pin) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("Transaction History");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.add(title, BorderLayout.NORTH);

        // Define column names for the table
        String[] columns = {"Type", "Amount", "Date & Time"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes table non-editable
            }
        };

        try {
            Con c = new Con();  // Database connection

            // Fetch transactions from today to oldest in correct order
            ResultSet rs = c.statement.executeQuery(
                    "SELECT type, amount, date FROM bank " +
                            "WHERE pin = '" + pin + "' " +
                            "ORDER BY STR_TO_DATE(date, '%Y-%m-%d %H:%i:%s') DESC"
            );

            while (rs.next()) {
                String type = rs.getString("type");
                int amount = rs.getInt("amount");
                String dateTime = rs.getString("date");

                // Add transaction row (newest first)
                model.insertRow(0, new Object[]{type, "₹ " + amount, dateTime});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the table
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setFocusable(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(false);

        // Custom cell renderer for colors
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String type = table.getValueAt(row, 0).toString(); // Get transaction type

                if (type.equalsIgnoreCase("Deposit")) {
                    c.setForeground(new Color(0, 128, 0)); // Green
                } else if (type.equalsIgnoreCase("Withdrawal") || type.equalsIgnoreCase("Transfer")) {
                    c.setForeground(Color.RED); // Red
                } else if (type.equalsIgnoreCase("loanAmount")) {
                    c.setForeground(new Color(255, 140, 0)); // Orange
                } else {
                    c.setForeground(Color.BLACK); // Default
                }

                return c;
            }
        });

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == transferMoneyButton) {
            setVisible(false);
            new Transaction(pin).setVisible(true); // Open Transfer Money frame
        } else if (e.getSource() == applyLoanButton) {
            setVisible(false);
            new Loans(pin).setVisible(true); // Open Apply Loan frame
        } else if (e.getSource() == downloadStatementButton) {
            setVisible(false);
            new mini(pin).setVisible(true); // Open Download Statement frame
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
        SwingUtilities.invokeLater(() -> {
            Home home = new Home("");
            home.setVisible(true);
        });
    }
}