package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class userfrontface {
    public JFrame frame;
    public JLabel label;
    public String url = "java:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;

    public userfrontface() throws Exception {
        frame = new JFrame("Movie List");
        label = new JLabel("Movie Names");
        frame.add(label);
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
        while (rs.next()) {
            String name = rs.getString("movienames");
            JButton button = new JButton(name);
            button.addActionListener(listener);
            frame.add(button);
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
