package org.exploremore;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LoginPage implements ActionListener {
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("Register");
	JTextField userIDField = new JTextField();
	JButton resetPasswordButton = new JButton("ResetPW");
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	JLabel titleLabel = new JLabel("Login");


	LoginPage() {
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
		resetPasswordButton.setBounds(125, 175, 100, 25);
		resetPasswordButton.setFocusable(false);
		resetPasswordButton.addActionListener(this);
		titleLabel.setBounds(180, 50, 60, 25);
		titleLabel.setFont(new Font(null, Font.BOLD, 20));
		frame.add(titleLabel);
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(resetPasswordButton);
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
			User user = new User(null,null,userID,password);
			if (DatabaseController.validateUser(userID, password)) {
				messageLabel.setForeground(Color.green);
				messageLabel.setText("Logging IN");
				MainPage mainPage = new MainPage(user);
				frame.dispose(); // Close the LoginPage frame
			} else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Invalid Info");
			}
		}

		if (e.getSource() == registerButton) {
			RegisterPage registerPage = new RegisterPage();
			frame.dispose();
		}
		if (e.getSource() == resetPasswordButton) {
			ResetPassword resetPassword = new ResetPassword();
			frame.dispose();
		}
	}
}
