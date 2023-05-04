package org.exploremore;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateUser implements ActionListener {

    JFrame frame = new JFrame();
    JButton updateButton = new JButton("Update my Information");
    JTextField newFNameField = new JTextField();
    JTextField newLNameField = new JTextField();
    JTextField newEmailField = new JTextField();
    JPasswordField newPasswordField = new JPasswordField();
    JLabel newFNameLabel = new JLabel("New First Name:");
    JLabel newLNameLabel = new JLabel("New Last Name:");
    JLabel newEmailLabel = new JLabel("New Email:");
    JLabel newPasswordLabel = new JLabel("New Password:");
    JLabel messageLabel = new JLabel();

    String userEmail;

    UpdateUser(String email) {
        userEmail = email;

        newFNameLabel.setBounds(50, 50, 150, 25);
        newLNameLabel.setBounds(50, 100, 150, 25);
        newEmailLabel.setBounds(50, 150, 150, 25);
        newPasswordLabel.setBounds(50, 200, 150, 25);

        messageLabel.setBounds(125, 300, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        newFNameField.setBounds(200, 50, 200, 25);
        newLNameField.setBounds(200, 100, 200, 25);
        newEmailField.setBounds(200, 150, 200, 25);
        newPasswordField.setBounds(200, 200, 200, 25);

        updateButton.setBounds(125, 250, 200, 25);
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);

        frame.add(newFNameLabel);
        frame.add(newLNameLabel);
        frame.add(newEmailLabel);
        frame.add(newPasswordLabel);
        frame.add(messageLabel);
        frame.add(newFNameField);
        frame.add(newLNameField);
        frame.add(newEmailField);
        frame.add(newPasswordField);
        frame.add(updateButton);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String newFName = newFNameField.getText().trim();
            String newLName = newLNameField.getText().trim();
            String newEmail = newEmailField.getText().trim();
            String newPassword = String.valueOf(newPasswordField.getPassword()).trim();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jdbcdemo", "explorer", "rhett"
                );

                String query = "UPDATE user SET fname = IFNULL(?, fname), lname = IFNULL(?, lname), email = IFNULL(?, email), password = IFNULL(?, password) WHERE email = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, newFName.isEmpty() ? null : newFName);
                statement.setString(2, newLName.isEmpty() ? null : newLName);
                statement.setString(3, newEmail.isEmpty() ? null : newEmail);
                statement.setString(4, newPassword.isEmpty() ? null : newPassword);
                statement.setString(5, userEmail);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("User information updated");
                    userEmail = newEmail.isEmpty() ? userEmail : newEmail;
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Update failed");
                }
                connection.close();
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}