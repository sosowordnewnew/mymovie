package org.example;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

import static org.example.userlogin.uname;
import static org.example.userlogin.ugender;
public class moviesdata {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JTable table;
    public JLabel labelp;
    public JPanel panelp;
    public int x = 0;
    public int y = 0;
    public moviesdata() throws Exception{
        frame = new JFrame();
        frame.setBounds(300,200,500,700);
        frame.setVisible(true);
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select movienames,description,ratings from movies";
        Statement ptmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ptmt.executeQuery(sql);
        rs.last();
        int rows = rs.getRow();
        String[][] contents = new String[rows+1][3];
        String[] columnname= {"0","0","0"};
        int i = 2;
        rs.first();
        contents[0][0] = "Moviename";
        contents[0][1] = "Movietype";
        contents[0][2] = "Rating";
        contents[1][0] = rs.getString("movienames");
        contents[1][1] = rs.getString("description");
        contents[1][2] = rs.getString("ratings");
        while(rs.next()){
            String moviename = rs.getString("movienames");
            String description = rs.getString("description");
            String ratings = rs.getString("ratings");
            contents[i][0] = moviename;
            contents[i][1] = description;
            contents[i][2] = ratings;
            i++;
        }
        table = new JTable(contents,columnname);
        frame.add(table);
        ImageIcon bg = new ImageIcon("src/main/resources/bg8.jpg");
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
                if ((x<500)&&(y<700)){
                    x = x+10;
                    y = y+10;
                    labelp.setLocation(x,y);
                } else{
                    x = 0;
                    y = 0;
                    labelp.setLocation(x,y);
                }
            }
        },0,100);
        System.out.println(uname);
        System.out.println(ugender);
    }

}
