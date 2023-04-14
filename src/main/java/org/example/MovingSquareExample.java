package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class MovingSquareExample {
    public JLabel image;
    public int x = 0;
    public int y = 500;
    public JPanel panel;
    private static Timer timer;
    public MovingSquareExample(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon bg = new ImageIcon("src/main/resources/bg3.jpg");
        frame.setSize(500,700);
        image = new JLabel(bg);
        image.setSize(200,200);
        frame.getLayeredPane().add(image, 0);
        panel = (JPanel) frame.getContentPane();
        image.setLocation(x,y);
        panel.setOpaque(false);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if ((x<500)&&(y>0)){
                x = x+10;
                y = y-10;
                image.setLocation(x,y);}
                else {
                    x = 0;
                    y = 500;
                }
            }
        },0,100);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        MovingSquareExample mse = new MovingSquareExample();
    }
}
