package com.whf.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Popup {
	public static String choicePath() {
		FileNameExtensionFilter filter=new FileNameExtensionFilter("*.xlsx","xlsx");
		JFileChooser fc=new JFileChooser();
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(false);
		int result=fc.showSaveDialog(null);
		String filePath=null;
		if (result==JFileChooser.APPROVE_OPTION) {
			File file=fc.getSelectedFile();
			if (!file.getPath().endsWith(".xls")) {
				file=new File(file.getPath()+".xls");
			}
			filePath = file.getPath();
		}
		return filePath;
	}
}
