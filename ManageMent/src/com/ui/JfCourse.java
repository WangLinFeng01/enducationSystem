package com.ui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.dao.CourseDao;
import com.dao.impl.CourseDaoImpl;
import com.pojo.Course;
import com.pojo.Student;
import com.pojo.Teacher;
import com.util.Readfile;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class JfCourse extends JFrame {

	JFrame frame;
	private JTable table;
	private JTextField stupNameText;
	private JTextField textField11;
	private JTextField textField22;
	private Student user;
	private  Teacher tea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JfCourse frame = new JfCourse();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JfCourse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
//		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 184, 670, 233);
		frame.getContentPane().add(scrollPane);
		
	      
		table = new JTable();
		
		//设置行高
		table.setRowHeight(60);
		
		//设置表头高度
		Dimension size = table.getTableHeader().getPreferredSize();
		size.height = 50;
		//设置新的表头高度40
		table.getTableHeader().setPreferredSize(size);
		
		 // 设置table内容居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		 tcr.setHorizontalAlignment(SwingConstants.CENTER);
		 // 这句和上句作用一样
		 table.setDefaultRenderer(Object.class, tcr);
		
		scrollPane.setViewportView(table);
		CourseDao coursedao=new CourseDaoImpl();
		List<Course> list=coursedao.course_query(1);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"上午", list.get(0).getMorning(), list.get(1).getMorning(), list.get(2).getMorning(), list.get(3).getMorning(), list.get(4).getMorning(), list.get(5).getMorning(), list.get(6).getMorning()},
				{"下午", list.get(0).getAfternoon(), list.get(1).getAfternoon(), list.get(2).getAfternoon(), list.get(3).getAfternoon(), list.get(4).getAfternoon(),list.get(5).getAfternoon(), list.get(6).getAfternoon()},
				{"晚上", list.get(0).getEvening(), list.get(1).getEvening(), list.get(2).getEvening(), list.get(3).getEvening(), list.get(4).getEvening(),list.get(5).getEvening(), list.get(6).getEvening()},
			},
			new String[] {
				"", "\u5468\u4E00", "\u5468\u4E8C", "\u5468\u4E09", "\u5468\u56DB", "\u5468\u4E94", "\u5468\u516D", "\u5468\u5929"
			}
		));
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u8868\r\n");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 40));
		lblNewLabel.setBounds(440, 81, 122, 55);
		frame.getContentPane().add(lblNewLabel);
		JButton replaceBtn=new JButton("更新课程表");
		
		replaceBtn.setBounds(650, 450, 100, 50);
		frame.add(replaceBtn);
		
		JLabel jlable=new JLabel("班级");
	
		jlable.setBounds(20, 20, 100, 50);
		frame.add(jlable);
		
		//下拉列表
		 String [] a = {"1","2"};     //定义字符串
	     JComboBox jcombo = new JComboBox(a);   //实例化下拉列表
	     jcombo.setBounds(60, 30, 80, 30);
	     frame.add(jcombo);
	     
	     //下拉列表的改变事件
	     jcombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED) {
					List<Course> list=coursedao.course_query(Integer.parseInt((String)jcombo.getSelectedItem()));
					table.setModel(new DefaultTableModel(
						new Object[][] {
							{"上午", list.get(0).getMorning(), list.get(1).getMorning(), list.get(2).getMorning(), list.get(3).getMorning(), list.get(4).getMorning(), list.get(5).getMorning(), list.get(6).getMorning()},
							{"下午", list.get(0).getAfternoon(), list.get(1).getAfternoon(), list.get(2).getAfternoon(), list.get(3).getAfternoon(), list.get(4).getAfternoon(),list.get(5).getAfternoon(), list.get(6).getAfternoon()},
							{"晚上", list.get(0).getEvening(), list.get(1).getEvening(), list.get(2).getEvening(), list.get(3).getEvening(), list.get(4).getEvening(),list.get(5).getEvening(), list.get(6).getEvening()},
						},
						new String[] {
							"", "\u5468\u4E00", "\u5468\u4E8C", "\u5468\u4E09", "\u5468\u56DB", "\u5468\u4E94", "\u5468\u516D", "\u5468\u5929"
						}
					));
				}
				
				
			}
		});
		
	   //数据导出
		JButton excelBnt=new JButton("课表导出");
		excelBnt.setBounds(530, 450, 100, 50);
		frame.add(excelBnt);
		excelBnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Readfile rf=new Readfile();
				rf.save();
			}
			
		});
		
		//更新课程表按钮事件
				replaceBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						      int result=0;
							  DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
							  int columnCount=tableModel.getColumnCount();
							  for(int i=0;i<columnCount-1;i++){
								  Course course=new Course(((Integer.valueOf((String)jcombo.getSelectedItem()))-1)*7+i+1, tableModel.getColumnName(i+1), (String)tableModel.getValueAt(0, i+1), (String)tableModel.getValueAt(1, i+1), (String)tableModel.getValueAt(2, i+1),Integer.valueOf((String)jcombo.getSelectedItem()));
							      CourseDao cd=new CourseDaoImpl();
							      result=cd.course_update(course);
							  }
							  if(result>0) {
						    	  JOptionPane.showMessageDialog(null," 更改成功");
						      }else {
						    	  JOptionPane.showMessageDialog(null," 更改失败");
						      }
						
					}
				});
		
		JButton exitBtn=new JButton("退出");
		exitBtn.setBounds(770, 450, 100, 50);
		frame.add(exitBtn);
		
		textField22 = new JTextField("");
		textField22.setFont(new Font("SimSun", Font.BOLD, 20));
		textField22.setEditable(false);
		textField22.setBounds(73, 90, 59, 23);
		String l11=LoginFrame.stupNameText.getText();
		textField22.setText(l11);
	    textField22.setBorder(null);
	    frame.add(textField22);
	      
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LoginFrame.bool) {			
					goBackStudent(e);
				}else {			
				goBackTeacher(e); 
				}
			}
		});
	
	     
     
		frame.setBounds(100, 70, 1050, 650);
	}

	protected void goBackTeacher(ActionEvent e) {
		this.dispose2();//当前的窗体关闭
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

	private void dispose2() {
		frame.dispose();
		
	}

	protected void goBackStudent(ActionEvent e) {
		this.dispose1();//当前的窗体关闭
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

	private void dispose1() {
		frame.dispose();
		
	}
}
