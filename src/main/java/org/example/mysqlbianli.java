package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class mysqlbianli {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;


    public static void main(String[] args) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from user_info";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        JFrame frame = new JFrame();
        frame.setBounds(300, 200, 500, 1000);
        frame.setVisible(true);
        frame.setTitle("来开盒！");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = e.getActionCommand();
                try{
                    mysqlbianli2 name = new mysqlbianli2(username);
                }catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        while(rs.next()){
            String name = rs.getString("username");
            JButton button = new JButton(name);
            button.addActionListener(listener);
            frame.add(button);
        }
    }
}
