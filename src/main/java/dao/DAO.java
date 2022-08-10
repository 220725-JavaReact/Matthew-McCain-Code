package dao;

import model.Customer;
import model.Product;
import model.StoreFront;

import java.util.ArrayList;

public interface DAO {
    void addCustomer(Customer customer);
    Customer findCustomer(String name) throws Exception;
    void addProduct(Product product);
    Product findProduct(String name) throws Exception;
    void addStoreFront(StoreFront store);
    ArrayList<StoreFront> getStores();
}
