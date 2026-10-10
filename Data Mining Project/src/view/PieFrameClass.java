package view;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieFrameClass extends JFrame{
	public PieFrameClass() {
		
		setTitle("Pie Chart Demo");
		setSize(600, 500);
		
		PieDataset dataset = createDataset();
		JFreeChart chart = ChartFactory.createPieChart("Favourite Toronto Sports Team", dataset, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		
		add(chartPanel);
		setVisible(true);
	}

	private PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Raptors", 50);
		dataset.setValue("Leafs", 30);
		dataset.setValue("Jays", 15);
		dataset.setValue("TFC", 5);
		return dataset;
		
	}
}
