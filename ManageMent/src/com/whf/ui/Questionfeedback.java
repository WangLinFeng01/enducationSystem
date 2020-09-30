package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Questionfeedback {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Questionfeedback window = new Questionfeedback();
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
	public Questionfeedback() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.setFont(new Font("Copperplate Gothic Light", Font.BOLD | Font.ITALIC, 12));
		frame.setTitle("\u7591\u95EE\u53CD\u9988");
		frame.setBounds(100, 100, 626, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(155, 72, 332, 52);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JTextField fill = null;
		if(textField == fill) {
			char newChar=0;
			char oldChar = 0;
			this.textField.getText().replace(oldChar, newChar);
		}
		
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(246, 253, 122, 41);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u7591\u95EE\u70B9\u6216\u53CD\u9988");
		lblNewLabel.setBounds(64, 88, 58, 15);
		frame.getContentPane().add(lblNewLabel);
	}

}
