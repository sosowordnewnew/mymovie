package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class userfrontface {
    public JFrame frame;
    public JLabel label;
    public JPanel panel;
    public JPanel panelb;
    public JButton button;
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;

    public userfrontface() throws Exception {
        frame = new JFrame("Movie List");
        frame.setBounds(300,200,500,700);
        frame.setVisible(true);
        panel = new JPanel();
        panel.setSize(500,100);
        panel.setLocation(0,0);
        label = new JLabel("Movie Names");
        panel.add(label);
        frame.add(panel);
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from movies";
        Statement ptmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ptmt.executeQuery(sql);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moviename = e.getActionCommand();
                try {
                    if (moviename.equals("Movie Recommendation")){
                        movierecommend mr = new movierecommend();
                    }
                    else{
                    detailinformation movie = new detailinformation(moviename);}
                } catch (Exception f) {
                    System.out.println(f);
                }
            }
        };
        rs.last();
        int rows = rs.getRow();
        JPanel[] panels = new JPanel[rows];
        JButton[] buttons= new JButton[rows];
        rs.first();
        buttons[0] = new JButton(rs.getString("movienames"));
        buttons[0].addActionListener(listener);
        panels[0] = new JPanel();
        panels[0].setLocation(0,100);
        panels[0].setSize(500,100);
        panels[0].add(buttons[0]);
        frame.add(panels[0]);
        int i = 1;
        while (rs.next()) {
            String name = rs.getString("movienames");
            buttons[i] = new JButton(name);
            buttons[i].addActionListener(listener);
            panels[i]=  new JPanel();
            panels[i].setLocation(0,100*(i+1));
            panels[i].setSize(500,100);
            panels[i].add(buttons[i]);
            frame.add(panels[i]);
        }
        panelb = new JPanel();
        panelb.setLocation(0,600);
        panelb.setSize(500,100);
        button = new JButton("Movie Recommendation");
        button.addActionListener(listener);
        panelb.add(button);
        frame.add(panelb);
    }
}
