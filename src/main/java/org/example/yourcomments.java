package org.example;
import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    public JPanel panel3;
    public JButton button;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public String oc;
    public String fc;
    public String yc;
    public yourcomments(String moviename) throws Exception{
        frame = new JFrame("Your Comments");
        frame.setBounds(0,0,1400,700);
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
        String sql3 = "update comments set pictures=? where moviename=?";
        String sql4 = "select pictures from comments where moviename=?";
        PreparedStatement ptmt2 = con.prepareStatement(sql2);
        ptmt2.setString(1,moviename);
        ResultSet rs2 = ptmt2.executeQuery();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonname = e.getActionCommand();
              if(buttonname.equals("Add Comments")) {
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
              else if (buttonname.equals("Browse")){
                  JFileChooser filechooser = new JFileChooser();
                  int result = filechooser.showOpenDialog(frame);
                  if (result==JFileChooser.APPROVE_OPTION){
                      File selectedfile=  filechooser.getSelectedFile();
                      comments.setText(selectedfile.getAbsolutePath());
                  }
              }
              else if (buttonname.equals("Upload Pictures")){
                  String picturepath = comments.getText();
                  try{
                      PreparedStatement ptmt = con.prepareStatement(sql4);
                      ptmt.setString(1,moviename);
                      ResultSet rs = ptmt.executeQuery();
                      String fpicturepath = "";
                      while(rs.next()){
                          fpicturepath = rs.getString("pictures");
                      }
                      String epicturepath = fpicturepath+"@"+picturepath;
                      PreparedStatement ptmt2 = con.prepareStatement(sql3);
                      ptmt2.setString(1, epicturepath);
                      ptmt2.setString(2, moviename);
                      ptmt2.executeUpdate();
                      JOptionPane.showMessageDialog(frame, "upload success!");
                  } catch(Exception f){
                      System.out.print(f);
                  }
              }
              }
        };
        button = new JButton("Add Comments");
        button.addActionListener(listener);
        button2 = new JButton("Browse");
        button2.addActionListener(listener);
        panel2 = new JPanel();
        panel2.setLocation(0,300);
        panel2.setSize(500,200);
        panel2.add(button);
        panel2.add(button2);
        frame.add(panel2);
        panel3 = new JPanel();
        panel3.setSize(500,200);
        panel3.setLocation(0,500);
        button3 = new JButton("Upload Pictures");
        button3.addActionListener(listener);
        panel3.add(button3);
        frame.add(panel3);
    }
}

