package com.whf.ui;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.whf.dao.UserDao;
import com.whf.pojo.Student;
import com.whf.util.StringUtil;
import javax.swing.ImageIcon;

public class LogonFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameText;
	private JTextField passwordText;
	private UserDao userDao=new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogonFrame frame = new LogonFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogonFrame() {
		setResizable(false);
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(460, 160, 403, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("---\u6CE8\u518C---");
		lblNewLabel.setBounds(131, 30, 142, 49);
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setBounds(131, 101, 112, 26);
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1);
		
		userNameText = new JTextField();
		userNameText.setBounds(131, 127, 142, 26);
		contentPane.add(userNameText);
		userNameText.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		lblNewLabel_1_1_1.setBounds(131, 203, 112, 26);
		lblNewLabel_1_1_1.setFont(new Font("SimSun", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1_1_1);
		
		passwordText = new JTextField();
		passwordText.setBounds(131, 230, 142, 24);
		passwordText.setColumns(10);
		contentPane.add(passwordText);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.setIcon(new ImageIcon(LogonFrame.class.getResource("/images/enter.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logonActionPerfromed(e);
		
			}
		});
		btnNewButton.setBounds(80, 314, 93, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.setIcon(new ImageIcon(LogonFrame.class.getResource("/images/goBack.png")));
		btnNewButton_1.setBounds(228, 314, 93, 34);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    toLogin(e);

			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LogonFrame.class.getResource("/images/zhuce.bg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 399, 420);
		contentPane.add(lblNewLabel_2);
	}

	//注册事件处理
	protected void logonActionPerfromed(ActionEvent e) {
		String userName=this.userNameText.getText();
		String password=new String(this.passwordText.getText());
		if(StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
    	
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			userNameText.setText("");
	       return;	
		}
		
		//获取到数据库中的userName的集合
		//判断用户名是否重复
		boolean bool =new UserDao().isLogonFrame(userName);
		if(bool) {
	    	JOptionPane.showMessageDialog(null, "用户名已存在，请重新输入");
	    	passwordText.setText("");
	    	userNameText.setText("");
	    	return;
		}
		
		
    	//添加数据----获取的是用户名和密码
		
    	Student user=new Student(userName,password);
    	UserDao  dao2 = new UserDao();
    	try {
    		dao2.add(user);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	finally {
    		JOptionPane.showMessageDialog(null, "注册成功！");			
    	}
		
	}

	
	public void toLogin(ActionEvent e) {
	
			this.dispose();//当前的窗体关闭
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
}
