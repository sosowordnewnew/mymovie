package org.example;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class myloginwindow {
    public JFrame frame;
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JPanel panel4;
    public JPanel panelp;
    public JPanel panelb;
    public JLabel label1;
    public JLabel label2;
    public JLabel labelp;
    public JTextField input1;
    public JTextField input2;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public static String username;
    public static String password;

    public myloginwindow()
    {
        frame = new JFrame("Welcome to MyMovie! Please Log In");
        frame.setLayout(null);
        frame.setVisible(true);
        panel1 = new JPanel();
        panel1.setSize(500, 200);
        panel1.setLocation(0,0);
        label1 = new JLabel("Username:");
        input1 = new JTextField(username, 20);
        input1.setEditable(true);
        panel1.add(label1);
        panel1.add(input1);
        frame.add(panel1);
        panel2 = new JPanel();
        panel2.setSize(500, 200);
        panel2.setLocation(0, 200);
        label2 = new JLabel("Password:");
        input2 = new JTextField(password, 20);
        input2.setEditable(true);
        panel2.add(label2);
        panel2.add(input2);
        frame.add(panel2);
        panel3 = new JPanel();
        panel3.setSize(500, 200);
        panel3.setLocation(0, 400);
        button1 = new JButton("Manager Login");
        BtnCountListener listener1 = new BtnCountListener();
        button1.addActionListener(listener1);
        button2 = new JButton("User Login");
        button2.addActionListener(listener1);
        panel3.add(button1);
        panel3.add(button2);
        frame.add(panel3);
        panel4 = new JPanel();
        panel4.setSize(500, 200);
        panel4.setLocation(0, 600);
        button3 = new JButton("Register");
        button3.addActionListener(listener1);
        panel4.add(button3);
        frame.add(panel4);
        ImageIcon bg = new ImageIcon("src/main/resources/background2.jpg");
        labelp = new JLabel(bg);
        labelp.setSize(bg.getIconWidth(), bg.getIconHeight());
        frame.setSize(bg.getIconWidth(),bg.getIconHeight());
        frame.getLayeredPane().add(labelp, new Integer(Integer.MIN_VALUE));
        panelp = (JPanel)frame.getContentPane();
        panelp.setOpaque(false);
        panelp.setLayout(new FlowLayout());
        panelb = new JPanel();
        panelb.setLocation(0,800);
        panelb.setSize(500,200);
        button4 = new JButton("Forget Password");
        button4.addActionListener(listener1);
        panelb.add(button4);
        frame.add(panelb);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    class BtnCountListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            username = input1.getText();
            password = input2.getText();
            String buttonname = e.getActionCommand();
            if (buttonname.equals("Register")){
                myregisterwindow app2 = new myregisterwindow();
            }
            else if (buttonname.equals("Forget Password")){
                try{
                    findpassword fp = new findpassword();
                } catch(Exception f){
                    System.out.println(f);
                }
            }
            else{
                    if(username.isEmpty()||password.isEmpty()){
                        JOptionPane.showConfirmDialog(null, "Information Incomplete! Please try again!");
                    }
                    else if (buttonname.equals("Manager Login")){
                        try {
                            managerlogin ml = new managerlogin(username, password);
                            if (!ml.check()) {
                                JOptionPane.showConfirmDialog(null, "Username/Password Incorrect! Please Try Again!");
                            } else {
                                managerfrontface mf = new managerfrontface();
                            }
                        }catch(Exception f){
                            System.out.println(f);
                        }
                    }
                    else if (buttonname.equals("User Login")) {
                        try {
                            userlogin ul = new userlogin(username, password);
                            if (!ul.check()) {
                                JOptionPane.showConfirmDialog(null, "Username/Password Incorrect! Please Try Again!");
                            } else {
                                userfrontface uf = new userfrontface();
                            }
                        }catch(Exception f){
                            System.out.println(f);
                        }
                    }
            }
        }
    }
}
