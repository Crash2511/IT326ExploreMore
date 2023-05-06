package org.exploremore;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class ScheduledReminder {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Send Email");
    JLabel fromLabel = new JLabel("From: ");
    JLabel toLabel = new JLabel("To: ");
    JLabel subjectLabel = new JLabel("Subject: ");
    JLabel textLabel = new JLabel("Text: ");
    JLabel timeLabel = new JLabel("Time in seconds");
    JTextField fromField = new JTextField();
    JTextField toField = new JTextField();
    JTextField subjectField = new JTextField();
    JTextArea textArea = new JTextArea();
    JTextField timeField = new JTextField();
//    JButton sendButton = new JButton("Send Now");
    JButton scheduleButton = new JButton("Schedule Email");

    ScheduledReminder() {
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

        timeLabel.setBounds(10, 320, 100, 25);
        timeField.setBounds(120, 320, 180, 25);

//        sendButton.setBounds(120, 460, 100, 25);
//        sendButton.addActionListener(e -> sendEmail());

        scheduleButton.setBounds(120, 350, 180, 25);
        scheduleButton.addActionListener(e -> scheduleEmail());

        frame.add(label);
        frame.add(fromLabel);
        frame.add(toLabel);
        frame.add(subjectLabel);
        frame.add(textLabel);
        frame.add(timeLabel);
        frame.add(fromField);
        frame.add(toField);
        frame.add(subjectField);
        frame.add(textArea);
        frame.add(timeField);
//        frame.add(sendButton);
        frame.add(scheduleButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

//    private void sendEmail() {
//        String from = fromField.getText();
//        String to = toField.getText();
//        String subject = subjectField.getText();
//        String text = textArea.getText();
//
//        JavaMailSender mailSender = createMailSender();
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(from);
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(text);
//
//        mailSender.send(mailMessage);
//
//        JOptionPane.showMessageDialog(frame, "Email sent successfully!");
//    }

    public void scheduleEmail() {
        String from = fromField.getText();
        String to = toField.getText();
        String subject = subjectField.getText();
        String text = textArea.getText();
        String time = timeField.getText();

        long timeLeft = 0;
        try {
            int seconds = Integer.parseInt(time);
            timeLeft = seconds * 1000L;
            if (timeLeft <= 0) {
                JOptionPane.showMessageDialog(frame, "Scheduled time must be in the future.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid time format. Please enter time in seconds.");
            return;

        }

        timeLabel.setText("Time to send: " + (timeLeft / 1000) + " seconds");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JavaMailSender mailSender = createMailSender();

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(from);
                mailMessage.setTo(to);
                mailMessage.setSubject(subject);
                mailMessage.setText(text);

                mailSender.send(mailMessage);

                JOptionPane.showMessageDialog(frame, "Email sent successfully!");
            }
        }, timeLeft);
    }



    private JavaMailSender createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("exploremore326@gmail.com"); // your Gmail username
        mailSender.setPassword("vsamkvsdgruljrqx"); // your Gmail password

        mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
        mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    public static void main(String[] args) {
        new ScheduledReminder();
    }
}

