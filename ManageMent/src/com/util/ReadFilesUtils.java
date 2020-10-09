package com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * @author Administrator
 *	文件输出流工具类
 */
public class ReadFilesUtils {
	//保存文件方法
	public static void save(String filePath,HSSFWorkbook wb) {
		File file=new File(filePath);
		OutputStream stream=null;
		try {
			stream=new FileOutputStream(file);
			wb.write(stream);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
            if(stream != null);
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
			
		
	}
}
