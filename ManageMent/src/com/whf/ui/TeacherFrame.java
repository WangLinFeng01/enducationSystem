package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class TeacherFrame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherFrame window = new TeacherFrame();
					window.frame.setVisible(true);
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
		frame.setTitle("\u6559\u5E08\u7BA1\u7406\u754C\u9762");
		frame.setBounds(100, 100, 542, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel.setBounds(121, 43, 64, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u8BFE\u8868");
		btnNewButton.setIcon(new ImageIcon(TeacherFrame.class.getResource("/images/course.png")));
		btnNewButton.setBounds(121, 198, 111, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5B66\u751F\u7BA1\u7406");
		btnNewButton_1.setIcon(new ImageIcon(TeacherFrame.class.getResource("/images/student.png")));
		btnNewButton_1.setBounds(310, 198, 111, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(187, 43, 111, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
