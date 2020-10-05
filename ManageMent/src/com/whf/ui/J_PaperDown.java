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
	//�ύ��ť���ص���ѧ��������;�Լ����ɼ�¼�뵽���ݿ���

	JFrame frame;
	public JTextField textField;
	//����һ��װ��JexamUI���������Ծ�Id;��static���α�֤��Ψһ��,��Ҫʵ�ֶ��˿���,Ҫ�õ�����ع�
	//���������ӿ�
	
	public static Integer subjectId;//��ĿID
	//(1)ʵ�ֽ����ݿ��е���Ŀ��Ϣ����textAre��

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
		
		JButton btnNewButton = new JButton("ȷ������");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createExam();
				String str = s.toString();//�õ�����е���Ŀ
				System.out.println(str);
				if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�����뱣��ĵ�ַ!");
				}
				new UserDao().downFile(textField_1.getText(),str);
			}
		});
		btnNewButton.setBounds(317, 151, 93, 35);
		frame.getContentPane().add(btnNewButton);
	}

	//����һ������ģ��(�����ݿ��е���Ϣ��ȡ�������б���)
	public void createExam() {// ��������ģ��
		//Ҫ����id��ֵ���鿴�Ծ�
		String sql = "select * from question where subjectId = ?";
		Object[] params = {subjectId};
		//���list�д���Ź������ݿⲻͬ��question��Ԫ��
		List<Question> list=(List<Question>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Question>(Question.class));
		for(Question p:list) {
			String  strA = p.getOptionA();
			String  strB = p.getOptionB();
			String  strC = p.getOptionC();
			String  strD = p.getOptionD();
			String  strE = p.getOptionE();
			String  subStr= p.getSubText();//��һ�е���Ŀ,
			//�õ�����
			s.append(subStr+ "\r\n"+strA+ "\r\n"+strB+ "\r\n"+strC+ "\r\n"+strC+ "\r\n"+strD+ "\r\n"+strE+ "\r\n");
		}
	}
}



