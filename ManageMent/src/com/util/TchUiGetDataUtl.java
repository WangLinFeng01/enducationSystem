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
		//"学号","姓名","语文","数学","英语","总分"}
		if(title=="学生" || title=="全部") {
			list.add("学号");
			list.add("姓名");
			list.add("语文");
			list.add("数学");
			list.add("英语");
			list.add("总分");
		}else if(title=="科目"){
			if(text.equals("语文")||text.equals("数学")||text.equals("英语")) {
				list.add("学号");
				list.add("姓名");
				list.add(text);
			}
		}	
		return list;	
	}
	
	
	public static List<TScore> getDateBase(String title,String text) {
		scdaoip=new ScoreDaoImpl();
		List<TScore> list=new ArrayList<TScore>(); 
		if(title=="学生") {
			list=scdaoip.query1(text);
		}else if(title=="科目"){
			list=scdaoip.query2(text);
		}else if(title=="全部") {
			list=scdaoip.Score_query();
		}
		return list;	
	}
	public static void main(String[] args) {
		List<String> list=TchUiGetDataUtl.getcolumnNames("科目", "英语");
		for (String string : list) {
			System.out.println(string);
		}
	}
}
