/*
 * Created by JFormDesigner on Tue Mar 30 19:57:24 CST 2021
 */

package View;

import java.awt.event.*;
import model.GameModel;
import model.Rank;

import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import javax.swing.*;

/**
 * @author unknown
 */
public class GameWindow extends JFrame {
    GameModel gameModel;
    GButton [][]shipCard;
    int row,col,model;
    public GameWindow(int row,int col,int model) {
        this.model = model;
        this.row = row;
        this.col = col;
        initComponents();
        initGame();

    }

    public GameModel getGameModel() {
        return gameModel;
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
        btnHighScore = new JButton();
        btnNewGame = new JButton();
        action1 = new quit();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Play 1");
        contentPane.add(label1);
        label1.setBounds(35, 20, 110, 45);

        //---- label2 ----
        label2.setText("Play 2");
        contentPane.add(label2);
        label2.setBounds(340, 20, 110, 45);

        //---- play1Score ----
        play1Score.setText("text");
        contentPane.add(play1Score);
        play1Score.setBounds(160, 20, 90, 45);

        //---- play2Score ----
        play2Score.setText("text");
        contentPane.add(play2Score);
        play2Score.setBounds(250, 20, 90, 45);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
            , 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
            , new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
            panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        }
        contentPane.add(panel1);
        panel1.setBounds(5, 90, 675, 685);

        //---- exit ----
        exit.setAction(action1);
        contentPane.add(exit);
        exit.setBounds(new Rectangle(new Point(560, 55), exit.getPreferredSize()));

        //---- btnHighScore ----
        btnHighScore.setText("High Score");
        contentPane.add(btnHighScore);
        btnHighScore.setBounds(new Rectangle(new Point(445, 55), btnHighScore.getPreferredSize()));

        //---- btnNewGame ----
        btnNewGame.setText("New Game");
        contentPane.add(btnNewGame);
        btnNewGame.setBounds(new Rectangle(new Point(560, 15), btnNewGame.getPreferredSize()));

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
        setSize(705, 825);
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
    private JButton btnHighScore;
    private JButton btnNewGame;
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
    private void finish(){
        int winner;
        if(gameModel.getP1() > gameModel.getP2()) winner = 1;
        else winner = 2;
        JOptionPane.showMessageDialog(null,"Play"+winner+" is win!Game is over!","Game Over",JOptionPane.WARNING_MESSAGE);
        for(int i = 0 ; i < row ; i++)
            for(int j = 0 ; j < col ; j++){
                int numberOfShip = gameModel.getNumberOfShip(i,j);
                shipCard[i][j].setForeground(gameModel.getShipColor(numberOfShip));
                shipCard[i][j].setBackground(gameModel.getShipColor(numberOfShip));
            }
        saveScore();
    }
    private void initGame(){
        Font font=new Font("宋体",Font.BOLD,24);
        btnHighScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankWindow();
            }
        });
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameWindow(row,col,model);
                dispose();
            }
        });
        ((JFrame)rootPane.getParent()).setTitle("BattleShip");
        label1.setFont(font);
        label2.setFont(font);
        play1Score.setFont(font);
        play2Score.setFont(font);
        play2Score.setText("0");
        play1Score.setText("0");
        boolean flag = true;
        while(flag) {
            try {
                gameModel = new GameModel(row, col,model);
                flag = false;
            } catch (java.lang.StackOverflowError e) {
                flag = true;
            }
        }
        shipCard = new GButton[row][];
        for(int i = 0 ; i < row ; i++){
            shipCard[i] = new GButton[col];
        }
        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col ; j++){
                shipCard[i][j] = new GButton();

                shipCard[i][j].setPreferredSize(new Dimension(640/row,640/col));
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
                            int numberOfShip = gameModel.getNumberOfShip(gButton.getPx(),gButton.getPy());
                            gButton.setForeground(gameModel.getShipColor(numberOfShip));
                            gButton.setBackground(gameModel.getShipColor(numberOfShip));
                            if(gameModel.check()) finish();
                        }
                    }
                });
                panel1.add(shipCard[i][j]);
            }
        }
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        finish();
    }
    void saveScore(){
        Rank rank = null;
        try
        {
            FileInputStream fis =
                    new FileInputStream("score.data");
            ObjectInputStream in = new ObjectInputStream(fis);
            rank = (Rank) in.readObject();
            in.close();
            fis.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            rank = new Rank();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            rank = new Rank();
        }
        rank.push(Math.max(gameModel.getP1(),gameModel.getP2()));
        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream("score.data");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(rank);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
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
