package com.whf.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import com.whf.dao.UserDao;
import com.whf.pojo.Student;
import com.whf.pojo.Teacher;
import com.whf.util.JdbcUtils;
import com.whf.util.StringUtil;
import com.whf.ui.TeacherFrame;

import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginFrame extends JFrame {

	private UserDao userDao=new UserDao();
	private JPanel contentPane;
	public static JTextField stupNameText;
	private JTextField passwordText;
	public static boolean bool;
	 private JRadioButton rbtn1;
	 private JRadioButton rbtn2;
	 public  static Student user;
	 public  static Teacher tea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/bg.jpg")));
		setResizable(false);
		setTitle("\u7528\u6237\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 170, 504, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("\u6559\u52A1\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/manage.png")));
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 24));
		lblNewLabel.setBounds(131, 38, 251, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/user.png")));
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel_1.setBounds(120, 129, 82, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801:");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/password.png")));
		lblNewLabel_2.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel_2.setBounds(120, 185, 82, 26);
		contentPane.add(lblNewLabel_2);
		
		stupNameText = new JTextField();
		stupNameText.setBounds(212, 135, 137, 21);
		contentPane.add(stupNameText);
		stupNameText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setColumns(10);
		passwordText.setBounds(212, 188, 137, 21);
		contentPane.add(passwordText);
		
		JButton btnNewButton_1_1 = new JButton("\u767B\u5F55");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerfromed(e);
			
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/denglu.png")));
		btnNewButton_1_1.setBounds(39, 278, 82, 32);
		contentPane.add(btnNewButton_1_1);
		
		//重置
		JButton btnNewButton_1_1_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetValueActionPerformed( e);
				
			}
		});
		btnNewButton_1_1_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/tuichu2.png")));
		
		btnNewButton_1_1_1.setBounds(154, 278, 82, 32);
		contentPane.add(btnNewButton_1_1_1);
		
		//退出
		JButton btnNewButton_1_1_2 = new JButton("\u9000\u51FA");
		btnNewButton_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e1) {
				closeJFrame(e1);
			}
		});
	
		btnNewButton_1_1_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/tuichu.png")));
		btnNewButton_1_1_2.setBounds(384, 278, 82, 32);
		contentPane.add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_2_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1_1_2_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/zhuce.png")));
		btnNewButton_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toLogon(e);
			}
		});
		btnNewButton_1_1_2_1.setBounds(267, 278, 82, 32);
		contentPane.add(btnNewButton_1_1_2_1);
		
	    rbtn1 = new JRadioButton("\u5B66\u751F");	
		rbtn1.addMouseListener(new MouseAdapter() {

		});
		rbtn1.setBounds(208, 234, 58, 26);
		contentPane.add(rbtn1);
		
	    rbtn2 = new JRadioButton("\u6559\u5E08");
		rbtn2.addMouseListener(new MouseAdapter() {

		});
		rbtn2.setBounds(300, 234, 58, 26);
		contentPane.add(rbtn2);
		
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rbtn1);
		bg.add(rbtn2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8BF7\u9009\u62E9\u8BBF\u95EE\u8EAB\u4EFD\uFF1A");
		lblNewLabel_3.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel_3.setBounds(74, 237, 128, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/bg.jpg")));
		lblNewLabel_4.setBounds(0, 0, 498, 351);
	
		contentPane.add(lblNewLabel_4);
	
		
	}


	//登录事件处理
    protected void loginActionPerfromed(ActionEvent e) {
		String userName=this.stupNameText.getText();
		String password=new String(this.passwordText.getText());
    	String teaName=this.stupNameText.getText();
    	
		if(StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}   	
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
	       return;	
		}
		
	
        user=new Student(userName,password);
    	tea=new Teacher(teaName,password);
    	Connection con=null;
    	try {
    		con=JdbcUtils.getConnection();
			Student currentUser=userDao.login(con, user);
			Teacher currentUser1=userDao.login1(con, tea);
			
			if(null!=currentUser&&rbtn1.isSelected()) {
				JOptionPane.showMessageDialog(null, "登录成功！");			   
				bool = true;
				//登录跳转至学生界面
		    	this.dispose();//当前的窗体关闭
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
			else if(null!=currentUser1&&rbtn2.isSelected()) {
				JOptionPane.showMessageDialog(null, "登录成功！");	  	
				bool = false;
				//登录跳转至教师界面
		    	this.dispose();//当前的窗体关闭
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TeacherFrame frame = new TeacherFrame();
							frame.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});		
								
			}else{
				JOptionPane.showMessageDialog(null, "用户名或者密码错误！");		
			}
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
  	
	}


	//注册页面的跳转
	protected void toLogon(ActionEvent e) {
		
    	this.dispose();//当前的窗体关闭
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogonFrame frame = new LogonFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
  	
	}

	//关闭窗体
	protected void closeJFrame(MouseEvent e1) {
	     
	      this.dispose();
		
	}

	//重置事件处理
	protected void resetValueActionPerformed(MouseEvent e) {
		this.stupNameText.setText("");
		this.passwordText.setText("");
	}
}
