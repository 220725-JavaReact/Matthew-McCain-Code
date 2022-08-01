package model;

import java.util.*;

public class Order {
	
	private ArrayList<LineItem> items;

	public void addProduct(LineItem item) {
		items.add(item);
	}

	public ArrayList<LineItem> getItems() {
		return items;
	}

	public double getTotal() {
		double total = 0;
		for (LineItem item : items) {
			total += item.total();
		}
		return total;
	}

}
