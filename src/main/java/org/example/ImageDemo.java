package org.example;
import java.awt.*;
import javax.swing.*;
public class ImageDemo {
    public static void main(String[] args) {
        JFrame jf=new JFrame("背景图片测试");
        //1.把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon bg=new ImageIcon("src/main/resources/background2.jpg");
        JLabel label=new JLabel(bg);
        label.setSize(bg.getIconWidth(),bg.getIconHeight());
        jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //2.把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan=(JPanel)jf.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());
        //3.之后把组件和面板添加到窗口面板就可以；
        JButton btn=new JButton("测试按钮");
        pan.add(btn);
        jf.setSize(bg.getIconWidth(),bg.getIconHeight());
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
