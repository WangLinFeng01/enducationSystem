package com.ui;


import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.dao.FeedbackDao;
import com.dao.impl.FeedbackDaoImpl;
import com.pojo.Feedback;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Queryfeedback extends JFrame {

	JFrame frame;
	private JTable table;
	private FeedbackDao feedbackDao = new FeedbackDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Queryfeedback window = new Queryfeedback();
					window.frame.setVisible(true);
					window.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Queryfeedback() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(400, 140, 571, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 54, 410, 181);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		//��ʼ����
		String[] titles= {"����","��������"};
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u59D3\u540D", "\u53CD\u9988\u5185\u5BB9"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(64);
		table.getColumnModel().getColumn(0).setMinWidth(64);
		table.getColumnModel().getColumn(1).setPreferredWidth(364);
		table.getColumnModel().getColumn(1).setMinWidth(364);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.setIcon(new ImageIcon(Queryfeedback.class.getResource("/images/enter.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toBack1(arg0);
			}
		});
		btnNewButton.setBounds(415, 259, 85, 29);
		frame.getContentPane().add(btnNewButton);
		//������
		fillTable();
	}
	protected void toBack1(ActionEvent arg0) {
		this.dispose1();//��ǰ�Ĵ���ر�
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

	private void fillTable() {
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//���һ�±��е�����
		model.setRowCount(0);
		//�õ����ڽ�����е�����
		List<Feedback> datas=new FeedbackDaoImpl().getDatas();
		for(Feedback p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getFname());
			lineData.add(p.getFeedback());
			model.addRow(lineData);
		}
		//����һ��table�ϵ�������¼�
//		mouseClicked();
	}
}
