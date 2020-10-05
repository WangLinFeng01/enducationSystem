package com.whf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;

import com.whf.pojo.Paper;
import com.whf.pojo.Question;
import com.whf.util.BeanListResultSetHandler;
import com.whf.util.QueryRunner;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class J_ExamingUI {
	//提交按钮返回的是学生主界面;以及将成绩录入到数据库中

	JFrame frame;
	public JTextField textField;
	private JTextArea textArea;
	//设置一个装从JexamUI传递来的试卷Id;用static修饰保证是唯一的,且要实现多人考试,要用到事务回滚
	//留的两个接口
	public  Integer stuId = 1;//学生id
	public String nameStu = "凤眼夋";
	
	public static Integer examId;//科目ID
	public  Integer paperId;//科目ID
	public Integer score;//成绩
	public Date insertTime;//插入时间
	//(1)实现将数据库中的题目信息传到textAre中

	int p = 0;// 设置题目数指针
	int topicnum = 0;//记录题目的总数
	ArrayList<String> subStrs = new ArrayList<>() ;//保存试题
	ArrayList<String> answerAry = new ArrayList<>() ;//保存题库中正确答案
	ArrayList<String> answerResponse = new ArrayList<>();//保存回答的答案
	//(2)实现倒计时
	ClockDispaly mt; // 倒计时模块

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_ExamingUI window = new J_ExamingUI();
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
	public J_ExamingUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8003\u8BD5\u754C\u9762");
		frame.setBounds(100, 100, 539, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("提交试卷");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				score = showScore();//拿到分数;
				transferTable();
				btnNewButton_1.setEnabled(false);
				
//				System.exit(0); // 退出
			}

		});
		btnNewButton_1.setBounds(395, 327, 92, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(39, 73, 17, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_3 = new JButton("上一题");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p--;
				if (p == -1) {
					JOptionPane.showMessageDialog(null, "已经是第一题");
					p++;
				}
				showQuestion();
				lblNewLabel_5.setText((p+1)+"");
				//点击上一题,直接长度设置为当前长度
				if(p==0) {
					return;
				}
				answerResponse.remove(p-1);	
				
			}
		});
		btnNewButton_3.setBounds(185, 327, 81, 31);
		frame.getContentPane().add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 504, 31);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		

		
		JButton btnNewButton = new JButton("开始按钮");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(examId == null) {
					JOptionPane.showMessageDialog(null, "错误:请重新登录,选择要考试的题目");
					return;
				}
				createExam(); // 调用createExam模块
				p = 0; // 题目序号
				lblNewLabel_5.setText((p+1)+"");
				showQuestion(); // 调用showQuestion模块
				mt.start(); // 考试时间倒计时启动
				btnNewButton.setEnabled(false);// 设置按钮不可点击
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u8003\u8BD5\u603B\u65F6\u95F4\u662F:30\u5206\u949F");
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
				mt = new ClockDispaly(lblNewLabel, 30); // 调用并设置倒计时的时间
		panel.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 90, 504, 163);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u7B54\u6848:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(41, 251, 136, 54);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(185, 260, 226, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u7B2C");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 73, 26, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u9898");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(59, 73, 26, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		JButton btnNewButton_2 = new JButton("下一题");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p++;
				if (p == topicnum) {
					JOptionPane.showMessageDialog(null, "已经是最后一题");
					p--;
				}
				lblNewLabel_5.setText((p+1)+"");
				//刷新出下一题
				showQuestion();
				//但是点击上一题时,输入答案,下一题怎么知道这个是
				answerResponse.add(textField.getText());
				textField.setText("");//清空
				textField.requestFocus();
			}
		});
		btnNewButton_2.setBounds(291, 327, 78, 31);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(10, 12, 205, 15);
		frame.getContentPane().add(lblNewLabel_6);
		//接口设置姓名
				lblNewLabel_6.setText("欢迎你! 考生"+nameStu);
		//测试是否拿到
//		System.out.println("dd:"+examId);

		
	}

	//创建一个考试模块(将数据库中的信息读取到向量列表中)
	public void createExam() {// 创建考试模块
		String s;
		//要根据id的值来查看试卷
		String sql = "select * from question where subjectId = ?";
		Object[] params = {examId};
		//这个list中存放着关于数据库不同的question表元素
		List<Question> list=(List<Question>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Question>(Question.class));
		for(Question p:list) {
			s="";
			String  ansStr = p.getAnswer();//第一行的答案
			String  strA = p.getOptionA();
			String  strB = p.getOptionB();
			String  strC = p.getOptionC();
			String  strD = p.getOptionD();
			String  strE = p.getOptionE();
			String  subStr= p.getSubText();//第一行的题目,
			paperId = p.getPaperId();//试卷ID
			insertTime = p.getJoinTime();//加入的时间
			//得到试题:
			s = subStr+ '\n'+strA+ '\n'+strB+ '\n'+strC+ '\n'+strC+ '\n'+strD+ '\n'+strE;
			//在创建一个集合专门存放试题;
			subStrs.add(s);
//			//创建一个集合专门存放答案
			answerAry.add(ansStr);
			topicnum++;//记录总题数
		}
	}
	
	private void showQuestion() {// 设置试题模块
		textArea.setText("");
		textArea.append(subStrs.get(p));// 在文本区域显示试题.p变量就在按钮的点击事件中自增或者自减	
		textArea.setEnabled(false);
	}
	
	//得到这门考试的总分数
	private int showScore() {
		int right = 0;//正确的题数
		int error = 0;//错误
		for (int i = 0; i < topicnum; i++) {
			if (answerAry.get(i).equals(answerResponse.get(i))) {// 判断答案的正确与错误
				right++;
			} else {
				error++;
			}
		}
		int score = (int) (right * 100 / topicnum); // 设置分数
		JOptionPane.showMessageDialog(null, "答对" + right + "题，答错" + error + "题，分数为" + score);
		return score;
	}	
	//将分数传到t_scoer表中
	public void transferTable() {
		//成绩id,加入时间,最高成绩,试卷id,血
		String sql="insert into t_scoer value (null,?,?,?,?,?)";
		Object[] obj = {insertTime,score,examId,stuId,paperId};
		new QueryRunner().execute(sql, obj);
	}
}

class ClockDispaly extends Thread {// 设置Thread考试倒计时模块

	private JLabel lefttimer;
	private int testtime;

	public ClockDispaly(JLabel lt, int time) {
		lefttimer = lt;
		testtime = time * 60;
	}

	public void run() {
		NumberFormat numberFormat = NumberFormat.getInstance();// 控制时间的显示格式
		numberFormat.setMinimumIntegerDigits(2);// 设置数值的整数部分允许的最小位数
		int h, m, s;// 定义时分秒
		while (testtime >= 0) {
			h = testtime / 3600;
			m = testtime % 3600 / 60;
			s = testtime % 60;
			StringBuffer stringBuffer = new StringBuffer("");
			// 增加到lefttimer标签
			stringBuffer.append(
					"考试剩余时间为：" + numberFormat.format(h) + ":" + numberFormat.format(m) + ":" + numberFormat.format(s));
			lefttimer.setText(stringBuffer.toString());//这个lefttimer是传进来的时间label按钮
			try {
				Thread.sleep(1000);// 延时一秒
			} catch (Exception e) {
				// ignore error
			}
			testtime = testtime - 1;
		}
		if (testtime <= 0) {
			JOptionPane.showMessageDialog(null, "考试结束");
			System.exit(0);
		}
	}
}



