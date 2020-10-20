package com.ui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.base.BarChartDemoImpl;
import com.dao.T_scheduleDao;
import com.dao.impl.T_scheduleDaoImpl;
import com.pojo.T_schedule;
import com.util.QueryRunner;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class J_scheduleUI {

	JFrame frame;
	private JTable table;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private T_scheduleDao t = new T_scheduleDaoImpl();
	private String str1, str2, str3;
	private int i1, i2, i3 ;
	private JLabel picture;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_scheduleUI window = new J_scheduleUI();
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
	public J_scheduleUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8BFE\u7A0B\u8FDB\u5EA6\u56FE");
		frame.setBounds(100, 100, 698, 329);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		BarChartDemoImpl bcd = new BarChartDemoImpl();
		
		JLabel lblNewLabel = new JLabel("�γ̽��ȱ�");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 0, 91, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 320, 158);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u79D1\u76EE", "\u5B66\u65F6", "\u8FDB\u5EA6"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("\u79D1\u76EE");
		lblNewLabel_1.setBounds(10, 219, 34, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("��Ŀ��");
		lblNewLabel_2.setBounds(45, 219, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(184, 216, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u8FDB\u5EA6\u4FEE\u6539:");
		lblNewLabel_3.setBounds(109, 219, 65, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("�޸�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTableStruct();
				//�޸�i1��i3��ֵ
			}
		});
		btnNewButton.setBounds(260, 215, 72, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ˢ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTable();//���
				bcd.getBarChart3D(str1,str2,str3, i1, i2, i3);
				ImageIcon icon = new ImageIcon(bcd.image);
				//�ı�ͼƬ��С	    
				icon.setImage(icon.getImage().getScaledInstance(296,199,Image.SCALE_DEFAULT));//80��100Ϊ��С ������������		
				picture.setIcon(icon);//�����ɵ���״ͼ����JLabel��.
			}
		});
		btnNewButton_1.setBounds(264, 3, 66, 23);
		frame.getContentPane().add(btnNewButton_1);
		fillTable();
		bcd.getBarChart3D(str1,str2,str3, i1, i2, i3);
		
		picture = new JLabel();
		picture.setText("\u56FE");
		picture.setBounds(363, 35, 296, 199);
		ImageIcon icon = new ImageIcon(bcd.image);
		//�ı�ͼƬ��С	    
		icon.setImage(icon.getImage().getScaledInstance(296,199,Image.SCALE_DEFAULT));//80��100Ϊ��С ������������		
		picture.setIcon(icon);//�����ɵ���״ͼ����JLabel��.
		frame.getContentPane().add(picture);
		picture.setIcon(icon);//�����ɵ���״ͼ����JLabel��.
		
		JButton btnNewButton_1_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toTeacherFrame(e);
			}

			private void toTeacherFrame(ActionEvent e) {
				frame.dispose();//��ǰ�Ĵ���ر�
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
		});
		btnNewButton_1_1.setBounds(184, 3, 66, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
	}
	
	//����ѧ�ı���е������ó���
	private void fillTable() {
		String[] strS = new String[3];
		int[] strS1 = new int[3];
		int i = 0;
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//���һ�±��е�����
		model.setRowCount(0);
		//�õ����ڽ�����е�����
		List<T_schedule> datas=t.getDatas();
		for(T_schedule p:datas) {
			Vector lineData=new Vector();
			strS[i] = p.getSubject();//�õ���Ŀ��
			lineData.add(p.getSubject());
			lineData.add(p.getHour());
			lineData.add(p.getSpeed());
			strS1[i] = p.getSpeed();//�õ���ʼ����
			model.addRow(lineData);
			i++;
		}
		System.out.println("ˢ�³ɹ�");
		str1 = strS[0];
		str2 = strS[1];
		str3 = strS[2];
		i1 = strS1[0];
		i2 = strS1[1];
		i3 = strS1[2];
		//����һ��table�ϵ�������¼�
		mouseClicked();
	}
	
	//������¼�
	private void mouseClicked() {
		table.addMouseListener(new MouseAdapter() {
			//��д����¼�
			@Override
			public void mouseClicked(MouseEvent e) {
				//����
				int columnCount=table.getSelectedColumn() ;
				//����
				int rowCount=table.getSelectedRow();
//				System.out.println(columnCount+".."+rowCount);
				//�õ���Ԫ���е�����
				Object value=table.getValueAt(rowCount, columnCount);
				//��ȡ����Ϣ
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				//��ȡ���������е�����
				Vector v=model.getDataVector();
				String str = v.get(rowCount).toString();
				//�õ���һ���еĵ�һ��subjectֵ;
				String subStrs = str.split(",")[0].substring(1);
				//���
				textField.setText(value+"");					
				//status��ֵ
				//�ж�value��ֵ�Ƿ���int����  �޸�һ��bug.
				if(subStrs instanceof String) {
					lblNewLabel_2.setText(subStrs); 
				}
			}
		});
	}
	
	private void updateTableStruct() {
		//�ж�������Ƿ�Ϊ��,Ϊ�ղ�ִ�в���
		if(textField.getText().equals("") || lblNewLabel_2.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸Ŀγ�״̬��һ��" ,"alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int str = Integer.parseInt(textField.getText());//id
		String stu =lblNewLabel_2.getText();//status
		//ִ�в���
		String str1 = "";
		if(!str1.equals(stu)) {
			String sql = "update t_schedule set speed = ? where subject = ?";
			Object[] params = {str,stu};//����ֵ
			int i =new QueryRunner().execute(sql, params);
			if(i>0) {
				JOptionPane.showMessageDialog(null, "���³ɹ���");				
			}
		}
		return;
	}
}
