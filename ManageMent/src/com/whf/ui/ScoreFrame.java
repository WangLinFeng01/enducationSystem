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
		frame.setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u8BE2");
		frame.setBounds(100, 100, 479, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setBounds(74, 60, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(126, 57, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		String l1=LoginFrame.stupNameText.getText();
		textField.setText(l1);
		
		JLabel lblNewLabel_1 = new JLabel("\u79D1\u76EE\uFF1A");
		lblNewLabel_1.setBounds(74, 120, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(126, 177, 66, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
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
		
		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				try {
					con=JdbcUtils.getConnection();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
    			String sql = "select score from course where courseName=?";
    			try {
					PreparedStatement ps = con.prepareStatement(sql);
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(51, 176, 66, 22);
		frame.getContentPane().add(btnNewButton_1);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel
		(new String[] {"\u6570\u5B66", "java\u57FA\u7840"}));	
		comboBox.setBounds(120, 117, 82, 21);
		frame.getContentPane().add(comboBox);

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
