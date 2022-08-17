package services;

import dao.DAO;
import dao.PostgresqlDAO;
import model.*;
import services.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static services.util.Logger.LogLevel.*;

public class Client {

DAO dao = new PostgresqlDAO();

    public void addStore(Scanner in) {
        System.out.println("Store Name:");
        String name = in.nextLine();
        Address address = captureAddress(in);
        String phone = capturePhone(in);
        StoreFront store = new StoreFront(name, address, phone);
        dao.addStoreFront(store);
        Logger.getLogger().log(info, "Store '" + store.getName() + "' was added");
    }

    public StoreFront getStore(Scanner in) {
        StoreFront store = new StoreFront();
        int response = -1;
        ArrayList<StoreFront> storeFronts = dao.getStores();
        while (response == -1) {
            for (int i = 0; i < storeFronts.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + storeFronts.get(i).getName());
            }
            System.out.println("Which Store Front would you like to choose?");
            response = in.nextInt();
            in.nextLine();
            if (response > 0 && response <= storeFronts.size()) {
                store = storeFronts.get(response - 1);
                Logger.getLogger().log(info, store.getName() + " was found.");
            } else {
                response = -1;
            }
        }
        return store;
    }

    public void placeOrder(Scanner in, int storeID) {
        // create order id w/ store_id and customer_id
        System.out.println("Enter a name to search for:");
        String searchString = in.nextLine();
        boolean customerFound = false;
        Customer customer = null;
        while (!customerFound) {
            try {
                customer = dao.findCustomer(searchString);
                customerFound = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        int customerID = dao.getCustomerID(customer);
        int orderID = dao.createOrder(storeID, customerID);
        // create line_item w/ order_id, product_id, & quantity
        ArrayList<Product> order = new ArrayList<>();
        boolean finished = false;
        while (!finished) {
            CATEGORIES category = getCategory(in);
            ArrayList<Product> products = dao.getProducts(category);
            System.out.println("Choose a product:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + products.get(i).getName());
            }
            int choice = in.nextInt();
            in.nextLine();
            order.add(products.get(choice - 1));
            System.out.println("How many " + products.get(choice - 1).getName() + " would you like to purchase?");
            int quantity = in.nextInt();
            in.nextLine();
            dao.addLineItem(orderID, products.get(choice - 1), quantity);
            System.out.println("Would you like to add another product? [y/n]");
            String shouldContinue = in.nextLine();
            if (shouldContinue.equalsIgnoreCase("n")) {
                finished = true;
            }
        }
        // update order_id w/ total
        double total = 0;
        for (Product product : order) {
            total += product.getPrice();
        }
        dao.updateOrder(orderID, total);
    }

    public ArrayList<StoreFront> getStores() {
        return dao.getStores();
    }

    public Product captureProduct (Scanner in){
        System.out.println("Product Name:");
        String name = in.nextLine();
        CATEGORIES category = getCategory(in);
        System.out.println("");
        System.out.println("Product Price");
        double price = in.nextDouble();
        in.nextLine();
        System.out.println("Product Description:");
        String description = in.nextLine();
        Product product = new Product(name, category, price, description);
        Logger.getLogger().log(info, "Product: " + product.getName() + " was added.");
        return product;
    }

    private CATEGORIES getCategory(Scanner in) {
        while (true) {
            System.out.println("Choose a category:\n" +
                    "[1] Automotive\n" +
                    "[2] Food\n" +
                    "[3] Cleaner\n" +
                    "[4] Hardware\n" +
                    "[5] Office\n" +
                    "[6] personal\n" +
                    "[7] deli");
            String response = in.nextLine();
            switch (response.toLowerCase()) {
                case "1" :
                    return CATEGORIES.automotive;
                case "2" :
                    return CATEGORIES.food;
                case "3" :
                    return CATEGORIES.cleaner;
                case "4" :
                    return CATEGORIES.hardware;
                case "5" :
                    return CATEGORIES.office;
                case "6" :
                    return CATEGORIES.personal;
                case "7" :
                    return CATEGORIES.deli;
                default:
                    System.out.println("Not a valid option");
            }
        }
    }

    public static CATEGORIES getCategory(String category) {
        switch (category) {
            case "automotive" :
                return CATEGORIES.automotive;
            case "food" :
                return CATEGORIES.food;
            case "cleaner" :
                return CATEGORIES.cleaner;
            case "hardware" :
                return CATEGORIES.hardware;
            case "office" :
                return CATEGORIES.office;
            case "personal" :
                return CATEGORIES.personal;
            case "deli" :
                return CATEGORIES.deli;
            default:
                return null;
        }
    }

    public void replenish(Scanner in, StoreFront store) {
        CATEGORIES category = getCategory(in);
        ArrayList<Product> products = dao.getProducts(category);
        System.out.println("Choose a Product:");
        for (int i = 0; i < products.size() ; i++){
            System.out.println("[" + (i + 1) + "] " + products.get(i).getName());
        }
        int choice = in.nextInt();
        in.nextLine();
        Product product = products.get(choice - 1);
        System.out.println("Enter quantity adjustment:");
        int quantity = in.nextInt();
        in.nextLine();
        dao.adjustInventory(store, product, quantity);
    }

    public void displayInventory(StoreFront store) {
        ArrayList<ArrayList<String>> inventory = dao.getInventory(store);
        System.out.printf("%-20s%-20s%s%n", "[Category]", "[Product Name]", "[Quantity]");
        for (ArrayList<String> row : inventory) {
            System.out.printf("%-20s%-20s%s%n", row.get(0), row.get(1), row.get(2));
        }
    }

    public Customer captureCustomer (Scanner in){
        System.out.println("Full Name: ");
        String name = in.nextLine();
        Address address = captureAddress(in);
        String email = captureEmail(in);
        String phone = capturePhone(in);
        Customer customer = new Customer(name, address, email, phone);
        Logger.getLogger().log(info, customer.getName() + " was added.");
        return customer;
    }

    public Address captureAddress (Scanner in){
        System.out.println("Building Number: ");
        int streetNumber = in.nextInt();
        in.nextLine();
        System.out.println("Street Name: ");
        String streetName = in.nextLine();
        System.out.println("City: ");
        String city = in.nextLine();
        System.out.println("State [Abbreviation]: ");
        String state = in.nextLine();
//		Class stateClass = STATE.class;
//		boolean stateSet = false;
//		while (!stateSet) {
//			System.out.println("State [Abbreviation]: ");
//			String inState = in.nextLine();
//			for (STATE st : (STATE[]) stateClass.getEnumConstants()) {
//				if (st.equals(inState.substring(0,1).toUpperCase())) {
//					state = st;
//				}
//			}
//		}
//		in.nextLine();
        System.out.println("Zip code: ");
        int zip = in.nextInt();
        in.nextLine();
        return new Address(streetNumber, streetName, city, state, zip);
    }

    public String captureEmail (Scanner in){
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        boolean emailSet = false;
        String email = "";
        while (!emailSet) {
            System.out.println("Email address: ");
            email = in.nextLine();
            if (email.matches(pattern)) {
                emailSet = true;
            }
            if (!emailSet) {
                System.out.println("INVALID EMAIL");
            }
        }
        return email;
    }

    public String capturePhone (Scanner in){
//        String pattern = "^/d(?:-/d{3}){3}/d$";
//        boolean phoneSet = false;
//        String phone = "";
//        while(!phoneSet) {
//            System.out.println("Enter phone number:");
//            phone = in.nextLine();
//            if (phone.matches(pattern)) {
//                phoneSet = true;
//            }
//        }
        System.out.println("Enter phone number:");
        return in.nextLine();
    }

    public void displayOrders(int storeID) {
        ArrayList<ArrayList<String>> orderList = dao.getOrders();
        System.out.printf("%-15s%s%n", "[Customer]", "[Total]");
        for (ArrayList<String> row : orderList) {
            System.out.printf("%-15s%-5s%n");
        }
    }
}