package org.exploremore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URISyntaxException;
import java.io.FileNotFoundException;
import java.io.*;

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.net.URI;

import java.awt.event.ItemEvent;

public class Export extends JLabel{

    private String htmlStuff = "<html><a href=''>%s</a></html>";

    private String httpURL;
    public Object[][] data;

    public Export(String text) {
        this(text, null, null);
    }

    public Export(Object[][] data) {
        this.data = data;
    }

    public void setURL(String url) {
        this.httpURL = url;
    }

    public Export(String text, String url, String tooltip) {
        super(text);
        this.httpURL = url;

        setForeground(Color.BLUE.darker());

        setToolTipText(tooltip);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));



        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setText(String.format(htmlStuff, text));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setText(text);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI(Export.this.httpURL));

                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(Export.this,
                            "Could not open the hyperlink. Error: " + e1.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });

    }

    private boolean convertToHTML(String src, String dst, String... header) {

        String headerText = null;
        if (header.length != 0) {
            headerText = header[0];
        }


        BufferedReader input;
        try {
            input = new BufferedReader(
                    new FileReader(src));
            if (!input.ready()) {
                throw new IOException(); }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error: ", "Did not write to file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error: ", "Did not write to file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        ArrayList<String> text = new ArrayList<>();
        text.add("<html>");
        text.add("<head>");
        text.add(headerText);
        text.add("</head>");
        text.add("<body>");
        try {
            String temp;
            while((temp = input.readLine()) != null){
                text.add("<p>" + temp + "</p>");}
            input.close();
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error: ", "Did not write to file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        text.add("</body>");
        text.add("</html>");
        try {
            FileWriter fw = new FileWriter(dst);
            Writer w = new BufferedWriter(fw);

            for (int i = 0; i < text.size(); i++) {

                w.write(text.get(i) + "\n");
            }
            w.close();
            return true;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error: ", "Did not write to file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void fileToConvert(){
        //converts file in from .txt to .HTML
        boolean converted = convertToHTML("schedule.txt", "Schedule.html", "Schedule");
        if (converted) {
            System.out.println("HTML file made!");
        }
        else {
            System.out.println("ERROR! HTML file not made...");
        }
        /*try {
            File htmlFile = new File("Schedule.html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException ex) {
        }*/
    }


    public void download() throws IOException {
        try {
            File f = new File("schedule.txt");
            if (f.createNewFile()) {
                System.out.println("File made: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ArrayList<String> txt = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[j].length; j++) {
                txt.add(data[i][j].toString() + " ");
            }
            txt.add("\n");

        }

        try {
            FileWriter fw = new FileWriter("schedule.txt");
            Writer w = new BufferedWriter(fw);

            for (int i = 0; i < txt.size(); i++) {
                w.write(txt.get(i));
            }
            w.close();

        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error: ", "Did not write to file", JOptionPane.ERROR_MESSAGE);

        }
        //System.out.println("WE MADE IT");

    }
}