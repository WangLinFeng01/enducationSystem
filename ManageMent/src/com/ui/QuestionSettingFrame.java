package com.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import com.dao.QuestionSettingDao;
import com.dao.impl.QuestionSettingDaoImpl;
import com.pojo.Question;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import javax.swing.ImageIcon;

public class QuestionSettingFrame extends JFrame {

	JFrame frame;
	private JTextField optionA;
	private JTextField optionB;
	private JTextField optionC;
	private JTextField optionD;
	private JTextField optionE;
	private JTextField answer;
	private JTextField paperId;
	private JComboBox subjectId;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JButton resetBtn;
	private JButton exitBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionSettingFrame window = new QuestionSettingFrame();
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
	public QuestionSettingFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("宋体", Font.PLAIN, 17));
		frame.setBounds(400, 102, 524, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BD5\u9898\u7C7B\u578B\uFF1A");
		lblNewLabel.setBounds(26, 73, 75, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea subText = new JTextArea();
		subText.setBounds(95, 118, 300, 83);
		frame.getContentPane().add(subText);
		
		optionA = new JTextField();
		optionA.setBounds(95, 211, 303, 21);
		frame.getContentPane().add(optionA);
		optionA.setColumns(10);
		
		optionB = new JTextField();
		optionB.setBounds(95, 242, 303, 21);
		frame.getContentPane().add(optionB);
		optionB.setColumns(10);
		
		optionC = new JTextField();
		optionC.setBounds(94, 273, 304, 21);
		frame.getContentPane().add(optionC);
		optionC.setColumns(10);
		
		optionD = new JTextField();
		optionD.setBounds(95, 304, 303, 21);
		frame.getContentPane().add(optionD);
		optionD.setColumns(10);
		
		optionE = new JTextField();
		optionE.setBounds(95, 340, 303, 21);
		frame.getContentPane().add(optionE);
		optionE.setColumns(10);
		
		JComboBox type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(new String[] {"\u5355\u9009", "\u591A\u9009"}));
		type.setBounds(96, 72, 75, 21);
		frame.getContentPane().add(type);
		
		answer = new JTextField();
		answer.setBounds(105, 379, 66, 21);
		frame.getContentPane().add(answer);
		answer.setColumns(10);
		
		paperId = new JTextField();
		paperId.setBounds(257, 410, 66, 21);
		frame.getContentPane().add(paperId);
		paperId.setColumns(10);
		
		JButton addBtn = new JButton("\u6DFB\u52A0");
		addBtn.setIcon(new ImageIcon(QuestionSettingFrame.class.getResource("/images/add.png")));
		addBtn.setBounds(78, 458, 93, 31);
		frame.getContentPane().add(addBtn);
		
		subjectId = new JComboBox();
		subjectId.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		subjectId.setBounds(98, 410, 72, 21);
		frame.getContentPane().add(subjectId);
		
		lblNewLabel_1 = new JLabel("\u8BD5\u9898\u5185\u5BB9\uFF1A");
		lblNewLabel_1.setBounds(26, 122, 75, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\u9009\u9879A\uFF1A");
		lblNewLabel_2.setBounds(47, 214, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("\u9009\u9879B\uFF1A");
		lblNewLabel_3.setBounds(47, 245, 54, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("\u9009\u9879C\uFF1A");
		lblNewLabel_4.setBounds(47, 276, 54, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("\u9009\u9879D\uFF1A");
		lblNewLabel_5.setBounds(47, 307, 54, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("\u9009\u9879E\uFF1A");
		lblNewLabel_6.setBounds(47, 343, 54, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("\u79D1\u76EE\uFF1A");
		lblNewLabel_7.setBounds(47, 413, 54, 15);
		frame.getContentPane().add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("\u8BD5\u5377ID\uFF1A");
		lblNewLabel_8.setBounds(204, 413, 54, 15);
		frame.getContentPane().add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("\u6B63\u786E\u7B54\u6848\uFF1A");
		lblNewLabel_9.setBounds(47, 382, 75, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("\u8BD5\u9898\u8BBE\u7F6E");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_10.setBounds(219, 20, 119, 45);
		frame.getContentPane().add(lblNewLabel_10);
		
		resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.setIcon(new ImageIcon(QuestionSettingFrame.class.getResource("/images/chongzi1.png")));
		resetBtn.setBounds(211, 458, 93, 31);
		frame.getContentPane().add(resetBtn);
		
		exitBtn = new JButton("\u9000\u51FA");
		exitBtn.setIcon(new ImageIcon(QuestionSettingFrame.class.getResource("/images/goBack.png")));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转至教师界面
		    	this.dispose1();//当前的窗体关闭
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
			}

			private void dispose1() {
				frame.dispose();
				
			}
		});
		exitBtn.setBounds(343, 458, 93, 31);
		frame.getContentPane().add(exitBtn);
		
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				answer.setText("");
				optionA.setText("");
				optionB.setText("");
				optionC.setText("");
				optionD.setText("");
				optionE.setText("");	
				subText.setText("");
				paperId.setText("");
				
			}
		});
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				
				
				Date d=new Date();
		
 				Question question=new Question(answer.getText(),d,optionA.getText(),optionB.getText(),optionC.getText(),optionD.getText(),optionE.getText(),subText.getText(),(String)type.getSelectedItem(),Integer.valueOf((String)(String)subjectId .getSelectedItem()),Integer.valueOf((String)paperId.getText()));
				QuestionSettingDao questionset=new QuestionSettingDaoImpl();
				Integer result=questionset.questionSetting(question);
				if(result>0) {
					
					int exi=JOptionPane.showConfirmDialog(null,"添加成功，是否退出？","友情提示",JOptionPane.YES_NO_OPTION,JOptionPane.DEFAULT_OPTION);
					if(exi==JOptionPane.YES_OPTION)
							{
						//跳转至教师界面
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
							}
							else
							{
							return;
							}
					
					
				}
				
			}

			private void dispose() {
				frame.dispose();
				
			}
		});
	}
}
