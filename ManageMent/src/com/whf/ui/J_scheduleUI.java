package com.whf.ui;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.whf.dao.T_scheduleDao;
import com.whf.dao.impl.T_scheduleDaoImpl;
import com.whf.pojo.T_schedule;
import com.whf.util.QueryRunner;
import javax.swing.ImageIcon;

public class J_scheduleUI {

	JFrame frame;
	private JTable table;
	private T_scheduleDao t = new T_scheduleDaoImpl();
	private JTextField textField;
	private JTextField textField_1;
	private String str = "��ѧ";
	//����һ��yinyong���������õ�����Ķ�����.

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
		frame.setTitle("��ѧ����ͼ");
		frame.setBounds(100, 100, 598, 394);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 99, 410, 181);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		//��ʼ����
		String[] titles= {"���","date","info","status"};
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			titles
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(31);
		table.getColumnModel().getColumn(0).setMinWidth(19);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(134, 48, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("�޸�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�ϴ����ݿ�
				selectFirst();
			}
		});
		btnNewButton_1.setBounds(434, 46, 66, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(286, 48, 104, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("ˢ��");
		btnNewButton.setIcon(new ImageIcon(J_scheduleUI.class.getResource("/images/chongzi1.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//����table�ϵ��ض���������¼�-->�����ݴ��뵽���ݿ���
				if(str.equals("��ѧ")) {
					fillTable();
				}else if(str.equals("java����")) {
					fillJavaTable();
				}
				
			}
		});
		btnNewButton.setBounds(434, 294, 104, 33);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("���:");
		lblNewLabel.setBounds(90, 51, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("״̬:");
		lblNewLabel_1.setBounds(243, 49, 37, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("����������");
		btnNewButton_2.setIcon(new ImageIcon(J_scheduleUI.class.getResource("/images/goBack.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toTeacher(arg0);
				
			}
		});
		btnNewButton_2.setBounds(100, 292, 128, 36);
		frame.getContentPane().add(btnNewButton_2);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("�γ�����");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("��ѧ");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setTitle("��ѧ����ͼ");
				str = "��ѧ";
				fillTable();
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("java����");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setTitle("java��������ͼ");
				str= "java����";
				fillJavaTable();
			}
		});
		
		//�����
		fillTable();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//��ת����ʦ����
	protected void toTeacher(ActionEvent arg0) {
		this.dispose();//��ǰ�Ĵ���ر�
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
		List<T_schedule> datas=t.getDatas("��ѧ");
		for(T_schedule p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getId());
			lineData.add(p.getDate());
			lineData.add(p.getInfo());
			lineData.add(p.getStatus());
			model.addRow(lineData);
		}
		System.out.println("ˢ�³ɹ�");
		//����һ��table�ϵ�������¼�
		mouseClicked();
	}

	//��java�ı���е������ó���
	private void fillJavaTable() {
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//���һ�±��е�����
		model.setRowCount(0);
		//�õ����ڽ�����е�����
		List<T_schedule> datas=t.getDatas("java����");
		for(T_schedule p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getId());
			lineData.add(p.getDate());
			lineData.add(p.getInfo());
			lineData.add(p.getStatus());
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
				
//				System.out.println(columnCount+".."+rowCount);
				//�õ���Ԫ���е�����
				Object value=table.getValueAt(rowCount, columnCount);
				//��ȡ����Ϣ
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				//��ȡ���������е�����
				Vector v=model.getDataVector();
				
				String str = v.get(rowCount).toString();
				//�õ���һ���еĵ�һ��IDֵ;
				String idStrs = str.split(",")[0].substring(1);
				//���
				textField.setText(idStrs); 
				//status��ֵ
				//�ж�value��ֵ�Ƿ���int����  �޸�һ��bug.
				if(value instanceof String) {
					textField_1.setText((String) value);					
				}
			}
		});
	}
	//�޸ı������ݺ��ϴ������ݿ�ķ���
	/**
	 * @param value �ǵ����ı�ĵ�Ԫ���ڵ�ֵ
	 * @param str �����һ�е�IDֵ 
	 */
	
	//ִ��SQL����
	//�и�bug --- ��������ǿյ�ʱ��,����ᱨ��
	private void selectFirst() {
		//�ж�������Ƿ�Ϊ��,Ϊ�ղ�ִ�в���
		if(textField.getText().equals("") || textField_1.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸Ŀγ�״̬��һ��" ,"alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String str = textField_1.getText();//id
		int id =Integer.parseInt(textField.getText());//status
		//ִ�в���
		String str1 = "";
		if(!str.equals(str1)) {
			String sql = "update t_schedule set status = ? where id = ?";
			Object[] params = {str,id};//����ֵ
			new QueryRunner().execute(sql, params);
		}
		return;
	}
	
}

