package dl;

import dl.storage.StoreData;
import exceptions.CustomerNotFoundException;
import exceptions.ProductNotFoundException;
import model.*;

public class StoreDAO implements DAO {

    public void addCustomer(Customer customer) {
        StoreData.customers.add(customer);
    }

    public Customer findCustomer(String name) throws Exception {
        for (Customer customer : StoreData.customers) {
            if (customer.getName().matches(name)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer not found.");
    }

    public void addProduct(Product product) {
        StoreData.products.add(product);
    }

    public Product findProduct(String name) throws Exception {
        for (Product product : StoreData.products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product not found!");
    }

}
