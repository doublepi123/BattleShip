package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);  // 设置窗体显示
                    frame.setResizable(false);  // 设置放大按钮禁用
                    frame.setLocationRelativeTo(null); // 设置居中屏幕显示
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {

        setType(Type.POPUP);
        setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
        setBounds(100, 100, 584, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome to BattleShip");
        lblNewLabel.setBounds(5, 5, 558, 34);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 29));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Please select your options and get started");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(103, 39, 364, 26);
        contentPane.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Choose Ship Placement");
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
        btnNewButton.setBounds(5, 75, 179, 34);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Choose Scoring System");
        btnNewButton_1.setBounds(194, 75, 164, 34);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("Choose the board"
                + " size Rows");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(394, 75, 164, 34);

        contentPane.add(lblNewLabel_2);

        JButton btnNewButton_1_1 = new JButton("Start Game");
        btnNewButton_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                // 页面跳转
                new Game();
                // 消失当前窗口
                dispose();
            }
        });
        btnNewButton_1_1.setBounds(194, 183, 164, 23);
        contentPane.add(btnNewButton_1_1);

        JButton btnNewButton_1_1_1 = new JButton("Rules\r\n");
        btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                new MyDialog1(Login.this).setVisible(true);
//				JOptionPane.showMessageDialog(null, "游戏规则:当玩家击中一艘船时，他们将获得一定数量的分数，这取决于击中的是哪艘船。你可以自行决定特定舰船的每击点数，但每艘舰船的每击点数必须不同。在任何情况下，如果玩家的命中导致船只沉没(游戏备注:即他们的命中意味着船只对应的所有贴图现在都被击中了)，那么他们将获得双倍的点数。两名玩家轮流点击灰色贴图，直到所有船只沉没，此时游戏结束。游戏结束时得分最高的玩家获胜。");
            }
        });
        btnNewButton_1_1_1.setBounds(0, 216, 169, 23);
        contentPane.add(btnNewButton_1_1_1);

        JButton btnNewButton_1_1_1_1 = new JButton("High Scores");
        btnNewButton_1_1_1_1.setBounds(179, 216, 194, 23);
        contentPane.add(btnNewButton_1_1_1_1);

        JButton btnNewButton_1_1_1_1_1 = new JButton("Exit");
        btnNewButton_1_1_1_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);

            }
        });
        btnNewButton_1_1_1_1_1.setBounds(394, 216, 169, 23);
        contentPane.add(btnNewButton_1_1_1_1_1);
    }



    // 规则弹窗
    class MyDialog1 extends JDialog{
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public MyDialog1(JFrame frame){
            super(frame,"Game Rules");
            Container conn = getContentPane();
            JLabel j=new JLabel();
            j.setText("<html>Game Rules:<br>When a player hits a ship, they receive a set number of points, depending on which ship is hit. It is up to you to determine the points-per-hit for<br> the specific ships, but the points-per-hit must be different for each ship. In any case, a player <br>receives double the usual number of points if their hit results in a ship sinking (i.e., their hit means that all tiles corresponding to the ship have <br>now been hit). The two players take turns clicking grey tiles until all ships have been sunk, at which point the game ends. The player with <br>the most points at the end of the game wins the game. </html>");
            conn.add(j);
            setBounds(100,100,300,500);
        }
    }


}
