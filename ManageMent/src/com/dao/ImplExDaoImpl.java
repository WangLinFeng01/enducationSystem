package com.dao;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.ui.ImlThread;


public class ImplExDaoImpl implements ExListenerDao {
	
	JButton btn,btn_1;
	JLabel lblNewLabel_1;
	static boolean isStop;
	ImlThread t;
    @Override
    public void set(AbstractButton btn, AbstractButton btn_1, JLabel lblNewLabel_1, Object isStop) {
    	btn=btn;
    	btn_1=btn_1;
    	this.lblNewLabel_1=(JLabel) lblNewLabel_1;
    	this.isStop=(boolean) isStop;
    	
    	try {
			t=new ImlThread(lblNewLabel_1);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

}
