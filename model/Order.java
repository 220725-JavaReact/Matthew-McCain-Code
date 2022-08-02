package model;

import java.util.*;

public class Order {
	
	private ArrayList<LineItem> items;

	public Order() {
		items = new ArrayList<>();
	}

	public void addProduct(LineItem item) {
		System.out.println("Adding " + item + " to order.");
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

	@Override
	public String toString() {
		return "Order{" +
				"items=" + items +
				'}';
	}
}
