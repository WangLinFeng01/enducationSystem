package com.whf.ui;


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

import com.whf.dao.FeedbackDao;
import com.whf.dao.impl.FeedbackDaoImpl;
import com.whf.pojo.Feedback;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Queryfeedback {

	private JFrame frame;
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
		frame.setBounds(100, 100, 571, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 54, 410, 181);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		//初始化表
		String[] titles= {"姓名","反馈内容"};
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
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(415, 259, 85, 29);
		frame.getContentPane().add(btnNewButton);
		//填充表格
		fillTable();
	}
	private void fillTable() {
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//清空一下表中的数据
		model.setRowCount(0);
		//拿到放在结果集中的数据
		List<Feedback> datas=new FeedbackDaoImpl().getDatas();
		for(Feedback p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getFname());
			lineData.add(p.getFeedback());
			model.addRow(lineData);
		}
		//设置一下table上的鼠标点击事件
//		mouseClicked();
	}
}

