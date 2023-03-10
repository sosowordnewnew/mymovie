package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class comments {
    public String url = "java:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public String comments;
    public String[] comment;
    public JFrame frame;
    public JPanel panelc;
    public JButton button;
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
        comment = comments.split(" ");
        frame = new JFrame("Comments");
        frame.setVisible(true);
        frame.setBounds(300,200,500,900);
        for (int i = 0; i<comment.length; i++){
            JLabel label = new JLabel(comment[i]);
            JPanel panel = new JPanel();
            panel.setLocation(0,i*100);
            panel.add(label);
            frame.add(panel);
        }
        panelc = new JPanel();
        panelc.setLocation(0,800);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yourcomments yc = new yourcomments(moviename);
            }
        };
        JButton button = new JButton("Leave Your Comments");
        button.addActionListener(listener);
        panelc.add(button);
        frame.add(panelc);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
