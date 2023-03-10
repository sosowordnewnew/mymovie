package org.example;
import java.sql.*;
import javax.swing.*;
public class mysqlbianli2 {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public mysqlbianli2(String username) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select username,password,email from user_info where username=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1,username);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()){
            System.out.println("此人用户名是："+rs.getString("username"));
            System.out.println("此人账号密码是："+rs.getString("password"));
            System.out.println("此人电子邮箱是："+rs.getString("email"));
        }
    }
}
