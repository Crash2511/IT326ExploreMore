package org.exploremore;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ResetPassword implements ActionListener {
    JFrame frame = new JFrame();
    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField();
    JButton resetButton = new JButton("Reset Password");
    JButton backButton = new JButton("Back");
    JLabel messageLabel = new JLabel();

    ResetPassword() {
        emailLabel.setBounds(50, 100, 100, 25);
        emailField.setBounds(150, 100, 200, 25);
        resetButton.setBounds(150, 150, 200, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        backButton.setBounds(150, 200, 200, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(resetButton);
        frame.add(backButton);
        frame.add(messageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private boolean isEmailRegistered(String email) {
        boolean isRegistered = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbcdemo", "explorer", "rhett"
            );
            String selectSql = "SELECT * FROM user WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isRegistered = true;
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            String email = emailField.getText().trim();
            if (!email.isEmpty()) {
                if (isEmailRegistered(email)) {
                    messageLabel.setText("Email found, proceed with resetting password");
                    // TODO: Implement password reset functionality
                } else {
                    messageLabel.setText("Email not found");
                }
            } else {
                messageLabel.setText("Please enter an email");
            }
        }
        if (e.getSource() == backButton) {
            LoginPage loginPage = new LoginPage();
            frame.dispose(); // Close the ResetPassword frame
        }
    }
}
