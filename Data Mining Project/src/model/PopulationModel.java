package model;
/* Name: Dailiang Chen (Group #1)
 * Date: May 22, 2025
 * Class Code: ICS4U1-03
 * Teacher: Mr. Fernandes
 * Description: This class is the model class that models a fish population within a body of water within a fishing district from 2021-2025
 * Major Skills: Object Oriented Programming, Model-View-Controller
 * Areas of Concern: Not all years will have population data. Value set to zero if there's no data for that year.
 */

// import statement
import java.util.Arrays;

// this class models the population of a fish species within a body of water in a fishing district
public class PopulationModel {

	// attributes
	private String waterBody;
	private String township;
	private double latitude, longitude;
	private int[] population = new int[5];

	// constructor method
	public PopulationModel(String waterBody, String township, double latitude, double longitude, int[] population) {
		super();
		this.waterBody = waterBody;
		this.township = township;
		this.latitude = latitude;
		this.longitude = longitude;
		this.population = population;
	}

	// setters and getters
	public String getWaterBody() {
		return waterBody;
	}

	public void setWaterBody(String waterBody) {
		this.waterBody = waterBody;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int[] getPopulation() {
		return population;
	}

	public void setPopulation(int[] population) {
		this.population = population;
	}

	// to string method
	@Override
	public String toString() {
		return "PopulationModel [waterBody=" + waterBody + ", township=" + township + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", population=" + Arrays.toString(population) + "]";
	}

}
