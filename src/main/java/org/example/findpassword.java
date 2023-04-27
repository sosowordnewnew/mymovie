package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class findpassword {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JPanel panel1;
    public JPanel panel2;
    public JLabel label;
    public JTextField input;
    public JButton button;
    public String email;
    public findpassword() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from user_info where email=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        frame = new JFrame("Find Your Password");
        frame.setVisible(true);
        frame.setBounds(0,0,1400,700);
        panel1 = new JPanel();
        panel1.setSize(500,200);
        panel1.setLocation(0,0);
        label = new JLabel("Enter Your Email:");
        input = new JTextField(email, 30);
        input.setEditable(true);
        panel1.add(label);
        panel1.add(input);
        frame.add(panel1);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email = input.getText();
                try{
                    ptmt.setString(1,email);
                    ResultSet rs = ptmt.executeQuery();
                    if (!rs.isBeforeFirst()) JOptionPane.showConfirmDialog(null,"Sorry! No passwords found!");
                    else {
                        while (rs.next()) {
                            String username = rs.getString("username");
                            String password = rs.getString("password");
                            String result = "Your Username is: " + username + "\n" + "Your Password is: " + password;
                            JOptionPane.showConfirmDialog(null, result);
                            break;
                        }
                    }
                } catch(Exception f){
                    System.out.println(f);
                }
            }
        };
        panel2 = new JPanel();
        panel2.setLocation(0,200);
        panel2.setSize(500,200);
        button = new JButton("Find My Password");
        button.addActionListener(listener);
        panel2.add(button);
        frame.add(panel2);
    }
}
