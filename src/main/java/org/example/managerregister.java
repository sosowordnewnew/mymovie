package org.example;
import java.sql.*;
public class managerregister {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public managerregister(String username, String password, String email) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "insert into manager_info (username,password,email) values(?,?,?)";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1,username);
        ptmt.setString(2,password);
        ptmt.setString(3,email);
        ptmt.execute();
    }
}
