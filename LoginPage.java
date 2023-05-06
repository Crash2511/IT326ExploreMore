package org.exploremore;
//Partial code and comments by Clay Remen
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class LoginPage implements ActionListener {
	//Initilization of UI elements
	JFrame frame = new JFrame();
	JLabel logLabel = new JLabel("Login");
	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("Register");
	JTextField userIDField = new JTextField();
	JButton resetPasswordButton = new JButton("ResetPW");
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	private int counter = 0;
	LoginPage() {
		//Implementation of UI elements

		logLabel.setBounds(0,0,300,35);
		logLabel.setFont(new Font(null,Font.PLAIN,30));
		logLabel.setText("Login");
		logLabel.setForeground(Color.white);
		logLabel.setOpaque(false);

		userIDLabel.setBounds(50, 100, 75, 25);
		userIDLabel.setForeground(Color.white);
		userPasswordLabel.setBounds(50, 140, 75, 25);
		userPasswordLabel.setForeground(Color.white);

		messageLabel.setBounds(125, 250, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 25));

		userIDField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 140, 200, 25);

		loginButton.setBounds(125, 200, 100, 25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		loginButton.setBackground(Color.black);
		loginButton.setForeground(Color.white);

		registerButton.setBounds(225, 200, 100, 25);
		registerButton.setFocusable(false);
		registerButton.addActionListener(this);
		registerButton.setBackground(Color.black);
		registerButton.setForeground(Color.white);

		resetPasswordButton.setBounds(125, 175, 100, 25);
		resetPasswordButton.setFocusable(false);
		resetPasswordButton.addActionListener(this);
		resetPasswordButton.setBackground(Color.black);
		resetPasswordButton.setForeground(Color.white);

		frame.add(logLabel);
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(registerButton);
		frame.add(resetPasswordButton);
		frame.getContentPane().setBackground(Color.darkGray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 330);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginButton) { //Checks to see whether user is within the database and logs them in if they are
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			User user = new User(null,null,userID,password);
			if (DatabaseController.validateUser(userID, password)) {
				messageLabel.setForeground(Color.green);
				messageLabel.setText("Login successful");
				MainPage mainPage = new MainPage(user);
				frame.dispose(); // Close the LoginPage frame

			} else {  //Begins a counter whenever a user enter the wrong values
				counter++;
				messageLabel.setBounds(10,250,1000,35);
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Invalid email or password");

			}
			if (counter >= 10){ //Once the counter gets up to 10 it performs and account lock where the user is no longer
				String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //allowed to log in and has their password randomized
				StringBuilder sb = new StringBuilder();
				Random random = new Random();
				String newPass = " ";
				for (int i=0; i <= 8; i++){
					int counter = random.nextInt(alphabet.length());
					char rand = alphabet.charAt(counter);
					sb.append(rand);
				}
				newPass = sb.toString();
				messageLabel.setBounds(10,250,1000,35);
				messageLabel.setText("Incorrect Login Account Locked.");
				boolean updatePass = DatabaseController.updateUser(null,null,null,newPass,userID);
				messageLabel.setText("User password has been changed please reset password" );
				loginButton.setEnabled(false);
			}
		}

		if (e.getSource() == registerButton) { //Takes the user to the register page
			RegisterPage registerPage = new RegisterPage();
			frame.dispose();
		}
		if (e.getSource() == resetPasswordButton) { //Takes the user to the resset password page
			ResetPassword resetPassword = new ResetPassword();
			frame.dispose();
		}
	}
}