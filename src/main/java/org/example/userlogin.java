package org.example;
import java.sql.*;
public class userlogin {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public static Boolean isexist;
    public static String uname = "";
    public static String ugender = "";
    public static String uage = "";
    public userlogin(String username, String password) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from user_info where username=? and password=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1,username);
        ptmt.setString(2,password);
        ResultSet rs = ptmt.executeQuery();
        if (rs.next()){
            isexist = true;
            uname = rs.getString("username");
            ugender = rs.getString("gender");
            uage = rs.getString("age");
        }
        else{
            isexist = false;
        }
    }
    public static Boolean check(){
        return isexist;
    }
}
