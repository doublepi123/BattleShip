/*
 * Created by JFormDesigner on Wed Mar 31 01:05:19 CST 2021
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
public class RankList extends JFrame {
    Rank rank = null;
    JScrollPane scrollPane1 = new JScrollPane();
    public RankList(){
        initRank();
        add(scrollPane1);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400,200);
    }
    void initRank(){
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
        String [][]data = new String[1][];
        data[0] = new String[10];
        for(int i = 0 ; i < 10 ; i++){
            data[0][i] = String.valueOf(rank.getData()[i]);
        }
        String []c = new String[1];
        c[0] = "RankList";
        DefaultTableModel defaultTableModel = new DefaultTableModel(data,c);
        JTable table  = new JTable(10, 1);
        scrollPane1.add(table);
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
