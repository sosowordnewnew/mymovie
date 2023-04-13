package org.example;

import javax.swing.*;
import java.sql.*;
public class userinformation {
    public JFrame frame;
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
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
        String[] columnname = {"0","0"};
        String[][] contents = new String[rows][2];
        rs.first();
        contents[0][0] = rs.getString("username");
        contents[0][1] = rs.getString("setupdate");
        int i = 1;
        while (rs.next()){
            String username = rs.getString("username");
            String date = rs.getString("setupdate");
            contents[i][0] = username;
            contents[i][1] = date;
            i++;
        }
        table = new JTable(contents, columnname);
        frame.add(table);
    }
}
