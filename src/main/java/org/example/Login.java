package org.example;
import java.sql.*;
import java.util.*;
public class Login {
        public static String username;//用户登录注册的姓名
        public static String password;//用户密码
        public static String url = "jdbc:mysql://localhost:3306/user_info";
        public static String user = "root";//mysql登录名
        public static String pass = "547471wjs";//mysql登录密码（写自己之前设置的）
        public static Connection con;//
        public static Scanner input = new Scanner(System.in);

        public static void main(String[] args) throws Exception {
            //加载数据库连接驱动并连接
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

            System.out.println("********用户界面********");
            System.out.println("请选择：\n 1:用户登录\n 2：用户注册");
            System.out.println("**********************");

            int i = input.nextInt();
            switch (i) {
                case 1:
                    denglu();
                    break;
                case 2:
                    zhuce();
                    break;
                default:
                    System.out.println("输入有误！");
                    System.exit(0);
            }

        }

        //用户注册
        public static void zhuce() throws SQLException {
            System.out.println("请输入你的姓名：");
            username = input.next();
            System.out.println("请输入你的登录密码：");
            String p1 = input.next();
            System.out.println("请再次输入你的确认密码：");
            String p2 = input.next();
            if (p1.equals(p2)) {
                //两次输入的密码相同才可以注册
                password = p1;
                String sql = "insert into user_info (username,password) values(?,?)";
                PreparedStatement ptmt = con.prepareStatement(sql);
                ptmt.setString(1, username);
                ptmt.setString(2, password);
                ptmt.execute();
                System.out.println("注册成功！\n请登录：");
                denglu();
            } else {
                System.out.println("你输入的密码与确认密码不相符，请重新注册：");
                zhuce();
            }

        }

        //用户登录
        public static void denglu() throws SQLException {
            System.out.println("请输入你的姓名：");
            username = input.next();
            System.out.println("请输入你的密码：");
            password = input.next();

            String sql = "select username,password from user_info where username=? and password=?";
            PreparedStatement ptmt = con.prepareStatement(sql);
            ptmt.setString(1, username);
            ptmt.setString(2, password);
            ResultSet rs = ptmt.executeQuery();
            //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
            if (rs.next()) {
                System.out.println("登录成功！");
            } else {
                System.out.println("姓名或密码错误！\n请重新登录：");
                denglu();
            }

        }
    }


