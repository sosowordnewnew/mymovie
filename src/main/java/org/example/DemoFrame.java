package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoFrame extends JFrame{

    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    JPanel panel6;

    JLabel label1;
    JLabel label2;
    JTextArea description;
    JLabel label3;
    String input;
    JLabel label4;
    JCheckBox box1;
    JCheckBox box2;
    JCheckBox box3;
    JCheckBox box4;
    JCheckBox box5;
    public DemoFrame(){
        panel1 = new JPanel();
        panel2 = new JPanel();
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        setBounds(300, 200, 500, 600);
        add(sp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        sp.setDividerLocation(0.33);
        label1 = new JLabel("Movie names");
        label2 = new JLabel("Detailed Information");
        description = new JTextArea(input, 5, 30);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        label3 = new JLabel("Ratings: 3.9/5.0");
        label4 = new JLabel("Your Rating:");
        panel1.add(label1);
        label2.setLocation(250, 0);
        description.setLocation(200, 100);
        label3.setLocation(250, 400);
        label4.setLocation(250, 600);
        panel2.add(label2);
        panel2.add(description);
        panel2.add(label3);
        panel2.add(label4);
        ActionListener actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (box1.isSelected()){
                    JOptionPane.showConfirmDialog(null, "呜呜呜！为啥要给差评！");
                }
                else if(box2.isSelected()){
                    JOptionPane.showConfirmDialog(null, "好低的分。。。能说一哈哪里不满意吗？");
                }
                else if (box3.isSelected()){
                    JOptionPane.showConfirmDialog(null, "您是懂中规中矩的。。。");
                }
                else if (box4.isSelected()){
                    JOptionPane.showConfirmDialog(null, "感谢评价！欢迎下次光临！");
                }
                else{
                    JOptionPane.showConfirmDialog(null, "哇！五星好评！您晚上可以预约嗦牛牛哟！");
                }
            }
        };
        box1 = new JCheckBox("1");
        box2 = new JCheckBox("2");
        box3 = new JCheckBox("3");
        box4 = new JCheckBox("4");
        box5 = new JCheckBox("5");
        box1.addActionListener(actionlistener);
        box2.addActionListener(actionlistener);
        box3.addActionListener(actionlistener);
        box4.addActionListener(actionlistener);
        box5.addActionListener(actionlistener);
        box1.setLocation(200, 600);
        box2.setLocation(220, 600);
        box3.setLocation(240, 600);
        box4.setLocation(260, 600);
        box5.setLocation(280, 600);
        panel2.add(box1);
        panel2.add(box2);
        panel2.add(box3);
        panel2.add(box4);
        panel2.add(box5);
    }
    public static void main (String[] args){
        new DemoFrame();
    }
}
