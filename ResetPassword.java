package org.exploremore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

class ResetPassword extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JLabel logLabel = new JLabel("Login");
    JButton resetPasswordButton = new JButton("Send Email");
    JButton nextButton = new JButton("Next");
    JTextField userEmailField = new JTextField();
    JLabel userEmailLabel = new JLabel("Email:");
    JButton submitButton = new JButton("Submit");
    JLabel messageLabel = new JLabel();
    JLabel messageLabel2 = new JLabel();
    HashMap<String,String> logininfo = new HashMap<String,String>();

    ResetPassword(HashMap<String,String> loginInfoOriginal) {
        logininfo = loginInfoOriginal;
        logLabel.setBounds(110,50,300,35);
        logLabel.setFont(new Font(null,Font.PLAIN,30));
        logLabel.setText("Reset Password");

        userEmailLabel.setBounds(60,175,75,25);

        messageLabel.setBounds(205,220,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,15));
        messageLabel.setForeground(Color.red);

        messageLabel2.setBounds(90,75,300,150);
        messageLabel2.setFont(new Font(null,Font.ITALIC,15));
        messageLabel2.setText("<html>Please enter in your email, if its in our <br> system  we'll send you an email with a code.</html>");


        userEmailField.setBounds(100,175,250,25);

        nextButton.setBounds(250, 200, 100, 25);
        nextButton.setFocusable(false);
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);

        resetPasswordButton.setBounds(100, 200, 100, 25);
        resetPasswordButton.setFocusable(false);
        resetPasswordButton.addActionListener(this);

        submitButton.setBounds(250, 200, 100, 25);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        submitButton.setVisible(false);

        frame.add(logLabel);
        frame.add(submitButton);
        frame.add(messageLabel);
        frame.add(messageLabel2);
        frame.add(userEmailLabel);
        frame.add(userEmailField);
        frame.add(resetPasswordButton);
        frame.add(nextButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Generate a random number
        int randomNumber = (int) (Math.random() * 1000000);
        String code = Integer.toString(randomNumber);
        if (e.getSource() == resetPasswordButton) {

            String senderEmail = "exploremore326@gmail.com";
            String senderPassword = "vsamkvsdgruljrqx";

            // Recipient's email address
            String userEmail = userEmailField.getText();
            nextButton.setEnabled(true);
            // Email properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Specify the TLS protocol version

            // Enable SSL debugging
            properties.put("mail.debug", "true");

            // Create a session with authentication
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {

                // Create a default MimeMessage object
                MimeMessage message = new MimeMessage(session);

                // Set the sender's email address
                message.setFrom(new InternetAddress(senderEmail));

                // Set the recipient's email address
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

                // Set the subject
                message.setSubject("Password Reset");

                // Set the content of the message
                String content = "Hello,\n\n";
                content += "Your password reset code is: " + randomNumber + "\n\n";
                content += "Please use this code to reset your password.\n\n";
                content += "Best regards,\n";
                content += "The explore more team";
                message.setText(content);

                // Send the email
                Transport.send(message);

                System.out.println("Password reset email sent successfully!");
            } catch (MessagingException d) {
                d.printStackTrace();
            }

        }
        if (e.getSource() == nextButton) {
            userEmailLabel.setText("Code: ");
            messageLabel2.setText("Please enter the code from your email.");
            userEmailField.setText("");
            nextButton.setVisible(false);
            submitButton.setVisible(true);
            submitButton.setEnabled(true);


        }
        if (e.getSource() == submitButton) {
            messageLabel.setText(code);
            userEmailField.setText("");
            userEmailLabel.setBounds(50,175,75,25);
            userEmailLabel.setText("New Password: ");
            submitButton.setText("Update Password");
        }
        else{
            messageLabel.setText("code is not the same");
        }
    }
}