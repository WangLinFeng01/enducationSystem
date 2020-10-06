package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.whf.util.JdbcUtils;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class ScoreFrame extends JFrame {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JTextField stupNameText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public ScoreFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u8BE2");
		frame.setBounds(100, 100, 479, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel.setBounds(54, 56, 66, 23);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(126, 57, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		String l1=LoginFrame.stupNameText.getText();
		textField.setText(l1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(126, 122, 66, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText((J_ExamingUI.score).toString());
		
		
		
		btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack11(e);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			
		});
		btnNewButton.setBounds(332, 237, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("\u5206\u6570\uFF1A");
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel_1.setBounds(69, 122, 66, 21);
		frame.getContentPane().add(lblNewLabel_1);

	}

	private String toString(Integer score) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void goBack11(ActionEvent e) {
		this.dispose1();//当前的窗体关闭
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
		frame.dispose();
		
	}
}
