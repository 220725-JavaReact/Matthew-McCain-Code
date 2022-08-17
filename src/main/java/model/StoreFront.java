/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;


public class StoreFront {

	private String name;
	private Address address;
	private String phone;
	private HashMap<Product, Integer> inventory;
	private ArrayList<Order> orders;

	public StoreFront() {

	}
	public StoreFront(String name, Address address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public HashMap<Product, Integer> getInventory() {
		return inventory;
	}
	public void addInventory(Product product, int quantity) {

	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
}
