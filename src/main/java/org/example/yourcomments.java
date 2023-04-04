package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class yourcomments {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JTextArea comments;
    public JPanel panel1;
    public JPanel panel2;
    public JButton button;
    public String oc;
    public String fc;
    public String yc;
    public yourcomments(String moviename) throws Exception{
        frame = new JFrame("Your Comments");
        frame.setBounds(300,200,500,700);
        frame.setVisible(true);
        panel1 = new JPanel();
        panel1.setSize(500,300);
        panel1.setLocation(0,0);
        comments = new JTextArea(yc, 5,40);
        comments.setEditable(true);
        comments.setLineWrap(true);
        comments.setWrapStyleWord(true);
        panel1.add(comments);
        frame.add(panel1);
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "update comments set comment=? where moviename=?";
        String sql2 = "select comment from comments where moviename=?";
        PreparedStatement ptmt2 = con.prepareStatement(sql2);
        ptmt2.setString(1,moviename);
        ResultSet rs2 = ptmt2.executeQuery();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yc = comments.getText();
                try{
                    while (rs2.next()){
                        oc = rs2.getString("comment");
                    }
                    fc = oc+"@"+yc;
                    PreparedStatement ptmt = con.prepareStatement(sql);
                    ptmt.setString(1, fc);
                    ptmt.setString(2, moviename);
                    ptmt.execute();
                    JOptionPane.showConfirmDialog(null,"Comment Success!");
                } catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        button = new JButton("Add Comments");
        button.addActionListener(listener);
        panel2 = new JPanel();
        panel2.setLocation(0,300);
        panel2.setSize(0,200);
        panel2.add(button);
        frame.add(panel2);
    }
}

