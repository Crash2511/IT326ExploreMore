package org.exploremore;

import java.awt.*;
import java.awt.event.*;

import java.util.*;
import javax.swing.*;



public class CreateAvailability implements ActionListener {

	JFrame frame = new JFrame();
	JButton setAvButton = new JButton("Set Availability");
	JLabel selectAvLabel = new JLabel("Select Availability");
	JLabel messageLabel = new JLabel();
	HashMap<String,String> logininfo = new HashMap<String,String>();
	JCheckBox am8;
	JCheckBox am9;
	JCheckBox am10;
	JCheckBox am11;
	JCheckBox pm12;
	JCheckBox pm1;
	JCheckBox pm2;
	JCheckBox pm3;
	JCheckBox pm4;
	JCheckBox pm5;
	JCheckBox pm6;
	JCheckBox pm7;
	JCheckBox pm8;
	Boolean[] avCheck;
	CreateAvailability(Boolean[] availabilityCheck) {
		avCheck = availabilityCheck;

		am8 = new JCheckBox("8AM",avCheck[0]);
		am9 = new JCheckBox("9AM",avCheck[1]);
		am10 = new JCheckBox("10AM",avCheck[2]);
		am11 = new JCheckBox("11AM",avCheck[3]);
		pm12 = new JCheckBox("12PM",avCheck[4]);
		pm1 = new JCheckBox("1PM",avCheck[5]);
		pm2 = new JCheckBox("2PM",avCheck[6]);
		pm3 = new JCheckBox("3PM",avCheck[7]);
		pm4 = new JCheckBox("4PM",avCheck[8]);
		pm5 = new JCheckBox("5PM",avCheck[9]);
		pm6 = new JCheckBox("6PM",avCheck[10]);
		pm7 = new JCheckBox("7PM",avCheck[11]);
		pm8 = new JCheckBox("8PM",avCheck[12]);
		messageLabel.setBounds(150,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));

		selectAvLabel.setBounds(125,50,300,35);
		selectAvLabel.setFont(new Font(null,Font.PLAIN,30));
		selectAvLabel.setText("Select Availability");

		setAvButton.setBounds(150,250,150,30);
		setAvButton.setFocusable(false);
		setAvButton.addActionListener(this);

		am8.setBounds(150, 100, 100, 20);
		am8.setFocusable(false);
		am8.addItemListener(this::itemStateChanged);

		am9.setBounds(150, 120, 100, 20);
		am9.setFocusable(false);
		am9.addItemListener(this::itemStateChanged);

		am10.setBounds(150, 140, 100, 20);
		am10.setFocusable(false);
		am10.addItemListener(this::itemStateChanged);

		am11.setBounds(150, 160, 100, 20);
		am11.setFocusable(false);
		am11.addItemListener(this::itemStateChanged);

		pm12.setBounds(150, 180, 100, 20);
		pm12.setFocusable(false);
		pm12.addItemListener(this::itemStateChanged);

		pm1.setBounds(150, 200, 100, 20);
		pm1.setFocusable(false);
		pm1.addItemListener(this::itemStateChanged);

		pm2.setBounds(150, 220, 100, 20);
		pm2.setFocusable(false);
		pm2.addItemListener(this::itemStateChanged);

		pm3.setBounds(250, 100, 100, 20);
		pm3.setFocusable(false);
		pm3.addItemListener(this::itemStateChanged);

		pm4.setBounds(250, 120, 100, 20);
		pm4.setFocusable(false);
		pm4.addItemListener(this::itemStateChanged);

		pm5.setBounds(250, 140, 100, 20);
		pm5.setFocusable(false);
		pm5.addItemListener(this::itemStateChanged);

		pm6.setBounds(250, 160, 100, 20);
		pm6.setFocusable(false);
		pm6.addItemListener(this::itemStateChanged);

		pm7.setBounds(250, 180, 100, 20);
		pm7.setFocusable(false);
		pm7.addItemListener(this::itemStateChanged);

		pm8.setBounds(250, 200, 100, 20);
		pm8.setFocusable(false);
		pm8.addItemListener(this::itemStateChanged);

		frame.add(messageLabel);
		frame.add(selectAvLabel);
		frame.add(am8);
		frame.add(am9);
		frame.add(am10);
		frame.add(am11);
		frame.add(pm12);
		frame.add(pm1);
		frame.add(pm2);
		frame.add(pm3);
		frame.add(pm4);
		frame.add(pm5);
		frame.add(pm6);
		frame.add(pm7);
		frame.add(pm8);
		frame.add(setAvButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setVisible(true);

	}
	public Boolean[] getAvailability() {
		return avCheck;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==setAvButton) {
			frame.dispose();
		}

	}
	public void itemStateChanged(ItemEvent e){
		if (e.getSource() == am8) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[0] = true;
			}
			else{
				avCheck[0] = false;
			}
		}
		if (e.getSource() == am9) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[1] = true;
			}
			else{
				avCheck[1] = false;
			}
		}
		if (e.getSource() == am10) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[2] = true;
			}
			else{
				avCheck[2] = false;
			}
		}
		if (e.getSource() == am11) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[3] = true;
			}
			else{
				avCheck[3] = false;
			}
		}
		if (e.getSource() == pm12) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[4] = true;
			}
			else{
				avCheck[4] = false;
			}
		}
		if (e.getSource() == pm1) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[5] = true;
			}
			else{
				avCheck[5] = false;
			}
		}
		if (e.getSource() == pm2) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[6] = true;
			}
			else{
				avCheck[6] = false;
			}
		}
		if (e.getSource() == pm3) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[7] = true;
			}
			else{
				avCheck[7] = false;
			}
		}
		if (e.getSource() == pm4) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[8] = true;
			}
			else{
				avCheck[8] = false;
			}
		}
		if (e.getSource() == pm5) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[9] = true;
			}
			else{
				avCheck[9] = false;
			}
		}
		if (e.getSource() == pm6) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[10] = true;
			}
			else{
				avCheck[10] = false;
			}
		}
		if (e.getSource() == pm7) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[11] = true;
			}
			else{
				avCheck[11] = false;
			}
		}
		if (e.getSource() == pm8) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				avCheck[12] = true;
			}
			else{
				avCheck[12] = false;
			}
		}
	}
}

