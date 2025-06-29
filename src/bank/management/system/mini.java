package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@SuppressWarnings("all")
public class mini extends JFrame implements ActionListener {
    String pin;
    JButton backButton;

    mini(String pin) {
        this.pin = pin;
        setTitle("Mini Statement");
        setLayout(null);

        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icn/Bank_Logo.png"));
        setIconImage(logo.getImage());

        JLabel title = new JLabel("SDPS Bank - Mini Statement");
        title.setFont(new Font("System", Font.BOLD, 24));
        title.setBounds(150, 20, 400, 30);
        add(title);

        String[] columnNames = {"Date", "Type", "Amount"};
        ArrayList<String[]> transactionList = new ArrayList<>();

        NumberFormat indianFormat = NumberFormat.getInstance(new Locale("en", "IN"));

        try {
            Con c = new Con();
            ResultSet rs = c.statement.executeQuery("SELECT date, type, amount FROM bank WHERE pin = '" + pin + "' ORDER BY STR_TO_DATE(date, '%d-%m-%Y %H:%i:%s') DESC");

            while (rs.next()) {
                String[] row = new String[3];
                row[0] = rs.getString("date");
                row[1] = rs.getString("type");
                row[2] = indianFormat.format(Double.parseDouble(rs.getString("amount")));
                transactionList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] data = transactionList.toArray(new String[0][]);

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 80, 600, 400);
        add(scrollPane);

        JButton downloadButton = new JButton("Download");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 16));
        downloadButton.setBounds(375, 500, 125, 30);
        downloadButton.addActionListener(e -> downloadPDF(data, columnNames));
        add(downloadButton);


        backButton = new JButton("BACK");
        backButton.setBounds(200, 500, 125, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        add(backButton);

        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void downloadPDF(String[][] data, String[] columnNames) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Mini Statement");
            fileChooser.setSelectedFile(new java.io.File("MiniStatement.pdf"));
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
                Paragraph title = new Paragraph("SDPS Bank - Mini Statement", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(new Paragraph("\n"));

                PdfPTable pdfTable = new PdfPTable(columnNames.length);
                pdfTable.setWidthPercentage(100);

                for (String columnName : columnNames) {
                    PdfPCell headerCell = new PdfPCell(new Phrase(columnName));
                    headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(headerCell);
                }

                for (String[] row : data) {
                    for (String cellData : row) {
                        pdfTable.addCell(cellData != null ? cellData : "");
                    }
                }

                document.add(pdfTable);
                document.close();
                JOptionPane.showMessageDialog(null, "Mini Statement downloaded successfully to: " + filePath);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while generating PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new mini("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        if (e.getSource() == backButton) {
            setVisible(false);  // Hide the current frame
            new Home(pin).setVisible(true); // Navigate back to the Home page and make it visible
        }
    }
}
