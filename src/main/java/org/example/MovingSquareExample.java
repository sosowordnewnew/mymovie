package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MovingSquareExample {
    public static final JPanel square = new JPanel();
    public static int x = 20;

    public static void createAndShowGUI(){
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(square);
        square.setBounds(20,200,100,100);
        square.setBackground(Color.BLUE);

        Timer timer = new Timer(1000/60,new MyActionListener());
        timer.start();
        frame.setVisible(true);
    }

     static class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            square.setLocation(x++, 200);

        }

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                createAndShowGUI();

            }
        });


    }
}
