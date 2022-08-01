package controller;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StoreDAO {

    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    private ArrayList<StoreFront> storeFronts;

    public StoreDAO() {
        this.customers = new ArrayList<>();
        this.products = new ArrayList<>();
        this.storeFronts = new ArrayList<>();
    }

    public boolean addCustomer(Customer customer) {
        return this.customers.add(customer);
    }

    public Customer findCustomer(String name) throws Exception {
        for (Customer customer : customers) {
            if (customer.getName().matches(name)) {
                return customer;
            }
        }
        throw new Exception("Customer not found.");
    }

}
