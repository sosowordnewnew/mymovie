package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class yourcomments {
    public String url = "java:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JTextArea comments;
    public JButton button;
    public String comment;
    public String oc;
    public String fc;
    public yourcomments(String moviename){
        frame = new JFrame("Your Comments");
        JTextArea comments = new JTextArea(comment, 5,40);
        comments.setEditable(true);
        comments.setLineWrap(true);
        comments.setWrapStyleWord(true);
        frame.add(comments);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String yc = comments.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(url, user, pass);
                    String sql = "update comments set (comment) values(?) where moviename=?";
                    String sql2 = "select comment from comments where moviename=?";
                    PreparedStatement ptmt2 = con.prepareStatement(sql2);
                    ptmt2.setString(1,moviename);
                    ResultSet rs2 = ptmt2.executeQuery();
                    while (rs2.next()){
                        oc = rs2.getString("comment");
                    }
                    fc = oc+" "+yc;
                    PreparedStatement ptmt = con.prepareStatement(sql);
                    ptmt.setString(1, fc);
                    ptmt.setString(2, moviename);
                    ptmt.execute();
                } catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        button = new JButton("Add Comments");
        button.addActionListener(listener);
        frame.add(button);
    }
}
