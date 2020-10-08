package com.whf.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.whf.dao.T_scoreDao;
import com.whf.dao.impl.T_scoreDaoImpl;
import com.whf.pojo.T_score;
import com.whf.util.ExcelUtil;
import com.whf.util.Popup;
import com.whf.util.ReadFilesUtils;
//维护表格
public class TeacherGUI extends JFrame{

    private DefaultTableModel tableModel;   //表格模型对象
    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;
    JFrame frame;
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherGUI window = new TeacherGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public TeacherGUI() {
    	initialize();
    }
    private void initialize() {
    	frame = new JFrame();
        frame.setBounds(100,100,800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"学号","语文","数学","英语"};   //列名
        T_scoreDao scoreDao=new T_scoreDaoImpl();
        List<T_score> list=scoreDao.T_score_query();
        //数据封装
        Integer[] tepm=new Integer[list.size()];
		int index=0;
		for (T_score t_score : list) {
			if(index==tepm.length) {
				break;
			}
			tepm[index]=t_score.getStudentId();
			index++;
		}
		int max=(int)Collections.max(Arrays.asList(tepm));
		
		String [][]tableVales=new String[max][4]; //数据
		
		int count=0;
		for (int i = 0; i < max; i++) {
			tableVales[i][0]=String.valueOf(i+1);
    	  for (int j = 1; j <4; j++) {
    		  tableVales[i][j]=String.valueOf(list.get(count).getScore());
    		   count++;
    	  }
		}
		
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
        
      
        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
        frame.getContentPane().add(panel,BorderLayout.SOUTH);

        final JButton excelButton = new JButton("导出");   //修改按钮
        excelButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            	HSSFWorkbook wb=ExcelUtil.getHSSFWorkbook("学生分数统计", columnNames, tableVales, null);
            	String filePath=Popup.choicePath();
            	if(null!=filePath) {
            		ReadFilesUtils.save(filePath, wb);            		
            	}
            }
        });
        panel.add(excelButton);
        final JButton delButton = new JButton("删除");
        delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                    tableModel.removeRow(selectedRow);  //删除行
                }
            }
        });
        panel.add(delButton);
        
        JButton exitBtn = new JButton("退出");
        panel.add(exitBtn);
        exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBackTeacher(e);
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
