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
import javax.swing.text.JTextComponent;

import com.dao.FeedbackDao;
import com.dao.impl.FeedbackDaoImpl;
import com.pojo.Feedback;
import com.util.QueryRunner;
import com.util.StringUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Queryfeedback {

	JFrame frame;
	private JTable table;
	private FeedbackDao feedbackDao = new FeedbackDaoImpl();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField idd;

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
		frame.setTitle("\u53CD\u9988\u4FE1\u606F");
		frame.setBounds(100, 100, 908, 623);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 45, 807, 181);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClicked();
			}

			//获取table上的鼠标点击事件
			private void mouseClicked() {
				table.addMouseListener(new MouseAdapter() {
					//重写点击事件
					@Override
					public void mouseClicked(MouseEvent e) {
						//获取单元格
						//列数
						int columnCount=table.getSelectedColumn();
						//行数
						int rowCount=table.getSelectedRow();
						System.out.println(columnCount+".."+rowCount);
						//得到单元格中的内容
						Object values=table.getValueAt(rowCount, columnCount);
						
						//获取表当中的反馈人id
						Object id =  table.getValueAt(rowCount, 0);
						//将鼠标获取到行的反馈信息赋值给textField_1
						String information = (String) table.getValueAt(rowCount, 2);
						textField_1.setText(information);
						idd.setText(id.toString());
						//获取行信息
						DefaultTableModel model=(DefaultTableModel)table.getModel();
						//获取到表中所有的数据
						Vector v=model.getDataVector();
						
					}
				});
				
			}
		});
		//初始化表
		String[] titles= {"id","反馈人","疑难反馈信息","老师答复","教师姓名"};
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "\u53CD\u9988\u4EBA", "\u7591\u96BE\u53CD\u9988\u4FE1\u606F", "\u6559\u5E08\u7B54\u590D", "\u6559\u5E08\u59D3\u540D"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setMinWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setMinWidth(300);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setMinWidth(20);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toTeacher();
			}

		});
		btnNewButton.setBounds(485, 259, 85, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u7B54\u590D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reply(e);
			}
		});
		btnNewButton_1.setBounds(64, 503, 85, 28);
		frame.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(158, 437, 515, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7B54 \u590D \u5185 \u5BB9\uFF1A");
		lblNewLabel.setBounds(64, 442, 84, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("\u5237\u65B0");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		btnNewButton_2.setBounds(326, 259, 85, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 364, 515, 38);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7591\u96BE\u53CD\u9988\u4FE1\u606F\uFF1A");
		lblNewLabel_1.setBounds(64, 369, 85, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		idd = new JTextField();
		idd.setEnabled(false);
		idd.setBounds(158, 305, 52, 21);
		frame.getContentPane().add(idd);
		idd.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u53CD\u9988\u4EBAid:");
		lblNewLabel_2.setBounds(64, 308, 58, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Queryfeedback.class.getResource("/images/timg (5.2).jpg")));
		lblNewLabel_3.setBounds(10, 10, 874, 574);
		frame.getContentPane().add(lblNewLabel_3);
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
			lineData.add(p.getId());
			lineData.add(p.getFname());
			lineData.add(p.getFeedback());
			lineData.add(p.getInformation());
			lineData.add(p.getTeaName());
			model.addRow(lineData);
		}
	}
	//老师答复模块
	private void reply(ActionEvent e) {
		
		String content=textField.getText();
		String id=idd.getText();
		System.out.println(id);
		if(StringUtil.isEmpty(content)) {
			JOptionPane.showMessageDialog(null, "请输入答复内容！");
			JTextComponent FnameText = null;//JTextComponent 文本组件
			FnameText.setText("");
	        return;	
		}         	
		   //数据导入数据库
		   try {
			  //修改数据库表
			  String sql = "update feedback set information ="+"'"+content+"'"+" where id="+id;
			  System.out.println(sql);
		      Object[] params= null;
		      new QueryRunner().execute(sql, params);
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			JOptionPane.showMessageDialog(null, "答复成功！");
		}
		return;	
	}
	private void toTeacher() {
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
}

