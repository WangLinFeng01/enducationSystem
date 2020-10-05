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
	//�ύ��ť���ص���ѧ��������;�Լ����ɼ�¼�뵽���ݿ���

	JFrame frame;
	public JTextField textField;
	private JTextArea textArea;
	//����һ��װ��JexamUI���������Ծ�Id;��static���α�֤��Ψһ��,��Ҫʵ�ֶ��˿���,Ҫ�õ�����ع�
	//���������ӿ�
	public  Integer stuId = 1;//ѧ��id
	public String nameStu = "���ۉ�";
	
	public static Integer examId;//��ĿID
	public  Integer paperId;//��ĿID
	public Integer score;//�ɼ�
	public Date insertTime;//����ʱ��
	//(1)ʵ�ֽ����ݿ��е���Ŀ��Ϣ����textAre��

	int p = 0;// ������Ŀ��ָ��
	int topicnum = 0;//��¼��Ŀ������
	ArrayList<String> subStrs = new ArrayList<>() ;//��������
	ArrayList<String> answerAry = new ArrayList<>() ;//�����������ȷ��
	ArrayList<String> answerResponse = new ArrayList<>();//����ش�Ĵ�
	//(2)ʵ�ֵ���ʱ
	ClockDispaly mt; // ����ʱģ��

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
		
		JButton btnNewButton_1 = new JButton("�ύ�Ծ�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				score = showScore();//�õ�����;
				transferTable();
				btnNewButton_1.setEnabled(false);
				
//				System.exit(0); // �˳�
			}

		});
		btnNewButton_1.setBounds(395, 327, 92, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(39, 73, 17, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_3 = new JButton("��һ��");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p--;
				if (p == -1) {
					JOptionPane.showMessageDialog(null, "�Ѿ��ǵ�һ��");
					p++;
				}
				showQuestion();
				lblNewLabel_5.setText((p+1)+"");
				//�����һ��,ֱ�ӳ�������Ϊ��ǰ����
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
		

		
		JButton btnNewButton = new JButton("��ʼ��ť");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(examId == null) {
					JOptionPane.showMessageDialog(null, "����:�����µ�¼,ѡ��Ҫ���Ե���Ŀ");
					return;
				}
				createExam(); // ����createExamģ��
				p = 0; // ��Ŀ���
				lblNewLabel_5.setText((p+1)+"");
				showQuestion(); // ����showQuestionģ��
				mt.start(); // ����ʱ�䵹��ʱ����
				btnNewButton.setEnabled(false);// ���ð�ť���ɵ��
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u8003\u8BD5\u603B\u65F6\u95F4\u662F:30\u5206\u949F");
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
				mt = new ClockDispaly(lblNewLabel, 30); // ���ò����õ���ʱ��ʱ��
		panel.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 90, 504, 163);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u7B54\u6848:");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(41, 251, 136, 54);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(185, 260, 226, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u7B2C");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 73, 26, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u9898");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(59, 73, 26, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		JButton btnNewButton_2 = new JButton("��һ��");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p++;
				if (p == topicnum) {
					JOptionPane.showMessageDialog(null, "�Ѿ������һ��");
					p--;
				}
				lblNewLabel_5.setText((p+1)+"");
				//ˢ�³���һ��
				showQuestion();
				//���ǵ����һ��ʱ,�����,��һ����ô֪�������
				answerResponse.add(textField.getText());
				textField.setText("");//���
				textField.requestFocus();
			}
		});
		btnNewButton_2.setBounds(291, 327, 78, 31);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(10, 12, 205, 15);
		frame.getContentPane().add(lblNewLabel_6);
		//�ӿ���������
				lblNewLabel_6.setText("��ӭ��! ����"+nameStu);
		//�����Ƿ��õ�
//		System.out.println("dd:"+examId);

		
	}

	//����һ������ģ��(�����ݿ��е���Ϣ��ȡ�������б���)
	public void createExam() {// ��������ģ��
		String s;
		//Ҫ����id��ֵ���鿴�Ծ�
		String sql = "select * from question where subjectId = ?";
		Object[] params = {examId};
		//���list�д���Ź������ݿⲻͬ��question��Ԫ��
		List<Question> list=(List<Question>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Question>(Question.class));
		for(Question p:list) {
			s="";
			String  ansStr = p.getAnswer();//��һ�еĴ�
			String  strA = p.getOptionA();
			String  strB = p.getOptionB();
			String  strC = p.getOptionC();
			String  strD = p.getOptionD();
			String  strE = p.getOptionE();
			String  subStr= p.getSubText();//��һ�е���Ŀ,
			paperId = p.getPaperId();//�Ծ�ID
			insertTime = p.getJoinTime();//�����ʱ��
			//�õ�����:
			s = subStr+ '\n'+strA+ '\n'+strB+ '\n'+strC+ '\n'+strC+ '\n'+strD+ '\n'+strE;
			//�ڴ���һ������ר�Ŵ������;
			subStrs.add(s);
//			//����һ������ר�Ŵ�Ŵ�
			answerAry.add(ansStr);
			topicnum++;//��¼������
		}
	}
	
	private void showQuestion() {// ��������ģ��
		textArea.setText("");
		textArea.append(subStrs.get(p));// ���ı�������ʾ����.p�������ڰ�ť�ĵ���¼������������Լ�	
		textArea.setEnabled(false);
	}
	
	//�õ����ſ��Ե��ܷ���
	private int showScore() {
		int right = 0;//��ȷ������
		int error = 0;//����
		for (int i = 0; i < topicnum; i++) {
			if (answerAry.get(i).equals(answerResponse.get(i))) {// �жϴ𰸵���ȷ�����
				right++;
			} else {
				error++;
			}
		}
		int score = (int) (right * 100 / topicnum); // ���÷���
		JOptionPane.showMessageDialog(null, "���" + right + "�⣬���" + error + "�⣬����Ϊ" + score);
		return score;
	}	
	//����������t_scoer����
	public void transferTable() {
		//�ɼ�id,����ʱ��,��߳ɼ�,�Ծ�id,Ѫ
		String sql="insert into t_scoer value (null,?,?,?,?,?)";
		Object[] obj = {insertTime,score,examId,stuId,paperId};
		new QueryRunner().execute(sql, obj);
	}
}

class ClockDispaly extends Thread {// ����Thread���Ե���ʱģ��

	private JLabel lefttimer;
	private int testtime;

	public ClockDispaly(JLabel lt, int time) {
		lefttimer = lt;
		testtime = time * 60;
	}

	public void run() {
		NumberFormat numberFormat = NumberFormat.getInstance();// ����ʱ�����ʾ��ʽ
		numberFormat.setMinimumIntegerDigits(2);// ������ֵ�����������������Сλ��
		int h, m, s;// ����ʱ����
		while (testtime >= 0) {
			h = testtime / 3600;
			m = testtime % 3600 / 60;
			s = testtime % 60;
			StringBuffer stringBuffer = new StringBuffer("");
			// ���ӵ�lefttimer��ǩ
			stringBuffer.append(
					"����ʣ��ʱ��Ϊ��" + numberFormat.format(h) + ":" + numberFormat.format(m) + ":" + numberFormat.format(s));
			lefttimer.setText(stringBuffer.toString());//���lefttimer�Ǵ�������ʱ��label��ť
			try {
				Thread.sleep(1000);// ��ʱһ��
			} catch (Exception e) {
				// ignore error
			}
			testtime = testtime - 1;
		}
		if (testtime <= 0) {
			JOptionPane.showMessageDialog(null, "���Խ���");
			System.exit(0);
		}
	}
}



