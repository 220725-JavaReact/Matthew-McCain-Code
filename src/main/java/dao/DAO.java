package dao;

import model.CATEGORIES;
import model.Customer;
import model.Product;
import model.StoreFront;

import java.util.ArrayList;
import java.util.HashMap;

public interface DAO {
    void addCustomer(Customer customer);
    Customer findCustomer(String name) throws Exception;
    void addProduct(Product product);
    Product findProduct(String name) throws Exception;
    void addStoreFront(StoreFront store);
    ArrayList<StoreFront> getStores();
    ArrayList<Product> getProducts(CATEGORIES category);
    void adjustInventory(StoreFront store, Product product, int adjustment);

    ArrayList<ArrayList<String>> getInventory(StoreFront store);

    int getStoreID(StoreFront store);

    int getCustomerID(Customer customer);

    int createOrder(int storeID, int customerID);

    void addLineItem(int orderID, Product product, int quantity);

    void updateOrder(int orderID, double adjustment);

    ArrayList<ArrayList<String>> getOrders();
}
