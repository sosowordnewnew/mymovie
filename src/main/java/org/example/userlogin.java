package org.example;
import java.sql.*;
public class userlogin {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public static Boolean isexist;
    public userlogin(String username, String password) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select username,password from user_info where username=? and password=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1,username);
        ptmt.setString(2,password);
        ResultSet rs = ptmt.executeQuery();
        if (rs.next()){
            isexist = true;
        }
        else{
            isexist = false;
        }
    }
    public static Boolean check(){
        return isexist;
    }
}
