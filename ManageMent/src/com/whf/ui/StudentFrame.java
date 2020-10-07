package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class StudentFrame extends JFrame{

	JFrame frame;
	private JTextField textField11;
	private JTextField textField22;
	private JTextField stupNameText;
  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrame frame = new StudentFrame();
					frame.frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u5B66\u751F\u4E3B\u754C\u9762");
		frame.setBounds(430, 177, 510, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u60A8\uFF1A");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel.setBounds(96, 46, 74, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField11 = new JTextField();
		textField11.setFont(new Font("SimSun", Font.BOLD, 20));
		textField11.setEditable(false);
		textField11.setBounds(154, 37, 99, 33);
		frame.getContentPane().add(textField11);
		textField11.setColumns(10);
		String l1=LoginFrame.stupNameText.getText();
		textField11.setText(l1);
	    textField11.setBorder(null);
	    textField11.setOpaque(false);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u8BFE\u8868");
		btnNewButton.setIcon(new ImageIcon(StudentFrame.class.getResource("/images/table.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toJfCourse(e);
			}
		});
		btnNewButton.setBounds(96, 125, 117, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u7591\u95EE\u53CD\u9988");
		btnNewButton_1.setIcon(new ImageIcon(StudentFrame.class.getResource("/images/question.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转至疑问反馈
				this.dispose1();//当前的窗体关闭
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Questionfeedback frame = new Questionfeedback ();
							frame.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}

			private void dispose1() {
				frame.dispose();
				
			}
		});
		btnNewButton_1.setBounds(287, 125, 125, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("\u9009\u62E9\u8BD5\u5377");
		btnNewButton_1_1.setIcon(new ImageIcon(StudentFrame.class.getResource("/images/perper.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toJ_ExamingUI(e);
			}
		});
		btnNewButton_1_1.setBounds(96, 188, 117, 33);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("\u67E5\u8BE2\u8003\u8BD5\u6210\u7EE9");
		btnNewButton_1_1_1.setIcon(new ImageIcon(StudentFrame.class.getResource("/images/paper.png")));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toSelectedScore(e);
				
			}
		});
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			toScore(e);
				
				
			}
		});
		btnNewButton_1_1_1.setBounds(287, 188, 133, 33);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_2 = new JButton("\u8FD4\u56DE\u767B\u5F55");
		btnNewButton_1_1_1_2.setIcon(new ImageIcon(StudentFrame.class.getResource("/images/goBack.png")));
		btnNewButton_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(e);
			}
		});
		btnNewButton_1_1_1_2.setBounds(96, 248, 117, 33);
		frame.getContentPane().add(btnNewButton_1_1_1_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(StudentFrame.class.getResource("/images/stu.bg3.jpg")));
		lblNewLabel_1.setBounds(0, 0, 500, 313);
		frame.getContentPane().add(lblNewLabel_1);
	}

	protected void toSelectedScore(ActionEvent e) {
		
		
		
		
		
		
	}

	//跳转至课程表
	protected void toJfCourse(ActionEvent e) {
		System.out.println("ddddd");
		this.dispose1();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JfCourse frame = new JfCourse();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	protected void toJ_ExamingUI(ActionEvent e) {
		this.dispose1();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JexamU frame = new JexamU();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}

	protected void toScore(MouseEvent e) {
		this.dispose1();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreFrame frame = new ScoreFrame();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	protected void goBack(ActionEvent e) {
		this.dispose1();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	private void dispose1() {
		frame.dispose();
	}

}
