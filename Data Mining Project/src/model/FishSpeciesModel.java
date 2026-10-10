package model;
/* Name: Dailiang Chen (Group #1)
 * Date: May 22, 2025
 * Class Code: ICS4U1-03
 * Teacher: Mr. Fernandes
 * Description: This class is the screen class that will model the fish species and its distribution within a fishing district
 * Major Skills: Object Oriented Programming, Model-View-Controller
 * Areas of Concern: None
 */

// import statement
import java.util.ArrayList;

// this class models each fish species and their population distribution within a fishing district
public class FishSpeciesModel {
	
	// attributes
	private String fishName;
	private ArrayList<PopulationModel> distribution = new ArrayList<PopulationModel>();

	// constructor method
	public FishSpeciesModel(String fishName) {
		this.fishName = fishName;
	}

	// setters and getters
	public String getFishName() {
		return fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public ArrayList<PopulationModel> getDistribution() {
		return distribution;
	}

	public void setDistribution(ArrayList<PopulationModel> distribution) {
		this.distribution = distribution;
	}

	// to string method
	@Override
	public String toString() {
		return "FishSpeciesModel [fishName=" + fishName + ", distribution=" + distribution + "]";
	}

}
