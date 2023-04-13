package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MovingSquareExample {
    public static JLabel image;
    public static int x = 0;
    public static int y = 500;
    public static JPanel panel;
    public void createAndShowGUI(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon bg = new ImageIcon("src/main/java/resources/bg3.jpg");
        frame.setSize(bg.getIconWidth(),bg.getIconHeight());
        image = new JLabel(bg);
        image.setSize(bg.getIconWidth(),bg.getIconHeight());
        frame.getLayeredPane().add(image,new Integer(Integer.MIN_VALUE));
        panel = (JPanel)frame.getContentPane();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        frame.setVisible(true);
    }

     static class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if ((x>500)||(y<0)) {
                x = 0;
                y = 500;
            }
            else image.setLocation(x++,y--);
        }

    }

}
