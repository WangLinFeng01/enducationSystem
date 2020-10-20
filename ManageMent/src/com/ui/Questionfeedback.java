package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

import com.dao.impl.FeedbackDaoImpl;
import com.dao.impl.TeacherDaoImpl;
import com.pojo.Feedback;
import com.pojo.Student;
import com.pojo.Teacher;
import com.util.JdbcUtils;
import com.util.QueryRunner;
import com.util.ResultSetHandler;
import com.util.StringUtil;

import jxl.biff.drawing.ComboBox;

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

import javax.security.auth.login.LoginContext;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Questionfeedback extends JFrame{
    
    public JFrame frame;
	private JTextField textField;
	protected int x;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JComboBox comboBox;

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
                Question(e);
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
		
		btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.setIcon(new ImageIcon(Questionfeedback.class.getResource("/images/goBack.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toStudent(e);
			}
		});
		btnNewButton_1.setBounds(347, 354, 122, 41);
		frame.getContentPane().add(btnNewButton_1);
        
		//获取学生登录时的姓名
		String name=LoginFrame.stupNameText.getText();
		JLabel lblNewLabel_2 = new JLabel(name);
		
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(168, 101, 132, 41);
		lblNewLabel_2.setEnabled(false);
		frame.getContentPane().add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(347, 110, 122, 32);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Questionfeedback.class.getResource("/images/timg (4).jpg")));
		lblNewLabel_3.setBounds(10, 10, 602, 447);
		frame.getContentPane().add(lblNewLabel_3);
		
		//填充下拉框
		fillcomboBox();
	}
	
	//填充下拉框的实现
	public void fillcomboBox() {
		ComboBoxModel cm=comboBox.getModel();
		DefaultComboBoxModel model=(DefaultComboBoxModel)cm;
		//拿到放在结果集中的数据
		List<Teacher> datas=new TeacherDaoImpl().getDatas();
		for(Teacher p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getUserName());
			model.addElement(lineData);;
		}
	}
	
    //界面跳转到学生界面
	protected void toStudent(ActionEvent e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		frame.dispose();
	}
	//疑难反馈按钮实现
	private void Question(ActionEvent e) {
		String name=LoginFrame.stupNameText.getText();
		String Feedback=textField.getText();
		String teacherName=comboBox.getSelectedItem().toString();
		System.out.println(teacherName);
		
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
			String sql = "insert into feedback values(null,'"+name+"','"+Feedback+"',DEFAULT,'"+teacherName+"');";
			Object[] params= null;
			System.out.println(sql);
			new QueryRunner().execute(sql, params);
		} catch (Exception e1) {
			e1.printStackTrace();
		}  finally {
			JOptionPane.showMessageDialog(null, "反馈成功！");
	   }
		   return;
	}
}
