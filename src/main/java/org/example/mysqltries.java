package org.example;
import java.sql.*;
import java.util.*;
public class mysqltries {
    public static String url = "jdbc:mysql://localhost:3306/mymovie";
    public static String user = "root";
    public static String pass = "547471wjs";
    public static Connection con;
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        String name= scan.nextLine();
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        String sql = "select comment from comments where moviename=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1,name);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            String comments = rs.getString("comment");
            String[] t = comments.split("@");
            for (int i = 0; i<t.length; i++){
                System.out.println(t[i]);
            }
        }
    }
}
