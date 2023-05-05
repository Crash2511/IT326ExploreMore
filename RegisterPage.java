package org.exploremore;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterPage implements ActionListener {
	private boolean areFieldsFilled() {
		return !firstNameField.getText().trim().isEmpty() &&
				!lastNameField.getText().trim().isEmpty() &&
				!userIDField.getText().trim().isEmpty() &&
				userPasswordField.getPassword().length > 0 &&
				userConfirmPasswordField.getPassword().length > 0;
	}
	JFrame frame = new JFrame();
	JLabel regLabel = new JLabel("Register");
	JButton registerButton = new JButton("Register");


	JButton resetButton = new JButton("Clear");
	// if user has an existing - login
	JButton loginButton = new JButton("Login");
	JTextField firstNameField = new JTextField();
	JTextField lastNameField = new JTextField();
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JPasswordField userConfirmPasswordField = new JPasswordField();
	JLabel firstNameLabel = new JLabel("First Name:");
	JLabel lastNameLabel = new JLabel("Last Name:");
	JLabel userIDLabel = new JLabel("Email:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel userConfirmPasswordLabel = new JLabel("Confirm:");
	JLabel messageLabel = new JLabel();
	HashMap<String,String> registerinfo = new HashMap<String,String>();
	RegisterPage(){
		regLabel.setBounds(175, 10, 300, 35);
		regLabel.setFont(new Font(null, Font.PLAIN, 30));
		regLabel.setText("Register");

		firstNameLabel.setBounds(50, 60, 100, 25);
		lastNameLabel.setBounds(50, 100, 100, 25);
		userIDLabel.setBounds(50, 140, 75, 25);
		userPasswordLabel.setBounds(50, 180, 75, 25);
		userConfirmPasswordLabel.setBounds(50, 220, 150, 25);

		messageLabel.setBounds(125, 300, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 25));

		firstNameField.setBounds(150, 60, 200, 25);
		lastNameField.setBounds(150, 100, 200, 25);
		userIDField.setBounds(150, 140, 200, 25);
		userPasswordField.setBounds(150, 180, 200, 25);
		userConfirmPasswordField.setBounds(200, 220, 150, 25);

		registerButton.setBounds(150, 260, 100, 25);
		registerButton.setFocusable(false);
		registerButton.addActionListener(this);

		resetButton.setBounds(250, 260, 100, 25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);

		loginButton.setBounds(200, 285, 100, 25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);

		frame.add(firstNameLabel);
		frame.add(lastNameLabel);
		frame.add(userIDLabel);
		frame.add(regLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(firstNameField);
		frame.add(lastNameField);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(userConfirmPasswordLabel);
		frame.add(userConfirmPasswordField);
		frame.add(registerButton);
		frame.add(resetButton);

		frame.add(loginButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	private boolean isEmailAlreadyRegistered(String email) {
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
			firstNameField.setText("");
			lastNameField.setText("");
			userIDField.setText("");
			userPasswordField.setText("");
			userConfirmPasswordField.setText("");
		}
		if (e.getSource() == registerButton) {
			if (areFieldsFilled()) {
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String userID = userIDField.getText();
				String password = new String(userPasswordField.getPassword());
				String confirmPassword = new String(userConfirmPasswordField.getPassword());
				if (isEmailAlreadyRegistered(userID)) {
					messageLabel.setText("Already Exists");
				} else {
					if (confirmPassword.equals(password)) {
						User user = new User(firstName, lastName, userID, password);
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection connection = DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/jdbcdemo", "explorer", "rhett"
							);
							Statement statement = connection.createStatement();
							String insertSql = "INSERT INTO user (fname, lname, email, password) VALUES ('"
									+ firstName + "', '"
									+ lastName + "', '"
									+ userID + "', '"
									+ password + "')";
							statement.executeUpdate(insertSql);
							messageLabel.setText("Registered");
							ResultSet resultSetAfterUpdate = statement.executeQuery("SELECT * FROM user");
							System.out.println(resultSetAfterUpdate.getString(1) + " " + resultSetAfterUpdate.getString(2) + " " + resultSetAfterUpdate.getString(3) + " " + resultSetAfterUpdate.getString(4));
							connection.close();
						} catch (Exception exc) {
							System.out.println(exc);
						}
					} else {
						messageLabel.setText("Check Password"); //passwords dont match
					}
				}
			}else{
				messageLabel.setText("Empty Fields!"); //for if user forgets to put all info in
			}
		}
		if (e.getSource() == loginButton) {
			LoginPage loginPage = new LoginPage();
			frame.dispose(); // Close the RegisterPage frame
		}
	}
}
