//package bank.management.system;
//
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.Properties;
//
//public class EmailNotificationService {
//
//    public static void sendEmail(String to, String subject, String body) {
//        // Sender's email credentials
//        final String senderEmail = "spdsbank@gmail.com"; // Replace with your email
//        final String senderPassword = "eumw fpnf twyn chip"; // Replace with your email password
//
//        // Email server configuration
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        // Create session with authenticator
//        Session session = Session.getInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(senderEmail, senderPassword);
//            }
//        });
//
//        try {
//            // Create the email message
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(senderEmail)); // Sender's email
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Recipient's email
//            message.setSubject(subject); // Email subject
//            message.setText(body); // Email body
//
//            // Send the email
//            Transport.send(message);
//            System.out.println("Email sent successfully to " + to);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//}


package bank.management.system;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailNotificationService {

    public static void sendEmail(String to, String subject, String body) {
        // Sender's email credentials
        final String senderEmail = "spdsbank@gmail.com"; // Replace with your email
        final String senderPassword = "eumw fpnf twyn chip"; // Replace with your email password

        // Email server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session with authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail)); // Sender's email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Recipient's email
            message.setSubject(subject); // Email subject

            // Enable HTML content
            message.setContent(body, "text/html; charset=utf-8");

            // Send the email
            Transport.send(message);
            System.out.println("HTML Email sent successfully to " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
