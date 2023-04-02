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
    public JButton button;
    public JPanel panel;
    public moviesdata() throws Exception{
        frame = new JFrame();
        frame.setBounds(300,200,500,700);
        frame.setVisible(true);
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select movienames,description from movies";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        JPanel[] panels = new JPanel[20];
        JLabel[] labels = new JLabel[20];
        while(rs.next()){
            String moviename = rs.getString("movienames");
            String description = rs.getString("description");
            labels[rs.getRow()] = new JLabel(moviename+"    "+description);
            panels[rs.getRow()]=  new JPanel();
            panels[rs.getRow()].setSize(500,100);
            panels[rs.getRow()].setLocation(0,100*(rs.getRow()-1));
            panels[rs.getRow()].add(labels[rs.getRow()]);
            frame.add(panels[rs.getRow()]);
        }
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieratings mr = new movieratings();
            }
        };
        button = new JButton("See Movie Ratings");
        button.addActionListener(listener);
        panel = new JPanel();
        panel.setLocation(0,600);
        panel.setSize(500,100);
        panel.add(button);
        frame.add(panel);
    }

}
