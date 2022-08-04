package ui;

import services.Client;
import dao.DAO;
import dao.StoreDAO;
import dao.storage.StoreData;
import model.*;
import services.util.Logger;

import java.util.Scanner;

import static services.util.Logger.LogLevel.*;

public class StoreMenu {

    StoreFront store;
    Client client;
    Scanner in;

    public StoreMenu(StoreFront store, Client client, Scanner in) {
        this.store = store;
        this.client = client;
        this.in = in;
    }

    public void run() {

        String userInput = "";
        DAO dao = new StoreDAO();

        // Interactive menu
        do {
            switch (userInput) {
                case "h":
                    displayOptions();
                    break;
                case "1":
                    // Add a customer
                    dao.addCustomer(client.captureCustomer(in));
                    break;
                case "2":
                    // Search for a customer
                    System.out.println("Enter a name to search for:");
                    String searchString = in.nextLine();
                    try {
                        Customer customer = dao.findCustomer(searchString);
                        Logger.getLogger().log(info, "Customer: " + customer.getName() + " was found");
                        System.out.println("Found customer: " + customer.getName());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    // add a product to inventory
                    dao.addProduct(client.captureProduct(in));
                    break;
                case "4":
                    // View store inventory
                    for(Product product : StoreData.products) {
                        System.out.println(product);
                    }
                    break;
                case "5":
                    // Place an order
                    StoreData.orders.add(client.createOrder(in, dao));
                    break;
                case "6":
                    // View order history
                    for(Order order : StoreData.orders) {
                        System.out.println(order);
                    }
                    break;
                case "7":
                    // Replenish inventory

                    break;
                case "x":
                    System.out.println("Going back to main menu.");
                    break;
                default:
                    System.out.println("Store Commands:");
                    displayOptions();
            }
            System.out.println("Enter next command: ");
            userInput = in.nextLine();
        } while (!userInput.equals("x"));
        in.close();
    }


    void displayOptions() {
        System.out.println("[h] See this menu again\n" +
                "[1] Add a customer\n" +
                "[2] Search for a customer\n" +
                "[3] Add a product\n" +
                "[4] View store inventory\n" +
                "[5] Place an order\n" +
                "[6] View order history\n" +
                "[7] Replenish inventory\n" +
                "[x] Exit");
    }


}
