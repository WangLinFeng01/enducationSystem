package com.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dao.ScoreDao;
import com.dao.impl.ScoreDaoImpl;
import com.pojo.TScore;
import com.util.ExcelUtil;
import com.util.Popup;
import com.util.ReadFilesUtils;
import com.util.TchUiGetDataUtl;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Color;

public class TeacherUI  {

	JFrame frame;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField textField;
	private TchUiGetDataUtl tchu;
	String city =null;//接收下拉菜单中的值
	String text=null;
	int typeti=0;//接收city传值
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherUI window = new TeacherUI();
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
	public TeacherUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 831, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBounds(0, 0, 813, 44);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//标题栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 7, 53, 31);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("菜单");
		mnNewMenu.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu);
		
		JMenuItem exclebtn=new JMenuItem("导出xls");
		mnNewMenu.add(exclebtn);
		
		
		
		JMenuItem exitbtn=new JMenuItem("退出");
		mnNewMenu.add(exitbtn);
		exitbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				goBackTeacher(e);
			}
		} );
		
		
		
		String[] a= {"学生","科目","全部"};
		JList<String> jlist=new JList<String>(a);
		JComboBox<String> comboBox = new JComboBox<String>(a);
		comboBox.setBounds(305, 7, 83, 31);
		panel.add(comboBox);
		 
		
		JLabel lblNewLabel_1 = new JLabel("查询类型：");
		lblNewLabel_1.setBounds(229, 0, 159, 44);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("请输入：");
		lblNewLabel.setBounds(411, 0, 169, 44);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(472, 10, 108, 24);
		panel.add(textField);
		textField.setColumns(10);
		
//		comboBox.addActionListener(new ActionListener() {
//	         @Override
//	         public void actionPerformed(ActionEvent e) {
//	        	 city= comboBox.getSelectedItem().toString();
//	         }
//	    });
//			
		JButton btnNewButton = new JButton("查询");
		btnNewButton.setBounds(606, 7, 63, 27);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 city= comboBox.getSelectedItem().toString();
				 text=textField.getText();
				
				 String wb="语文 数学 英语";
				 int flag= wb.indexOf(text);
				 
				 if(text.equals("")) { 
					 JOptionPane.showMessageDialog(null, "请输入查询内容！", "", JOptionPane.ERROR_MESSAGE); 
				 }else if(city=="科目"&&flag==-1){
					 JOptionPane.showMessageDialog(null, "查询内容无效！", "", JOptionPane.ERROR_MESSAGE); 
				 }else {
					 if(city=="学生"||city=="全部"){
						 typeti=6;
					 }else if(city=="科目"){
						 typeti=3;
					 }
					 List<String>li=TchUiGetDataUtl.getcolumnNames(city, text);
					 String[] column= new String[typeti];
					 for (int i = 0; i < column.length; i++) {
						column[i]=li.get(i);
					 }
					 List<TScore> list=TchUiGetDataUtl.getDateBase(city, text);
					 String[][] tablevul=new String[list.size()][column.length];
					 for(int i=0;i<list.size();i++) {
						 if(city=="学生"||city=="全部") {
							 tablevul[i][0]=String.valueOf(list.get(i).getStudentId());
							 tablevul[i][1]=list.get(i).getUserName();
							 tablevul[i][2]=String.valueOf(list.get(i).getChinese());
							 tablevul[i][3]=String.valueOf(list.get(i).getMathematics());
							 tablevul[i][4]=String.valueOf(list.get(i).getEnglish());
							 tablevul[i][5]=String.valueOf(list.get(i).getTotal());
						 }else if(city=="科目") {
							 tablevul[i][0]=String.valueOf(list.get(i).getStudentId());
							 tablevul[i][1]=list.get(i).getUserName();
							 tablevul[i][2]=String.valueOf(list.get(i).getScore());
						 }
					 }
					 tableModel = new DefaultTableModel(tablevul,column);
					 table = new JTable(tableModel);
					 JScrollPane scrollPane = new JScrollPane(table);
					 scrollPane.setViewportView(table);
					 scrollPane.setBounds(0, 43, 813, 442);
					 frame.getContentPane().add(scrollPane);
				 }	
								
			}
		});
		
			
		exclebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames ={"学号","姓名","语文","数学","英语","总分"};
				ScoreDao tp=new ScoreDaoImpl();
				List<TScore> list=tp.Score_query();
				String[][] tableVales=new String[list.size()][columnNames.length];
				for(int i=0;i<list.size();i++) {
					tableVales[i][0]=String.valueOf(list.get(i).getStudentId());
					tableVales[i][1]=list.get(i).getUserName();
					tableVales[i][2]=String.valueOf(list.get(i).getChinese());
					tableVales[i][3]=String.valueOf(list.get(i).getMathematics());
					tableVales[i][4]=String.valueOf(list.get(i).getEnglish());
					tableVales[i][5]=String.valueOf(list.get(i).getTotal());
				}
				HSSFWorkbook wb=ExcelUtil.getHSSFWorkbook("学生分数统计", columnNames, tableVales, null);
            	String filePath=Popup.choicePath();
            	if(null!=filePath) {
            		ReadFilesUtils.save(filePath, wb);            		
            	}	
			}
		 });
	}	
	
	private void dispose2() {
	  	frame.dispose();		
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
	
}
