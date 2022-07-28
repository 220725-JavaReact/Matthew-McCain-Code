/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Matthew McCain
 * Information pertaining to various store locations.
 */
public class StoreFront {
	private String name;
	private Address address;
	private HashMap<Product, Integer> inventory;
	private ArrayList<Order> orders;
	
	/**
	 * @param name
	 * @param address
	 * @param inventory
	 * @param orders
	 */
	public StoreFront(String name, Address address, HashMap<Product, Integer> inventory, ArrayList<Order> orders) {
		this.name = name;
		this.address = address;
		this.inventory = inventory;
		this.orders = orders;
	}

	/**
	 * @return String
	 * returns the name of the StoreFront
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name String
	 * sets the name of the StoreFront
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Address
	 * returns the address of the StoreFront
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address Address
	 * sets the address of the StoreFront
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return HashMap<Product, Integer>
	 * returns an hash map of the products available 
	 */
	public HashMap<Product, Integer> getInventory() {
		return inventory;
	}

	/**
	 * @param products HashMap<Product, Integer>
	 * set the products and their quantity
	 */
	public void setInventory(HashMap<Product, Integer> products) {
		this.inventory = products;
	}
	
	public void changeProductQuantity(Product product, int quantity) {
		Integer quant = inventory.get(product);
		quant += quantity;
		this.inventory.replace(product, quant);
	}

	/**
	 * @return ArrayList<Order>
	 * returns an array list of the StoreFront's orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders ArrayList<Order>
	 * sets the orders of the StoreFront
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order newOrder) {
		this.orders.add(newOrder);
	}
}
