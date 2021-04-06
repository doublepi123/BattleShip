package View;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application. 
	 */

	/**
	 * Create the frame.
	 */
	public Login() {
		JComboBox jComboBox2;
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
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 29));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please select your options and get started");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(103, 39, 364, 26);
		contentPane.add(lblNewLabel_1);
		JComboBox jComboBox = new JComboBox<String>();
		jComboBox.addItem("Random Ship");
		jComboBox.setBounds(5, 75, 179, 34);
		contentPane.add(jComboBox);
		JComboBox jComboBox1 = new JComboBox<String>();
		jComboBox1.addItem("Default Scoring System");
		jComboBox1.addItem("Different Scoring System");
		jComboBox1.setBounds(194, 75, 164, 34);
		contentPane.add(jComboBox1);
		jComboBox2 = new JComboBox<String>();
		jComboBox2.addItem("8x8");
		jComboBox2.addItem("7x7");
		jComboBox2.addItem("6x6");
		jComboBox2.setBounds(394, 75, 164, 34);
		contentPane.add(jComboBox2);
//		JButton btnNewButton = new JButton("Choose Ship Placement");
//		btnNewButton.setFont(new Font("����", Font.PLAIN, 12));
//		btnNewButton.setBounds(5, 75, 179, 34);
//		contentPane.add(btnNewButton);
//
//		JButton btnNewButton_1 = new JButton("Choose Scoring System");
//		btnNewButton_1.setBounds(194, 75, 164, 34);
//		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Choose the board"
				+ " size Rows");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(394, 50, 164, 34);
		
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1_1 = new JButton("Start Game");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Here");
				int index = jComboBox2.getSelectedIndex();
				int model = jComboBox1.getSelectedIndex();
				System.out.println(index);
				if(index== 0)
					new GameWindow(8,8,model);
				else if(index == 1)
					new GameWindow(7,7,model);
				else new GameWindow(6,6,model);
				// ��ʧ��ǰ����
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
//				JOptionPane.showMessageDialog(null, "��Ϸ����:����һ���һ�Ҵ�ʱ�����ǽ����һ�������ķ�������ȡ���ڻ��е������Ҵ�����������о����ض�������ÿ����������ÿ�ҽ�����ÿ���������벻ͬ�����κ�����£������ҵ����е��´�ֻ��û(��Ϸ��ע:�����ǵ�������ζ�Ŵ�ֻ��Ӧ��������ͼ���ڶ���������)����ô���ǽ����˫���ĵ���������������������ɫ��ͼ��ֱ�����д�ֻ��û����ʱ��Ϸ��������Ϸ����ʱ�÷���ߵ���һ�ʤ��");
			}
		});
		btnNewButton_1_1_1.setBounds(0, 216, 169, 23);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("High Scores");
		btnNewButton_1_1_1_1.setBounds(179, 216, 194, 23);
		contentPane.add(btnNewButton_1_1_1_1);
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RankWindow();
			}
		});
		
		JButton btnNewButton_1_1_1_1_1 = new JButton("Exit");
		btnNewButton_1_1_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
				
			}
		});
		btnNewButton_1_1_1_1_1.setBounds(394, 216, 169, 23);
		contentPane.add(btnNewButton_1_1_1_1_1);
		setVisible(true);

	}


	
	// ���򵯴�
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
