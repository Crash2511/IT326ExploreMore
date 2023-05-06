package org.exploremore;

import org.checkerframework.checker.units.qual.C;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MainPage implements ActionListener{
	User user;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame frame = new JFrame();
	JLabel logoLabel = new JLabel("ExploreMore");
	//JButton filterButton = new JButton("Filters");
	JLabel logLabel = new JLabel("Filters");
	JButton generateScheduleButton = new JButton("Generate Schedule");
	JButton createAvailability = new JButton("Create Availability");
	JButton listViewButton = new JButton("List View");
	JButton logoutButton = new JButton("Logout");
	JButton updateUserButton = new JButton("UPDATE");
	JButton deleteUserButton = new JButton("DELETE");
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
	String[] availabilityTimes = {"8AM","9AM","10AM","11AM","12PM","1PM","2PM","3PM","4PM","5PM","6PM","7PM","8PM"};
	Boolean[] availabilityCheck ={true,true,true,true,true,true,true,true,true,true,true,true,true};
	String[] filters = {"Restaurant", "Museum", "Gym", "Zoo", "Bar", "Beach", "Park", "Landmark"};
	Boolean[] filterStatus ={false, false, false, false, false, false, false, false};


	MainPage(User user){
		this.user = user;
		//shows whos logged in
		logoLabel.setBounds(0,0,300,35);
		logoLabel.setFont(new Font(null,Font.PLAIN,15));
		logoLabel.setText(user.getEmail());
		logoLabel.setForeground(Color.white);

		// search bar for location
		searchLabel.setBounds(screenSize.width/4, 100, 400, 30);
		searchLabel.setForeground(Color.white);
		searchTxtField.setBounds((screenSize.width/4)+100, 100, 600, 30);

		// generate schedule button
		generateScheduleButton.setBounds((screenSize.width/2)-250,screenSize.height-250,250,100);
		generateScheduleButton.setFocusable(false);
		generateScheduleButton.addActionListener(this);
		generateScheduleButton.setBackground(Color.black);
		generateScheduleButton.setForeground(Color.white);

		createAvailability.setBounds((screenSize.width/2),screenSize.height-250,250,100);
		createAvailability.setFocusable(false);
		createAvailability.addActionListener(this);
		createAvailability.setForeground(Color.white);
		createAvailability.setBackground(Color.BLACK);

		//list view button
		listViewButton.setBounds((screenSize.width/2)-450, screenSize.height-250, 200, 100);
		listViewButton.setFocusable(false);
		listViewButton.addActionListener(this);
		listViewButton.setBackground(Color.black);
		listViewButton.setForeground(Color.white);


		// logout
		logoutButton.setBounds(0,50,100,30);
		logoutButton.setFocusable(false);
		logoutButton.addActionListener(this);
		logoutButton.setBackground(Color.black);
		logoutButton.setForeground(Color.white);

		// update user
		updateUserButton.setBounds(0,80,100,30);
		updateUserButton.setFocusable(false);
		updateUserButton.addActionListener(this);
		updateUserButton.setBackground(Color.black);
		updateUserButton.setForeground(Color.white);
		// delete user
		deleteUserButton.setBounds(0,110,100,30);
		deleteUserButton.setFocusable(false);
		deleteUserButton.addActionListener(this);
		deleteUserButton.setBackground(Color.black);
		deleteUserButton.setForeground(Color.white);

		// select filters
		logLabel.setBounds((screenSize.width/2)+670,50,300,35);
		logLabel.setFont(new Font(null,Font.PLAIN, 30));
		logLabel.setText("Filters");
		logLabel.setForeground(Color.white);
		logLabel.setOpaque(false);

		restaurants.setBounds((screenSize.width/2)+670, 110, 100, 30);
		restaurants.setFocusable(false);
		restaurants.addItemListener(this::itemStateChanged);
		restaurants.setOpaque(false);
		restaurants.setForeground(Color.white);

		gym.setBounds((screenSize.width/2)+670, 150, 100, 30);
		gym.setFocusable(false);
		gym.addItemListener(this::itemStateChanged);
		gym.setOpaque(false);
		gym.setForeground(Color.white);

		museum.setBounds((screenSize.width/2)+670, 190, 100, 30);
		museum.setFocusable(false);
		museum.addItemListener(this::itemStateChanged);
		museum.setOpaque(false);
		museum.setForeground(Color.white);

		zoo.setBounds((screenSize.width/2)+670, 230, 100, 30);
		zoo.setFocusable(false);
		zoo.addItemListener(this::itemStateChanged);
		zoo.setOpaque(false);
		zoo.setForeground(Color.white);

		bar.setBounds((screenSize.width/2)+670, 270, 100, 30);
		bar.setFocusable(false);
		bar.addItemListener(this::itemStateChanged);
		bar.setOpaque(false);
		bar.setForeground(Color.white);

		beach.setBounds((screenSize.width/2)+670, 310, 100, 30);
		beach.setFocusable(false);
		beach.addItemListener(this::itemStateChanged);
		beach.setOpaque(false);
		beach.setForeground(Color.white);

		park.setBounds((screenSize.width/2)+670, 350, 100, 30);
		park.setFocusable(false);
		park.addItemListener(this::itemStateChanged);
		park.setOpaque(false);
		park.setForeground(Color.white);

		landmark.setBounds((screenSize.width/2)+670, 390, 100, 30);
		landmark.setFocusable(false);
		landmark.addItemListener(this::itemStateChanged);
		landmark.setOpaque(false);
		landmark.setForeground(Color.white);

		frame.add(logoLabel);
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
		frame.add(createAvailability);
		frame.add(listViewButton);
		frame.add(logoutButton);
		frame.add(updateUserButton);
		frame.add(deleteUserButton);
		frame.getContentPane().setBackground(Color.darkGray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenSize.width, screenSize.height);
		frame.setLayout(null);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==createAvailability) {
			// filter class should open screen with filter options

			CreateAvailability availability = new CreateAvailability(availabilityCheck);
			availabilityCheck = availability.getAvailability();
			createAvailability.setText("Edit Availability");
		}

		if(e.getSource()==generateScheduleButton) {
			// still need to get the filters
			String txt = searchTxtField.getText();
			String[] location = txt.split(" ");

			GenerateSchedule schedule = new GenerateSchedule();
			Location results = schedule.GenerateSchedule(location, filters, filterStatus);

			int[] randNum = new int[13];
			int size = results.i;

			ArrayList<Integer> list = new ArrayList<Integer>(size);
			for(int i = 0; i < size; i++) {
				list.add(i);
			}
			Collections.shuffle(list);
			Random rand = new Random();
			for(int i = 0; i < 13; i++) {
				randNum[i] = list.remove(0);
				if(availabilityCheck[i] == false) {
					results.name[randNum[i]] = "X";
					results.type[randNum[i]] = "X";
					results.rating[randNum[i]] = 0.0;
					results.address[randNum[i]] = "X";
				}
			}
			String[] columnNames = {"Time", "Name", "Address", "Type", "Rating"};
			Object[][] data = {
					{"Time", "Name", "Address", "Type", "Rating"},
					{"8AM", results.name[randNum[0]], results.address[randNum[0]], results.type[randNum[0]], results.rating[randNum[0]] },
					{"9AM", results.name[randNum[1]], results.address[randNum[1]], results.type[randNum[1]], results.rating[randNum[1]] },
					{"10AM", results.name[randNum[2]], results.address[randNum[2]], results.type[randNum[2]], results.rating[randNum[2]] },
					{"11AM", results.name[randNum[3]], results.address[randNum[3]], results.type[randNum[3]], results.rating[randNum[3]] },
					{"12PM", results.name[randNum[4]], results.address[randNum[4]], results.type[randNum[4]], results.rating[randNum[4]] },
					{"1PM", results.name[randNum[5]], results.address[randNum[5]], results.type[randNum[5]], results.rating[randNum[5]] },
					{"2PM", results.name[randNum[6]], results.address[randNum[6]], results.type[randNum[6]], results.rating[randNum[6]] },
					{"3PM", results.name[randNum[7]], results.address[randNum[7]], results.type[randNum[7]], results.rating[randNum[7]] },
					{"4PM", results.name[randNum[8]], results.address[randNum[8]], results.type[randNum[8]], results.rating[randNum[8]] },
					{"5PM", results.name[randNum[9]], results.address[randNum[9]], results.type[randNum[9]], results.rating[randNum[9]] },
					{"6PM", results.name[randNum[10]], results.address[randNum[10]], results.type[randNum[10]], results.rating[randNum[10]] },
					{"7PM", results.name[randNum[11]], results.address[randNum[11]], results.type[randNum[11]], results.rating[randNum[11]] },
					{"8PM", results.name[randNum[12]], results.address[randNum[12]], results.type[randNum[12]], results.rating[randNum[12]] },

			};
			JTable standard;
			DefaultTableModel model;
			model = new DefaultTableModel(data, columnNames);
			standard = new JTable(model);
			standard.setBackground(Color.black);
			standard.setForeground(Color.white);
			standard.setBounds(screenSize.width/16, 140, 1400, 420);
			standard.setRowHeight(30);
			generateScheduleButton.setText("Reroll");
			TableColumnModel columnModel = standard.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(1);
			columnModel.getColumn(1).setPreferredWidth(200);
			columnModel.getColumn(2).setPreferredWidth(200);
			columnModel.getColumn(3).setPreferredWidth(1);
			columnModel.getColumn(4).setPreferredWidth(1);
			//columnModel.getColumn(5).setPreferredWidth(5);


			frame.add(standard);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			listViewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==listViewButton) {
						DefaultTableModel model = (DefaultTableModel) standard.getModel();
						String[] LISTcolumnNames = {"8AM", "9AM","10AM","11AM","12AM","1PM","2PM","3PM","4PM","5PM","6PM","7PM","8PM"};
						Object[][] listData= {
								{"8AM", "9AM","10AM","11AM","12AM","1PM","2PM","3PM","4PM","5PM","6PM","7PM","8PM"},
								{results.name[randNum[0]], results.name[randNum[1]], results.name[randNum[2]], results.name[randNum[3]], results.name[randNum[4]], results.name[randNum[5]], results.name[randNum[6]], results.name[randNum[7]], results.name[randNum[8]], results.name[randNum[9]], results.name[randNum[10]], results.name[randNum[11]], results.name[randNum[12]] },
								{results.type[randNum[0]], results.type[randNum[1]], results.type[randNum[2]], results.type[randNum[3]], results.type[randNum[4]], results.type[randNum[5]], results.type[randNum[6]], results.type[randNum[7]], results.type[randNum[8]], results.type[randNum[9]], results.type[randNum[10]], results.type[randNum[11]], results.type[randNum[12]] },
								{results.rating[randNum[0]], results.rating[randNum[1]], results.rating[randNum[2]], results.rating[randNum[3]], results.rating[randNum[4]], results.rating[randNum[5]], results.rating[randNum[6]], results.rating[randNum[7]], results.rating[randNum[8]], results.rating[randNum[9]], results.rating[randNum[10]], results.rating[randNum[11]], results.rating[randNum[12]] },
								{results.address[randNum[0]], results.address[randNum[1]], results.address[randNum[2]], results.address[randNum[3]], results.address[randNum[4]], results.address[randNum[5]], results.address[randNum[6]], results.address[randNum[7]], results.address[randNum[8]], results.address[randNum[9]], results.address[randNum[10]], results.address[randNum[11]], results.address[randNum[12]] },

						};
						DefaultTableModel listModel = new DefaultTableModel(listData, LISTcolumnNames);
						JTable listView = new JTable(listModel);
						listView.setBounds(screenSize.width/8-150, 600, 1600, 100);
						listView.setRowHeight(25);
						listView.setForeground(Color.white);
						listView.setBackground(Color.black);
						frame.add(listView);
						frame.setVisible(true);
						//model.setRowCount(0);
						//model.setColumnCount(0);
					}
				}
			});

		}

		if(e.getSource()==logoutButton) {
			frame.dispose();
			LoginPage loginPage = new LoginPage();
		}
		if(e.getSource()==updateUserButton) {
			frame.dispose();
			UpdateUser updateUser = new UpdateUser(user);
		}
		if(e.getSource()==deleteUserButton) {
			frame.dispose();
			DeleteUser deleteUser = new DeleteUser();
		}
	}


	public void itemStateChanged(ItemEvent e){
		if (e.getSource() == gym) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[2] = true;
			} else{
				filterStatus[2] = false;
			}
		}
		if (e.getSource() == restaurants) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[0] = true;
			} else{
				filterStatus[0] = false;
			}
		}
		if (e.getSource() == museum) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[1] = true;
			} else{
				filterStatus[1] = false;
			}
		}
		if (e.getSource() == zoo) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[3] = true;
			} else{
				filterStatus[3] = false;
			}
		}
		if (e.getSource() == bar) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[4] = true;
			} else{
				filterStatus[4] = false;
			}
		}
		if (e.getSource() == beach) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[5] = true;
			} else{
				filterStatus[5] = false;
			}
		}
		if (e.getSource() == park) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[6] = true;
			} else{
				filterStatus[6] = false;
			}
		}
		if (e.getSource() == landmark) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				filterStatus[7] = true;
			} else{
				filterStatus[7] = false;
			}
		}
	}
}