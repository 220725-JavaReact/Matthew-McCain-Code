package dao;

import dao.storage.StoreData;
import services.exceptions.CustomerNotFoundException;
import services.exceptions.ProductNotFoundException;
import model.*;

import java.util.ArrayList;

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

    public void addStoreFront(StoreFront store) {
        StoreData.storeFronts.add(store);
    }

    @Override
    public ArrayList<StoreFront> getStores() {
        return new ArrayList<StoreFront>();
    }
}
