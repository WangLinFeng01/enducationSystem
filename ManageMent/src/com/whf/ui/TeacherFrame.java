package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherFrame extends JFrame {
	
	JFrame frame;
	private JTextField textField;
	private JTextField stupNameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherFrame frame= new TeacherFrame();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the application.
	 */
	public TeacherFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u6559\u5E08\u7BA1\u7406\u754C\u9762");
		frame.setBounds(100, 100, 511, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u60A8\uFF1A");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel.setBounds(121, 43, 64, 18);
		
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u8BFE\u8868\u4FE1\u606F");
		btnNewButton.addMouseListener(new MouseAdapter() {
		
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toJfCourse(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(TeacherFrame.class.getResource("/images/course.png")));
		btnNewButton.setBounds(70, 103, 141, 32);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5B66\u751F\u70B9\u540D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toRandomRollCallUI(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(TeacherFrame.class.getResource("/images/student.png")));
		btnNewButton_1.setBounds(288, 103, 141, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setFont(new Font("SimSun", Font.BOLD, 20));
		textField.setToolTipText("\u7528\u6237\u6635\u79F0");
		textField.setEditable(false);
		textField.setBounds(184, 34, 110, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		String a=LoginFrame.stupNameText.getText();
		textField.setText(a);
		textField.setBorder(null);
		
		JButton btnNewButton_1_1 = new JButton("\u5B66\u751F\u6210\u7EE9\u67E5\u8BE2");
		btnNewButton_1_1.setBounds(288, 213, 141, 32);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("\u67E5\u770B\u8BFE\u7A0B\u8FDB\u5EA6");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toJ_scheduleUI(e);
			}
		});
		btnNewButton_1_2.setBounds(70, 155, 141, 32);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("\u5B66\u751F\u7591\u70B9\u53CD\u9988");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toQueryfeedback(e);
			}
		});
		btnNewButton_1_3.setBounds(70, 213, 141, 32);
		frame.getContentPane().add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("\u8BBE\u7F6E\u8003\u5377");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toQuestionSetting(e);
			}
		});
		btnNewButton_1_4.setBounds(288, 155, 141, 32);
		frame.getContentPane().add(btnNewButton_1_4);
		
		JButton btnNewButton_1_1_2 = new JButton("\u8FD4\u56DE\u767B\u5F55");
		btnNewButton_1_1_2.setIcon(new ImageIcon(TeacherFrame.class.getResource("/images/goBack.png")));
		btnNewButton_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(e);
			}
		});
		btnNewButton_1_1_2.setBounds(288, 266, 141, 32);
		frame.getContentPane().add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_2_1 = new JButton("\u8868\u683C\u5BFC\u51FA");
		btnNewButton_1_1_2_1.setBounds(70, 266, 141, 32);
		frame.getContentPane().add(btnNewButton_1_1_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TeacherFrame.class.getResource("/images/tea.bg3.jpg")));
		lblNewLabel_1.setBounds(0, 0, 500, 313);
		frame.getContentPane().add(lblNewLabel_1);
	}


		protected void toQuestionSetting(ActionEvent e) {
			this.dispose1();//当前的窗体关闭
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						QuestionSettingFrame frame = new QuestionSettingFrame();
						frame.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	}


		protected void toJfCourse(ActionEvent e) {
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


//跳转至疑问反馈
	protected void toQueryfeedback(ActionEvent e) {
		this.dispose1();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Queryfeedback frame = new Queryfeedback();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}


//跳转至课程进度表
	protected void toJ_scheduleUI(ActionEvent e) {
		this.dispose();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_scheduleUI frame = new J_scheduleUI();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
		
	}


//跳转至随机点名界面
	protected void toRandomRollCallUI(ActionEvent e) {
		
    	this.dispose();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomRollCallUI frame = new RandomRollCallUI();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
		
		
		
	}


//跳转至主界面
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
