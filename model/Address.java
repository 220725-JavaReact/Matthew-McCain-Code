/**
 * 
 */
package model;

/**
 * @author Matthew McCain
 * Holds address information
 */
public class Address {
	
	private int streetNumber;
	private String streetName;
	private String city;
	private STATE state;
	private int zip;
	
	/**
	 * @param streetNumber
	 * @param streetName
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Address(int streetNumber, String streetName, String city, STATE state, int zip) {
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s%n%s%n%s%n%d", streetNumber, streetName, city, state, zip);
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
}
