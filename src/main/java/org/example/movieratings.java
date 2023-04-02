package org.example;
import java.sql.*;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import javax.swing.*;

public class movieratings extends Application {
    public String url = "jdbc:mysql://localhost:3306/mymovie";
    public String user = "root";
    public String pass = "547471wjs";
    public Connection con;
    public JFrame frame;
    public BarChart bc;
    public void start(Stage primaryStage) throws Exception{
        CategoryAxis xAxis = (CategoryAxis) bc.getXAxis();
        NumberAxis yAxis = (NumberAxis) bc.getYAxis();
        bc.setTitle("Movie Ratings");
        xAxis.setLabel("Movies");
        yAxis.setLabel("Ratings");
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);
        String sql = "select movienames,ratings.description from movies";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        while(rs.next()) {
            String moviename = rs.getString("movienames");
            double rating = Double.parseDouble(rs.getString("ratings"));
            series.getData().add(new XYChart.Data<>(moviename, rating));
        }
        bc.getData().add(series);
        Group root = new Group();
        root.getChildren().add(bc);
        Scene scene = new Scene(root,600,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Movies Ratings Information");
        primaryStage.show();
    }
    public static void main(String[] args1){
        launch(args1);
    }
}
