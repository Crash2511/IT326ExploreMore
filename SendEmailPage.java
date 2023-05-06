package org.exploremore;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class SendEmailPage {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Send Email");
    JLabel fromLabel = new JLabel("From: ");
    JLabel toLabel = new JLabel("To: ");
    JLabel subjectLabel = new JLabel("Subject: ");
    JLabel textLabel = new JLabel("Text: ");
    JTextField fromField = new JTextField();
    JTextField toField = new JTextField();
    JTextField subjectField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton sendButton = new JButton("Send");

    SendEmailPage() {
        label.setBounds(165, 20, 200, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        fromLabel.setBounds(50, 80, 80, 25);
        fromField.setBounds(120, 80, 200, 25);

        toLabel.setBounds(50, 120, 80, 25);
        toField.setBounds(120, 120, 200, 25);

        subjectLabel.setBounds(50, 160, 80, 25);
        subjectField.setBounds(120, 160, 200, 25);

        textLabel.setBounds(50, 200, 80, 25);
        textArea.setBounds(120, 200, 200, 100);

        sendButton.setBounds(170, 320, 80, 25);
        sendButton.addActionListener(e -> sendEmail());

        frame.add(label);
        frame.add(fromLabel);
        frame.add(fromField);
        frame.add(toLabel);
        frame.add(toField);
        frame.add(subjectLabel);
        frame.add(subjectField);
        frame.add(textLabel);
        frame.add(textArea);
        frame.add(sendButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void sendEmail() {
        String from = fromField.getText();
        String to = toField.getText();
        String subject = subjectField.getText();
        String text = textArea.getText();

        JavaMailSender mailSender = createMailSender();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);

        JOptionPane.showMessageDialog(frame, "Email sent successfully!");
    }

    private JavaMailSender createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("exploremore326@gmail.com"); // your Gmail username
        mailSender.setPassword("vsamkvsdgruljrqx"); // your Gmail password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public static void main(String[] args) {
        new SendEmailPage();
    }
}
