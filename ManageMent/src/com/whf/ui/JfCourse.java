package com.whf.ui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.whf.dao.CourseDao;
import com.whf.dao.impl.CourseDaoImpl;
import com.whf.pojo.Course;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JfCourse {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JfCourse window = new JfCourse();
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
	public JfCourse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 184, 670, 233);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		//�����и�
		table.setRowHeight(60);
		
		//���ñ�ͷ�߶�
		Dimension size = table.getTableHeader().getPreferredSize();
		size.height = 50;//�����µı�ͷ�߶�40
		table.getTableHeader().setPreferredSize(size);
		
		// ����table���ݾ���
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		 tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
		 table.setDefaultRenderer(Object.class, tcr);
		
		scrollPane.setViewportView(table);
		CourseDao coursedao=new CourseDaoImpl();
		List<Course> list=coursedao.course_query();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"����", list.get(0).getMorning(), list.get(1).getMorning(), list.get(2).getMorning(), list.get(3).getMorning(), list.get(4).getMorning(), list.get(5).getMorning(), list.get(6).getMorning()},
				{"����", list.get(0).getAfternoon(), list.get(1).getAfternoon(), list.get(2).getAfternoon(), list.get(3).getAfternoon(), list.get(4).getAfternoon(),list.get(5).getAfternoon(), list.get(6).getAfternoon()},
				{"����", list.get(0).getEvening(), list.get(1).getEvening(), list.get(2).getEvening(), list.get(3).getEvening(), list.get(4).getEvening(),list.get(5).getEvening(), list.get(6).getEvening()},
			},
			new String[] {
				"", "\u5468\u4E00", "\u5468\u4E8C", "\u5468\u4E09", "\u5468\u56DB", "\u5468\u4E94", "\u5468\u516D", "\u5468\u5929"
			}
		));
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u8868\r\n");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 40));
		lblNewLabel.setBounds(440, 81, 122, 55);
		frame.getContentPane().add(lblNewLabel);
		JButton replaceBtn=new JButton("���¿γ̱�");
		replaceBtn.setBounds(650, 450, 100, 50);
		frame.add(replaceBtn);
		
		//���¿γ̱�ť�¼�
		replaceBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					  List<Course> list=new ArrayList<Course>();
					  DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					  int columnCount=tableModel.getColumnCount();
					  for(int i=0;i<columnCount-1;i++){
						  Course course=new Course(i+1, tableModel.getColumnName(i+1), (String)tableModel.getValueAt(0, i+1), (String)tableModel.getValueAt(1, i+1), (String)tableModel.getValueAt(2, i+1));
					      CourseDao cd=new CourseDaoImpl();
					      cd.course_update(course);
					  }
				
			}
		});
		
		JButton exitBtn=new JButton("�˳�");
		exitBtn.setBounds(770, 450, 100, 50);
		frame.add(exitBtn);
		

		JLayeredPane  jj=new JLayeredPane();
		ImageIcon image=new ImageIcon("src/images/part-00337-957.jpg");//�����һ��ͼ�Ϳ��Կ���Ч����		
		//������������Щ����
		JPanel jp=new JPanel();
		jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
 
		JLabel jl=new JLabel(image);
//		jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
		jp.add(jl);
		
		//��jp�ŵ���ײ㡣
		jj.add(jp,JLayeredPane.DEFAULT_LAYER);
		//��jb�ŵ���һ��ĵط�
		jj.add(lblNewLabel,JLayeredPane.MODAL_LAYER);
		jj.add(scrollPane,JLayeredPane.MODAL_LAYER);
		jj.add(exitBtn,JLayeredPane.MODAL_LAYER);
		jj.add(replaceBtn,JLayeredPane.MODAL_LAYER);
		frame.setLayeredPane(jj);
		frame.setSize(image.getIconWidth(),image.getIconHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(image.getIconWidth(),image.getIconHeight());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
