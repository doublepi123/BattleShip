package View;

import javax.swing.*;
public class Game{
    JFrame frame;
    public Game(){
        frame = new JFrame("Gameing");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel jPanel = new JPanel();
        JButton btn = new JButton("我是一个按钮");
        jPanel.add(btn);
        frame.add(jPanel);
        frame.setVisible(true);
    }
}
