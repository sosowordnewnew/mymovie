package org.example;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class moviesdata {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JTable table;
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
        String[][] contents = new String[rows][3];
        String[] columnname= {"Moviename","Movietype","Rating"};
        int i = 1;
        rs.first();
        contents[0][0] = rs.getString("movienames");
        contents[0][1] = rs.getString("description");
        contents[0][2] = rs.getString("ratings");
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
    }

}
