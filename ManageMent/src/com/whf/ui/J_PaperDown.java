package com.whf.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.whf.dao.UserDao;
import com.whf.pojo.Question;
import com.whf.util.BeanListResultSetHandler;
import com.whf.util.QueryRunner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class J_PaperDown {
	//提交按钮返回的是学生主界面;以及将成绩录入到数据库中

	JFrame frame;
	public JTextField textField;
	//设置一个装从JexamUI传递来的试卷Id;用static修饰保证是唯一的,且要实现多人考试,要用到事务回滚
	//留的两个接口
	
	public static Integer subjectId;//科目ID
	//(1)实现将数据库中的题目信息传到textAre中

	StringBuffer s=new StringBuffer();
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_PaperDown window = new J_PaperDown();
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
	public J_PaperDown() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4E0B\u8F7D\u8BD5\u5377\u754C\u9762");
		frame.setBounds(100, 100, 539, 289);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4F60\u9009\u62E9\u7684\u8BD5\u5377ID\u662F:");
		lblNewLabel.setBounds(114, 48, 123, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(218, 51, 83, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u8F93\u5165\u4FDD\u5B58\u7684\u8DEF\u5F84:");
		lblNewLabel_2.setBounds(114, 96, 123, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(207, 96, 203, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		lblNewLabel_1.setText(subjectId+"");
		
		JButton btnNewButton = new JButton("确认下载");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createExam();
				String str = s.toString();//得到题库中的题目
				System.out.println(str);
				if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入保存的地址!");
				}
				new UserDao().downFile(textField_1.getText(),str);
			}
		});
		btnNewButton.setBounds(317, 151, 93, 35);
		frame.getContentPane().add(btnNewButton);
	}

	//创建一个考试模块(将数据库中的信息读取到向量列表中)
	public void createExam() {// 创建考试模块
		//要根据id的值来查看试卷
		String sql = "select * from question where subjectId = ?";
		Object[] params = {subjectId};
		//这个list中存放着关于数据库不同的question表元素
		List<Question> list=(List<Question>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Question>(Question.class));
		for(Question p:list) {
			String  strA = p.getOptionA();
			String  strB = p.getOptionB();
			String  strC = p.getOptionC();
			String  strD = p.getOptionD();
			String  strE = p.getOptionE();
			String  subStr= p.getSubText();//第一行的题目,
			//得到试题
			s.append(subStr+ "\r\n"+strA+ "\r\n"+strB+ "\r\n"+strC+ "\r\n"+strC+ "\r\n"+strD+ "\r\n"+strE+ "\r\n");
		}
	}
}



