package org.exploremore;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginPage implements ActionListener {

	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("Register");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();

	HashMap<String, String> logininfo;

	LoginPage(HashMap<String, String> loginInfoOriginal) {
		logininfo = loginInfoOriginal;

		userIDLabel.setBounds(50, 100, 75, 25);
		userPasswordLabel.setBounds(50, 140, 75, 25);

		messageLabel.setBounds(125, 250, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 25));

		userIDField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 140, 200, 25);

		loginButton.setBounds(125, 200, 100, 25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);

		registerButton.setBounds(225, 200, 100, 25);
		registerButton.setFocusable(false);
		registerButton.addActionListener(this);

		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(registerButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 330);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(
						//jdbcdemo=database name.  explorer=username for admin.   rhett=password for admin    localhost=connection
						"jdbc:mysql://localhost:3306/jdbcdemo", "explorer", "rhett"
				);

				String query = "SELECT * FROM user WHERE email = ? AND password = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, userID);
				statement.setString(2, password);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					MainPage mainPage = new MainPage(userID);
					frame.dispose(); // Close the RegisterPage frame
				} else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Invalid email or password");
				}
				connection.close();
			} catch (ClassNotFoundException | SQLException ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource() == registerButton) {
			IDandPassword idandPassword = new IDandPassword();
			RegisterPage registerPage = new RegisterPage(idandPassword.getLoginInfo());
			frame.dispose(); // Close the LoginPage frame
		}
	}
}
