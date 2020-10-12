package com.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import com.dao.QuestionSettingDao;
import com.dao.impl.QuestionSettingDaoImpl;
import com.pojo.Course;
import com.pojo.Question;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionSettingFrame extends JFrame {

	JFrame frame;
	private JTextArea subText;
	private JTextField optionA;
	private JTextField optionB;
	private JTextField optionC;
	private JTextField optionD;
	private JTextField optionE;
	private JTextField answer;
	private JTextField paperId;
	private JComboBox subjectId;
	private JComboBox type;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JButton resetBtn;
	private JButton exitBtn;
	private JTable table;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionSettingFrame window = new QuestionSettingFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public JTable showtable(int paperId) {
    	List<Question> list=new QuestionSettingDaoImpl().queryQuestion(paperId);
		Object[][] objData = new Object[list.size()][12];
		for(int i=0;i<objData.length;i++) {
			objData[i][0]=list.get(i).getId();
			objData[i][1]=list.get(i).getSubText();
			objData[i][2]=list.get(i).getOptionA();
			objData[i][3]=list.get(i).getOptionB();
			objData[i][4]=list.get(i).getOptionC();
			objData[i][5]=list.get(i).getOptionD();
			objData[i][6]=list.get(i).getOptionE();
			objData[i][7]=list.get(i).getAnswer();
			objData[i][8]=list.get(i).getType();
			objData[i][9]=list.get(i).getJoinTime();
			objData[i][10]=list.get(i).getSubjectId();
			objData[i][11]=list.get(i).getPaperId();
			
		}
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getValueAt(table.getSelectedRow(),0)!=null)
				{
					Integer s = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					id.setText(s.toString());
					String s1 = (String) table.getValueAt(table.getSelectedRow(), 1);
					subText.setText(s1);
					String s2 = (String) table.getValueAt(table.getSelectedRow(), 2);
					optionA.setText(s2);
					String s3 = (String) table.getValueAt(table.getSelectedRow(), 3);
					optionB.setText(s3);
					String s4 = (String) table.getValueAt(table.getSelectedRow(), 4);
					optionC.setText(s4);
					String s5 = (String) table.getValueAt(table.getSelectedRow(), 5);
					optionD.setText(s5);
					String s6 = (String) table.getValueAt(table.getSelectedRow(), 6);
					optionE.setText(s6);
					String s7 = (String) table.getValueAt(table.getSelectedRow(), 7);
					answer.setText(s7);
					String s8 = (String) table.getValueAt(table.getSelectedRow(), 8);
					type.setSelectedItem(s8);
					Integer s10 = (Integer) table.getValueAt(table.getSelectedRow(), 10);
					subjectId.setSelectedItem(s10.toString());
					Integer s11 = (Integer) table.getValueAt(table.getSelectedRow(), 11);
					QuestionSettingFrame.this.paperId.setText(s11.toString());
		
				}
				
			}
		});
		table.setModel(new DefaultTableModel(
				objData,
			new String[] {
				"\u9898\u53F7", "\u5185\u5BB9", "\u9009\u9879A", "\u9009\u9879B", "\u9009\u9879C", "\u9009\u9879D", "\u9009\u9879E", "\u6B63\u786E\u7B54\u6848", "\u9898\u76EE\u7C7B\u578B", "\u51FA\u9898\u65F6\u95F4", "\u79D1\u76EE", "\u8BD5\u5377"
			}
		));
    return table;

    }
	/**
	 * Create the application.
	 */
	public QuestionSettingFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("宋体", Font.PLAIN, 17));
		frame.setBounds(100, 102, 1194, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BD5\u9898\u7C7B\u578B\uFF1A");
		lblNewLabel.setBounds(26, 73, 75, 23);
		frame.getContentPane().add(lblNewLabel);
		
		subText = new JTextArea();
		subText.setBounds(95, 118, 300, 83);
		frame.getContentPane().add(subText);
	
		optionA = new JTextField();
		optionA.setBounds(95, 211, 303, 21);
		frame.getContentPane().add(optionA);
		optionA.setColumns(10);
		
		optionB = new JTextField();
		optionB.setBounds(95, 242, 303, 21);
		frame.getContentPane().add(optionB);
		optionB.setColumns(10);
		
		optionC = new JTextField();
		optionC.setBounds(94, 273, 304, 21);
		frame.getContentPane().add(optionC);
		optionC.setColumns(10);
		
		optionD = new JTextField();
		optionD.setBounds(95, 304, 303, 21);
		frame.getContentPane().add(optionD);
		optionD.setColumns(10);
		
		optionE = new JTextField();
		optionE.setBounds(95, 340, 303, 21);
		frame.getContentPane().add(optionE);
		optionE.setColumns(10);
		
		type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(new String[] {"\u5355\u9009", "\u591A\u9009"}));
		type.setBounds(96, 72, 75, 21);
		frame.getContentPane().add(type);
		
		answer = new JTextField();
		answer.setBounds(105, 379, 66, 21);
		frame.getContentPane().add(answer);
		answer.setColumns(10);
		
		paperId = new JTextField();
		paperId.setBounds(257, 410, 66, 21);
		frame.getContentPane().add(paperId);
		paperId.setColumns(10);
		JButton addBtn = new JButton("\u6DFB\u52A0");
		addBtn.setIcon(new ImageIcon(QuestionSettingFrame.class.getResource("/images/add.png")));
		addBtn.setBounds(78, 458, 93, 31);
		frame.getContentPane().add(addBtn);
		
		subjectId = new JComboBox();
		subjectId.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		subjectId.setBounds(98, 410, 72, 21);
		frame.getContentPane().add(subjectId);
		
		lblNewLabel_1 = new JLabel("\u8BD5\u9898\u5185\u5BB9\uFF1A");
		lblNewLabel_1.setBounds(26, 122, 75, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\u9009\u9879A\uFF1A");
		lblNewLabel_2.setBounds(47, 214, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("\u9009\u9879B\uFF1A");
		lblNewLabel_3.setBounds(47, 245, 54, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("\u9009\u9879C\uFF1A");
		lblNewLabel_4.setBounds(47, 276, 54, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("\u9009\u9879D\uFF1A");
		lblNewLabel_5.setBounds(47, 307, 54, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("\u9009\u9879E\uFF1A");
		lblNewLabel_6.setBounds(47, 343, 54, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("\u79D1\u76EE\uFF1A");
		lblNewLabel_7.setBounds(47, 413, 54, 15);
		frame.getContentPane().add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("\u8BD5\u5377ID\uFF1A");
		lblNewLabel_8.setBounds(204, 413, 54, 15);
		frame.getContentPane().add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("\u6B63\u786E\u7B54\u6848\uFF1A");
		lblNewLabel_9.setBounds(47, 382, 75, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("\u8BD5\u9898\u8BBE\u7F6E");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_10.setBounds(431, 10, 119, 45);
		frame.getContentPane().add(lblNewLabel_10);
		
		resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.setIcon(new ImageIcon(QuestionSettingFrame.class.getResource("/images/chongzi1.png")));
		resetBtn.setBounds(211, 458, 93, 31);
		frame.getContentPane().add(resetBtn);
		
		exitBtn = new JButton("\u9000\u51FA");
		exitBtn.setIcon(new ImageIcon(QuestionSettingFrame.class.getResource("/images/goBack.png")));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转至教师界面
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
		});
		exitBtn.setBounds(343, 458, 93, 31);
		frame.getContentPane().add(exitBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(431, 87, 704, 344);
		frame.getContentPane().add(scrollPane);
		

		scrollPane.setViewportView(showtable(1));
		
		JLabel lblNewLabel_11 = new JLabel("\u8BD5\u5377ID\uFF1A");
		lblNewLabel_11.setBounds(1019, 59, 54, 15);
		frame.getContentPane().add(lblNewLabel_11);
		
		//右上角的试卷id下拉列表实现
		QuestionSettingDao qs=new QuestionSettingDaoImpl();
		List list=qs.queryPaperId();
		String[] strings = new String[list.size()];
		list.toArray(strings);
		JComboBox comboBox = new JComboBox(strings);
		comboBox.setBounds(1073, 56, 59, 21);
		frame.getContentPane().add(comboBox);
		
		JButton updateBtn = new JButton("\u4FEE\u6539");
		updateBtn.setBounds(718, 460, 93, 27);
		frame.getContentPane().add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Question question=new Question();
				question.setId(Integer.valueOf(id.getText().toString()));
				question.setSubText(subText.getText());
				question.setOptionA(optionA.getText());
				question.setOptionB(optionB.getText());
				question.setOptionC(optionC.getText());
				question.setOptionD(optionD.getText());
				question.setOptionE(optionE.getText());
				question.setAnswer(answer.getText());
				question.setJoinTime(new Date());
				question.setType(type.getSelectedItem().toString());
				question.setSubjectId(Integer.valueOf(subjectId.getSelectedItem().toString()));
				question.setPaperId(Integer.valueOf(paperId.getText()));
				QuestionSettingDao qsd=new QuestionSettingDaoImpl();
				int a=qsd.questionUpdate(question);
				if(a>0) {
					JOptionPane.showMessageDialog(null, "修改成功");
				}
				scrollPane.setViewportView(showtable(Integer.parseInt((comboBox.getSelectedItem().toString().trim()))));
			}
			
		});
		
		
		JLabel lblNewLabel_12 = new JLabel("\u9898\u53F7\uFF1A");
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_12.setBounds(544, 461, 79, 23);
		frame.getContentPane().add(lblNewLabel_12);
		
		id = new JTextField();
		id.setBounds(591, 463, 66, 21);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		 //下拉列表的改变事件
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
					if(e.getStateChange()==ItemEvent.SELECTED){
						
						scrollPane.setViewportView(showtable(Integer.parseInt((comboBox.getSelectedItem().toString().trim()))));
					}
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				answer.setText("");
				optionA.setText("");
				optionB.setText("");
				optionC.setText("");
				optionD.setText("");
				optionE.setText("");	
				subText.setText("");
				paperId.setText("");
				
			}
		});
		
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				Date d=new Date();
				if("".equals(answer.getText())||optionA.getText().equals("")||optionB.getText().equals("")||optionC.getText().equals("")||optionD.getText().equals("")||subText.getText().equals("")||type.getSelectedItem().equals("")||subjectId .getSelectedItem().equals("")||paperId.getText().equals("")) {
			
					JOptionPane.showMessageDialog(null, "不能有空");
					
				}
 				Question question=new Question(answer.getText(),d,optionA.getText(),optionB.getText(),optionC.getText(),optionD.getText(),optionE.getText(),subText.getText(),(String)type.getSelectedItem(),Integer.valueOf((String)(String)subjectId .getSelectedItem()),Integer.valueOf((String)paperId.getText()));
				QuestionSettingDao questionset=new QuestionSettingDaoImpl();
				Integer result=questionset.questionSetting(question);
				if(result>0) {
					
					scrollPane.setViewportView(showtable(Integer.valueOf((String)(paperId.getText()))));
					comboBox.setSelectedItem(paperId.getText());
					JOptionPane.showMessageDialog(null, "添加成功");
//					
					
				}
				
			}

			private void dispose() {
				frame.dispose();
				
			}
		});
	}
}
