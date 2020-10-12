package com.util;

import java.util.ArrayList;
import java.util.List;

import com.dao.ScoreDao;
import com.dao.impl.ScoreDaoImpl;
import com.pojo.TScore;



public class TchUiGetDataUtl {
	private static ScoreDao scdaoip;
		
	
	public static List<String> getcolumnNames(String title,String text) {
		List<String> list=new ArrayList<String>(); 
		//"ѧ��","����","����","��ѧ","Ӣ��","�ܷ�"}
		if(title=="ѧ��" || title=="ȫ��") {
			list.add("ѧ��");
			list.add("����");
			list.add("����");
			list.add("��ѧ");
			list.add("Ӣ��");
			list.add("�ܷ�");
		}else if(title=="��Ŀ"){
			if(text.equals("����")||text.equals("��ѧ")||text.equals("Ӣ��")) {
				list.add("ѧ��");
				list.add("����");
				list.add(text);
			}
		}	
		return list;	
	}
	
	
	public static List<TScore> getDateBase(String title,String text) {
		scdaoip=new ScoreDaoImpl();
		List<TScore> list=new ArrayList<TScore>(); 
		if(title=="ѧ��") {
			list=scdaoip.query1(text);
		}else if(title=="��Ŀ"){
			list=scdaoip.query2(text);
		}else if(title=="ȫ��") {
			list=scdaoip.Score_query();
		}
		return list;	
	}
	public static void main(String[] args) {
		List<String> list=TchUiGetDataUtl.getcolumnNames("��Ŀ", "Ӣ��");
		for (String string : list) {
			System.out.println(string);
		}
	}
}
