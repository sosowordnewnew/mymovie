package org.example;
import java.sql.*;
import javax.swing.*;

import static org.example.userlogin.ugender;
import static org.example.userlogin.uname;
import static org.example.userlogin.uage;

public class movierecommend {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public JFrame frame;
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;
    public movierecommend() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from movies";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        double maxrate = 0.0;
        String rmoviename = "";
        String rmovietype = "";
        while (rs.next()){
            String moviename = rs.getString("movienames");
            String movietype = rs.getString("description");
            double rating = Double.parseDouble(rs.getString("ratings"));
            int people = Integer.parseInt(rs.getString("people"));
            int typerate = getrate(ugender, uage, movietype);
            double rates = typerate*0.3+rating*0.5+people*0.2;
            if (rates>maxrate) {
                maxrate = rates;
                rmoviename = moviename;
                rmovietype = movietype;
            }
        }
        frame = new JFrame("Movie Recommendation");
        frame.setBounds(0,0,1400,400);
        frame.setVisible(true);
        label1 = new JLabel("The Recommended Movie is:");
        panel1 = new JPanel();
        panel1.setLocation(0,0);
        panel1.setSize(500,100);
        panel1.add(label1);
        frame.add(panel1);
        label2 = new JLabel(rmoviename);
        label2.setSize(500,100);
        panel2 = new JPanel();
        panel2.setLocation(0,100);
        panel2.setSize(500,100);
        panel2.add(label2);
        frame.add(panel2);
        String lb3 = "Since it is a "+rmovietype+" with a high rate of "+maxrate+" and we believe you will enjoy this movie!";
        label3 = new JLabel(lb3);
        label3.setSize(500,100);
        panel3 = new JPanel();
        panel3.setSize(500,100);
        panel3.setLocation(0,400);
        panel3.add(label3);
        frame.add(panel3);
    }
    public int getrate(String gender, String ages, String movietype) {
        int rate = 0;
        int age = Integer.parseInt(ages);
        if (gender.equals("male")) {
            if (age <= 30) {
                rate = switch (movietype) {
                    case "Scientific Movie" -> 3;
                    case "Action Movie" -> 3;
                    case "Comedy" -> 2;
                    case "Superhero Movie" -> 2;
                    case "Crime Movie" -> 2;
                    case "Patriotic Movie" -> 2;
                    case "Documentary" -> 2;
                    default -> 1;
                };
            } else if (age <= 60) {
                rate = switch (movietype) {
                    case "Comedy" -> 3;
                    case "Inspirational Movie" -> 3;
                    case "Documentary" -> 2;
                    case "Love Movie" -> 2;
                    case "Historical Movie" -> 2;
                    default -> 1;
                };
            } else {
                rate = switch (movietype) {
                    case "Comedy" -> 3;
                    case "Documentary" -> 3;
                    case "Historical Movie" -> 3;
                    case "Inspirational Movie" -> 2;
                    case "Love Movie" -> 2;
                    case "Patriotic Movie" -> 2;
                    default -> 1;
                };
            }
        } else {
            if (age <= 30) {
                rate = switch (movietype) {
                    case "Love Movie" -> 3;
                    case "Inspirational Movie" -> 3;
                    case "Comedy" -> 2;
                    case "Anime Movie" -> 2;
                    case "Horror Movie" -> 2;
                    default -> 1;
                };
            } else if (age <= 60) {
                rate = switch (movietype) {
                    case "Comedy" -> 3;
                    case "Inspirational Movie" -> 3;
                    case "Documentary" -> 2;
                    case "Love Movie" -> 2;
                    case "Historical Movie" -> 2;
                    default -> 1;
                };
            } else {
                rate = switch (movietype) {
                    case "Comedy" -> 3;
                    case "Documentary" -> 3;
                    case "Historical Movie" -> 3;
                    case "Inspirational Movie" -> 2;
                    case "Love Movie" -> 2;
                    case "Patriotic Movie" -> 2;
                    default -> 1;
                };
            }
        }
        return rate;
    }
}
