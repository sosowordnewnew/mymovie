package org.example;

import javafx.scene.effect.ImageInput;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Timer;

public class userinformation {
    public JFrame frame;
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JLabel labelp;
    public JPanel panelp;
    public int x = 0;
    public int y = 0;
    public JTable table;
    public userinformation() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select * from user_info";
        Statement ptmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ptmt.executeQuery(sql);
        frame = new JFrame();
        frame.setBounds(300,200,500,700);
        frame.setVisible(true);
        rs.last();
        int rows = rs.getRow();
        String[] columnname = {"0","0","0","0"};
        String[][] contents = new String[rows+1][4];
        rs.first();
        contents[0][0]=  "Username";
        contents[0][1] = "Registerdate";
        contents[0][2] = "Gender";
        contents[0][3] = "Age";
        contents[1][0] = rs.getString("username");
        contents[1][1] = rs.getString("setupdate");
        contents[1][2] = rs.getString("gender");
        contents[1][3] = rs.getString("age");
        int i = 2;
        while (rs.next()){
            String username = rs.getString("username");
            String date = rs.getString("setupdate");
            String gender = rs.getString("gender");
            String age = rs.getString("age");
            contents[i][0] = username;
            contents[i][1] = date;
            contents[i][2] = gender;
            contents[i][3] = age;
            i++;
        }
        table = new JTable(contents, columnname);
        frame.add(table);
        ImageIcon bg = new ImageIcon("src/main/resources/bg7.jpg");
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
                    y = y+20;
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
