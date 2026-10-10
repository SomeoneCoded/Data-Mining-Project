package controller;
/* Name: Dailiang Chen (Group #1)
 * Date: May 22, 2025
 * Class Code: ICS4U1-03
 * Teacher: Mr. Fernandes
 * Description: This class is the controller class
 * Major Skills: Object Oriented Programming, API, Model-View-Controller
 * Areas of Concern: Error message occurs when user presses a button when there's no data presented on the screen. This does not effect the overall program as once you have data, the button will work.
 */

// import statements
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import model.*;
import view.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

// this class is the controller class that reads in the fish species data and sends the information to the screen
public class MainController implements ActionListener, ChangeListener, ItemListener {
	private ArrayList<DistrictModel> districts = new ArrayList<DistrictModel>(); // district specific
	private FishDistributionFrame fishFrame = new FishDistributionFrame();
	private ArrayList<String> fileLines = new ArrayList<String>(); // arraylist of strings to store files
	private DefaultPieDataset dataset;
	private DistrictModel district;
	// general fish distribution across Ontario
	private HashMap<String, List<PopulationModel>> fishMap = new HashMap<String, List<PopulationModel>>();
	private int year = fishFrame.getYearSlider().getValue() - 2021;
	private String graphName = "";

	// constructor method
	public MainController() {
		// 1. Create frame
		// 1.1. Add change listener to year slider
		fishFrame.getYearSlider().setEnabled(false);
		fishFrame.getYearSlider().addChangeListener(this);
		// 1.2. Add item listener to toggle button
		fishFrame.getPieChartDimensionButton().addItemListener(this);

		// 2. Read in the data
		readFile();
		// 3. Create button options
		createButtons();

	}

	// method to read the file
	public void readFile() {
		for (int fileNumber = 0; fileNumber <= 17; fileNumber++) {
			try {
				File fishFile = new File("district/Fish Stocking List " + fileNumber + ".csv");
				Scanner fileReader = new Scanner(fishFile).useDelimiter("\r\n");
				while (fileReader.hasNextLine()) {
					fileLines.add(fileReader.nextLine());
				}
				fileReader.close();
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}

			// interpret file data
			convertData();
		}
	}

	// method to take the input and convert into the model
	public void convertData() {
		// get district name
		DistrictModel district = new DistrictModel(fileLines.get(0)); // make a district model
		fileLines.remove(0); // remove the district name
		// while there's data to be read in
		while (!fileLines.isEmpty()) {
			if (!fileLines.get(0).contains(",")) { // it's the start of a fish species
				FishSpeciesModel fishSpecies = new FishSpeciesModel(fileLines.get(0));
				fileLines.remove(1); // remove the subheader
				fileLines.remove(0); // already read the fish species, so removed that
				// while there's data to be read in
				while (!fileLines.isEmpty()) {
					if (!fileLines.get(0).contains(",")) // implies it's a new fish species
						break;
					else {
						// reach each line
						Scanner lineReader = new Scanner(fileLines.get(0)).useDelimiter(",|\r\n");
						while (lineReader.hasNext()) {
							String waterBody = lineReader.next();
							String township = lineReader.next();
							double latitude = lineReader.nextDouble();
							double longitude = lineReader.nextDouble();
							int[] yearPopulation = new int[5];
							String year1 = lineReader.next();
							if (!year1.isEmpty())
								yearPopulation[0] = Integer.parseInt(year1);
							String year2 = lineReader.next();
							if (!year2.isEmpty())
								yearPopulation[1] = Integer.parseInt(year2);
							String year3 = lineReader.next();
							if (!year3.isEmpty())
								yearPopulation[2] = Integer.parseInt(year3);
							String year4 = lineReader.next();
							if (!year4.isEmpty())
								yearPopulation[3] = Integer.parseInt(year4);
							if (lineReader.hasNext()) { // program will throw an error if there's nothing on the last
														// character
								String year5 = lineReader.next();
								if (!year5.isEmpty())
									yearPopulation[4] = Integer.parseInt(year5);
							}

							// add the distribution to the fish species
							fishSpecies.getDistribution()
									.add(new PopulationModel(waterBody, township, latitude, longitude, yearPopulation));

							// add the population models to a Hashmap containing all fishes in Lake Ontario
							if (!fishMap.containsKey(fishSpecies.getFishName())) {
								List<PopulationModel> population = new ArrayList<>();
								fishMap.put(fishSpecies.getFishName(), population);
								fishMap.get(fishSpecies.getFishName()).add(
										new PopulationModel(waterBody, township, latitude, longitude, yearPopulation));

							} else {
								fishMap.get(fishSpecies.getFishName()).add(
										new PopulationModel(waterBody, township, latitude, longitude, yearPopulation));

							}
						}

					}

					fileLines.remove(0); // already read the line so remove it
				}
				district.getFishSpecies().add(fishSpecies); // add fish species to the district
			}
		}

		districts.add(district); // add the district to our district
		district.toString();

	}

	// method to create an array of buttons with the district name
	public void createButtons() {

		// every district button
		for (DistrictModel district : districts) {
			JButton districtButton = new JButton(district.getDistrictName());
			districtButton.setPreferredSize(new Dimension(300, 100));
			districtButton.addActionListener(this);
			fishFrame.getDistrictButtons().add(districtButton);
			fishFrame.getButtonPanels().add(districtButton);
		}

		// ontario button
		JButton ontarioButton = new JButton("All of Ontario");
		ontarioButton.setPreferredSize(new Dimension(300, 100));
		ontarioButton.addActionListener(this);
		fishFrame.getDistrictButtons().add(ontarioButton);
		fishFrame.getButtonPanels().add(ontarioButton);

		// repaint and revalidate the panel holding the districts
		fishFrame.getButtonPanels().repaint();
		fishFrame.getButtonPanels().revalidate();
	}

	// method to create a pie chart
	public void createDistrictPieChart() {
		// 3. Within the distrct go through each fish species
		for (int index = 0; index < district.getFishSpecies().size(); index++) {
			double fishPopulation = 0; // fish population

			// 3.1. For each fish species, get the population size per year
			for (int j = 0; j < district.getFishSpecies().get(index).getDistribution().size(); j++) {
				fishPopulation += district.getFishSpecies().get(index).getDistribution().get(j).getPopulation()[year];
			}

			// 3.2. add to dataset
			dataset.setValue(district.getFishSpecies().get(index).getFishName(), fishPopulation);
		}

		// 4. Create a chart based on the given data set
		// if it is set to 3D, make a 3D pie chart
		if (fishFrame.getPieChartDimensionButton().isSelected()) {
			JFreeChart chart = ChartFactory.createPieChart3D(district.getDistrictName() + " in " + (year + 2021),
					dataset, true, true, false);
			fishFrame.getChartPanel().setChart(chart);
			fishFrame.getChartPanel().repaint();
		} else { // if it is set to 2D, make a regular pie chart
			JFreeChart chart = ChartFactory.createPieChart(district.getDistrictName() + " in " + (year + 2021), dataset,
					true, true, false);
			fishFrame.getChartPanel().setChart(chart);
			fishFrame.getChartPanel().repaint();
		}

		// set the current graph name to the graph the user wants to look at
		graphName = district.getDistrictName();
	}

	private void createOntarioPieChart() {

		// https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
		for (Entry<String, List<PopulationModel>> entry : fishMap.entrySet()) {
			String fishName = entry.getKey();
			long fishPopulation = 0;
			for (PopulationModel population : entry.getValue()) {
				fishPopulation += population.getPopulation()[year];
			}

			// 3.2. add to dataset
			dataset.setValue(fishName, fishPopulation);
		}

		// 4. Create a chart based on the given data set
		// if it is set to 3D, make a 3D pie chart
		if (fishFrame.getPieChartDimensionButton().isSelected()) {
			JFreeChart chart = ChartFactory.createPieChart3D("All of Ontario in " + (year + 2021), dataset, true, true,
					false);
			fishFrame.getChartPanel().setChart(chart);
			fishFrame.getChartPanel().repaint();
		} else { // if it is set to 2D, make a regular pie chart
			JFreeChart chart = ChartFactory.createPieChart("All of Ontario in " + (year + 2021), dataset, true, true,
					false);
			fishFrame.getChartPanel().setChart(chart);
			fishFrame.getChartPanel().repaint();
		}

		// set the graph name to the graph the user wants to look at
		graphName = "All of Ontario";

	}

	// action listener for district buttons
	@Override
	public void actionPerformed(ActionEvent event) {
		// 1. Create dataset
		// this will clear the previous dataset
		dataset = new DefaultPieDataset();

		// 2. Determine the district the button was selected for
		int districtArrayIndex = 0;
		for (int index = 0; index < fishFrame.getDistrictButtons().size(); index++) {
			if (event.getSource() == fishFrame.getDistrictButtons().get(index)) {
				districtArrayIndex = index;
				if (index == districts.size()) {
					break;
				} else {
					district = districts.get(index);
					break;
				}

			}
		}

		// create the pie chart based on the button clicked
		if (districtArrayIndex != districts.size())
			createDistrictPieChart();
		else {
			createOntarioPieChart();
		}

		// enable the buttons and sliders if this was the first time
		if (!fishFrame.getYearSlider().isEnabled())
			fishFrame.getYearSlider().setEnabled(true);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if (event.getSource() == fishFrame.getYearSlider())
			year = fishFrame.getYearSlider().getValue() - 2021;
		if (graphName.equals("All of Ontario"))
			createOntarioPieChart();
		else if (district != null) {
			createDistrictPieChart();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		// https://www.geeksforgeeks.org/java-swing-jtogglebutton-class/
		// the following code toggles the orientation of the pie chart (2D or 3D)
		if (event.getSource() == fishFrame.getPieChartDimensionButton()) {

			if (event.getStateChange() == event.SELECTED) { // if the button is selected
				fishFrame.getPieChartDimensionButton().setText("3D");
				JFreeChart chart = ChartFactory.createPieChart3D(graphName + " in " + (year + 2021), dataset, true,
						true, false);

				fishFrame.getChartPanel().setChart(chart);
				fishFrame.getChartPanel().repaint();

			} else { // if the button is deselected
				fishFrame.getPieChartDimensionButton().setText("2D");
				JFreeChart chart = ChartFactory.createPieChart(graphName + " in " + (year + 2021), dataset, true, true,
						false);

				fishFrame.getChartPanel().setChart(chart);
				fishFrame.getChartPanel().repaint();

			}

		}

	}
}
