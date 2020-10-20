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
		
		JLabel lblNewLabel = new JLabel("课程进度表");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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
		
		lblNewLabel_2 = new JLabel("科目名");
		lblNewLabel_2.setBounds(45, 219, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(184, 216, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u8FDB\u5EA6\u4FEE\u6539:");
		lblNewLabel_3.setBounds(109, 219, 65, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTableStruct();
				//修改i1到i3的值
			}
		});
		btnNewButton.setBounds(260, 215, 72, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("刷新");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTable();//填充
				bcd.getBarChart3D(str1,str2,str3, i1, i2, i3);
				ImageIcon icon = new ImageIcon(bcd.image);
				//改变图片大小	    
				icon.setImage(icon.getImage().getScaledInstance(296,199,Image.SCALE_DEFAULT));//80和100为大小 可以自由设置		
				picture.setIcon(icon);//将生成的柱状图放入JLabel中.
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
		//改变图片大小	    
		icon.setImage(icon.getImage().getScaledInstance(296,199,Image.SCALE_DEFAULT));//80和100为大小 可以自由设置		
		picture.setIcon(icon);//将生成的柱状图放入JLabel中.
		frame.getContentPane().add(picture);
		picture.setIcon(icon);//将生成的柱状图放入JLabel中.
		
		JButton btnNewButton_1_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toTeacherFrame(e);
			}

			private void toTeacherFrame(ActionEvent e) {
				frame.dispose();//当前的窗体关闭
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
	
	//将数学的表格中的内容拿出来
	private void fillTable() {
		String[] strS = new String[3];
		int[] strS1 = new int[3];
		int i = 0;
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//清空一下表中的数据
		model.setRowCount(0);
		//拿到放在结果集中的数据
		List<T_schedule> datas=t.getDatas();
		for(T_schedule p:datas) {
			Vector lineData=new Vector();
			strS[i] = p.getSubject();//拿到科目名
			lineData.add(p.getSubject());
			lineData.add(p.getHour());
			lineData.add(p.getSpeed());
			strS1[i] = p.getSpeed();//拿到初始进度
			model.addRow(lineData);
			i++;
		}
		System.out.println("刷新成功");
		str1 = strS[0];
		str2 = strS[1];
		str3 = strS[2];
		i1 = strS1[0];
		i2 = strS1[1];
		i3 = strS1[2];
		//设置一下table上的鼠标点击事件
		mouseClicked();
	}
	
	//鼠标点击事件
	private void mouseClicked() {
		table.addMouseListener(new MouseAdapter() {
			//重写点击事件
			@Override
			public void mouseClicked(MouseEvent e) {
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
				//得到这一行中的第一个subject值;
				String subStrs = str.split(",")[0].substring(1);
				//编号
				textField.setText(value+"");					
				//status的值
				//判断value的值是否是int类型  修改一个bug.
				if(subStrs instanceof String) {
					lblNewLabel_2.setText(subStrs); 
				}
			}
		});
	}
	
	private void updateTableStruct() {
		//判断输入框是否为空,为空不执行操作
		if(textField.getText().equals("") || lblNewLabel_2.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "请选择要修改课程状态的一行" ,"alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int str = Integer.parseInt(textField.getText());//id
		String stu =lblNewLabel_2.getText();//status
		//执行操作
		String str1 = "";
		if(!str1.equals(stu)) {
			String sql = "update t_schedule set speed = ? where subject = ?";
			Object[] params = {str,stu};//参数值
			int i =new QueryRunner().execute(sql, params);
			if(i>0) {
				JOptionPane.showMessageDialog(null, "更新成功！");				
			}
		}
		return;
	}
}
