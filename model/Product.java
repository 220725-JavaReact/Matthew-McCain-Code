/**
 * 
 */
package model;

/**
 * @author Matthew McCain
 * Holds data about the products
 */
public class Product {
	private String name;
	private Double price;
	private String description;
	//CATEGORY category;

	public Product(String name, Double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
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
