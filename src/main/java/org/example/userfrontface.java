package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class userfrontface {
    public JFrame frame;
    public JLabel label;
    public JPanel panel;
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
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moviename = e.getActionCommand();
                try {
                    detailinformation movie = new detailinformation(moviename);
                } catch (Exception f) {
                    System.out.println(f);
                }
            }
        };
        JPanel[] panels = new JPanel[20];
        JButton[] buttons= new JButton[20];
        while (rs.next()) {
            String name = rs.getString("movienames");
            buttons[rs.getRow()] = new JButton(name);
            buttons[rs.getRow()].addActionListener(listener);
            panels[rs.getRow()]=  new JPanel();
            panels[rs.getRow()].setLocation(0,100*(rs.getRow()));
            panels[rs.getRow()].setSize(500,100);
            panels[rs.getRow()].add(buttons[rs.getRow()]);
            frame.add(panels[rs.getRow()]);
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
