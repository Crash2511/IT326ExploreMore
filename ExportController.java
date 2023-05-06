package org.exploremore;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.IOException;

public class ExportController extends JFrame implements ActionListener{
    private Export hyperLink = new Export("View Schedule in Browser");;

    Object[][] data;
    private Export linkDownload = new Export("Email to yourself");

    JButton downloadButton = new JButton("Download");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public ExportController(Object[][] data) throws HeadlessException {
        this.data = data;

        setTitle("Export");

        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));


        hyperLink.setToolTipText("");

        //linkEmail.setURL("mailto:info@codejava.net");
        //linkEmail.setToolTipText("Send an email to info@codejava.net");



        downloadButton.setBounds((screenSize.width/2),screenSize.height,125,50);
        downloadButton.setFocusable(false);
        downloadButton.addActionListener(this);

        getContentPane().add(hyperLink);
        //getContentPane().add(linkEmail);
        getContentPane().add(downloadButton);

        setSize(380, 190);
        //to display it
        setVisible(true);
        //
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==downloadButton) {
            Export ex = new Export(this.data);
            try{
                ex.download();
                ex.fileToConvert();
                hyperLink.setURL("Schedule.html");
                //ex.fileToConvert();
            }
            catch(IOException io){
                io.printStackTrace();
            }

        }
            }
        }


