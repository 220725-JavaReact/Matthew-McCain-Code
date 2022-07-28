/**
 * 
 */
package model;

import java.util.*;

/**
 * @author Matthew McCain
 *
 */
public class Order {
	
	private ArrayList<LineItem> items;

	/**
	 * @param items
	 */
	public Order(ArrayList<LineItem> items) {
		this.items = items;
	}

	/**
	 * @return
	 */
	public ArrayList<LineItem> getItems() {
		return items;
	}

	/**
	 * @param items
	 */
	public void setItems(ArrayList<LineItem> items) {
		this.items = items;
	}

}
