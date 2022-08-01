package model;

public class Customer {
	private String name;
	private Address address;
	private String email;
	private String phone;

	public Customer(String name, Address address, String email, String phone) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

}
