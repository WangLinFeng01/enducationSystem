package com.whf.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.whf.dao.UserDao;
import com.whf.pojo.Student;
import com.whf.util.DbUtil;
import com.whf.util.StringUtil;
import javax.swing.JScrollPane;

public class LoginFrame extends JFrame {



	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	private JPanel contentPane;
	private JTextField stupNameText;
	private JTextField passwordText;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setTitle("\u7528\u6237\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6559\u52A1\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/book.png")));
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 24));
		lblNewLabel.setBounds(165, 49, 215, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/user.png")));
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel_1.setBounds(131, 129, 82, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801:");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/password.png")));
		lblNewLabel_2.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel_2.setBounds(131, 185, 82, 26);
		contentPane.add(lblNewLabel_2);
		
		stupNameText = new JTextField();
		stupNameText.setBounds(223, 135, 137, 21);
		contentPane.add(stupNameText);
		stupNameText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setColumns(10);
		passwordText.setBounds(223, 188, 137, 21);
		contentPane.add(passwordText);
		
		JButton btnNewButton_1_1 = new JButton("\u767B\u5F55");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerfromed(e);
				
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/login.png")));
		btnNewButton_1_1.setBounds(67, 278, 82, 32);
		contentPane.add(btnNewButton_1_1);
		
		//重置
		JButton btnNewButton_1_1_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetValueActionPerformed( e);
				
			}
		});
		btnNewButton_1_1_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/refresh.png")));
		
		btnNewButton_1_1_1.setBounds(176, 278, 82, 32);
		contentPane.add(btnNewButton_1_1_1);
		
		//退出
		JButton btnNewButton_1_1_2 = new JButton("\u9000\u51FA");
		btnNewButton_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e1) {
				closeJFrame(e1);
			}
		});
	
		btnNewButton_1_1_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/logout.png")));
		btnNewButton_1_1_2.setBounds(398, 278, 82, 32);
		contentPane.add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_2_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1_1_2_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/logon.png")));
		btnNewButton_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toLogon(e);
			}
		});
		btnNewButton_1_1_2_1.setBounds(288, 278, 82, 32);
		contentPane.add(btnNewButton_1_1_2_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(192, 229, 190, 39);
		contentPane.add(panel);
		
		JRadioButton rbtn1 = new JRadioButton("\u6559\u5E08");
		rbtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		panel.add(rbtn1);
		
		JRadioButton rbtn2 = new JRadioButton("\u5B66\u751F");
		rbtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				
				
				
			}
		});
		panel.add(rbtn2);
	}

	//登录事件处理
    protected void loginActionPerfromed(ActionEvent e) {
		String userName=this.stupNameText.getText();
		String password=new String(this.passwordText.getText());
		if(StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
    	
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
	       return;	
		}
		
    	Student user=new Student(userName,password);
    	Connection con=null;
    	try {
			con=dbUtil.getCon();
			Student currentUser=userDao.login(con, user);
			if(null!=currentUser) {
				JOptionPane.showMessageDialog(null, "登录成功！");		
			}else {
				JOptionPane.showMessageDialog(null, "用户名或者密码错误！");		
			}
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
    	
    	
    	
	}

    
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
