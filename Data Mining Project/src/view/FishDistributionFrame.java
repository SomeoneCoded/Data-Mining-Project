package view;
/* Name: Dailiang Chen (Group #1)
 * Date: May 22, 2025
 * Class Code: ICS4U1-03
 * Teacher: Mr. Fernandes
 * Description: This class is the screen class that will show the data of fishes in certain fishing districts of Ontario
 * Major Skills: Object Oriented Programming, API, Model-View-Controller
 * Areas of Concern: Features are relatively simple (2D/3D button, and a slider to change the year)
 */

// import statements
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jfree.chart.ChartPanel;

import controller.*;

// this class is the screen that displays the fish species distribution in Ontario
public class FishDistributionFrame extends JFrame implements ActionListener{

	// header
	private JLabel headerName = new JLabel("Fish Species Distribution Per Ontario Fishing District");

	// main frame panel
	private JLayeredPane mainPane = new JLayeredPane();
	// background panel
	private JLabel backgroundImage = new JLabel(new ImageIcon("images/Lake Ontario.jpeg"));

	// jfreechart panel
	private ChartPanel chartPanel = new ChartPanel(null);

	// 2D or 3D buttons
	private JToggleButton pieChartDimensionButton = new JToggleButton("2D");

	// scrollpane for district buttons
	private JPanel buttonPanels = new JPanel();
	private JScrollPane districtScrollPane = new JScrollPane(buttonPanels);

	// district buttons
	private ArrayList<JButton> districtButtons = new ArrayList<>();

	// timeline slider
	private JSlider yearSlider = new JSlider(2021, 2025, 2024);

	// menubar
	private JMenuBar generalMenuBar = new JMenuBar();
	// menus
	private JMenu windowMenu = new JMenu("Window");
	private JMenu huntingMenu = new JMenu("Hunting");
	private JMenu fishingMenu = new JMenu("Fishing");
	// window menu items
	private JMenuItem home = new JMenuItem("Home");
	private JMenuItem quit = new JMenuItem("Quit");
	// hunting menu items
	private JMenuItem animalHarvest = new JMenuItem("Harvests");
	private JMenuItem activeHunters = new JMenuItem("Active Hunters");
	// fishing menu items
	private JMenuItem fishDistribution = new JMenuItem("Fish Distribution");
	private JMenuItem fishEconomicValue = new JMenuItem("Economic Values");

	// constructor method
	public FishDistributionFrame() {
		// set up the frame
		setSize(1920, 1080);
		setLayout(null);
		setTitle("Minstry of Environment and Natural Resources");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// set up the menu bar
		// add action listeners to each menu item
		home.addActionListener(this);
		quit.addActionListener(this);
		animalHarvest.addActionListener(this);
		activeHunters.addActionListener(this);
		fishDistribution.addActionListener(this);
		fishEconomicValue.addActionListener(this);
		// add items to window section
		windowMenu.add(home);
		windowMenu.add(quit);
		// add items to hunting sections
		huntingMenu.add(animalHarvest);
		huntingMenu.add(activeHunters);
		// add items to fishing section

		fishingMenu.add(fishDistribution);
		fishingMenu.add(fishEconomicValue);
		// add menus to menubar
		generalMenuBar.add(windowMenu);
		generalMenuBar.add(huntingMenu);
		generalMenuBar.add(fishingMenu);
		// set the frame's menubar to
		setJMenuBar(generalMenuBar);

		// setup background image
		backgroundImage.setBounds(0, 0, 1920, 1080);
		mainPane.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);

		// set header Name
		headerName.setFont(new Font("Arial", Font.BOLD, 40));
		headerName.setForeground(Color.WHITE);
		headerName.setBounds(100, 60, 1100, 80);
		mainPane.add(headerName, JLayeredPane.PALETTE_LAYER);

		// set scroll pane
		buttonPanels.setLayout(new BoxLayout(buttonPanels, BoxLayout.Y_AXIS));
		districtScrollPane.setBounds(900, 200, 300, 400);
		districtScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		districtScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		mainPane.add(districtScrollPane, JLayeredPane.PALETTE_LAYER);

		// set time slider
		yearSlider.setBounds(900, 600, 300, 100);
		yearSlider.setPaintLabels(true);
		yearSlider.setPaintTicks(true);
		yearSlider.setPaintTrack(true);
		yearSlider.setMajorTickSpacing(4);
		yearSlider.setMinorTickSpacing(1);
		yearSlider.setOpaque(false);
		yearSlider.setForeground(Color.WHITE);
		mainPane.add(yearSlider, JLayeredPane.PALETTE_LAYER);

		// set up chart panel
		chartPanel.setBounds(100, 200, 600, 400);
		mainPane.add(chartPanel, JLayeredPane.PALETTE_LAYER);
		// set up the 2D / 3D togglebutton
		pieChartDimensionButton.setBounds(100, 600, 600, 50);
		mainPane.add(pieChartDimensionButton, JLayeredPane.PALETTE_LAYER);

		// setup the main panel
		mainPane.setBounds(0, 0, 1920, 1080);
		add(mainPane);

		setVisible(true);

	}

	// accessors and mutators
	public JLabel getHeaderName() {
		return headerName;
	}

	public void setHeaderName(JLabel headerName) {
		this.headerName = headerName;
	}

	public JLayeredPane getMainPane() {
		return mainPane;
	}

	public void setMainPane(JLayeredPane mainPane) {
		this.mainPane = mainPane;
	}

	public JLabel getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(JLabel backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public JPanel getButtonPanels() {
		return buttonPanels;
	}

	public void setButtonPanels(JPanel buttonPanels) {
		this.buttonPanels = buttonPanels;
	}

	public JScrollPane getDistrictScrollPane() {
		return districtScrollPane;
	}

	public void setDistrictScrollPane(JScrollPane districtScrollPane) {
		this.districtScrollPane = districtScrollPane;
	}

	public ArrayList<JButton> getDistrictButtons() {
		return districtButtons;
	}

	public void setDistrictButtons(ArrayList<JButton> districtButtons) {
		this.districtButtons = districtButtons;
	}

	public JSlider getYearSlider() {
		return yearSlider;
	}

	public void setYearSlider(JSlider yearSlider) {
		this.yearSlider = yearSlider;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

	public JToggleButton getPieChartDimensionButton() {
		return pieChartDimensionButton;
	}

	public void setPieChartDimensionButton(JToggleButton pieChartDimensionButton) {
		this.pieChartDimensionButton = pieChartDimensionButton;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// detects which screen the user wants to go to next
		if(event.getSource() == quit) 
			dispose();
		else {
			// setVisible(false);
			if(event.getSource() == home) {
				
			} else if(event.getSource() == animalHarvest) {
				
			} else if(event.getSource() == activeHunters) {
				
			} else if(event.getSource() == fishDistribution) {
				new MainController();
			} else if(event.getSource() == fishEconomicValue) {
				
			}
			
		}
			
		
	}

}
