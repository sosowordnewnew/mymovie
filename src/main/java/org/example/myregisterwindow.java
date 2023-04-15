package org.example;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class myregisterwindow {
    public JFrame frame;
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JPanel panel4;
    public JPanel panel5;
    public JPanel panel6;
    public JPanel panel7;
    public JPanel panelp;
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;
    public JLabel label4;
    public JLabel label5;
    public JLabel label6;
    public JLabel labelp;
    public JTextField input1;
    public JTextField input2;
    public JTextField input3;
    public JTextField input4;
    public JTextField input5;
    public JTextField input6;
    public JButton button1;
    public JButton button2;
    public static String username;
    public static String password1;
    public static String password2;
    public static String age;
    public static String gender;
    public static String email;
    public static String url = "jdbc:mysql://localhost:3306/user_info";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;

    public myregisterwindow(){
        frame = new JFrame("Welcome! New Client!");
        frame.setVisible(true);
        panel1 = new JPanel();
        panel1.setSize(500, 200);
        panel1.setLocation(0,0);
        label1 = new JLabel("username");
        input1 = new JTextField(username, 20);
        input1.setEditable(true);
        panel1.add(label1);
        panel1.add(input1);
        frame.add(panel1);
        panel2 = new JPanel();
        panel2.setSize(500, 200);
        panel2.setLocation(0, 200);
        label2 = new JLabel("password");
        input2 = new JTextField(password1, 20);
        input2.setEditable(true);
        panel2.add(label2);
        panel2.add(input2);
        frame.add(panel2);
        panel3 = new JPanel();
        panel3.setSize(500, 200);
        panel3.setLocation(0, 400);
        label3 = new JLabel("confirm password");
        input3 = new JTextField(password2, 20);
        input3.setEditable(true);
        panel3.add(label3);
        panel3.add(input3);
        frame.add(panel3);
        panel4 = new JPanel();
        panel4.setSize(500, 200);
        panel4.setLocation(0, 600);
        label4 = new JLabel("email address");
        input4 = new JTextField(email, 20);
        input4.setEditable(true);
        panel4.add(label4);
        panel4.add(input4);
        frame.add(panel4);
        panel6 = new JPanel();
        panel6.setSize(500,200);
        panel6.setLocation(0,800);
        label5 = new JLabel("Gender:");
        input5 = new JTextField(gender,10);
        input5.setEditable(true);
        panel6.add(label5);
        panel6.add(input5);
        frame.add(panel6);
        panel7 = new JPanel();
        panel7.setSize(500,200);
        panel7.setLocation(0,1000);
        label6 = new JLabel("Age:");
        input6 = new JTextField(age, 10);
        input6.setEditable(true);
        panel7.add(label6);
        panel7.add(input6);
        frame.add(panel7);
        panel5 = new JPanel();
        panel5.setSize(500, 200);
        panel5.setLocation(0, 800);
        button1 = new JButton("Manager Register");
        BtnCountListener listener = new BtnCountListener();
        button1.addActionListener(listener);
        button2 = new JButton("User Register");
        button2.addActionListener(listener);
        panel5.add(button1);
        panel5.add(button2);
        frame.add(panel5);
        ImageIcon bg = new ImageIcon("src/main/resources/background.jpg");
        labelp = new JLabel(bg);
        labelp.setSize(bg.getIconWidth(), bg.getIconHeight());
        frame.setSize(bg.getIconWidth(),bg.getIconHeight());
        frame.getLayeredPane().add(labelp, new Integer(Integer.MIN_VALUE));
        panelp = (JPanel) frame.getContentPane();
        panelp.setOpaque(false);
        panelp.setLayout(new FlowLayout());
    }
    class BtnCountListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            username = input1.getText();
            password1 = input2.getText();
            password2 = input3.getText();
            email = input4.getText();
            gender = input5.getText();
            age = input6.getText();
            if (username.isEmpty()||password1.isEmpty()||password2.isEmpty()||email.isEmpty()||gender.isEmpty()||age.isEmpty()){
                JOptionPane.showConfirmDialog(null, "Information Incomplete!");
            }
            else if (!password1.equals(password2)){
                JOptionPane.showConfirmDialog(null, "Password Incorrect!");
            }
            else if (!email.contains("@")){
                JOptionPane.showConfirmDialog(null, "Email Address Incorrect");
            }
            else{
                String buttonname = e.getActionCommand();
                if (buttonname.equals("Manager Register")){
                    try {
                        managerregister mr = new managerregister(username, password1, email, gender, age);
                        System.out.println("get in!");
                        JOptionPane.showConfirmDialog(null, "Register Success! You can Log in Now!");
                    }catch(Exception f){
                        System.out.println(f);
                    }
                }
                else{
                    try {
                        userregister ur = new userregister(username, password1, email, gender, age);
                        JOptionPane.showConfirmDialog(null, "Register Success! You can Log in Now!");
                    }catch(Exception f){
                        System.out.println(f);
                    }
                }
            }
        }
    }
}
