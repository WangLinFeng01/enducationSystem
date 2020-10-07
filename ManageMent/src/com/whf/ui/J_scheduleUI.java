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
	private String str = "数学";
	//设置一个yinyong变量可以拿到里面的东西呢.

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
		frame.setTitle("数学进度图");
		frame.setBounds(100, 100, 598, 394);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 99, 410, 181);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		//初始化表
		String[] titles= {"编号","date","info","status"};
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
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//上传数据库
				selectFirst();
			}
		});
		btnNewButton_1.setBounds(434, 46, 66, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(286, 48, 104, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("刷新");
		btnNewButton.setIcon(new ImageIcon(J_scheduleUI.class.getResource("/images/chongzi1.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//调用table上的特定的鼠标点击事件-->将数据传入到数据库中
				if(str.equals("数学")) {
					fillTable();
				}else if(str.equals("java基础")) {
					fillJavaTable();
				}
				
			}
		});
		btnNewButton.setBounds(434, 294, 104, 33);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("编号:");
		lblNewLabel.setBounds(90, 51, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("状态:");
		lblNewLabel_1.setBounds(243, 49, 37, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("返回主界面");
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
		
		JMenu mnNewMenu = new JMenu("课程类型");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("数学");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setTitle("数学进度图");
				str = "数学";
				fillTable();
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("java基础");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setTitle("java基础进度图");
				str= "java基础";
				fillJavaTable();
			}
		});
		
		//填充表格
		fillTable();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//跳转至教师界面
	protected void toTeacher(ActionEvent arg0) {
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

	private void dispose() {
		frame.dispose();
		
	}

	//将数学的表格中的内容拿出来
	private void fillTable() {
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//清空一下表中的数据
		model.setRowCount(0);
		//拿到放在结果集中的数据
		List<T_schedule> datas=t.getDatas("数学");
		for(T_schedule p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getId());
			lineData.add(p.getDate());
			lineData.add(p.getInfo());
			lineData.add(p.getStatus());
			model.addRow(lineData);
		}
		System.out.println("刷新成功");
		//设置一下table上的鼠标点击事件
		mouseClicked();
	}

	//将java的表格中的内容拿出来
	private void fillJavaTable() {
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//清空一下表中的数据
		model.setRowCount(0);
		//拿到放在结果集中的数据
		List<T_schedule> datas=t.getDatas("java基础");
		for(T_schedule p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getId());
			lineData.add(p.getDate());
			lineData.add(p.getInfo());
			lineData.add(p.getStatus());
			model.addRow(lineData);
		}
		System.out.println("刷新成功");
		//设置一下table上的鼠标点击事件
		mouseClicked();
	}
	
	private void mouseClicked() {
		table.addMouseListener(new MouseAdapter() {
			//重写点击事件
			@Override
			public void mouseClicked(MouseEvent e) {
				//获取单元格
				//列数
				int columnCount=table.getSelectedColumn() ;
				//行数
				int rowCount=table.getSelectedRow();
				
//				System.out.println(columnCount+".."+rowCount);
				//得到单元格中的内容
				Object value=table.getValueAt(rowCount, columnCount);
				//获取行信息
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				//获取到表中所有的数据
				Vector v=model.getDataVector();
				
				String str = v.get(rowCount).toString();
				//得到这一行中的第一个ID值;
				String idStrs = str.split(",")[0].substring(1);
				//编号
				textField.setText(idStrs); 
				//status的值
				//判断value的值是否是int类型  修改一个bug.
				if(value instanceof String) {
					textField_1.setText((String) value);					
				}
			}
		});
	}
	//修改表中数据后上传到数据库的方法
	/**
	 * @param value 是点击后的表的单元格内的值
	 * @param str 点击这一行的ID值 
	 */
	
	//执行SQL操作
	//有个bug --- 当输入框是空的时候,插入会报错
	private void selectFirst() {
		//判断输入框是否为空,为空不执行操作
		if(textField.getText().equals("") || textField_1.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "请选择要修改课程状态的一行" ,"alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String str = textField_1.getText();//id
		int id =Integer.parseInt(textField.getText());//status
		//执行操作
		String str1 = "";
		if(!str.equals(str1)) {
			String sql = "update t_schedule set status = ? where id = ?";
			Object[] params = {str,id};//参数值
			new QueryRunner().execute(sql, params);
		}
		return;
	}
	
}

