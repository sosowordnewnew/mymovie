package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

public class addmovies {
    public String url = "java:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;
    public JLabel label4;
    public JTextField name;
    public String movienames;
    public String descriptions;
    public String comments;
    public String ratings;
    public JTextArea description;
    public JCheckBox box1;
    public JCheckBox box2;
    public JCheckBox box3;
    public JCheckBox box4;
    public JCheckBox box5;
    public JTextArea comment;
    public JButton button;
    public addmovies() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql1 = "insert into movies (movienames, description, ratings, people) values(?,?,?,?)";
        String sql2 = "insert into comments (moviename,comment) values(?,?)";
        PreparedStatement ptmt1 = con.prepareStatement(sql1);
        PreparedStatement ptmt2 = con.prepareStatement(sql2);
        frame = new JFrame("Complete Information");
        frame.setVisible(true);
        frame.setBounds(300,200,500,1000);
        JPanel panel1 = new JPanel();
        panel1.setLocation(0,0);
        label1=  new JLabel("Movie Name:");
        name = new JTextField(movienames,  30);
        name.setEditable(true);
        panel1.add(label1);
        panel1.add(name);
        frame.add(panel1);
        JPanel panel2 = new JPanel();
        panel2.setLocation(0,100);
        label2 = new JLabel("Description:");
        description = new JTextArea(descriptions, 5, 30);
        description.setEditable(true);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        panel2.add(label2);
        panel2.add(description);
        frame.add(panel2);
        JPanel panel3=  new JPanel();
        panel3.setLocation(0, 500);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonname = e.getActionCommand();
                if (buttonname.equals("Add")){
                    movienames = name.getText();
                    descriptions = description.getText();
                    comments = comment.getText();
                    if (box1.isSelected()) ratings = "1.0";
                    else if (box2.isSelected()) ratings = "2.0";
                    else if(box3.isSelected()) ratings = "3.0";
                    else if(box4.isSelected()) ratings = "4.0";
                    else if(box5.isSelected()) ratings = "5.0";
                    try{
                        ptmt1.setString(1, movienames);
                        ptmt1.setString(2, descriptions);
                        ptmt1.setString(3, ratings);
                        ptmt1.setString(4, "1");
                        ptmt1.execute();
                        ptmt2.setString(1, movienames);
                        ptmt2.setString(2, comments);
                        ptmt2.execute();
                    }catch(Exception f){
                        System.out.println(f);
                    }
                }
            }
        };
        label3 = new JLabel("Your Rating:");
        box1 = new JCheckBox("1");
        box1.addActionListener(listener);
        box2 = new JCheckBox("2");
        box2.addActionListener(listener);
        box3 = new JCheckBox("3");
        box3.addActionListener(listener);
        box4 = new JCheckBox("4");
        box4.addActionListener(listener);
        box5 = new JCheckBox("5");
        box5.addActionListener(listener);
        panel3.add(label3);
        panel3.add(box1);
        panel3.add(box2);
        panel3.add(box3);
        panel3.add(box4);
        panel3.add(box5);
        frame.add(panel3);
        JPanel panel4 = new JPanel();
        panel4.setLocation(0, 600);
        label4 = new JLabel("Comments:");
        comment = new JTextArea(comments, 5, 30);
        panel4.add(label4);
        panel4.add(comment);
        frame.add(panel4);
        JPanel panel5 = new JPanel();
        panel5.setLocation(0,900);
        button = new JButton("Add");
        button.addActionListener(listener);
        panel5.add(button);
        frame.add(panel5);
    }
}
