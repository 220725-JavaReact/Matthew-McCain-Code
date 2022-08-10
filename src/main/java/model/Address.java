/**
 * 
 */
package model;

/**
 * @author Matthew McCain
 * Holds address information
 */
public class Address {
	
	private int buildingNumber;
	private String street;
	private String city;
//	private STATE state;
	private String state;
	private int zip;

	public Address(int buildingNumber, String streetName, String city, String state, int zip) {
		this.buildingNumber = buildingNumber;
		this.street = streetName;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public int getBuildingNumber() {
		return buildingNumber;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZip() {
		return zip;
	}

	@Override
	public String toString() {
		return String.format("%d %s%n%s%n%s%n%d", buildingNumber, street, city, state, zip);
	}

}
