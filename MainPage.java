package em;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPage implements ActionListener{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame frame = new JFrame();
	JLabel logoLabel = new JLabel("ExploreMore");
	JButton filterButton = new JButton("Filters");
	JButton generateScheduleButton = new JButton("Generate Schedule");
	JButton curLocScheduleButton = new JButton("Generate Schedule w/ current location");
	JButton logoutButton = new JButton("Logout");
	JTextField searchTxtField = new JTextField();
	JLabel searchLabel = new JLabel("Search Location");
	
	MainPage(String userID){
		
	   
		logoLabel.setBounds(0,0,300,35);
		logoLabel.setFont(new Font(null,Font.PLAIN,35));
		logoLabel.setText("ExploreMore");
		
		// search bar for location
		searchLabel.setBounds(screenSize.width/4, 100, 400, 30);
		searchTxtField.setBounds((screenSize.width/4)+100, 100, 600, 30);
		
		// generate schedule button
		generateScheduleButton.setBounds((screenSize.width/2)-250,screenSize.height-250,250,100);
		generateScheduleButton.setFocusable(false);
		generateScheduleButton.addActionListener(this);
		
		// generate based on current location
		curLocScheduleButton.setBounds((screenSize.width/2),screenSize.height-250,250,100);
		curLocScheduleButton.setFocusable(false);
		curLocScheduleButton.addActionListener(this);
		
		// logout
		logoutButton.setBounds(0,50,100,30);
		logoutButton.setFocusable(false);
		logoutButton.addActionListener(this);
		
		// select filters
		filterButton.setBounds((screenSize.width/4)+700,100,100,30);
		filterButton.setFocusable(false);
		filterButton.addActionListener(this);
		
		
		frame.add(logoLabel);
		frame.add(filterButton);
		frame.add(searchLabel);
		frame.add(searchTxtField);
		frame.add(generateScheduleButton);
		frame.add(curLocScheduleButton);
		frame.add(logoutButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenSize.width, screenSize.height);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==filterButton) {
			// filter class should open screen with filter options
		}
		
		if(e.getSource()==generateScheduleButton) {
			
			// if search is
		}
		
	}

}
