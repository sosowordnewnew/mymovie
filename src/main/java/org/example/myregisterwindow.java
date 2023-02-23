package org.example;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class myregisterwindow {
    public JFrame frame;
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JPanel panel4;
    public JPanel panel5;
    public JPanel panelp;
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;
    public JLabel label4;
    public JLabel labelp;
    public JTextField input1;
    public JTextField input2;
    public JTextField input3;
    public JTextField input4;
    public JButton button1;
    String username;
    String password1;
    String password2;
    String email;
    public myregisterwindow(){
        frame = new JFrame("Welcome! New Client!");
        frame.setSize(500, 1200);
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
        panel5 = new JPanel();
        panel5.setSize(500, 200);
        panel5.setLocation(0, 800);
        button1 = new JButton("Register");
        BtnCountListener listener = new BtnCountListener();
        button1.addActionListener(listener);
        panel5.add(button1);
        frame.add(panel5);
        ImageIcon bg = new ImageIcon("src/main/resources/background.jpg");
        labelp = new JLabel(bg);
        labelp.setSize(bg.getIconWidth(), bg.getIconHeight());
        frame.getLayeredPane().add(labelp, new Integer(Integer.MIN_VALUE));
        panelp = (JPanel) frame.getContentPane();
        panelp.setOpaque(false);
        panelp.setLayout(new FlowLayout());
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

    }
    class BtnCountListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            username = input1.getText();
            password1 = input2.getText();
            password2 = input3.getText();
            email = input4.getText();
            if (username.isEmpty()||password1.isEmpty()||password2.isEmpty()||email.isEmpty()){
                JOptionPane.showConfirmDialog(null, "Information Incomplete!");
            }
            else if (!password1.equals(password2)){
                JOptionPane.showConfirmDialog(null, "Password Incorrect!");
            }
            else if (!email.contains("@")){
                JOptionPane.showConfirmDialog(null, "Email Address Incorrect");
            }
            else{
                JOptionPane.showConfirmDialog(null, "Register Success!");
            }
        }
    }
}
