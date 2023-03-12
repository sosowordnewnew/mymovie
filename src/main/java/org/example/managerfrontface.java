package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class managerfrontface {
    public String url = "java:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JButton button1;
    public JButton button2;
    public JPanel panelm;
    public managerfrontface() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select movienames from movies";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        frame = new JFrame("Current Movies");
        frame.setVisible(true);
        frame.setBounds(300, 200, 500, 700);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moviename = e.getActionCommand();
                try{
                    if (moviename.equals("Add Movies")){
                        addmovies am = new addmovies();
                    }
                    else if (moviename.equals("Delete Movies")){
                        deletemovies dm = new deletemovies();
                    }
                    else {
                        detailinformation di = new detailinformation(moviename);
                    }
                } catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        while(rs.next()){
            String moviename = rs.getString("movienames");
            JButton button = new JButton(moviename);
            button.addActionListener(listener);
            frame.add(button);
        }
        panelm = new JPanel();
        panelm.setLocation(0, 600);
        button1 = new JButton("Add Movies");
        button1.addActionListener(listener);
        button2 = new JButton("Delete Movies");
        button2.addActionListener(listener);
        panelm.add(button1);
        panelm.add(button2);
        frame.add(panelm);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
