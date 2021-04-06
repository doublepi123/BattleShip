/*
 * Created by JFormDesigner on Wed Mar 31 01:40:07 CST 2021
 */

package View;

import model.Rank;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author unknown
 */
public class RankWindow extends JFrame {
    Rank rank = null;
    public RankWindow() {
        initComponents();
        initRank();
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder (
            new javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  ""
            , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
            , new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 )
            ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener(
            new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );
            panel1.setLayout(new FlowLayout());
        }
        contentPane.add(panel1);
        panel1.setBounds(45, 50, 65, 360);

        //---- label1 ----
        label1.setText("RANKLIST");
        contentPane.add(label1);
        label1.setBounds(15, 10, 120, 50);

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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    void initRank(){
        Font font=new Font("宋体",Font.BOLD,24);
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
        label1.setFont(font);
        for(int i = 0 ; i < 10 ; i++){
            JLabel jLabel = new JLabel(String.valueOf(rank.getData()[i])+"  ");
            jLabel.setFont(font);
            panel1.add(jLabel);
        }
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
