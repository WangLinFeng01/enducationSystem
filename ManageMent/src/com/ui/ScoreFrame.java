package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.StringUtils;
import com.pojo.Student;
import com.pojo.T_schedule;
import com.pojo.T_score;
import com.util.BeanListResultSetHandler;
import com.util.JdbcUtils;
import com.util.QueryRunner;
import com.util.StringUtil;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ScoreFrame extends JFrame {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JTextField stupNameText;
	private JTable table;
	private JTextField textField_1;
	String l1;//得到姓名
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreFrame frame = new ScoreFrame();
					frame.frame.setVisible(true);
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
	public ScoreFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u8BE2");
		frame.setBounds(400, 170, 479, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 66, 23);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(75, 11, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		l1=LoginFrame.stupNameText.getText();
		textField.setText(l1);

		
		
		
		btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setIcon(new ImageIcon(ScoreFrame.class.getResource("/images/goBack.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack11(e);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			
		});
		btnNewButton.setBounds(351, 247, 92, 29);
		frame.getContentPane().add(btnNewButton);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 58, 384, 179);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u79D1\u76EEID","\u6210\u7EE9"
			}
		));
		scrollPane.setViewportView(table);
		
		
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(258, 25, 82, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2");
		btnNewButton_1.setIcon(new ImageIcon(ScoreFrame.class.getResource("/images/enter.png")));
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "学生ID不能为空！");	
					return;
				}
				fillTable();
			}
		});
		btnNewButton_1.setBounds(350, 21, 93, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u751FID\uFF1A");
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 12));
		lblNewLabel_1.setBounds(204, 26, 55, 18);
		frame.getContentPane().add(lblNewLabel_1);

	}

	private String toString(Integer score) {
		// TODO Auto-generated method stub
		return null;
	}
	//查询根据页面上的学生姓名得到学生表中的学生id
	
	
	//查询分数表中的内容
	public List<T_score> queryT_score(){
		String sql = "select * from t_scoer where studentId = ?";
		Integer id = Integer.parseInt(textField_1.getText());
		Object[] params= {id};
		List<T_score> list=(List<T_score>) QueryRunner.query(sql, params, new BeanListResultSetHandler<T_score>(T_score.class));
		return list;
	}
	
	//将数学的表格中的内容拿出来
	private void fillTable() {
		TableModel jmodel=table.getModel();
		DefaultTableModel model=(DefaultTableModel)jmodel;
		//清空一下表中的数据
		model.setRowCount(0);
		//拿到放在结果集中的数据
		List<T_score> datas=queryT_score();
		for(T_score p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getPaperId());
			lineData.add(p.getScore());
			model.addRow(lineData);
		}
		System.out.println("刷新成功");
		//设置一下table上的鼠标点击事件
	}
	
	

	protected void goBack11(ActionEvent e) {
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
