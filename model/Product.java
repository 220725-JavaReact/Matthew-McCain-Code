/**
 * 
 */
package model;

/**
 * @author Matthew McCain
 * Holds data about the products
 */
public class Product {
	String name;
	Double price;
	String Description;
	CATEGORY category;
	
	/**
	 * @param name
	 * @param price
	 * @param description
	 * @param category
	 */
	public Product(String name, Double price, String description, CATEGORY category) {
		this.name = name;
		this.price = price;
		Description = description;
		this.category = category;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return
	 */
	public CATEGORY getCategory() {
		return category;
	}

	/**
	 * @param category
	 */
	public void setCategory(CATEGORY category) {
		this.category = category;
	}
	
}
