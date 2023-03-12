package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class deletemovies {
    public String url = "java:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public deletemovies() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql1 = "select movienames from movies";
        String sql2 = "delete from movies where movienames=?";
        PreparedStatement ptmt1 = con.prepareStatement(sql1);
        ResultSet rs1 = ptmt1.executeQuery();
        PreparedStatement ptmt2 = con.prepareStatement(sql2);
        frame = new JFrame("Delete Movies");
        frame.setVisible(true);
        frame.setBounds(300, 200, 500, 700);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moviename = e.getActionCommand();
                try{
                    ptmt2.setString(1, moviename);
                    ptmt2.execute();
                }catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        while(rs1.next()){
            String moviename = rs1.getString("movienames");
            JButton button = new JButton(moviename);
            button.addActionListener(listener);
            frame.add(button);
        }
    }
}
