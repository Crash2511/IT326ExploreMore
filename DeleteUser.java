package org.exploremore;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser implements ActionListener {
    JFrame frame = new JFrame();
    JButton deleteButton = new JButton("DELETE");
    JTextField emailField = new JTextField();
    JLabel emailLabel = new JLabel("Email:");
    JLabel messageLabel = new JLabel();

    DeleteUser() {
        emailLabel.setBounds(50, 50, 150, 25);
        messageLabel.setBounds(125, 150, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        emailField.setBounds(200, 50, 200, 25);
        deleteButton.setBounds(125, 100, 200, 25);
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(this);
        frame.add(emailLabel);
        frame.add(messageLabel);
        frame.add(emailField);
        frame.add(deleteButton);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 250);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String email = emailField.getText().trim();
            DatabaseController dbHandler = new DatabaseController();
            boolean success = dbHandler.deleteUser(email);
            if (success) {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("User Deleted");
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Failed");
            }
        }
    }
}