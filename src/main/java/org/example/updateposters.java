package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
public class updateposters {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public JTextArea comments;
    public JPanel panel1;
    public JPanel panel2;
    public JButton button;
    public JButton button2;
    public String posterpath;
    public updateposters(String moviename) throws Exception{
        frame = new JFrame("Your Comments");
        frame.setBounds(0,0,1400,700);
        frame.setVisible(true);
        panel1 = new JPanel();
        panel1.setSize(500,300);
        panel1.setLocation(0,0);
        comments = new JTextArea(posterpath, 5,40);
        comments.setEditable(true);
        comments.setLineWrap(true);
        comments.setWrapStyleWord(true);
        panel1.add(comments);
        frame.add(panel1);
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql1 = "update movies set posters=? where movienames=?";
        String sql2 = "select posters from movies where movienames=?";
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonname = e.getActionCommand();
                if (buttonname.equals("Upload Posters")){
                    String posterpath = comments.getText();
                    try{
                        PreparedStatement ptmt = con.prepareStatement(sql2);
                        ptmt.setString(1,moviename);
                        ResultSet rs = ptmt.executeQuery();
                        String fposterpath = "";
                        while(rs.next()){
                            fposterpath = rs.getString("posters");
                        }
                        String eposterpath = fposterpath+"@"+posterpath;
                        PreparedStatement ptmt2 = con.prepareStatement(sql1);
                        ptmt2.setString(1, eposterpath);
                        ptmt2.setString(2, moviename);
                        ptmt2.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "upload success!");
                    } catch(Exception f){
                        System.out.println(f);
                    }
                }
                else{
                    JFileChooser filechooser = new JFileChooser();
                    int result = filechooser.showOpenDialog(frame);
                    if (result==JFileChooser.APPROVE_OPTION){
                        File selectedfile=  filechooser.getSelectedFile();
                        comments.setText(selectedfile.getAbsolutePath());
                    }
                }
            }
        };
        button = new JButton("Upload Posters");
        button.addActionListener(listener);
        button2 = new JButton("Browse");
        button2.addActionListener(listener);
        panel2 = new JPanel();
        panel2.setLocation(0,300);
        panel2.setSize(500,200);
        panel2.add(button);
        panel2.add(button2);
        frame.add(panel2);
    }
}
