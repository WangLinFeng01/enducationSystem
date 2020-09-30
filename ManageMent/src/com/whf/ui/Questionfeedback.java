package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.whf.util.JdbcUtils;
import com.whf.util.StringUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Questionfeedback {

	protected static final Connection DbUtil = null;
	private JFrame frame;
	private JTextField textField;
	protected int x;

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
		frame.setTitle("\u7591\u95EE\u70B9\u53CD\u9988");
		frame.setBounds(100, 100, 626, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Feedback=textField.getText();
				 int x = 0;
				   try {
					Connection con =JdbcUtils.getConnection();
					String sql = "insert into feedback values(DEFAULT,'"+Feedback+"');" ;
					
					
					//ƒ√µΩ‘§±‡“Î
					PreparedStatement ps = con.prepareStatement(sql);
					boolean rs = ps.execute();
						
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				   return;
			}
		});
		btnNewButton.setBounds(246, 253, 122, 41);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u7591\u95EE\u70B9\u6216\u53CD\u9988");
		lblNewLabel.setBounds(64, 88, 58, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(132, 85, 344, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
