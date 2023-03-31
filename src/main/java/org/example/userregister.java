package org.example;
import java.sql.*;
public class userregister {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public userregister(String username, String password, String email) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "insert into user_info (username,password,email,setupdate) values(?,?,?,NOW())";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1,username);
        ptmt.setString(2,password);
        ptmt.setString(3,email);
        ptmt.execute();
    }
}
