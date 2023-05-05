package org.exploremore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ResetPassword extends JFrame implements ActionListener {
    JLabel emailLabel, newPasswordLabel, confirmNewPasswordLabel;
    final JTextField emailTextField, newPasswordTextField, confirmNewPasswordTextField;
    JButton submitButton;
    JPanel resetPasswordPanel;

    ResetPassword() {
        emailLabel = new JLabel(); //Takes user input of email address
        emailLabel.setText("Email:");
        emailTextField = new JTextField(30);
        newPasswordLabel = new JLabel();  //set a new password the user desires
        newPasswordLabel.setText("New Password:");
        newPasswordTextField = new JPasswordField(30);
        confirmNewPasswordLabel = new JLabel();   //confirm password, if don't match we will detect it
        confirmNewPasswordLabel.setText("Confirm New Password:");
        confirmNewPasswordTextField = new JPasswordField(30);
        submitButton = new JButton("Submit");  //submit the password
        resetPasswordPanel = new JPanel(new GridLayout(4, 2));   //All reset password related stuff
        resetPasswordPanel.add(emailLabel);
        resetPasswordPanel.add(emailTextField);
        resetPasswordPanel.add(newPasswordLabel);
        resetPasswordPanel.add(newPasswordTextField);
        resetPasswordPanel.add(confirmNewPasswordLabel);
        resetPasswordPanel.add(confirmNewPasswordTextField);
        resetPasswordPanel.add(submitButton);
        add(resetPasswordPanel, BorderLayout.CENTER); //the button gets added
        submitButton.addActionListener(this);
        setTitle("Reset Password"); //title for button
    }

    public void actionPerformed(ActionEvent ae) { //Actions that capture the users choice
        String emailValue = emailTextField.getText();
        String newPasswordValue = newPasswordTextField.getText();
        String confirmNewPasswordValue = confirmNewPasswordTextField.getText();
        if (newPasswordValue.equals(confirmNewPasswordValue)) {
            resetPassword(emailValue, newPasswordValue);
        }else{JOptionPane.showMessageDialog(null, "There is a discrepancy between the passwords, please fix now!");}
    }

    public void resetPassword(String email, String newPassword) {
        //This would handle the actual reset in our database (saving new data) edit at later date
        System.out.println("Enter email: "+email);
        System.out.println("Enter new password: "+newPassword);
    }
}
