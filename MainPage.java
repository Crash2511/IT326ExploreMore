package org.exploremore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
	String[] filters = {"restaurants", "museums", "gyms"};
	Boolean[] filterStatus = {false,false,false};
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
			// still need to get the filters
			String txt = searchTxtField.getText();
			String[] location = txt.split(" ");

			GenerateSchedule schedule = new GenerateSchedule();
			Location results = schedule.GenerateSchedule(location, filters, filterStatus);

			int[] randNum = new int[13];
			for(int i = 0; i < 13; i++) {
				Random randI = new Random();
				randNum[i] = randI.nextInt(results.i);;
			}

			String[] columnNames = {"Time", "Name", "Address"};

			Object[][] data = {
					{"8AM", results.name[randNum[0]], results.address[randNum[0]]},
					{"9AM", results.name[randNum[1]], results.address[randNum[1]]},
					{"10AM", results.name[randNum[2]], results.address[randNum[2]]},
					{"11AM", results.name[randNum[3]], results.address[randNum[3]]},
					{"12PM", results.name[randNum[4]], results.address[randNum[4]]},
					{"1PM", results.name[randNum[5]], results.address[randNum[5]]},
					{"2PM", results.name[randNum[6]], results.address[randNum[6]]},
					{"3AM", results.name[randNum[7]], results.address[randNum[7]]},
					{"4PM", results.name[randNum[8]], results.address[randNum[8]]},
					{"5PM", results.name[randNum[9]], results.address[randNum[9]]},
					{"6PM", results.name[randNum[10]], results.address[randNum[10]]},
					{"7PM", results.name[randNum[11]], results.address[randNum[11]]},
					{"8PM", results.name[randNum[12]], results.address[randNum[12]]}
			};
			JTable table = new JTable(data, columnNames);
			table.setBounds(screenSize.width/16, 140, 1200, 400);
			table.setRowHeight(30);
			frame.add(table);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}

	}

}
