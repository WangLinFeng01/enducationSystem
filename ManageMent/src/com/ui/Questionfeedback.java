package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.pojo.Student;
import com.util.JdbcUtils;
import com.util.QueryRunner;
import com.util.StringUtil;

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
import javax.swing.ImageIcon;

public class Questionfeedback extends JFrame{
    
    public JFrame frame;
	private JTextField textField;
	protected int x;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;
	private JButton btnNewButton_1;

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
		frame.setResizable(false);
		frame.setForeground(Color.BLACK);
		frame.setFont(new Font("Copperplate Gothic Light", Font.BOLD | Font.ITALIC, 12));
		frame.setTitle("\u5B66\u751F\u7591\u95EE\u53CD\u9988");
		frame.setBounds(400, 130, 626, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.setIcon(new ImageIcon(Questionfeedback.class.getResource("/images/enter.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Fname1=textField_1.getText();
				String Feedback=textField.getText();
				if(StringUtil.isEmpty(Fname1)) {
					JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
					JTextComponent FnameText = null;//JTextComponent �ı����
					FnameText.setText("");
			       return;	
				}
				if(StringUtil.isEmpty(Feedback)) {
					JOptionPane.showMessageDialog(null, "���ݲ���Ϊ�գ�");
					JTextComponent FeedbackText = null;
					FeedbackText.setText("");
			       return;	
				}
				              
					
				   //���ݵ������ݿ�
				   int x = 0;
				   try {
					   //���ǿ�ܵĲ����÷�
					String sql = "insert into feedback values(DEFAULT,'"+Fname1+"','"+Feedback+"');" ;
					Object[] params= null;
					new QueryRunner().execute(sql, params);
				} catch (Exception e1) {
					e1.printStackTrace();
				}  finally {
					JOptionPane.showMessageDialog(null, "�����ɹ���");
			   }
				   return;
			}
			
		});
		btnNewButton.setBounds(178, 354, 122, 41);
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
		
		btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.setIcon(new ImageIcon(Questionfeedback.class.getResource("/images/goBack.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toStudent(e);
			}
		});
		btnNewButton_1.setBounds(347, 354, 122, 41);
		frame.getContentPane().add(btnNewButton_1);
	}

	protected void toStudent(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose1();//��ǰ�Ĵ���ر�
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrame frame = new StudentFrame();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

	private void dispose1() {
		// TODO Auto-generated method stub
		frame.dispose();
	}

}
