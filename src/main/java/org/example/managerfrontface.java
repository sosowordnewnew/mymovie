package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class managerfrontface {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JPanel panelm;
    public JPanel panelo;
    public managerfrontface() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select movienames from movies";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
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
                    else {
                        detailinformation di = new detailinformation(moviename);
                    }
                } catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        JPanel[] panels = new JPanel[20];
        JButton[] buttons = new JButton[20];
        while(rs.next()){
            String moviename = rs.getString("movienames");
            buttons[rs.getRow()] = new JButton(moviename);
            buttons[rs.getRow()].addActionListener(listener);
            panels[rs.getRow()] = new JPanel();
            panels[rs.getRow()].setLocation(0,100*(rs.getRow()-1));
            panels[rs.getRow()].setSize(500,100);
            panels[rs.getRow()].add(buttons[rs.getRow()]);
            frame.add(panels[rs.getRow()]);
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
        panelo.add(button3);
        frame.add(panelo);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}