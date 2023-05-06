package org.exploremore;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



public class ListOfPlaces implements ActionListener {

	JFrame frame = new JFrame();
	JLabel logLabel = new JLabel("Login");
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	HashMap<String,String> logininfo = new HashMap<String,String>();

	ListOfPlaces(HashMap<String,String> loginInfoOriginal){

		logininfo = loginInfoOriginal;

		logLabel.setBounds(175,50,300,35);
		logLabel.setFont(new Font(null,Font.PLAIN,30));
		logLabel.setText("Login");


		userIDLabel.setBounds(75,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);

		messageLabel.setBounds(150,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));

		userIDField.setBounds(150,100,200,25);
		userPasswordField.setBounds(150,150,200,25);

		loginButton.setBounds(150,200,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);

		resetButton.setBounds(150,200,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);

		frame.add(userIDLabel);
		frame.add(logLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setVisible(true);



//
//	    // Replace YOUR_API_KEY with your actual API key
//	    GeoApiContext context = new GeoApiContext.Builder()
//	        .apiKey("YOUR_API_KEY")
//	        .build();
//
//	    // Define the location and radius for the search
//	    LatLng location = new LatLng(37.7749, -122.4194); // San Francisco, CA
//	    int radius = 5000; // meters
//
//	    // Perform the nearby search for restaurants
//	    PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
//	        .radius(radius)
//	        .type(PlaceType.RESTAURANT)
//	        .rankby(RankBy.PROMINENCE)
//	        .await();
//
//	    // Filter the results to only include top-rated restaurants
//	    for (PlacesSearchResult result : response.results) {
//	        if (result.rating != null && result.rating >= 4.5) {
//	            System.out.println(result.name + " - Rating: " + result.rating);
//	        }
//	    }
//

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
		}

		if(e.getSource()==loginButton) {

			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			User user = new User(null,null,userID,password);
			if(logininfo.containsKey(userID)) {
				if(logininfo.get(userID).equals(password)) {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					frame.dispose();
					MainPage welcomePage = new MainPage(user);
				}
				else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Incorrect password");
				}

			}
			else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Email not found");
			}
		}
	}
}
