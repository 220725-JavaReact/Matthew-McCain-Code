package dao.storage;

import model.Customer;
import model.Order;
import model.Product;
import model.StoreFront;

import java.util.ArrayList;

public class StoreData {
    public static ArrayList<StoreFront> storeFronts = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
}
