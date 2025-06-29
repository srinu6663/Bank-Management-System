package bank.management.system;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {

    JProgressBar progressBar;
    JLabel statusLabel;

    public SplashScreen() {
        // Set up the frame
        setTitle("Welcome TO SDPS BANK");
        setSize(1137, 522);
        setLocationRelativeTo(null);
        setLayout(null);

        // Add background image
        ImageIcon splashImageIcon = new ImageIcon(ClassLoader.getSystemResource("icn/big.png"));
        Image splashImage = splashImageIcon.getImage().getScaledInstance(1137, 522, Image.SCALE_DEFAULT);
        ImageIcon scaledSplashIcon = new ImageIcon(splashImage);
        JLabel background = new JLabel(scaledSplashIcon);
        background.setBounds(0, 0, 1137, 522);
        add(background);

        progressBar = new JProgressBar();
        progressBar.setBounds(93, 460, 950, 15);
        progressBar.setForeground(new Color(255, 165, 0));
        progressBar.setStringPainted(true);
        background.add(progressBar);

        statusLabel = new JLabel();
        statusLabel.setBounds(100, 480, 250, 20);
        statusLabel.setFont(new Font("AvantGarde", Font.BOLD, 18));
        statusLabel.setForeground(new Color(0, 0, 128));
        background.add(statusLabel);

        setUndecorated(true);
        setVisible(true);

        loadProgressBar();
    }
    @SuppressWarnings("all")
    public void loadProgressBar() {
        int i = 0;
        statusLabel.setText("Loading...");
        while (i <= 100) {
            try {
                Thread.sleep(50);
                progressBar.setValue(i);
                updateStatus(i);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setVisible(false);
        new Login();
    }

    private void updateStatus(int progress) {

        if (progress >= 10 && progress < 20) {
            statusLabel.setText("Loading.....");
        } else if (progress >= 20 && progress < 30) {
            statusLabel.setText("Loading Modules.....");
        } else if (progress >= 30 && progress < 70) {
            statusLabel.setText("Connecting to Database.....");
        } else if (progress >= 70 && progress < 80) {
            statusLabel.setText("Connection Successful....!");
        } else {
            statusLabel.setText("Launching Application.....");
        }

    }

    public static void main(String[] args) {
        try {
            // Set Nimbus Look and Feel
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new SplashScreen();
    }
}
