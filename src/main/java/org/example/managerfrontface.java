package org.example;
import javafx.application.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import static javafx.application.Application.launch;


public class managerfrontface{
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JPanel panelm;
    public JPanel panelo;
    public managerfrontface() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select movienames from movies";
        Statement ptmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ptmt.executeQuery(sql);
        frame = new JFrame("Current Movies");
        frame.setVisible(true);
        frame.setBounds(300, 200, 500, 900);
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
                    else if(moviename.equals("Users Information")){
                        userinformation ui = new userinformation();
                    }
                    else if(moviename.equals("Movies Data")){
                        moviesdata md = new moviesdata();
                    }
                    else {
                        detailinformation di = new detailinformation(moviename);
                    }
                } catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        rs.last();
        int rows = rs.getRow();
        JPanel[] panels = new JPanel[rows];
        JButton[] buttons = new JButton[rows];
        rs.first();
        buttons[0] = new JButton(rs.getString("movienames"));
        buttons[0].addActionListener(listener);
        panels[0] = new JPanel();
        panels[0].setLocation(0,0);
        panels[0].setSize(500,100);
        panels[0].add(buttons[0]);
        frame.add(panels[0]);
        int i = 1;
        while(rs.next()){
            String moviename = rs.getString("movienames");
            buttons[i] = new JButton(moviename);
            buttons[i].addActionListener(listener);
            panels[i] = new JPanel();
            panels[i].setLocation(0,100*i);
            panels[i].setSize(500,100);
            panels[i].add(buttons[i]);
            frame.add(panels[i]);
            i++;
        }
        panelm = new JPanel();
        panelm.setLocation(0, 500);
        panelm.setSize(500,100);
        button1 = new JButton("Add Movies");
        button1.addActionListener(listener);
        button2 = new JButton("Delete Movies");
        button2.addActionListener(listener);
        panelm.add(button1);
        panelm.add(button2);
        frame.add(panelm);
        panelo = new JPanel();
        panelo.setSize(500,100);
        panelo.setLocation(0,600);
        button3 = new JButton("Users Information");
        button3.addActionListener(listener);
        button4 = new JButton("Movies Data");
        button4.addActionListener(listener);
        panelo.add(button3);
        panelo.add(button4);
        frame.add(panelo);
    }
}
