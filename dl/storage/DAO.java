package dl.storage;

import model.Customer;
import model.Product;

public interface DAO {
    public void addCustomer(Customer customer);
    public Customer findCustomer(String name) throws Exception;
    public void addProduct(Product product);
    public Product findProduct(String name) throws Exception;
}
