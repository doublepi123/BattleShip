/*
 * Created by JFormDesigner on Tue Mar 30 19:57:24 CST 2021
 */

package View;

import java.awt.event.*;
import model.GameModel;

import java.awt.*;
import java.security.MessageDigest;
import javax.swing.*;

/**
 * @author unknown
 */
public class GameWindow extends JFrame {
    GameModel gameModel;
    GButton [][]shipCard;
    public GameWindow() {
        initComponents();
        initGame();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Chenyang
        label1 = new JLabel();
        label2 = new JLabel();
        play1Score = new JLabel();
        play2Score = new JLabel();
        panel1 = new JPanel();
        exit = new JButton();
        action1 = new quit();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Play 1");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(35, 15), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Play 2");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(315, 15), label2.getPreferredSize()));

        //---- play1Score ----
        play1Score.setText("text");
        contentPane.add(play1Score);
        play1Score.setBounds(new Rectangle(new Point(100, 15), play1Score.getPreferredSize()));

        //---- play2Score ----
        play2Score.setText("text");
        contentPane.add(play2Score);
        play2Score.setBounds(new Rectangle(new Point(265, 15), play2Score.getPreferredSize()));

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.
            awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder()))
            ;panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;
            panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        }
        contentPane.add(panel1);
        panel1.setBounds(15, 90, 355, 350);

        //---- exit ----
        exit.setAction(action1);
        contentPane.add(exit);
        exit.setBounds(new Rectangle(new Point(260, 40), exit.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(390, 480);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Chenyang
    private JLabel label1;
    private JLabel label2;
    private JLabel play1Score;
    private JLabel play2Score;
    private JPanel panel1;
    private JButton exit;
    private quit action1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private class quit extends AbstractAction {
        private quit() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - Chenyang
            putValue(NAME, "Quit Game");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
    private void initGame(){
        play2Score.setText("0");
        play1Score.setText("0");
        gameModel = new GameModel(2);
        shipCard = new GButton[8][];
        for(int i = 0 ; i < 8 ; i++){
            shipCard[i] = new GButton[8];
        }
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                shipCard[i][j] = new GButton();
                shipCard[i][j].setPreferredSize(new Dimension(40,40));
                shipCard[i][j].setPx(i);
                shipCard[i][j].setPy(j);
                shipCard[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        GButton gButton = (GButton) e.getSource();
                        GameWindow gameWindow = (GameWindow) gButton.getParent().getParent().getParent().getParent().getParent();
                        int ans = gameModel.tryATry(gButton.getPx(),gButton.getPy());
                        if(ans == 0){
                            JOptionPane.showMessageDialog(null, "You hit in the Ocean!", "Waring", JOptionPane.WARNING_MESSAGE);
                            gButton.setForeground(Color.blue);
                            gButton.setBackground(Color.blue);
                        }else if(ans == -1){
                            JOptionPane.showMessageDialog(null,"You have clicked this place!","Warining",JOptionPane.WARNING_MESSAGE);
                        }else{
//                            GameWindow gameWindow = (GameWindow) gButton.getParent().getParent().getParent().getParent();
                            gameWindow.play1Score.setText(String.valueOf(gameModel.getP1()));
                            gameWindow.play2Score.setText(String.valueOf(gameModel.getP2()));
//                            int numberOfShip = gameModel.getNumberOfShip();
//                            gButton.setForeground(new Color(gameModel.getShipColor()[gameModel.get]));
                        }
                    }
                });
                panel1.add(shipCard[i][j]);
            }
        }
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class GButton extends JButton{
    int px,py;
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }
}
