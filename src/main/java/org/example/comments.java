package org.example;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class comments {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public String comments;
    public String[] comment;
    public JFrame frame;
    public JPanel panelc;
    public JButton button;
    public JButton button2;
    public JButton button3;
    public JLabel labelp;
    public JPanel panelp;
    public int x = 0;
    public int y = 0;
    public comments(String moviename) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select comment from comments where moviename=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1, moviename);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
             comments = rs.getString("comment");
        }
        comment = comments.split("@");
        frame = new JFrame("Comments");
        frame.setVisible(true);
        frame.setBounds(0,0,1400,700);
        JPanel[] panels = new JPanel[comment.length];
        JLabel[] labels = new JLabel[comment.length];
        for (int i = 0; i<comment.length; i++){
            labels[i] = new JLabel(comment[i]);
            panels[i] = new JPanel();
            panels[i].setLocation(0,i*100);
            panels[i].setSize(500,100);
            panels[i].add(labels[i]);
            frame.add(panels[i]);
        }
        panelc = new JPanel();
        panelc.setSize(500,100);
        panelc.setLocation(0,800);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonname = e.getActionCommand();
                if (buttonname.equals("Leave Your Comments")){
                    try {
                            yourcomments ycm = new yourcomments(moviename);
                    } catch (Exception f) {
                        System.out.println(f);
                    }
                }
                else if(buttonname.equals("See Picture Comments")){
                    try {
                        picturecomments pc = new picturecomments(moviename);
                    } catch (Exception f){
                        System.out.print(f);
                    }
                }
                else{
                    try{
                        movieposters mp = new movieposters(moviename);
                    } catch(Exception f){
                        System.out.println(f);
                    }
                }
            }
        };
        button = new JButton("Leave Your Comments");
        button.addActionListener(listener);
        button2 = new JButton("See Picture Comments");
        button2.addActionListener(listener);
        button3 = new JButton("See Movie Posters");
        button3.addActionListener(listener);
        panelc.add(button);
        panelc.add(button2);
        panelc.add(button3);
        frame.add(panelc);
        ImageIcon bg = new ImageIcon("src/main/resources/bg5.jpg");
        labelp = new JLabel(bg);
        labelp.setSize(200,200);
        frame.getLayeredPane().add(labelp, Integer.MIN_VALUE);
        panelp = (JPanel) frame.getContentPane();
        panelp.setOpaque(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if ((x<1400)&&(y<700)){
                    x = x+20;
                    y = y+10;
                    labelp.setLocation(x,y);
                } else{
                    x = 0;
                    y = 0;
                    labelp.setLocation(x,y);
                }
            }
        },0,100);
    }
}
