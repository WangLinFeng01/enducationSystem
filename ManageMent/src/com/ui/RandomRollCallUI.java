package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

import com.dao.ExListenerDao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class RandomRollCallUI {
	JButton btn,btn_1;
	JLabel lblNewLabel_1;
	ImlThread t;
	JFrame frame;
	//创建监听器
	ExListenerDao listener;
	//判断当前状态
	static boolean isStop;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomRollCallUI window = new RandomRollCallUI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	public RandomRollCallUI() {
		initialize();
	}
	 void initialize() {
		frame = new JFrame("随机点名界面");
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(360, 120, 671, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u6B22\u8FCE\u4F7F\u7528");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(273, 201, 197, 45);
		frame.getContentPane().add(lblNewLabel_1);
		//开始按钮
		JButton btn = new JButton("\u5F00\u59CB");
		btn.setIcon(new ImageIcon(RandomRollCallUI.class.getResource("/images/start.png")));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取触发事件对象实现监听映射
		    	Object obj=e.getSource();
				if(obj==btn) {
					try {
						t= new ImlThread(lblNewLabel_1);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
		        	try {
		    			t=new ImlThread(lblNewLabel_1);
		    		} catch (Exception e1) {
		    			e1.printStackTrace();
		    		}
		        	
		    		try {
		    			isStop = true;
		    			t.setbool(isStop);
		    			t.start();
					} catch (Exception e1) {
						System.err.println("格式错误");
					}
		    		
		    	}
			}
		});
		btn.setBounds(179, 328, 97, 31);
		frame.getContentPane().add(btn);
		
		//停止按钮
		JButton btn_1 = new JButton("\u505C\u6B62");
		btn_1.setIcon(new ImageIcon(RandomRollCallUI.class.getResource("/images/stop1.png")));
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//			//获取触发事件对象实现监听映射
		    	Object obj=e.getSource();
		    	if(obj == btn_1){
		    		isStop = false;
		    		t.setbool(isStop);
		    		System.out.println("this is stop");
		    		}
			}
		});
		btn_1.setBounds(384, 328, 114, 31);
		frame.getContentPane().add(btn_1);
		
		JLabel lblNewLabel = new JLabel("\u73A9\u7684\u5C31\u662F\u6FC0\u60C5");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 50));
		lblNewLabel.setBounds(179, 98, 302, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setIcon(new ImageIcon(RandomRollCallUI.class.getResource("/images/goBack.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toTeacherFrame(e);
			}
		});
		btnNewButton.setBounds(179, 396, 97, 31);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(RandomRollCallUI.class.getResource("/images/timg (6.3).jpg")));
		lblNewLabel_2.setBounds(10, 10, 637, 453);
		frame.getContentPane().add(lblNewLabel_2);
		
		
	}
	protected void toTeacherFrame(ActionEvent e) {

		//跳转至老师界面
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


	void AListener(ExListenerDao listener, JLabel lblNewLabel_1){
		this.listener=listener;
		listener.set(btn, btn_1, lblNewLabel_1, isStop);
		//AncestorListener 
		//添加监听
		btn.addActionListener((ActionListener) listener); 
		btn_1.addActionListener((ActionListener) listener);
		
		}
}
