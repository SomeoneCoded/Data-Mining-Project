package model;
/* Name: Dailiang Chen (Group #1)
 * Date: May 22, 2025
 * Class Code: ICS4U1-03
 * Teacher: Mr. Fernandes
 * Description: This class models each fishing district and the fish species that live within it
 * Major Skills: Object Oriented Programming, Model-View-Controller
 * Areas of Concern: Due to the nature of how each district data is obtained, some fishing districts will only have one fish species and some will have 10+ species
 */

// import statement
import java.util.ArrayList;

// this class is the model class that models each fishing district
public class DistrictModel {

	// attributes
	private String districtName;
	private ArrayList<FishSpeciesModel> fishSpecies = new ArrayList<FishSpeciesModel>();

	// constructor class
	public DistrictModel(String districtName) {
		this.districtName = districtName;
	}

	// setters and getters
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String name) {
		this.districtName = name;
	}

	public ArrayList<FishSpeciesModel> getFishSpecies() {
		return fishSpecies;
	}

	public void setFishSpecies(ArrayList<FishSpeciesModel> fishSpecies) {
		this.fishSpecies = fishSpecies;
	}

	// to string method
	@Override
	public String toString() {
		return "DistrictModel [districtName=" + districtName + ", fishSpecies=" + fishSpecies + "]";
	}

}
