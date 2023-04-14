package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class detailinformation {
    public JFrame frame;
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JPanel panel4;
    public JPanel panel5;
    public JPanel panel6;
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;
    public JLabel label4;
    public JLabel label5;
    public JTextArea text;
    public JButton button;
    public JCheckBox box1;
    public JCheckBox box2;
    public JCheckBox box3;
    public JCheckBox box4;
    public JCheckBox box5;
    public String description;
    public String ratings;
    public JLabel labelp;
    public JPanel panelp;
    public int x = 0;
    public int y = 0;
    public String peoples;
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public detailinformation(String moviename) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select movienames,description,ratings,people from movies where movienames=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1, moviename);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            description = rs.getString("description");
            ratings = rs.getString("ratings");
            peoples = rs.getString("people");
        }
        frame = new JFrame(moviename);
        frame.setBounds(300, 200, 500, 900);
        frame.setVisible(true);
        panel1 = new JPanel();
        panel1.setLocation(0, 0);
        panel1.setSize(500,100);
        label1 = new JLabel(moviename);
        panel1.add(label1);
        frame.add(panel1);
        panel2 = new JPanel();
        panel2.setLocation(0, 100);
        panel2.setSize(500,100);
        label2 = new JLabel("Description");
        panel2.add(label2);
        frame.add(panel2);
        panel3 = new JPanel();
        panel3.setLocation(0, 200);
        panel3.setSize(500,200);
        text = new JTextArea(description, 5, 30);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        panel3.add(text);
        frame.add(panel3);
        panel4 = new JPanel();
        panel4.setLocation(0, 400);
        panel4.setSize(500,100);
        label3 = new JLabel("Ratings");
        label4 = new JLabel(ratings);
        panel4.add(label3);
        panel4.add(label4);
        frame.add(panel4);
        panel5 = new JPanel();
        panel5.setLocation(0, 500);
        panel5.setSize(500,100);
        label5 = new JLabel("Your Rating:");
        box1 = new JCheckBox("1");
        box2 = new JCheckBox("2");
        box3 = new JCheckBox("3");
        box4 = new JCheckBox("4");
        box5 = new JCheckBox("5");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (box1.isSelected()){
                    try{
                        ratingupdate box = new ratingupdate(1, Double.parseDouble(ratings), Integer.parseInt(peoples),moviename);
                        JOptionPane.showConfirmDialog(null,"Rating Success!");
                    } catch(Exception f){
                        System.out.println(f);
                    }
                }
                else if(box2.isSelected()){
                    try{
                        ratingupdate box = new ratingupdate(2,Double.parseDouble(ratings), Integer.parseInt(peoples),moviename);
                        JOptionPane.showConfirmDialog(null,"Rating Success!");
                    } catch(Exception f){
                        System.out.println(f);
                    }
                }
                else if (box3.isSelected()){
                    try{
                        ratingupdate box = new ratingupdate(3,Double.parseDouble(ratings), Integer.parseInt(peoples),moviename);
                        JOptionPane.showConfirmDialog(null,"Rating Success!");
                    } catch(Exception f){
                        System.out.println(f);
                    }
                }
                else if(box4.isSelected()){
                    try{
                        ratingupdate box = new ratingupdate(4,Double.parseDouble(ratings), Integer.parseInt(peoples),moviename);
                        JOptionPane.showConfirmDialog(null,"Rating Success!");
                    } catch(Exception f){
                        System.out.println(f);
                    }
                }
                else if(box5.isSelected()){
                    try{
                        ratingupdate box = new ratingupdate(5,Double.parseDouble(ratings), Integer.parseInt(peoples),moviename);
                        JOptionPane.showConfirmDialog(null,"Rating Success!");
                    } catch (Exception f){
                        System.out.println(f);
                    }
                }
                else{
                    try{
                        comments comment = new comments(moviename);
                    } catch(Exception f){
                        System.out.println(f);
                    }
                }
            }
        };
        box1.addActionListener(listener);
        box2.addActionListener(listener);
        box3.addActionListener(listener);
        box4.addActionListener(listener);
        box5.addActionListener(listener);
        panel5.add(label5);
        panel5.add(box1);
        panel5.add(box2);
        panel5.add(box3);
        panel5.add(box4);
        panel5.add(box5);
        frame.add(panel5);
        panel6 = new JPanel();
        panel6.setLocation(0, 600);
        button = new JButton("Comments");
        button.addActionListener(listener);
        panel6.add(button);
        frame.add(panel6);
        ImageIcon bg = new ImageIcon("src/main/resources/bg4.jpg");
        labelp = new JLabel(bg);
        labelp.setSize(200,200);
        labelp.setLocation(x,y);
        frame.getLayeredPane().add(labelp, Integer.MIN_VALUE);
        panelp = (JPanel) frame.getContentPane();
        panelp.setOpaque(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if((x<500)&&(y<900)){
                    x = x+10;
                    y = y+20;
                    labelp.setLocation(x,y);
                }
                else{
                    x = 0;
                    y = 0;
                    labelp.setLocation(x,y);
                }
            }
        },0,100);
    }
}
