package org.example;
import java.sql.*;
public class ratingupdate {
    public String url = "java:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public ratingupdate(int n, int k, int total, String moviename) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "update movies set (ratings,people) values(?,?) where movienames=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        double t = (k*total+n)*1.0/(total+1);
        String insert = String.valueOf(t);
        String peoples = String.valueOf(total+1);
        ptmt.setString(1,insert);
        ptmt.setString(2,peoples);
        ptmt.setString(3,moviename);
        ptmt.execute();
    }
}
