package com.demo.Util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {

    private static final String HOST     = "smtp.gmail.com";
    private static final String PORT     = "587";
    private static final String FROM     = "coldstorage@yourdomain.com"; // Change this
    private static final String PASSWORD = "your_email_password";         // Use App Password

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });
    }

    public static void sendPasswordResetEmail(String toEmail, String resetLink) {
        try {
            Session session = createSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Cold Storage System – Password Reset Request");
            message.setText(
                "Hello,\n\n"
                + "You have requested to reset your password for the Cold Storage Management System.\n\n"
                + "Click the link below to reset your password (valid for 1 hour):\n"
                + resetLink + "\n\n"
                + "If you did not request this, please ignore this email.\n\n"
                + "Regards,\nCold Storage Management Team"
            );
            Transport.send(message);
            System.out.println("Password reset email sent to: " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    public static void sendExpiryAlertEmail(String toEmail, String productName, String expiryDate) {
        try {
            Session session = createSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("⚠ Expiry Alert: " + productName);
            message.setText(
                "Dear Manager,\n\n"
                + "This is an automated alert from the Cold Storage Management System.\n\n"
                + "Product: " + productName + "\n"
                + "Expiry Date: " + expiryDate + "\n\n"
                + "Please take immediate action to dispose or transfer this product.\n\n"
                + "Regards,\nCold Storage Management System"
            );
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Failed to send expiry alert: " + e.getMessage());
        }
    }
}
