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
			
		// 1. 得到数据
		CategoryDataset dataset = getDataSet(str1,str2,str3,i1,i2,i3);
		// 2. 构造chart
		JFreeChart chart = ChartFactory.createBarChart3D(
				"课程进度图", // 图表标题
				"科目", // 目录轴的显示标签--横轴
				"学时", // 数值轴的显示标签--纵轴
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、
				true, // 是否显示图例(对于简单的柱状图必须
				false, // 是否生成工具
				false // 是否生成URL链接
				);

 
		// 4. chart输出图片
//		writeChartAsImage(chart);
		image = chart.createBufferedImage(296, 250);
// 
//		// 5. chart 以swing形式输出
//		ChartFrame pieFrame = new ChartFrame("XXX", chart);
//		pieFrame.pack();
//		pieFrame.setVisible(true);
 
	}
 
	/**
	 * 获取一个演示用的组合数据集对象
	 * 
	 * @return
	 */
	private  CategoryDataset getDataSet(String str1,String str2,String str3,int i1,int i2,int i3) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(i1, "完成", str1);
		dataset.addValue(42-i1, "未完成", str1);
		dataset.addValue(i2, "完成", str2);
		dataset.addValue(42-i2, "未完成", str2);
		dataset.addValue(i3, "完成",str3);
		dataset.addValue(42-i3, "未完成", str3);
		return dataset;
	}
}

