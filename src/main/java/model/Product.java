/**
 * 
 */
package model;

import jdk.jfr.Category;

/**
 * @author Matthew McCain
 * Holds data about the products
 */
public class Product {
	private String name;
	private CATEGORIES category;
	private Double price;
	private String description;
	//CATEGORY category;

	public Product(String name, CATEGORIES category, Double price, String description) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public CATEGORIES getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				'}';
	}
}
