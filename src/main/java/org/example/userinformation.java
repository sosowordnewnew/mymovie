package org.example;

import javax.swing.*;
import java.sql.*;
public class userinformation {
    public JFrame frame;
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public userinformation() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select * from user_info";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        frame = new JFrame();
        frame.setBounds(300,200,500,700);
        frame.setVisible(true);
        JPanel[] panels = new JPanel[20];
        JLabel[] labels = new JLabel[20];
        while (rs.next()){
            String username = rs.getString("username");
            String date = rs.getString("setupdate");
            labels[rs.getRow()] = new JLabel(username+"     "+date);
            panels[rs.getRow()] = new JPanel();
            panels[rs.getRow()].setLocation(0,100*(rs.getRow()-1));
            panels[rs.getRow()].setSize(500,100);
            panels[rs.getRow()].add(labels[rs.getRow()]);
            frame.add(panels[rs.getRow()]);
        }

    }
}
