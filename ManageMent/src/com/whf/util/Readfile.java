package com.whf.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.whf.dao.impl.CourseDaoImpl;
import com.whf.pojo.Course;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
 
public class Readfile {
	public void save (){
		FileNameExtensionFilter filter=new FileNameExtensionFilter("*.xls","xls");
		JFileChooser fc=new JFileChooser();
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(false);
		int result=fc.showSaveDialog(null);
		if (result==JFileChooser.APPROVE_OPTION) {
			File file=fc.getSelectedFile();
			if (!file.getPath().endsWith(".xls")) {
				file=new File(file.getPath()+".xls");
			}
//			System.out.println("file path="+file.getPath());
			 //1. 导出Excel的路径
            String filePath = file.getPath();
            WritableWorkbook wwb =null;
             
            try {
                wwb = Workbook.createWorkbook(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
             
            //创建Excel表的"学生"区域的数据
            WritableSheet sheet = wwb.createSheet("课程表",0);//或者rwb.getSheet(0)获取第一个区域
           
           CourseDaoImpl cdpl=new CourseDaoImpl();
           List list=cdpl.course_query(1);
            for(int i = 0; i<list.size(); i++){
                //Number对应数据库的int类型数据
                try {
					sheet.addCell(new jxl.write.Number(0,i,((Course) list.get(i)).getId()));
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //Label对应数据库String类型数据
                try {
					sheet.addCell(new Label(1,i,((Course) list.get(i)).getWeek()));
					sheet.addCell(new Label(2,i,((Course) list.get(i)).getMorning()));
					sheet.addCell(new Label(3,i,((Course) list.get(i)).getAfternoon()));
					sheet.addCell(new Label(1,i,((Course) list.get(i)).getEvening()));
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            try {
				wwb.write();
				wwb.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
          
		}
	}
}
