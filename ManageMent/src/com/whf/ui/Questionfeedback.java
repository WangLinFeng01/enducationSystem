package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;


import com.whf.pojo.Student;
import com.whf.util.JdbcUtils;
import com.whf.util.QueryRunner;
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

public class Questionfeedback extends JFrame{
    
	private JFrame frame;
	private JTextField textField;
	protected int x;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;

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
				String Fname1=textField_1.getText();
				String Feedback=textField.getText();
				if(StringUtil.isEmpty(Fname1)) {
					JOptionPane.showMessageDialog(null, "姓名不能为空！");
					JTextComponent FnameText = null;//JTextComponent 文本组件
					FnameText.setText("");
			       return;	
				}
				if(StringUtil.isEmpty(Feedback)) {
					JOptionPane.showMessageDialog(null, "内容不能为空！");
					JTextComponent FeedbackText = null;
					FeedbackText.setText("");
			       return;	
				}
				              
					
				   //数据导入数据库
				   int x = 0;
				   try {
					   //这是框架的插入用法
					String sql = "insert into feedback values(DEFAULT,'"+Fname1+"','"+Feedback+"');" ;
					Object[] params= null;
					new QueryRunner().execute(sql, params);
				} catch (Exception e1) {
					e1.printStackTrace();
				}  finally {
					JOptionPane.showMessageDialog(null, "反馈成功！");
			   }
				   return;
			}
			
		});
		btnNewButton.setBounds(238, 354, 122, 41);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u53CD\u9988\u5185\u5BB9\uFF1A");
		lblNewLabel.setBounds(62, 229, 65, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(137, 216, 344, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("\u53CD\u9988\u4EBA\uFF1A");
		lblNewLabel_1.setBounds(69, 114, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 101, 162, 41);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}

}
