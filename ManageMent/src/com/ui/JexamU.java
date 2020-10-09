package com.ui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.dao.PaperDao;
import com.dao.impl.PaperDaoImpl;
import com.pojo.Paper;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class JexamU {

    JFrame frame;
	private JTable table;
	private PaperDao pd = new PaperDaoImpl();
	private JTextField textField;
	private String sChoice;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JexamU window = new JexamU();
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
	public JexamU() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5B66\u751F\u8BD5\u5377\u9009\u62E9");
		frame.setBounds(400, 170, 618, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 143, 522, 155);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		String[] str = {"�Ծ�ID","�Ծ�����","��ʼʱ��"};
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			str
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u8BD5\u5377id:");
		lblNewLabel.setBounds(69, 91, 92, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(141, 88, 134, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//��ѯ��ť,ɸѡ��ѯ�¼�
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setIcon(new ImageIcon(JexamU.class.getResource("/images/enter.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choiceFillTable();
			}
		});
		btnNewButton.setBounds(319, 87, 85, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("��ʼ����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jump();//��ת�¼�
			}
		});
		btnNewButton_1.setBounds(451, 87, 100, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BD5\u5377Id\u8BF4\u660E: 1\u5BF9\u5E94\u7740\u6570\u5B66, 2\u5BF9\u5E94\u7740\u8BED\u6587  , 3\u5BF9\u5E94\u7740\u82F1\u8BED");
		lblNewLabel_1.setBounds(0, 10, 474, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		//���ص�����(�Ծ�����)
		JButton btnNewButton_2 = new JButton("\u4E0B\u8F7D\u5230\u672C\u5730");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jump1();
			}
		});
		btnNewButton_2.setBounds(451, 35, 100, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u8FD4\u56DE");
		btnNewButton_3.setIcon(new ImageIcon(JexamU.class.getResource("/images/goBack.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toStudent(e);
			}
		});
		btnNewButton_3.setBounds(319, 35, 85, 25);
		frame.getContentPane().add(btnNewButton_3);
		//��ĳ�ʼ��
		fillTable();
	}

	protected void toStudent(ActionEvent e) {
		//��¼��ת��ѧ������
    	this.dispose();//��ǰ�Ĵ���ر�
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

	private void dispose() {
		frame.dispose();
		
	}

		//����ѧ�ı���е������ó���
		private void fillTable() {
			TableModel jmodel=table.getModel();
			DefaultTableModel model=(DefaultTableModel)jmodel;
			//���һ�±��е�����
			model.setRowCount(0);
			//�õ����ڽ�����е�����
			//���ֻ�õ�һ�����е�����
			List<Paper> datas=pd.gettableDatas();
			for(Paper p:datas) {
				Vector lineData=new Vector();
				lineData.add(p.getId());
				lineData.add(p.getPaperName());
				lineData.add(p.getJoinDate());
				model.addRow(lineData);
			}
			System.out.println("ˢ�³ɹ�");
			//����һ��table�ϵ�������¼�
			mouseClicked();
		}
		//ɸѡ��ѯ�¼� ---->����һ��ɸѡ��ֵ��ȥ
		private void choiceFillTable() {
			TableModel jmodel=table.getModel();
			DefaultTableModel model=(DefaultTableModel)jmodel;
			//���һ�±��е�����
			model.setRowCount(0);
			//�õ����ڽ�����е�����
			//����һ��ɸѡ��ֵ��ȥ
			 sChoice = textField.getText();
			//�ж��Ƿ�Ϊ��
			if(sChoice.equals("")) {
				fillTable();
				return;
			}
			int ChoiceNum = Integer.parseInt(sChoice);
			List<Paper> datas=pd.getChoiceDatas(ChoiceNum);
			for(Paper p:datas) {
				Vector lineData=new Vector();
				lineData.add(p.getId());
				lineData.add(p.getPaperName());
				lineData.add(p.getJoinDate());
				model.addRow(lineData);
			}
			System.out.println("ˢ�³ɹ�");
			//����һ��table�ϵ�������¼�
			mouseClicked();
			
		}
	
	private void mouseClicked() {
		table.addMouseListener(new MouseAdapter() {
			//��д����¼�
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ȡ��Ԫ��
				//����
				int columnCount=table.getSelectedColumn() ;
				//����
				int rowCount=table.getSelectedRow();
				//�õ���Ԫ���е�����
				Object value=table.getValueAt(rowCount, columnCount);
				//��ȡ����Ϣ
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				//��ȡ���������е�����
				Vector v=model.getDataVector();
				
				String str = v.get(rowCount).toString();
				//�õ���һ���еĵ�һ���Ծ�����;
				String examNameStrs = str.split(",")[0].substring(1);
				//��ѡ�е��ֶη��͸�textField����
				textField.setText(examNameStrs); 
			}
		});
	}
	
	//��ת�¼��ķ�װ
	private void jump() {
		//�ж���ת
		if(textField.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "��ѡ��Ҫ���Ե��Ծ�", "��ʾ",JOptionPane.WARNING_MESSAGE);  
			return;
		}
		frame.dispose();
		//�õ����ݸ���һ�������examId;
		//��Ҫ�õ����ֵ,���ܷŵ���ת��������
		J_ExamingUI.examId = Integer.parseInt(textField.getText());
		//��ת
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
	
	//��ת�¼��ķ�װ
		private void jump1() {
			//�ж���ת
			if(textField.getText().equals("")) {
				JOptionPane.showMessageDialog(frame, "��ѡ��Ҫ���ص��Ծ�", "��ʾ",JOptionPane.WARNING_MESSAGE);  
				return;
			}
			frame.dispose();
			//�õ����ݸ���һ�������examId;
			//��Ҫ�õ����ֵ,���ܷŵ���ת��������
			J_PaperDown.subjectId = Integer.parseInt(textField.getText());
			//��ת
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
}
