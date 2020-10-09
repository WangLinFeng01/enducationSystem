package com.base;


 
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.FileOutputStream;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
 
public class BarChartDemoImpl {
		public Image image;
		public void getBarChart3D(String str1,String str2,String str3,int i1,int i2,int i3) {
			
		// 1. �õ�����
		CategoryDataset dataset = getDataSet(str1,str2,str3,i1,i2,i3);
		// 2. ����chart
		JFreeChart chart = ChartFactory.createBarChart3D(
				"�γ̽���ͼ", // ͼ�����
				"��Ŀ", // Ŀ¼�����ʾ��ǩ--����
				"ѧʱ", // ��ֵ�����ʾ��ǩ--����
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ��
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ����
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);

 
		// 4. chart���ͼƬ
//		writeChartAsImage(chart);
		image = chart.createBufferedImage(296, 250);
// 
//		// 5. chart ��swing��ʽ���
//		ChartFrame pieFrame = new ChartFrame("XXX", chart);
//		pieFrame.pack();
//		pieFrame.setVisible(true);
 
	}
 
	/**
	 * ��ȡһ����ʾ�õ�������ݼ�����
	 * 
	 * @return
	 */
	private  CategoryDataset getDataSet(String str1,String str2,String str3,int i1,int i2,int i3) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(i1, "���", str1);
		dataset.addValue(42-i1, "δ���", str1);
		dataset.addValue(i2, "���", str2);
		dataset.addValue(42-i2, "δ���", str2);
		dataset.addValue(i3, "���",str3);
		dataset.addValue(42-i3, "δ���", str3);
		return dataset;
	}
}

