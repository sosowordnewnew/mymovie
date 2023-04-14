package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class deletemovies {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JPanel panelp;
    public JLabel labelp;
    public int x;
    public int y;
    public deletemovies() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql1 = "select movienames from movies";
        String sql2 = "delete from movies where movienames=?";
        Statement ptmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = ptmt1.executeQuery(sql1);
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
                    JOptionPane.showConfirmDialog(null,"Delete Success!");
                }catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        rs1.last();
        int rows = rs1.getRow();
        JButton[] buttons = new JButton[rows];
        JPanel[] panels = new JPanel[rows];
        rs1.first();
        buttons[0] = new JButton(rs1.getString("movienames"));
        buttons[0].addActionListener(listener);
        panels[0] = new JPanel();
        panels[0].setSize(500,100);
        panels[0].setLocation(0,0);
        panels[0].add(buttons[0]);
        frame.add(panels[0]);
        int i = 1;
        while(rs1.next()){
            String moviename = rs1.getString("movienames");
            buttons[i] = new JButton(moviename);
            buttons[i].addActionListener(listener);
            panels[i] = new JPanel();
            panels[i].setSize(500,100);
            panels[i].setLocation(0,100*i);
            panels[i].add(buttons[i]);
            frame.add(panels[i]);
            i++;
        }
        ImageIcon bg = new ImageIcon("src/main/resources/bg6.jpg");
        labelp = new JLabel(bg);
        labelp.setSize(200,200);
        frame.getLayeredPane().add(labelp, Integer.MIN_VALUE);
        panelp = (JPanel) frame.getContentPane();
        panelp.setOpaque(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if ((x<500)&&(y<700)){
                    x = x+10;
                    y = y+20;
                    labelp.setLocation(x,y);
                } else{
                    x = 0;
                    y = 0;
                    labelp.setLocation(x,y);
                }
            }
        },0,100);
    }
}
