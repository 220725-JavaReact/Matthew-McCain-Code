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

	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public double total() {
		return product.getPrice() * quantity;
	}
	@Override
	public String toString() {
		return "LineItem{" +
				"product=" + product +
				", quantity=" + quantity +
				'}';
	}
}