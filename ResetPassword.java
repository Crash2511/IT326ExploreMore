package org.exploremore;
//Program and comments by Clay Remen.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

class ResetPassword extends JFrame implements ActionListener {
    //Initilization of UI elements
    JFrame frame = new JFrame();
    JButton resetPasswordButton = new JButton("Send Email");
    JButton nextButton = new JButton("Next");
    JButton loginButton = new JButton("Login");
    JButton submitButton = new JButton("Submit");
    JTextField userEmailField = new JTextField();
    JTextField userPasswordField = new JTextField();
    JLabel userEmailLabel = new JLabel("Email:");
    JLabel passwordConfirmationLabel = new JLabel("Confirm Password: ");
    JLabel logLabel = new JLabel("Login");
    JLabel messageLabel = new JLabel();
    JLabel messageLabel2 = new JLabel();
    HashMap<String,String> logininfo = new HashMap<String,String>();
    // Generate a random number and store it as a string for the email verification
    int randomNumber = (int) (Math.random() * 1000000);
    String code = Integer.toString(randomNumber);
    String userEmail = " ";

    ResetPassword() {
        //Implementation of UI elements
        logLabel.setBounds(110,50,300,35);
        logLabel.setFont(new Font(null,Font.PLAIN,30));
        logLabel.setForeground(Color.white);
        logLabel.setText("Reset Password");

        userEmailLabel.setBounds(65,175,250,25);
        userEmailLabel.setFont(new Font(null,Font.ITALIC,15));
        userEmailLabel.setForeground(Color.white);

        userEmailField.setBounds(110,175,250,25);


        userPasswordField.setBounds(110,195,250,25);

        passwordConfirmationLabel.setBounds(0,200,125,25);
        passwordConfirmationLabel.setFont(new Font(null,Font.ITALIC,15));
        passwordConfirmationLabel.setForeground(Color.white);



        messageLabel.setBounds(205,220,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,15));
        messageLabel.setForeground(Color.white);

        messageLabel2.setBounds(90,75,300,150);
        messageLabel2.setFont(new Font(null,Font.ITALIC,15));
        messageLabel2.setForeground(Color.white);
        messageLabel2.setText("<html>Please enter in your email, if its in our <br> system  we'll send you an email with a code.</html>");



        nextButton.setBounds(260, 200, 100, 25);
        nextButton.setFocusable(false);
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);
        nextButton.setBackground(Color.black);
        nextButton.setForeground(Color.white);

        resetPasswordButton.setBounds(110, 200, 100, 25);
        resetPasswordButton.setFocusable(false);
        resetPasswordButton.addActionListener(this);
        resetPasswordButton.setBackground(Color.black);
        resetPasswordButton.setForeground(Color.white);

        loginButton.setBounds(75, 400, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.black);
        loginButton.setForeground(Color.white);
        loginButton.setVisible(false);

        submitButton.setBounds(260, 200, 100, 25);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        submitButton.setVisible(false);
       submitButton.setBackground(Color.black);
        submitButton.setForeground(Color.white);

        frame.add(logLabel);
        frame.add(submitButton);
        frame.add(messageLabel);
        frame.add(messageLabel2);
        frame.add(userEmailLabel);
        frame.add(userEmailField);
        frame.add(resetPasswordButton);
        frame.add(nextButton);
        frame.add(loginButton);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetPasswordButton) { //Method for sending an email to user for reset code
            String senderEmail = "exploremore326@gmail.com";
            String senderPassword = "vsamkvsdgruljrqx";
            String userEmail = userEmailField.getText();
            nextButton.setEnabled(true);

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("exploremoreIT@gmail.com"); // your Gmail username
            mailSender.setPassword("avxbugzltgtvlgdb"); // your Gmail password

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderEmail);
            mailMessage.setTo(userEmail);
            mailMessage.setSubject("Password Reset");

            String content = "Hello,\n\n";
            content += "Your password reset code is: " + randomNumber + "\n\n";
            content += "Please use this code to reset your password.\n\n";
            content += "Best regards,\n";
            content += "The explore more team";
            mailMessage.setText(content);

            mailSender.send(mailMessage);
            System.out.println("Password reset email sent successfully!");


        }
        if (e.getSource() == nextButton) { // Takes the user to the next page to enter in their verification code
            userEmailLabel.setText("Code: ");
            messageLabel2.setText("Please enter the code from your email.");
            userEmailField.setText("");
            nextButton.setVisible(false);
            submitButton.setVisible(true);
            submitButton.setEnabled(true);
            resetPasswordButton.setEnabled(false);


        }
        if (e.getSource() == submitButton && code.equals(userEmailField.getText())) {// Takes user to window to fully reset their password
            userEmailField.setText("");
            messageLabel2.setVisible(false);
            userEmailLabel.setBounds(0,175,125,25);
            userEmailLabel.setText("New Password: ");
            frame.add(userPasswordField);
            frame.add(passwordConfirmationLabel);
            submitButton.setBounds(260, 225, 100, 25);
            resetPasswordButton.setBounds(110, 225, 100, 25);
            submitButton.setText("Update Password");

        }

        if(e.getSource() == submitButton && userEmailField.getText().equals(userPasswordField.getText())){//If the user enters a valid password twice it will be added to the databasee

            boolean updatePass = DatabaseController.updateUser(null,null,null,userEmailField.getText(),userEmail);
            loginButton.setVisible(true);


        }
        if (e.getSource() == loginButton){// Takes user back to login page and closes previous resetpassword page
            LoginPage loginPage = new LoginPage();
            frame.dispose();
        }
    }
}