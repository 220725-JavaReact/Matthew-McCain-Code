package services;

import dao.DAO;
import dao.StoreDAO;
import dao.storage.StoreData;
import model.*;
import services.util.Logger;

import java.util.Scanner;

import static services.util.Logger.LogLevel.*;

public class Client {

DAO dao = new StoreDAO();

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
        while (response == -1) {
            for (int i = 0; i < StoreData.storeFronts.size(); i++) {
                System.out.println("[" + i + 1 + "] " + StoreData.storeFronts.get(i).getName());
            }
            System.out.println("Which Store Front would you like to choose?");
            response = in.nextInt();
            in.nextLine();
            if (response > 0 && response <= StoreData.storeFronts.size()) {
                store = StoreData.storeFronts.get(response - 1);
                Logger.getLogger().log(info, store.getName() + " was found.");
            } else {
                response = -1;
            }
        }
        return store;
    }
    public Product captureProduct (Scanner in){
        System.out.println("Product Name:");
        String name = in.nextLine();
        System.out.println("Product Price");
        double price = in.nextDouble();
        in.nextLine();
        System.out.println("Product Description:");
        String description = in.nextLine();
        Product product = new Product(name, price, description);
        Logger.getLogger().log(info, "Product: " + product.getName() + " was added.");
        return product;
    }

    public Order createOrder (Scanner in, DAO dao){
        Order order = new Order();
        String input;
        do {
            addLineItem(in, order, dao);
            System.out.println("Add another item [y/n]:");
            input = in.nextLine();
        } while (input.toLowerCase().equals("y"));
        Logger.getLogger().log(info, "Placed Order:\n" + order);
        return order;
    }
    public void addLineItem (Scanner in, Order order, DAO dao){
        try {
            System.out.println("Name of Product to add to order:");
            Product product = dao.findProduct(in.nextLine());
            System.out.println("Quantity of " + product.getName() + " to add to order:");
            int quantity = in.nextInt();
            in.nextLine();
            LineItem item = new LineItem(product, quantity);
            order.addProduct(item);
        } catch (Exception e) {
            Logger.getLogger().log(error, e);
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
}