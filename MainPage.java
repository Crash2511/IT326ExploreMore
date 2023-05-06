package org.exploremore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class MainPage implements ActionListener{

	boolean generated = false;
	public Object[][] fileData;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame frame = new JFrame();
	JLabel logoLabel = new JLabel("ExploreMore");
	//JButton filterButton = new JButton("Filters");
	JLabel logLabel = new JLabel("Filters");
	JButton generateScheduleButton = new JButton("Generate Schedule");
	JButton curLocScheduleButton = new JButton("Generate Schedule w/ current location");
	JButton logoutButton = new JButton("Logout");

	JButton exportButton = new JButton("Export");
	JTextField searchTxtField = new JTextField();
	JLabel searchLabel = new JLabel("Search Location");
	JCheckBox restaurants = new JCheckBox("Restaurant", false);

	JCheckBox museum = new JCheckBox("Museum",false);
	JCheckBox gym = new JCheckBox("Gym",false);
	JCheckBox zoo = new JCheckBox("Zoo",false);
	JCheckBox bar = new JCheckBox("Bar",false);
	JCheckBox beach = new JCheckBox("Beach",false);
	JCheckBox park = new JCheckBox("Park",false);
	JCheckBox landmark = new JCheckBox("Landmark",false);

	String[] filters = {"Restaurant", "Museum", "Gym", "Zoo", "Bar", "Beach", "Park", "Landmark"};
	Boolean[] filterStatus ={false, false, false, false, false, false, false, false};


	MainPage(String userID){


		exportButton.setBounds((screenSize.width/2)-500,screenSize.height-250,250,100);
		exportButton.setFocusable(false);
		exportButton.addActionListener(this);


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
		logLabel.setBounds(1600,50,300,35);
		logLabel.setFont(new Font(null,Font.PLAIN, 30));
		logLabel.setText("Filters");

		restaurants.setBounds(1600, 110, 100, 30);
		restaurants.setFocusable(false);
		restaurants.addItemListener(this::itemStateChanged);

		gym.setBounds(1600, 150, 100, 30);
		gym.setFocusable(false);
		gym.addItemListener(this::itemStateChanged);

		museum.setBounds(1600, 190, 100, 30);
		museum.setFocusable(false);
		museum.addItemListener(this::itemStateChanged);

		zoo.setBounds(1600, 230, 100, 30);
		zoo.setFocusable(false);
		zoo.addItemListener(this::itemStateChanged);

		bar.setBounds(1600, 270, 100, 30);
		bar.setFocusable(false);
		bar.addItemListener(this::itemStateChanged);

		beach.setBounds(1600, 310, 100, 30);
		beach.setFocusable(false);
		beach.addItemListener(this::itemStateChanged);

		park.setBounds(1600, 350, 100, 30);
		park.setFocusable(false);
		park.addItemListener(this::itemStateChanged);

		landmark.setBounds(1600, 390, 100, 30);
		landmark.setFocusable(false);
		landmark.addItemListener(this::itemStateChanged);

		frame.add(logoLabel);
		//frame.add(filterButton);
		frame.add(logLabel);
		frame.add(restaurants);
		frame.add(gym);
		frame.add(museum);
		frame.add(zoo);
		frame.add(bar);
		frame.add(beach);
		frame.add(park);
		frame.add(landmark);
		frame.add(searchLabel);
		frame.add(searchTxtField);
		frame.add(generateScheduleButton);
		frame.add(curLocScheduleButton);
		frame.add(logoutButton);
		frame.add(exportButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenSize.width, screenSize.height);
		frame.setLayout(null);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==generateScheduleButton) {
			// still need to get the filters
			String txt = searchTxtField.getText();
			String[] location = txt.split(" ");

			GenerateSchedule schedule = new GenerateSchedule();
			Location results = schedule.GenerateSchedule(location, filters, filterStatus);

			int randNum[] = new int[15];
			for(int i = 0; i < 15; i++) {
				Random randI = new Random();
				randNum[i] = randI.nextInt(results.i);
			}

			String[] columnNames = {"Time", "Name", "Address"};

			Object[][] data = {
					{"8AM", results.name[randNum[0]]+" ["+results.type[randNum[0]]+"]"+" "+results.rating[randNum[0]], results.address[randNum[0]]},
					{"9AM", results.name[randNum[1]]+" ["+results.type[randNum[1]]+"]", results.address[randNum[1]]},
					{"10AM", results.name[randNum[2]]+" ["+results.type[randNum[2]]+"]", results.address[randNum[2]]},
					{"11AM", results.name[randNum[3]]+" ["+results.type[randNum[3]]+"]", results.address[randNum[3]]},
					{"12PM", results.name[randNum[4]]+" ["+results.type[randNum[4]]+"]", results.address[randNum[4]]},
					{"1PM", results.name[randNum[5]]+" ["+results.type[randNum[5]]+"]", results.address[randNum[5]]},
					{"2PM", results.name[randNum[6]]+" ["+results.type[randNum[6]]+"]", results.address[randNum[6]]},
					{"3AM", results.name[randNum[7]]+" ["+results.type[randNum[7]]+"]", results.address[randNum[7]]},
					{"4PM", results.name[randNum[8]]+" ["+results.type[randNum[8]]+"]", results.address[randNum[8]]},
					{"5PM", results.name[randNum[9]]+" ["+results.type[randNum[9]]+"]", results.address[randNum[9]]},
					{"6PM", results.name[randNum[10]]+" ["+results.type[randNum[10]]+"]", results.address[randNum[10]]},
					{"7PM", results.name[randNum[11]]+" ["+results.type[randNum[11]]+"]", results.address[randNum[11]]},
					{"8PM", results.name[randNum[12]]+" ["+results.type[randNum[12]]+"]", results.address[randNum[12]]}
			};
			this.fileData = data;
			generated = true;

			JTable table = new JTable(data, columnNames);
			table.setBounds(screenSize.width/16, 140, 1400, 500);
			table.setRowHeight(30);
			frame.add(table);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}

		if(generated){
			if(e.getSource()==exportButton) {
				Export ex = new Export(this.fileData);
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new ExportController(fileData).setVisible(true);
					}
				});

			}
		}


	}
	public void itemStateChanged(ItemEvent e){
		if (e.getSource() == gym) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[2] = true;
			}
			else{
				filterStatus[2] = false;
			}
		}
		if (e.getSource() == restaurants) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[0] = true;
			}
			else{
				filterStatus[0] = false;
			}
		}
		if (e.getSource() == museum) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[1] = true;
			}
			else{
				filterStatus[1] = false;
			}
		}
		if (e.getSource() == zoo) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[3] = true;
			}
			else{
				filterStatus[3] = false;
			}
		}
		if (e.getSource() == bar) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[4] = true;
			}
			else{
				filterStatus[4] = false;
			}
		}
		if (e.getSource() == beach) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[5] = true;
			}
			else{
				filterStatus[5] = false;
			}
		}
		if (e.getSource() == park) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[6] = true;
			}
			else{
				filterStatus[6] = false;
			}
		}
		if (e.getSource() == landmark) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[7] = true;
			}
			else{
				filterStatus[7] = false;
			}
		}


	}
}
