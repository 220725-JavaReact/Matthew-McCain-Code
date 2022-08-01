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
//	private STATE state;
	private String state;
	private int zip;

	public Address(int streetNumber, String streetName, String city, String state, int zip) {
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

}
