package org.exploremore;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class RegisterPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JLabel regLabel = new JLabel("Register");

	JButton registerButton = new JButton("Register");
	JButton resetButton = new JButton("Reset");
	
	// if user has an existing - login
	JButton loginButton = new JButton("Login");
	
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JPasswordField userConfirmPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel userConfirmPasswordLabel = new JLabel("confirm password:");
	
	JLabel messageLabel = new JLabel();
	HashMap<String,String> registerinfo = new HashMap<String,String>();
	
	RegisterPage(HashMap<String,String> registerInfoOriginal){
		
		registerinfo = registerInfoOriginal;
		
		regLabel.setBounds(175,50,300,35);
		regLabel.setFont(new Font(null,Font.PLAIN,30));
		regLabel.setText("Register");
		
		userIDLabel.setBounds(75,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		userConfirmPasswordLabel.setBounds(50,200,75,25);
		
		messageLabel.setBounds(150,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		userIDField.setBounds(150,100,200,25);
		userPasswordField.setBounds(150,150,200,25);
		userConfirmPasswordField.setBounds(150,200,200,25);
		
		registerButton.setBounds(150,250,100,25);
		registerButton.setFocusable(false);
		registerButton.addActionListener(this);
		
		resetButton.setBounds(250,250,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		loginButton.setBounds(200,300,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		frame.add(userIDLabel);
		frame.add(regLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(userConfirmPasswordLabel);
		frame.add(userConfirmPasswordField);
		frame.add(registerButton);
		frame.add(resetButton);
		frame.add(loginButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
		}
		if(e.getSource()==registerButton) {
			// add user
		}
		if(e.getSource()==loginButton) {
			IDandPassword idandPassword = new IDandPassword();
			LoginPage loginPage = new LoginPage(idandPassword.getLoginInfo());
			
		}
	}	
}
