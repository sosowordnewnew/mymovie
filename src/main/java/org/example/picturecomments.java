package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.sql.*;
public class picturecomments {
    public JFrame frame;
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public JLabel[] imagelabels;
    public String[] imagepaths;

    public picturecomments(String moviename) throws Exception {
        frame = new JFrame("Pictures in the Comments");
        frame.setBounds(0, 0, 1400, 1000);
        frame.setVisible(true);
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select pictures from comments where moviename=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1, moviename);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            String imagepath = rs.getString("pictures");
            imagepaths = imagepath.split("@");
        }
        imagelabels = new JLabel[imagepaths.length];
        for (int i = 0; i < imagelabels.length; i++) {
               try {
                   File imagefile = new File(imagepaths[i]);
                   System.out.println(imagepaths[i]);
                   ImageIcon imageicon = new ImageIcon(ImageIO.read(imagefile));
                   imagelabels[i] = new JLabel();
                   imagelabels[i].setIcon(imageicon);
                   imagelabels[i].setSize(300, 300);
                   frame.add(imagelabels[i]);
               } catch(Exception f){
                   System.out.println(f);
               }
        }
    }
}
