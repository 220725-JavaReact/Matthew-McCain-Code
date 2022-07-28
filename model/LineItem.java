/**
 * 
 */
package model;

/**
 * @author Matthew McCain
 * Information about a particular product and its quantity
 */
public class LineItem {

	private Product product;
	private int quantity;
	
	/**
	 * @param product
	 * @param quantity
	 */
	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * @return int
	 * returns the quantity of product
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 * set the quantity of product on the LineItem
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return Product
	 * returns the product of the LineItem
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * @param product
	 * set the product of the LineItem
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
}
