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
    private String url;
    private String html = "<html><a href=''>%s</a></html>";

    public Object[][] data;

    public Export(String text) {
        this(text, null, null);
    }

    public Export(Object[][] data) {
        this.data = data;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public Export(String text, String url, String tooltip) {
        super(text);
        this.url = url;

        setForeground(Color.BLUE.darker());

        setToolTipText(tooltip);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));



        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setText(String.format(html, text));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setText(text);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI(Export.this.url));

                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(Export.this,
                            "Could not open the hyperlink. Error: " + e1.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });

    }

    private static boolean convertToHTML(final String src,
                                            final String dst, String... headText) {

        String headTxt = "";
        if (headText.length != 0) {
            headTxt = headText[0];
        }


        BufferedReader input;
        try {
            input = new BufferedReader(new FileReader(src));
            if (!input.ready()) { throw new IOException(); }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error", "Error when writing to a file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error", "Error when writing to a file", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        ArrayList<String> txt = new ArrayList<>();
        txt.add("<html>");
        txt.add("<head>");
        txt.add(headTxt);
        txt.add("</head>");
        txt.add("<body>");

        try {
            String str;
            while((str = input.readLine()) != null){
                txt.add("<p>" + str + "</p>");
            }
            input.close();
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error", "Error when writing to a file", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Place our HTML finishing Tags into our String ArrayList...
        txt.add("</body>");
        txt.add("</html>");

        // Write the String ArrayList to our supplied Destination
        // File Path...
        try {
            FileWriter fw = new FileWriter(dst);
            Writer output = new BufferedWriter(fw);

            for (int i = 0; i < txt.size(); i++) {

                output.write(txt.get(i) + "\r\n");
            }
            output.close();
            return true;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error", "Error when writing to a file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void fileToConvert(){

        boolean success = convertToHTML("schedule.txt", "Schedule.html", "Schedule");
        if (success) {
            System.out.println("Text File Made!");
        }
        else {
            System.out.println("ERROR! Text File not made.");
        }
        /*try {
            File htmlFile = new File("Schedule.html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException ex) {}*/
    }


    public void download() throws IOException {
        try {
            File myObj = new File("schedule.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
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
            Writer output = new BufferedWriter(fw);

            for (int i = 0; i < txt.size(); i++) {
                output.write(txt.get(i));
            }
            output.close();

        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File Write Error", "Error when writing to a file", JOptionPane.ERROR_MESSAGE);

        }
        //System.out.println("WE MADE IT");

    }
}
