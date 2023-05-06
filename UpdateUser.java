package org.exploremore;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUser implements ActionListener {
    JFrame frame = new JFrame();
    JButton updateButton = new JButton("UPDATE");
    JTextField emailField = new JTextField();
    JLabel emailLabel = new JLabel("New Email:");
    JTextField fnameField = new JTextField();
    JLabel fnameLabel = new JLabel("New First Name:");
    JTextField lnameField = new JTextField();
    JLabel lnameLabel = new JLabel("New Last Name:");
    JPasswordField passwordField = new JPasswordField();
    JLabel passwordLabel = new JLabel("New Password:");
    JLabel messageLabel = new JLabel();

    String currentUserEmail;

    User currentUser;

    UpdateUser(User currentUser) {
        this.currentUser = currentUser;

        fnameLabel.setBounds(50, 50, 150, 25);
        fnameField.setBounds(200, 50, 200, 25);
        lnameLabel.setBounds(50, 90, 150, 25);
        lnameField.setBounds(200, 90, 200, 25);
        emailLabel.setBounds(50, 130, 150, 25);
        emailField.setBounds(200, 130, 200, 25);
        passwordLabel.setBounds(50, 170, 150, 25);
        passwordField.setBounds(200, 170, 200, 25);
        updateButton.setBounds(125, 210, 200, 25);
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        frame.add(fnameLabel);
        frame.add(fnameField);
        frame.add(lnameLabel);
        frame.add(lnameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(updateButton);
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String newFName = fnameField.getText().trim();
            String newLName = lnameField.getText().trim();
            String newEmail = emailField.getText().trim();
            String newPassword = new String(passwordField.getPassword()).trim();

            DatabaseController dbHandler = new DatabaseController();
            boolean success = dbHandler.updateUser(newFName, newLName, newEmail, newPassword, currentUser.getEmail());

            if (success) {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("User Updated");
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Update Failed");
            }
        }
    }
}